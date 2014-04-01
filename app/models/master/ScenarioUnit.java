package models.master;

import models.BaseMaster;
import models.BaseScenarioCondition;
import models.ID;
import org.codehaus.jackson.JsonNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/26
 * Time: 18:07
 * To change this template use File | Settings | File Templates.
 */
public class ScenarioUnit extends BaseScenarioCondition{
    @Override
    public int getMasterKey() {
        return ID.MASTER_SCENARIO_UNIT;
    }

    public ScenarioUnit(JsonNode node){
        super(node);
    }

    @Override
    protected void registerToParent() {
        Scenario scenario = getScenario();
        if(scenario != null){
            scenario.addScenarioUnit(this);
        }
    }
}
