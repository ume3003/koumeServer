package models.master.manager;

import models.*;
import models.master.ScenarioUnit;
import models.utils.JsonKeyString;
import org.codehaus.jackson.JsonNode;
import play.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/26
 * Time: 18:07
 * To change this template use File | Settings | File Templates.
 */
public class ScenarioUnitManager extends BaseConditionMasterManager {
    private static ScenarioUnitManager instance = new ScenarioUnitManager();
    public static ScenarioUnitManager getInstance() { return instance;};
    protected ScenarioUnitManager()
    {
        Logger.info("senariounit created");
        loadMasterData();
        addKindNo(ID.MASTER_UNIT);
    }

    @Override
    public String getName() {
        return Strings.SCENARIO_UNIT;
    }

    @Override
    public String getJsonFileName() {
        return Strings.SCENARIO_UNIT_FILE;
    }

    @Override
    protected String getTableKey() {
        return JsonKeyString.SCENARIO_UNIT;
    }

    @Override
    public BaseMaster createMaster(JsonNode col) {
        return new ScenarioUnit(col);
    }
    ScenarioUnit getScenarioUnit(long no)
    {
        return (ScenarioUnit)getMaster(no);
    }

    @Override
    public BaseMasterManager getParentMasterManager() {
        return Game.getInstance().getMasterManager(ID.MASTER_SCENARIO);
    }
}
