package models.master;

import models.BaseNamedMaster;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/13
 * Time: 10:50
 * To change this template use File | Settings | File Templates.
 */
public class MinorQuest extends BaseNamedMaster {
    protected long DirectionNo;
    protected long MajorNo;
    protected long MapNo;
    public long getDirectionNo()    { return DirectionNo;}
    public long getMajorNo()        { return MajorNo;}
    public long getMapNo()          { return MapNo;}

    public MinorQuest(JsonNode node){
        super(node);
    }

    public void setData(JsonNode node){
        super.setData(node);
        DirectionNo = JsonUtil.getLong(node, JsonKeyString.DIRECTION,-1);
        MajorNo = JsonUtil.getLong(node,JsonKeyString.MAJOR,-1);
        MapNo = JsonUtil.getLong(node,JsonKeyString.MAP,-1);
    }
    public ObjectNode toJsonObject()
    {
        ObjectNode result = super.toJsonObject();
        result.put(JsonKeyString.DIRECTION,getDirectionNo());
        result.put(JsonKeyString.MAJOR,getMajorNo());
        result.put(JsonKeyString.MAP,getMapNo());

        return result;
    }

}
