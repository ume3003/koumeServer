package models.master.manager;

import models.BaseMaster;
import models.BaseMasterManager;
import models.Strings;
import models.master.ScenarioRule;
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
public class ScenarioRuleManager extends BaseMasterManager{
    private static ScenarioRuleManager instance = new ScenarioRuleManager();
    public static ScenarioRuleManager getInstance() { return instance;};
    protected ScenarioRuleManager()
    {
        Logger.info("Scenario Rule master created");
        loadMasterData();
    }

    @Override
    public String getName() {
        return Strings.SCENARIO_RULE;
    }

    @Override
    public String getJsonFileName() {
        return Strings.SCENARIO_RULE_FILE;
    }

    @Override
    protected String getTableKey() {
        return JsonKeyString.SCENARIO_RULE;
    }

    @Override
    public BaseMaster createMaster(JsonNode col) {
        return new ScenarioRule(col);
    }
    public ScenarioRule getScenarioRule(long no)
    {
        return (ScenarioRule)getMaster(no);
    }
}
