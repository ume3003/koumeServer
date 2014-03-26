package models.master;

import models.BaseNamedMaster;
import models.master.manager.MapManager;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/26
 * Time: 15:16
 * To change this template use File | Settings | File Templates.
 */
public class Scenario extends BaseNamedMaster{
    public Scenario(JsonNode node){
        super(node);
    }
    protected long MapNo;

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
