package models.data;

import com.avaje.ebean.Query;
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
 * Date: 2014/03/13
 * Time: 15:02
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class GameCharacter extends Model implements BaseData {

    @Id
    public long id;

    @OneToOne
    @JoinColumn(name="login_id")
    public Login login;

    @NotNull
    public long stamina;

    @NotNull
    public long maxStamina;

    @NotNull
    public long money;       // リアルまねー

    @NotNull
    public long gold;        // ゲームマネー

    @NotNull
    public Date lastCommand;

    @CreatedTimestamp
    public Date createDate;

    @Version
    public Date updateDate;

    public ObjectNode toJsonObject()
    {
        ObjectNode result = Json.newObject();
        result.put(JsonKeyString.ID,id);
        result.put(JsonKeyString.STAMINA,stamina);
        result.put(JsonKeyString.MAX_STAMINA,maxStamina);
        result.put(JsonKeyString.MONEY,money);
        result.put(JsonKeyString.GOLD,gold);
        result.put(JsonKeyString.LAST_COMMAND,lastCommand.getTime());
        result.put(JsonKeyString.CREATE_DATE,createDate.getTime());
        result.put(JsonKeyString.UPDATE_DATE,updateDate.getTime());

        return result;
    }
    public void loadFromJson(JsonNode node)
    {
        id = JsonUtil.getLong(node,JsonKeyString.ID,id);
        stamina = JsonUtil.getLong(node,JsonKeyString.STAMINA,stamina);
        maxStamina = JsonUtil.getLong(node,JsonKeyString.MAX_STAMINA,maxStamina);
        money = JsonUtil.getLong(node,JsonKeyString.MONEY,money);
        gold = JsonUtil.getLong(node,JsonKeyString.GOLD,gold);
        lastCommand = new Date(JsonUtil.getLong(node,JsonKeyString.LAST_COMMAND,lastCommand.getTime()));

    }

    // access method
    public static Finder<Long,GameCharacter>find = new Finder<Long,GameCharacter>
    (
        Long.class,GameCharacter.class
    );

    public static GameCharacter createGameCharacter()
    {
        GameCharacter chara = new GameCharacter();
        chara.gold = 100;
        chara.maxStamina = 10;
        chara.stamina = 10;
        chara.money = 0;
        chara.lastCommand = new Date();
        return chara;
    }
}
