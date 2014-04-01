package models.master;

import models.BaseNamedMaster;
import models.FamilyMaster;
import models.Game;
import models.ID;
import models.master.manager.DirectionManager;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/13
 * Time: 10:40
 * To change this template use File | Settings | File Templates.
 */
public class MajorQuest extends BaseNamedMaster implements FamilyMaster {
    protected long DirectionNo;
    public long getDirectionNo()    {return DirectionNo;}

    @Override
    public String getParentName() {
        return getParent().getName();
    }

    public void setDirectionNo(long directionNo) {
        DirectionNo = directionNo;
    }

    protected Vector<MinorQuest> MinorQuests = new Vector<>();

    @Override
    public int getMasterKey() {
        return ID.MASTER_MAJOR_QUEST;
    }

    public MajorQuest(JsonNode node){
        super(node);
    }
    public void setData(JsonNode node){
        super.setData(node);
        DirectionNo = JsonUtil.getLong(node, JsonKeyString.DIRECTION,-1);
        Direction direction = (Direction)Game.getInstance().getMasterManager(ID.MASTER_DIRECTION).getMaster(DirectionNo);
        direction.addMajorQuest(this);
    }
    public Vector<MinorQuest> getMinorQuests()    { return MinorQuests;};

    public void addMinorQuest(MinorQuest quest)
    {
        MinorQuests.add(quest);
    }

    public ObjectNode toJsonObject()
    {
        ObjectNode result = super.toJsonObject();
        result.put(JsonKeyString.DIRECTION,String.valueOf(getDirectionNo()));
        return result;
    }
    public Direction getDirection()
    {
        return DirectionManager.getInstance().getDirection(DirectionNo);
    }
    public String getDirectionName()
    {
        Direction d = getDirection();
        if(d == null){
            return "";
        }
        return d.getName();
    }
    @Override
    public long getParentNo() {
        return DirectionNo;
    }

    @Override
    public BaseNamedMaster getParent() {
        return getDirection();
    }
}
