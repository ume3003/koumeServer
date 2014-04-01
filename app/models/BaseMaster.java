package models;

import models.utils.JsonKeyString;
import org.codehaus.jackson.JsonNode;
import models.utils.JsonUtil;
import org.codehaus.jackson.node.ObjectNode;
import play.libs.Json;

import java.util.HashMap;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/13
 * Time: 10:08
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseMaster {
    public abstract int getMasterKey();
    protected long masterNo;
    protected HashMap<Integer,HashMap<Integer,BaseMaster>> ConditionMap = new HashMap<>();
    public long getMasterNo()   { return masterNo;}

    public void setMasterNo(long masterNo) {
        this.masterNo = masterNo;
    }

    public BaseMaster(JsonNode node){
        setData(node);
    }

    public void setData(JsonNode node)
    {
        masterNo  = JsonUtil.getLong(node, JsonKeyString.NO, -1);
    }

    public ObjectNode toJsonObject()
    {
        ObjectNode result = Json.newObject();
        result.put(JsonKeyString.NO,String.valueOf(masterNo));
        return result;
    }

    public HashMap<Integer,BaseMaster> getConditions(int conditionType)
    {
        return ConditionMap.get(conditionType);
    }
    public void setConditions(int conditionType,HashMap<Integer,BaseMaster> conditions)
    {
        ConditionMap.put(conditionType,conditions);
    }

    public HashMap<Integer, HashMap<Integer, BaseMaster>> getConditionMap() {
        return ConditionMap;
    }

    public BaseMaster getCondition(int conditionType,int conditionNo)
    {
        HashMap<Integer,BaseMaster> masters = getConditions(conditionType);
        if(masters != null){
            return masters.get(conditionNo);
        }
        return null;
    }

}
