package models;

import models.utils.JsonKeyString;
import org.codehaus.jackson.JsonNode;
import models.utils.JsonUtil;
import org.codehaus.jackson.node.ObjectNode;
import play.libs.Json;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/13
 * Time: 10:08
 * To change this template use File | Settings | File Templates.
 */
public class BaseMaster {
    protected long masterNo;
    public long getMasterNo()   { return masterNo;}
    public BaseMaster(JsonNode node){
        setData(node);
    }

    public void setData(JsonNode node)
    {
        masterNo  = JsonUtil.getInt(node, JsonKeyString.NO, -1);
    }

    public ObjectNode toJsonObject()
    {
        ObjectNode result = Json.newObject();
        result.put(JsonKeyString.NO,masterNo);
        return result;
    }
}
