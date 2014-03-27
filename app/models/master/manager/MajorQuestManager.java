package models.master.manager;

import models.BaseMaster;
import models.BaseMasterManager;
import models.Strings;
import models.master.MajorQuest;
import models.utils.JsonKeyString;
import org.codehaus.jackson.JsonNode;
import play.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/25
 * Time: 13:50
 * To change this template use File | Settings | File Templates.
 */
public class MajorQuestManager extends BaseMasterManager {

    private static MajorQuestManager instance = new MajorQuestManager();
    public static MajorQuestManager getInstance()         { return instance;};

    @Override
    public String getName() {
        return Strings.MAJOR_QUEST;
    }

    public String getJsonFileName()
    {
        return Strings.MAJOR_QUEST_FILE;
    }
    protected String getTableKey()
    {
        return JsonKeyString.MAJOR;
    }
    public BaseMaster createMaster(JsonNode col)
    {
        return new MajorQuest(col);
    }

    protected MajorQuestManager()
    {
        Logger.info("MajorQuestManager created");
        loadMasterData();
    }
    public MajorQuest getMajorQuest(long no)
    {
        return (MajorQuest)getMaster(no);
    }
}
