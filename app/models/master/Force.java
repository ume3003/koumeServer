package models.master;

import models.BaseNamedMaster;
import models.Game;
import models.ID;
import models.master.manager.ForceManager;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/04/01
 * Time: 19:37
 * To change this template use File | Settings | File Templates.
 */
public class Force extends BaseNamedMaster{
    @Override
    public int getMasterKey() {
        return ID.MASTER_FORCE;
    }

    @Override
    public ObjectNode toJsonObject() {
        ObjectNode result = super.toJsonObject();
        result.put(JsonKeyString.ENEMY_FORCE,String.valueOf(getEnemyForceNo()));
        return result;
    }

    @Override
    public void setData(JsonNode node) {
        super.setData(node);
        setEnemyForceNo(JsonUtil.getInt(node, JsonKeyString.ENEMY_FORCE, 0));
    }

    public Force(JsonNode node) {
        super(node);
    }

    public int getEnemyForceNo() {
        return enemyForceNo;
    }

    public void setEnemyForceNo(int enemyForceNo) {
        this.enemyForceNo = enemyForceNo;
    }

    protected int enemyForceNo;

    public Force getEnemyForce()
    {
        return ForceManager.getInstance().getForce(getEnemyForceNo());
    }
    public String getEnemyForceName()
    {
        Force enemy = getEnemyForce();
        if(enemy != null){
            return enemy.getName();
        }
        return "";
    }

}
