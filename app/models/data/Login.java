package models.data;

import com.avaje.ebean.*;
import com.avaje.ebean.Query;
import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.validation.NotNull;
import models.BaseData;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/13
 * Time: 10:08
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Login extends Model implements BaseData {

    @Id
    public long id;

    @Constraints.Required
    public String uuid;

    @NotNull
    public String gppUUID;

    @NotNull
    public String displayName;

    @NotNull
    public String mailAddress;

    @NotNull
    public String imageUrl;

    @NotNull
    public long accessCnt;

    @CreatedTimestamp
    public Date createDate;

    @Version
    public Date updateDate;

    @OneToOne(cascade = CascadeType.REMOVE,mappedBy = "login")
    public GameCharacter gameCharacter;

    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "login")
    public List<Friend> friends = new ArrayList<Friend>();

    // access method
    public static Finder<Long,Login>find = new Finder<Long,Login>
    (
        Long.class,Login.class
    );
    public void init()
    {
        gppUUID = "";
        displayName = "";
        mailAddress = "";
        imageUrl = "";
        accessCnt = 1;

    }
    public static Login loadFromCache(ObjectNode node)
    {
        Login login = new Login();
        login.loadFromJson(node);
        return login;
    }
    // 新規　ローカル
    public static Login createNewLogin(JsonNode node)
    {
        Login login = new Login();
        login.uuid = UUID.randomUUID().toString();
        String gpp = JsonUtil.getString(node,JsonKeyString.GPP_UUID,"");
        if(gpp.length() > 0){
            login.loadFromJson(node);
        }
        else{
            login.init();
        }
        return login;
    }

    // GPP紐付け
    public static Login addGPPInfo(JsonNode node)
    {
        Login login = localLogin(node);
        if(login != null){
            String gpp = JsonUtil.getString(node,JsonKeyString.GPP_UUID,"");
            if(login.gppUUID.length() == 0 && gpp.length() > 0){
                login.loadFromJson(node);
                return login;
            }
        }
        return null;
    }
    public static Login reloadLogin(Login cache)
    {
        Query<Login> q = find.where("uuid" + "='" + cache.uuid + "'");
        Login l = q.findUnique();
        return l;
    }
    // ローカルログイン uuidで検索する
    public static Login localLogin(JsonNode node)
    {
        String uuid = JsonUtil.getString(node,JsonKeyString.UUID,"");
        if(uuid.length() > 0){
            com.avaje.ebean.Query<Login> q = find.where("uuid" + "='" + uuid + "'");
            Login l = q.findUnique();
            return l;
        }
        return null;
    }
    // GPPログインgppuuidで検索する
    public static Login gppLogin(JsonNode node)
    {
        String gpp = JsonUtil.getString(node,JsonKeyString.GPP_UUID,"");
        if(gpp.length() > 0){
            com.avaje.ebean.Query<Login> q = find.where("gppUUID" + "='" + gpp + "'");
            Login l = q.findUnique();
            return l;
        }
        return null;
    }
    public static Login findUser(String gmail)
    {
        if(gmail.length() > 0){
            com.avaje.ebean.Query<Login> q = find.where("mailAddress" + "='" + gmail + "'");
            Login l = q.findUnique();
            return l;
        }
        return null;
    }
    public static Login findUserById(long id)
    {
        com.avaje.ebean.Query<Login> q = find.where("id" + "=" + id);
        Login l = q.findUnique();
        return l;
    }
    public int addFriend(Login friend)
    {
        for(int i = 0 ; i < friends.size();i++){
            Friend ff = friends.get(i);
            if(ff.friend_id == friend.id){
                return -1;
            }
        }
        for(int i = 0 ; i < friend.friends.size();i++)
        {
            Friend ff = friend.friends.get(i);
            if(ff.friend_id == this.id){
                return -1;
            }
        }
        Friend f = new Friend();
        f.friend_id = friend.id;
        f.login = this;
        f.save();
        Friend ff = new Friend();
        ff.friend_id = this.id;
        ff.login = friend;
        ff.save();
        this.friends.add(f);
        return this.friends.size();
    };
    public ObjectNode friendList()
    {
        ObjectNode result = Json.newObject();
        ArrayNode arr = Json.newObject().arrayNode();
        for(int i = 0 ; i < friends.size();i++){
            Friend f = friends.get(i);
            arr.add(f.toJsonObject());
        }
        if(arr.size() > 0){
            result.put(JsonKeyString.FRIEND,arr);
        }
        return result;
    }

    public ObjectNode toPublicJsonObject()
    {
        ObjectNode result = Json.newObject();
        result.put(JsonKeyString.ID             ,String.valueOf(id));
        result.put(JsonKeyString.UUID           ,uuid);
        result.put(JsonKeyString.DISPLAY_NAME   ,displayName);
        result.put(JsonKeyString.MAIL_ADDRESS   ,mailAddress);
        result.put(JsonKeyString.IMAGE_URL      ,imageUrl);
        if(this.gameCharacter != null){
            result.put(JsonKeyString.HP,gameCharacter.hp);
            result.put(JsonKeyString.LEVEL,gameCharacter.level);
        }
        return result;

    }
    public ObjectNode toJsonObject()
    {
        ObjectNode result = Json.newObject();
        result.put(JsonKeyString.ID             ,String.valueOf(id));
        result.put(JsonKeyString.GPP_UUID       ,gppUUID);
        result.put(JsonKeyString.UUID           ,uuid);
        result.put(JsonKeyString.DISPLAY_NAME   ,displayName);
        result.put(JsonKeyString.MAIL_ADDRESS   ,mailAddress);
        result.put(JsonKeyString.IMAGE_URL      ,imageUrl);
        result.put(JsonKeyString.ACCESS_COUNT   ,String.valueOf(accessCnt));
        result.put(JsonKeyString.CREATE_DATE    ,String.valueOf(
                createDate == null ? 0 : createDate.getTime() / 1000));
        result.put(JsonKeyString.UPDATE_DATE    ,String.valueOf(
                updateDate == null ? 0 : updateDate.getTime() / 1000));


        return result;
    }

    public void loadFromJson(JsonNode node)
    {
        id          = JsonUtil.getLong(node,JsonKeyString.ID,id);
        gppUUID     = JsonUtil.getString(node,JsonKeyString.GPP_UUID,gppUUID);
        uuid        = JsonUtil.getString(node,JsonKeyString.UUID,uuid);
        displayName = JsonUtil.getString(node,JsonKeyString.DISPLAY_NAME,displayName);
        mailAddress = JsonUtil.getString(node,JsonKeyString.MAIL_ADDRESS,mailAddress);
        imageUrl    = JsonUtil.getString(node,JsonKeyString.IMAGE_URL,imageUrl);
        accessCnt   = JsonUtil.getLong(node,JsonKeyString.ACCESS_COUNT,accessCnt);
        long lc = createDate == null ? 0 : createDate.getTime() / 1000;
        long lu = updateDate == null ? 0 : updateDate.getTime() / 1000;
        lc = JsonUtil.getLong(node,JsonKeyString.CREATE_DATE,lc) * 1000;
        lu = JsonUtil.getLong(node,JsonKeyString.CREATE_DATE,lu) * 1000;
        createDate = new Date();
        createDate.setTime(lc);
        updateDate = new Date();
        updateDate.setTime(lu);
    }
}
