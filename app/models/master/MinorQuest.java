package models.master;

import models.*;
import models.master.manager.DirectionManager;
import models.master.manager.MajorQuestManager;
import models.master.manager.MapManager;
import models.master.manager.NPCManager;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import java.util.HashMap;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/13
 * Time: 10:50
 * To change this template use File | Settings | File Templates.
 */
public class MinorQuest extends BaseNamedMaster implements FamilyMaster{
    protected long DirectionNo;
    protected long MajorNo;
    protected long MapNo;

    public int getAttackCount() {
        return AttackCount;
    }

    public void setAttackCount(int attackCount) {
        AttackCount = attackCount;
    }

    public long getNPCNo() {
        return NPCNo;
    }

    public void setNPCNo(long NPCNo) {
        this.NPCNo = NPCNo;
    }

    protected long NPCNo;
    protected int AttackCount;


    public long getDirectionNo()    { return DirectionNo;}
    public long getMajorNo()        { return MajorNo;}
    public long getMapNo()          { return MapNo;}

    protected HashMap<Integer,BaseMaster> QuestUnits = new HashMap<>();
    public HashMap<Integer,BaseMaster> getQuestUnits() {
        return QuestUnits;
    }

    protected HashMap<Integer,BaseMaster> QuestAppearances = new HashMap<>();
    public HashMap<Integer,BaseMaster> getQuestAppearances() {
        return QuestAppearances;
    }
    protected HashMap<Integer,BaseMaster> QuestClears = new HashMap<>();
    protected HashMap<Integer,BaseMaster> QuestRewards = new HashMap<>();

    public HashMap<Integer,BaseMaster> getQuestClears() {
        return QuestClears;
    }

    public HashMap<Integer,BaseMaster> getQuestRewards() {
        return QuestRewards;
    }


    @Override
    public int getMasterKey() {
        return ID.MASTER_MINOR_QUEST;
    }

    public MinorQuest(JsonNode node){
        super(node);
        setConditions(ID.MASTER_QUEST_APPEARANCE, QuestAppearances);
        setConditions(ID.MASTER_QUEST_CLEAR, QuestClears);
        setConditions(ID.MASTER_QUEST_REWARD, QuestRewards);
        setConditions(ID.MASTER_QUEST_UNIT, QuestUnits);
    }
    public void setData(JsonNode node){
        super.setData(node);
        MapNo = JsonUtil.getLong(node,JsonKeyString.MAP,-1);
        NPCNo = JsonUtil.getLong(node,JsonKeyString.NPC,-1);
        AttackCount = JsonUtil.getInt(node,JsonKeyString.ATTACK_COUNT,1);

        DirectionNo = JsonUtil.getLong(node, JsonKeyString.DIRECTION,-1);
        Direction direction = getDirection();
        if(direction != null){
            direction.addMinorQuest(this);
        }

        MajorNo = JsonUtil.getLong(node,JsonKeyString.MAJOR,-1);
        MajorQuest major = getMajorQuest();
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
        result.put(JsonKeyString.NPC,String.valueOf(getNPCNo()));
        result.put(JsonKeyString.ATTACK_COUNT, String.valueOf(getAttackCount()));

        return result;
    }
    public void addQuestUnit(QuestUnit unit)
    {
        QuestUnits.put(unit.getConditionNo(),unit);
    }
    public void addQuestAppearance(QuestAppearance appearance)
    {
        QuestAppearances.put(appearance.getConditionNo(),appearance);
    }
    public void addQuestClear(QuestClear clear)
    {
        QuestClears.put(clear.getConditionNo(),clear);
    }

    public void addQuestReward(QuestReward reward)
    {
        QuestRewards.put(reward.getConditionNo(),reward);
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
    public NPC getNPC()
    {
        return NPCManager.getInstance().getNPCMaster(NPCNo);
    }

    @Override
    public long getParentNo() {
        return MajorNo;
    }

    @Override
    public String getParentName() {
        return getParent().getName();
    }

    @Override
    public BaseNamedMaster getParent() {
       return getMajorQuest();
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
    public String getNPCName()
    {
        NPC n = getNPC();
        if(n == null)
        {
            return "";
        }
        return n.getName();
    }
}
