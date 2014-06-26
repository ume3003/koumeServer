package models.master;

import models.BaseMaster;
import models.ID;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/06/26
 * Time: 17:02
 * To change this template use File | Settings | File Templates.
 */
public class BaseDamage extends BaseMaster{

    protected String Description;
    protected int OwnHP;
    protected int OwnPW;
    protected int EnemyHP;
    protected int EnemyPW;


    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getEnemyHP() {
        return EnemyHP;
    }

    public void setEnemyHP(int enemyHP) {
        EnemyHP = enemyHP;
    }

    public int getEnemyPW() {
        return EnemyPW;
    }

    public void setEnemyPW(int enemyPW) {
        EnemyPW = enemyPW;
    }

    public int getOwnHP() {
        return OwnHP;
    }

    public void setOwnHP(int ownHP) {
        OwnHP = ownHP;
    }

    public int getOwnPW() {
        return OwnPW;
    }

    public void setOwnPW(int ownPW) {
        OwnPW = ownPW;
    }

    @Override
    public ObjectNode toJsonObject() {
        ObjectNode result = super.toJsonObject();
        result.put(JsonKeyString.DESCRIPTION        ,getDescription());
        result.put(JsonKeyString.OWN_HIT_POINT      ,String.valueOf(getOwnHP()));
        result.put(JsonKeyString.OWN_POWER          ,String.valueOf(getOwnPW()));
        result.put(JsonKeyString.ENEMY_HIT_POINT    ,String.valueOf(getEnemyHP()));
        result.put(JsonKeyString.ENEMY_POWER        ,String.valueOf(getEnemyPW()));
        return result;
    }

    @Override
    public void setData(JsonNode node) {
        super.setData(node);
        setDescription( JsonUtil.getString( node,JsonKeyString.DESCRIPTION      ,""));
        setOwnHP(       JsonUtil.getInt(    node,JsonKeyString.OWN_HIT_POINT    ,0));
        setOwnPW(       JsonUtil.getInt(    node,JsonKeyString.OWN_POWER        ,0));
        setEnemyHP(     JsonUtil.getInt(    node,JsonKeyString.ENEMY_HIT_POINT  ,0));
        setEnemyPW(     JsonUtil.getInt(    node,JsonKeyString.ENEMY_POWER      ,0));
    }

    public BaseDamage(JsonNode node) {
        super(node);
    }

    @Override
    public int getMasterKey() {
        return ID.MASTER_BASE_DAMAGE;
    }
}
