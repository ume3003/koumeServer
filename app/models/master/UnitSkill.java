package models.master;

import models.BaseConditionMaster;
import models.BaseNamedMaster;
import models.ID;
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

    public long getUnitNo() {
        return getParentNo();
    }

    public void setUnitNo(long unitNo) {
        setParentNo(unitNo);
    }

    @Override
    protected void registerToParent() {
        Unit parent = getUnit();
        if(parent != null){
            parent.addSkill(this);

        }
    }

    public UnitSkill(JsonNode node) {
        super(node);

    }

    @Override
    protected int getParentKey() {
        return ID.MASTER_UNIT;
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
