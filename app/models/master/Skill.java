package models.master;

import models.BaseNamedMaster;
import models.ID;
import models.master.manager.BaseDamageManager;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/28
 * Time: 18:57
 * To change this template use File | Settings | File Templates.
 */
public class Skill extends BaseNamedMaster{
    @Override
    public int getMasterKey() {
        return ID.MASTER_SKILL;
    }

    @Override
    public ObjectNode toJsonObject() {
        ObjectNode result = super.toJsonObject();
        result.put(JsonKeyString.BASE_DAMAGE,String.valueOf(getBaseDamageNo()));
        return result;
    }

    @Override
    public void setData(JsonNode node) {
        super.setData(node);
        setBaseDamageNo(JsonUtil.getLong(node,JsonKeyString.BASE_DAMAGE,0));
    }

    public Skill(JsonNode node) {
        super(node);
    }

    protected long BaseDamageNo;

    public long getBaseDamageNo() {
        return BaseDamageNo;
    }

    public void setBaseDamageNo(long baseDamageNo) {
        BaseDamageNo = baseDamageNo;
    }
    public BaseDamage getBaseDamage()
    {
        return BaseDamageManager.getInstance().getBaseDamage(getBaseDamageNo());
    }
    public String getBaseDamageDescription()
    {
        BaseDamage bd = getBaseDamage();
        if(bd != null){
            return bd.getDescription();
        }
        return "";

    }

}
