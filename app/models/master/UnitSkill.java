package models.master;

import models.BaseConditionMaster;
import models.BaseNamedMaster;
import models.master.manager.UnitManager;
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
public class UnitSkill extends BaseConditionMaster {
    protected long UnitNo;

    public long getUnitNo() {
        return UnitNo;
    }

    public void setUnitNo(long unitNo) {
        UnitNo = unitNo;
    }

    @Override
    public void setData(JsonNode node) {
        super.setData(node);
        UnitNo = JsonUtil.getLong(node, JsonKeyString.UNIT,-1);
    }

    @Override
    public ObjectNode toJsonObject() {
        ObjectNode result = super.toJsonObject();
        result.put(JsonKeyString.UNIT,String.valueOf(getUnitNo()));
        return result;
    }

    @Override
    protected void registerToParent() {
        Unit parent = getUnit();
        if(parent != null){

        }
    }

    @Override
    public long getParentNo() {
        return getUnitNo();
    }

    @Override
    public BaseNamedMaster getParent() {
        return UnitManager.getInstance().getUnit(getUnitNo());
    }

    public UnitSkill(JsonNode node) {
        super(node);

    }

    public Unit getUnit()
    {
        return (Unit)getParent();
    }

    public Skill getSkill()
    {
        return (Skill)getKeyData();
    }
}
