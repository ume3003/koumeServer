package models;

import models.master.Scenario;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/28
 * Time: 10:17
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseScenarioCondition extends BaseConditionMaster {
    protected long ScenarioNo;

    public void setScenarioNo(long scenarioNo) {
        setParentNo(scenarioNo);
    }

    public long getScenarioNo() {

        return getParentNo();
    }

    @Override
    protected int getParentKey() {
        return ID.MASTER_SCENARIO;
    }

    public Scenario getScenario()
    {
        return (Scenario)getParent();
    }

    public BaseScenarioCondition(JsonNode node) {
        super(node);
    }

}
