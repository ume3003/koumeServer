package models.data;

import com.avaje.ebean.annotation.CreatedTimestamp;
import com.avaje.ebean.validation.NotNull;
import models.BaseData;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import play.db.ebean.Model;
import play.libs.Json;

import javax.persistence.*;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/05/08
 * Time: 10:41
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Friend extends Model implements BaseData {

    @Id
    public long id;

    @ManyToOne
    @JoinColumn(name="login_Id")
    public Login login;

    /*
    @OneToOne
    @JoinColumn(name="friend_id")
    public Login friend;
    */
    @NotNull
    public long friend_id;

    @CreatedTimestamp
    public Date createDate;

    @Version
    public Date updateDate;


    @Override
    public void loadFromJson(JsonNode node) {
        id          = JsonUtil.getLong(node, JsonKeyString.ID, id);
    }

    @Override
    public ObjectNode toJsonObject() {
        ObjectNode result = Json.newObject();
        result.put(JsonKeyString.ID,id);
        Login friend = Login.findUserById(friend_id);
        if(friend != null){
            result.put(JsonKeyString.FRIEND,friend.toPublicJsonObject());
        }
        result.put(JsonKeyString.CREATE_DATE    ,String.valueOf(
                createDate == null ? 0 : createDate.getTime() / 1000));
        result.put(JsonKeyString.UPDATE_DATE    ,String.valueOf(
                updateDate == null ? 0 : updateDate.getTime() / 1000));
        return result;
    }
}
