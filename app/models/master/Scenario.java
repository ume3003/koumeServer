package models.master;

import models.BaseMaster;
import models.BaseNamedMaster;
import models.ID;
import models.master.manager.MapManager;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import java.util.HashMap;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/26
 * Time: 15:16
 * To change this template use File | Settings | File Templates.
 */
public class Scenario extends BaseNamedMaster{
    @Override
    public int getMasterKey() {
        return ID.MASTER_SCENARIO;
    }

    public Scenario(JsonNode node){
        super(node);
        setConditions(ID.MASTER_SCENARIO_RULE,ScenarioRules);
        setConditions(ID.MASTER_SCENARIO_UNIT,ScenarioUnits);
    }
    protected long MapNo;
    protected HashMap<Integer,BaseMaster> ScenarioRules = new HashMap<>();
    protected HashMap<Integer,BaseMaster> ScenarioUnits = new HashMap<>();

    public HashMap<Integer,BaseMaster> getScenarioRules() {
        return ScenarioRules;
    }

    public HashMap<Integer,BaseMaster> getScenarioUnits() {
        return ScenarioUnits;
    }

    public void addScenarioRule(ScenarioRule rule)
    {
        ScenarioRules.put(rule.getConditionNo(),rule);
    }
    public void addScenarioUnit(ScenarioUnit unit)
    {
        ScenarioUnits.put(unit.getConditionNo(),unit);
    }

    public void setMapNo(long mapNo) {
        MapNo = mapNo;
    }

    public long getMapNo() {

        return MapNo;
    }

    @Override
    public void setData(JsonNode node) {
        super.setData(node);
        MapNo = JsonUtil.getLong(node, JsonKeyString.MAP,-1);
    }

    @Override
    public ObjectNode toJsonObject() {
        ObjectNode result = super.toJsonObject();
        result.put(JsonKeyString.MAP,String.valueOf(getMapNo()));
        return result;
    }

    public Map getMap()
    {
        return MapManager.getInstance().getMap(getMapNo());
    }
}
