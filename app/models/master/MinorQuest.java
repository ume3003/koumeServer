package models.master;

import models.BaseNamedMaster;
import models.FamilyMaster;
import models.Game;
import models.ID;
import models.master.manager.DirectionManager;
import models.master.manager.MajorQuestManager;
import models.master.manager.MapManager;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/13
 * Time: 10:50
 * To change this template use File | Settings | File Templates.
 */
public class MinorQuest extends FamilyMaster{
    protected long DirectionNo;
    protected long MajorNo;
    protected long MapNo;

    public long getDirectionNo()    { return DirectionNo;}
    public long getMajorNo()        { return MajorNo;}
    public long getMapNo()          { return MapNo;}

    protected Vector<QuestUnit> QuestUnits = new Vector<>();
    public Vector<QuestUnit> getQuestUnits() {
        return QuestUnits;
    }

    protected Vector<QuestAppearance> QuestAppearances = new Vector<>();
    public Vector<QuestAppearance> getQuestAppearances() {
        return QuestAppearances;
    }
    protected Vector<QuestClear> QuestClears = new Vector<>();
    protected Vector<QuestReward> QuestRewards = new Vector<>();

    public Vector<QuestClear> getQuestClears() {
        return QuestClears;
    }

    public Vector<QuestReward> getQuestRewards() {
        return QuestRewards;
    }

    public MinorQuest(JsonNode node){
        super(node);
    }

    public void setData(JsonNode node){
        super.setData(node);
        MapNo = JsonUtil.getLong(node,JsonKeyString.MAP,-1);

        DirectionNo = JsonUtil.getLong(node, JsonKeyString.DIRECTION,-1);
        Direction direction = (Direction)Game.getInstance().getMasterManager(ID.MASTER_DIRECTION).getMaster(DirectionNo);
        if(direction != null){
            direction.addMinorQuest(this);
        }

        MajorNo = JsonUtil.getLong(node,JsonKeyString.MAJOR,-1);
        MajorQuest major = (MajorQuest)Game.getInstance().getMasterManager(ID.MASTER_MAJOR_QUEST).getMaster(MajorNo);
        if(major != null){
            major.addMinorQuest(this);
        }
    }
    public ObjectNode toJsonObject()
    {
        ObjectNode result = super.toJsonObject();
        result.put(JsonKeyString.DIRECTION,String.valueOf(getDirectionNo()));
        result.put(JsonKeyString.MAJOR,String.valueOf(getMajorNo()));
        result.put(JsonKeyString.MAP,String.valueOf(getMapNo()));

        return result;
    }
    public void addQuestUnit(QuestUnit unit)
    {
        QuestUnits.add(unit);
    }
    public void addQuestAppearance(QuestAppearance appearance)
    {
        QuestAppearances.add(appearance);
    }
    public void addQuestClear(QuestClear clear)
    {
        QuestClears.add(clear);
    }

    public void addQuestReward(QuestReward reward)
    {
        QuestRewards.add(reward);
    }

    public void setDirectionNo(long directionNo) {
        DirectionNo = directionNo;
    }

    public void setMajorNo(long majorNo) {
        MajorNo = majorNo;
    }

    public void setMapNo(long mapNo) {
        MapNo = mapNo;
    }

    public Direction getDirection()
    {
        return DirectionManager.getInstance().getDirection(DirectionNo);
    }
    public MajorQuest getMajorQuest()
    {
        return MajorQuestManager.getInstance().getMajorQuest(MajorNo);
    }
    public Map getMap()
    {
        return MapManager.getInstance().getMap(MapNo);
    }

    @Override
    public long getParentNo() {
        return MajorNo;
    }

    public String getMajorQuestName()
    {
        MajorQuest q = getMajorQuest();
        if(q == null){
            return "";
        }
        return q.getName();
    }
    public String getDirectionName()
    {
        Direction d = getDirection();
        if(d == null){
            return "";
        }
        return d.getName();
    }
    public String getMapName()
    {
        Map m = getMap();
        if(m == null){
            return "";
        }
        return m.getName();
    }
}
