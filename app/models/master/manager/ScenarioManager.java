package models.master.manager;

import models.BaseMaster;
import models.BaseMasterManager;
import models.Strings;
import models.master.Scenario;
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
public class ScenarioManager extends BaseMasterManager{
    private static ScenarioManager instance = new ScenarioManager();
    public static ScenarioManager getInstance() { return instance;};
    protected ScenarioManager()
    {
        Logger.info("Scenario created");
        loadMasterData();
    }
    @Override
    protected String getJsonFileName() {
        return Strings.SCENARIO_FILE;
    }

    @Override
    protected String getTableKey() {
        return JsonKeyString.SCENARIO;
    }

    @Override
    public BaseMaster createMaster(JsonNode col) {
        return new Scenario(col);
    }
    Scenario getScenario(long no)
    {
        return (Scenario)getMaster(no);
    }
}
