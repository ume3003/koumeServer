package models.master.manager;

import models.*;
import models.master.MinorQuest;
import models.utils.JsonKeyString;
import org.codehaus.jackson.JsonNode;
import play.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/25
 * Time: 14:12
 * To change this template use File | Settings | File Templates.
 */
public class MinorQuestManager  extends BaseConditionOwnerMasterManager {

    private static MinorQuestManager instance = new MinorQuestManager();
    public static MinorQuestManager getInstance() { return instance;};

    @Override
    public String getName() {
        return Strings.MINOR_QUEST;
    }

    public String getJsonFileName()
    {
        return Strings.MINOR_QUEST_FILE;
    }
    protected String getTableKey()
    {
        return JsonKeyString.MAJOR;
    }
    public BaseMaster createMaster(JsonNode col)
    {
        return new MinorQuest(col);
    }
    protected MinorQuestManager()
    {
        Logger.info("MinorQuestManager created");
        loadMasterData();
        addChild(ID.MASTER_QUEST_APPEARANCE, QuestAppearanceManager.getInstance());
        addChild(ID.MASTER_QUEST_CLEAR, QuestClearManager.getInstance());
        addChild(ID.MASTER_QUEST_REWARD, QuestRewardManager.getInstance());
        addChild(ID.MASTER_QUEST_UNIT, QuestUnitManager.getInstance());
    }
    public MinorQuest getMinorQuest(long no)
    {
        return (MinorQuest)getMaster(no);
    }
}
