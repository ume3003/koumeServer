package models;

import models.master.manager.*;
import models.utils.JsonKeyString;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import play.Logger;
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
    private HashMap<Integer,BaseMasterManager> Data = new HashMap<Integer,BaseMasterManager>();
    private HashMap<Integer,BaseMasterManager> Menu = new HashMap<>();

    public HashMap<Integer, BaseMasterManager> getMenu() {
        return Menu;
    }

    public HashMap<Integer, BaseMasterManager> getData() {
        return Data;
    }

    public BaseMasterManager getMasterManager(int masterNo)
    {
        if(0 <= masterNo && masterNo < ID.MASTER_COUNT){
            return Data.get(masterNo);
        }
        return null;
    };
    public String getMasterManagerName(int masterNo)
    {
       BaseMasterManager manager = getMasterManager(masterNo);
        if(manager != null){
            return manager.getName();
        }
        return "";
    }
    public boolean init()
    {
        Data.put(ID.MASTER_DIRECTION, DirectionManager.getInstance());
        Data.put(ID.MASTER_MAJOR_QUEST, MajorQuestManager.getInstance());
        Data.put(ID.MASTER_MINOR_QUEST, MinorQuestManager.getInstance());
        Data.put(ID.MASTER_ITEM, ItemManager.getInstance());
        Data.put(ID.MASTER_MAP, MapManager.getInstance());
        Data.put(ID.MASTER_UNIT, UnitManager.getInstance());

        Data.put(ID.MASTER_QUEST_APPEARANCE, QuestAppearanceManager.getInstance());
        Data.put(ID.MASTER_QUEST_CLEAR, QuestClearManager.getInstance());
        Data.put(ID.MASTER_QUEST_REWARD, QuestRewardManager.getInstance());
        Data.put(ID.MASTER_QUEST_UNIT, QuestUnitManager.getInstance());

        Data.put(ID.MASTER_SCENARIO, ScenarioManager.getInstance());
        Data.put(ID.MASTER_COMPETITION_RULE, CompetitionRuleManager.getInstance());
        Data.put(ID.MASTER_SCENARIO_RULE, ScenarioRuleManager.getInstance());
        Data.put(ID.MASTER_SCENARIO_UNIT, ScenarioUnitManager.getInstance());

        Data.put(ID.MASTER_RANK,RankManager.getInstance());
        Data.put(ID.MASTER_CONDITION_KIND,KindManager.getInstance());
        Data.put(ID.MASTER_CHARACTER_COL,CharacterColumnManager.getInstance());

        Data.put(ID.MASTER_SKILL,SkillManager.getInstance());
        Data.put(ID.MASTER_UNIT_SKILL,UnitSkillManager.getInstance());
        Data.put(ID.MASTER_FORCE,ForceManager.getInstance());

        Data.put(ID.MASTER_NPC_DATA,NPCManager.getInstance());
        Data.put(ID.MASTER_WORDS,WordsManager.getInstance());
        Data.put(ID.MASTER_NPC_WORDS,NPCWordsManager.getInstance());
        Data.put(ID.MASTER_BASE_DAMAGE,BaseDamageManager.getInstance());

        Menu.put(ID.MASTER_DIRECTION, DirectionManager.getInstance());
        Menu.put(ID.MASTER_MAJOR_QUEST, MajorQuestManager.getInstance());
        Menu.put(ID.MASTER_MINOR_QUEST, MinorQuestManager.getInstance());
        Menu.put(ID.MASTER_ITEM, ItemManager.getInstance());
        Menu.put(ID.MASTER_MAP, MapManager.getInstance());
        Menu.put(ID.MASTER_UNIT, UnitManager.getInstance());

        Menu.put(ID.MASTER_SCENARIO, ScenarioManager.getInstance());
        Menu.put(ID.MASTER_COMPETITION_RULE, CompetitionRuleManager.getInstance());

        Menu.put(ID.MASTER_RANK,RankManager.getInstance());
        Menu.put(ID.MASTER_CONDITION_KIND,KindManager.getInstance());
        Menu.put(ID.MASTER_CHARACTER_COL,CharacterColumnManager.getInstance());
        Menu.put(ID.MASTER_SKILL,SkillManager.getInstance());
        Menu.put(ID.MASTER_FORCE,ForceManager.getInstance());

        Menu.put(ID.MASTER_NPC_DATA,NPCManager.getInstance());
        Menu.put(ID.MASTER_WORDS,WordsManager.getInstance());

        Menu.put(ID.MASTER_BASE_DAMAGE,BaseDamageManager.getInstance());

        QuestAppearanceManager.getInstance().registerAllCondition();
        QuestClearManager.getInstance().registerAllCondition();
        QuestRewardManager.getInstance().registerAllCondition();
        QuestUnitManager.getInstance().registerAllCondition();

        ScenarioRuleManager.getInstance().registerAllCondition();
        ScenarioUnitManager.getInstance().registerAllCondition();
        UnitSkillManager.getInstance().registerAllCondition();

        NPCWordsManager.getInstance().registerAllCondition();

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
            else{
                Logger.debug(i + " is null");
            }
        }
        ObjectNode result = Json.newObject();
        result.put(JsonKeyString.DATA,arr);
        return result;
    }
    public BaseMaster getMaster(int masterNo,long no)
    {
        BaseMasterManager manager = getMasterManager(masterNo);
        if(manager != null){
            return manager.getMaster(no);
        }
        return null;
    }
}
