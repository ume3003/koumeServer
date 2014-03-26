package models.master.manager;

import models.BaseMaster;
import models.BaseMasterManager;
import models.Strings;
import models.master.QuestAppearance;
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
public class QuestAppearanceManager extends BaseMasterManager{

    private static QuestAppearanceManager instance = new QuestAppearanceManager();
    public static QuestAppearanceManager getInstance() { return instance;};
    protected QuestAppearanceManager(){
        Logger.info("Quest Appearance created");
        loadMasterData();
    }
    @Override
    protected String getJsonFileName() {
        return Strings.QUEST_APPEARANCE_FILE;
    }

    @Override
    protected String getTableKey() {
        return JsonKeyString.QUEST_APPEARANCE;
    }

    @Override
    public BaseMaster createMaster(JsonNode col) {
        return new QuestAppearance(col);
    }
    public QuestAppearance getQuestAppearance(long no)
    {
        return (QuestAppearance)getMaster(no);
    }
}
