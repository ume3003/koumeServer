package models.data;

import com.avaje.ebean.*;
import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.validation.NotNull;
import models.BaseData;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;
import java.util.Date;
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
    // ローカルログイン uuidで検索する
    public static Login localLogin(JsonNode node)
    {
        String uuid = JsonUtil.getString(node,JsonKeyString.UUID,"");
        if(uuid.length() > 0){
            com.avaje.ebean.Query<Login> q = find.where(JsonKeyString.UUID + "='" + uuid + "'");
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
            com.avaje.ebean.Query<Login> q = find.where(JsonKeyString.GPP_UUID + "='" + gpp + "'");
            Login l = q.findUnique();
            return l;
        }
        return null;
    }


    public ObjectNode toJsonObject()
    {
        ObjectNode result = Json.newObject();
        result.put(JsonKeyString.ID,id);
        result.put(JsonKeyString.GPP_UUID,gppUUID);
        result.put(JsonKeyString.UUID,uuid);
        result.put(JsonKeyString.DISPLAY_NAME,displayName);
        result.put(JsonKeyString.MAIL_ADDRESS,mailAddress);
        result.put(JsonKeyString.IMAGE_URL,imageUrl);
        result.put(JsonKeyString.ACCESS_COUNT,accessCnt);
        result.put(JsonKeyString.CREATE_DATE,createDate.getTime());
        result.put(JsonKeyString.UPDATE_DATE,updateDate.getTime());

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
    }
}
