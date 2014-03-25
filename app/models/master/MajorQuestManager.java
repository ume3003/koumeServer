package models.master;

import models.BaseMaster;
import models.BaseMasterManager;
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

    protected String getJsonFileName()
    {
        return "major.json";
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
}
