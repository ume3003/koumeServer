package models.master.manager;

import models.BaseMaster;
import models.BaseMasterManager;
import models.Strings;
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
public class QuestClearManager extends BaseMasterManager{

    private static QuestClearManager instance = new QuestClearManager();
    public static QuestClearManager getInstance() { return instance;};
    protected QuestClearManager(){
        Logger.info("Quest Clear created");
        loadMasterData();
    }
    @Override
    protected String getJsonFileName() {
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
}
