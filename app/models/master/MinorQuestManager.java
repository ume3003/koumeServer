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
 * Time: 14:12
 * To change this template use File | Settings | File Templates.
 */
public class MinorQuestManager  extends BaseMasterManager {

    private static MinorQuestManager instance = new MinorQuestManager();
    public static MinorQuestManager getInstance() { return instance;};

    protected String getJsonFileName()
    {
        return "minor.json";
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
    }

}
