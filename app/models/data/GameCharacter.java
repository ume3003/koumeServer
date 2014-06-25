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
    public long exp;

    @NotNull
    public long level;

    @NotNull
    public long hp;

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
        result.put(JsonKeyString.ID             ,String.valueOf(id));
        result.put(JsonKeyString.STAMINA        ,String.valueOf(stamina));
        result.put(JsonKeyString.MAX_STAMINA    ,String.valueOf(maxStamina));
        result.put(JsonKeyString.EXPERIENCE     ,String.valueOf(exp));
        result.put(JsonKeyString.HP             ,String.valueOf(hp));
        result.put(JsonKeyString.LEVEL          ,String.valueOf(level));
        result.put(JsonKeyString.MONEY          ,String.valueOf(money));
        result.put(JsonKeyString.GOLD           ,String.valueOf(gold));
        result.put(JsonKeyString.LAST_COMMAND   ,String.valueOf(lastCommand.getTime() / 1000));
        result.put(JsonKeyString.CREATE_DATE    ,String.valueOf(
                createDate == null ? 0 : createDate.getTime() / 1000));
        result.put(JsonKeyString.UPDATE_DATE    ,String.valueOf(
                updateDate == null ? 0 : updateDate.getTime() / 1000));
        return result;
    }
    public void loadFromJson(JsonNode node)
    {
        id          = JsonUtil.getLong(node,JsonKeyString.ID,id);
        stamina     = JsonUtil.getLong(node,JsonKeyString.STAMINA,stamina);
        maxStamina  = JsonUtil.getLong(node,JsonKeyString.MAX_STAMINA,maxStamina);
        money       = JsonUtil.getLong(node,JsonKeyString.MONEY,money);
        gold        = JsonUtil.getLong(node,JsonKeyString.GOLD,gold);
        level       = JsonUtil.getLong(node,JsonKeyString.LEVEL,level);
        exp         = JsonUtil.getLong(node,JsonKeyString.EXPERIENCE,exp);
        hp          = JsonUtil.getLong(node,JsonKeyString.HP,hp);
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
        chara.exp = 0;
        chara.hp = 100;
        chara.level = 1;
        chara.lastCommand = new Date();
        return chara;
    }
}
