package models.master;

import models.*;
import models.master.*;
import models.master.manager.DirectionManager;
import models.master.manager.ForceManager;
import models.master.manager.UnitSkillManager;
import models.utils.*;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/13
 * Time: 11:10
 * To change this template use File | Settings | File Templates.
 */
public class Unit extends BaseNamedMaster {
    protected HashMap<Integer,BaseMaster> Skills = new HashMap<>();

    public HashMap<Integer, BaseMaster> getSkills() {
        return Skills;
    }
    public void addSkill(UnitSkill skill)
    {
        Skills.put(skill.getConditionNo(),skill);
    }

    protected long DirectionNo;
    public long getDirectionNo()    {return DirectionNo;}
    protected long ForceNo;

    public long getForceNo() {
        return ForceNo;
    }

    public void setForceNo(long forceNo) {
        ForceNo = forceNo;
    }

    @Override
    public int getMasterKey() {
        return ID.MASTER_UNIT;
    }

    public Unit(JsonNode node){
        super(node);
        setConditions(ID.MASTER_UNIT_SKILL,Skills);
    }

    public void setData(JsonNode node){
        super.setData(node);
        setDirectionNo(JsonUtil.getLong(node, JsonKeyString.DIRECTION,-1));
        setForceNo(JsonUtil.getLong(node,JsonKeyString.FORCE,-1));
    }

    public ObjectNode toJsonObject()
    {
        ObjectNode result = super.toJsonObject();
        result.put(JsonKeyString.DIRECTION,String.valueOf(getDirectionNo()));
        result.put(JsonKeyString.FORCE,String.valueOf(getForceNo()));
        return result;
    }
    public Direction getDirection()
    {
        return DirectionManager.getInstance().getDirection(DirectionNo);
    }

    public void setDirectionNo(long directionNo) {
        DirectionNo = directionNo;
    }
    public String getDirectionName()
    {
        Direction direction = getDirection();
        if(direction != null){
            return direction.getName();
        }
        return "";
    }
    public Force getForce()
    {
        return ForceManager.getInstance().getForce(ForceNo);
    }
    public String getForceName()
    {
        Force force = getForce();
        if(force != null){
            return force.getName();
        }
        return "";
    }
}
