package models.master.manager;

import models.*;
import models.master.QuestClear;
import models.utils.JsonKeyString;
import org.codehaus.jackson.JsonNode;
import play.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/26
 * Time: 15:19
 * To change this template use File | Settings | File Templates.
 */
public class QuestClearManager extends BaseConditionMasterManager {

    private static QuestClearManager instance = new QuestClearManager();
    public static QuestClearManager getInstance() { return instance;};
    protected QuestClearManager(){
        Logger.info("Quest Clear created");
        loadMasterData();
        addKindNo(ID.MASTER_CHARACTER_COL);
    }

    @Override
    public String getName() {
        return Strings.QUEST_CLEAR;
    }

    @Override
    public String getJsonFileName() {
        return Strings.QUEST_CLEAR_FILE;
    }

    @Override
    protected String getTableKey() {
        return JsonKeyString.QUEST_CLEAR;
    }

    @Override
    public BaseMaster createMaster(JsonNode col) {
        return new QuestClear(col);
    }
    public QuestClear getQuestClear(long no)
    {
        return (QuestClear)getMaster(no);

    }

    @Override
    public BaseMasterManager getParentMasterManager() {
        return Game.getInstance().getMasterManager(ID.MASTER_MINOR_QUEST);
    }
}
