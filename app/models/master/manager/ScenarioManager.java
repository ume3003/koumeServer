package models.master.manager;

import models.*;
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
public class ScenarioManager extends BaseConditionOwnerMasterManager{
    private static ScenarioManager instance = new ScenarioManager();
    public static ScenarioManager getInstance() { return instance;};
    protected ScenarioManager()
    {
        Logger.info("Scenario created");
        loadMasterData();
        addChild(ID.MASTER_SCENARIO_RULE, ScenarioRuleManager.getInstance());
        addChild(ID.MASTER_SCENARIO_UNIT, ScenarioUnitManager.getInstance());
    }

    @Override
    public String getName() {
        return Strings.SCENARIO;
    }

    @Override
    public String getJsonFileName() {
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
