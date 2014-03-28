package models.master.manager;

import models.*;
import models.master.QuestReward;
import models.utils.JsonKeyString;
import org.codehaus.jackson.JsonNode;
import play.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/26
 * Time: 15:20
 * To change this template use File | Settings | File Templates.
 */
public class QuestRewardManager extends BaseConditionMasterManager {
    private static QuestRewardManager instance = new QuestRewardManager();
    public static QuestRewardManager getInstance() { return instance;};
    protected QuestRewardManager(){
        Logger.info("QuestReward created");
        loadMasterData();
    }

    @Override
    public String getName() {
        return Strings.QUEST_REWARD;
    }

    @Override
    public String getJsonFileName() {
        return Strings.QUEST_REWARD_FILE;
    }

    @Override
    protected String getTableKey() {
        return JsonKeyString.QUEST_REWARD;
    }

    @Override
    public BaseMaster createMaster(JsonNode col) {
        return new QuestReward(col);
    }
    public QuestReward getQuestReward(long no)
    {
        return (QuestReward)getMaster(no);
    }

    @Override
    public BaseMasterManager getParentMasterManager() {
        return Game.getInstance().getMasterManager(ID.MASTER_MINOR_QUEST);
    }
}
