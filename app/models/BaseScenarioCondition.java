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
        ScenarioNo = scenarioNo;
    }

    public long getScenarioNo() {

        return ScenarioNo;
    }
    public Scenario getScnario()
    {
        return (Scenario)getParent();
    }

    public BaseScenarioCondition(JsonNode node) {
        super(node);
    }

    @Override
    public long getParentNo() {
        return getScenarioNo();
    }

    @Override
    public BaseNamedMaster getParent() {
        return (BaseNamedMaster)Game.getInstance().getMaster(ID.MASTER_SCENARIO,getParentNo());
    }

    @Override
    public ObjectNode toJsonObject() {
        ObjectNode result = super.toJsonObject();
        result.put(JsonKeyString.SCENARIO,getScenarioNo());
        return result;
    }

    @Override
    public void setData(JsonNode node) {
        super.setData(node);
        ScenarioNo = JsonUtil.getLong(node, JsonKeyString.SCENARIO, -1);
        registerToParent();
    }
}
