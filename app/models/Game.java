package models;

import models.master.manager.*;
import models.utils.JsonKeyString;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import play.libs.Json;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/18
 * Time: 9:30
 * To change this template use File | Settings | File Templates.
 */
public class Game {
    private static Game Instance = new Game();
    public static Game getInstance()    {return Instance;}
    private HashMap<Integer,BaseMasterManager> data = new HashMap<Integer,BaseMasterManager>();

    public BaseMasterManager getMasterManager(int masterNo)
    {
        if(0 <= masterNo && masterNo < ID.MASTER_COUNT){
            return data.get(masterNo);
        }
        return null;
    };
    public boolean init()
    {
        data.put(ID.MASTER_DIRECTION        ,DirectionManager.getInstance());
        data.put(ID.MASTER_MAJOR_QUEST      ,MajorQuestManager.getInstance());
        data.put(ID.MASTER_MINOR_QUEST      ,MinorQuestManager.getInstance());
        data.put(ID.MASTER_ITEM             ,ItemManager.getInstance());
        data.put(ID.MASTER_MAP              ,MapManager.getInstance());
        data.put(ID.MASTER_UNIT             ,UnitManager.getInstance());

        data.put(ID.MASTER_CONDITION        ,ConditionManager.getInstance());
        data.put(ID.MASTER_QUEST_APPEARANCE ,QuestAppearanceManager.getInstance());
        data.put(ID.MASTER_QUEST_CLEAR      ,QuestClearManager.getInstance());
        data.put(ID.MASTER_QUEST_REWARD     ,QuestRewardManager.getInstance());
        data.put(ID.MASTER_QUEST_UNIT       ,QuestUnitManager.getInstance());

        data.put(ID.MASTER_SCENARIO         ,ScenarioManager.getInstance());
        data.put(ID.MASTER_COMPETITION_RULE ,CompetitionRuleManager.getInstance());
        data.put(ID.MASTER_SCENARIO_RULE    ,ScenarioRuleManager.getInstance());
        data.put(ID.MASTER_SCENARIO_UNIT    ,ScenarioUnitManager.getInstance());
        return true;

    }
    public ObjectNode getMasterVersions()
    {
        ArrayNode arr = Json.newObject().arrayNode();
        for(int i = 0; i < ID.MASTER_COUNT;i++){
            BaseMasterManager master = getMasterManager(i);
            if(master != null){
               ObjectNode row = Json.newObject();
               row.put(JsonKeyString.MASTER_NO  ,String.valueOf(i));
               row.put(JsonKeyString.VERSION    ,String.valueOf(master.getVersion()));
               arr.add(row);
            }
        }
        ObjectNode result = Json.newObject();
        result.put(JsonKeyString.DATA,arr);
        return result;
    }
}
