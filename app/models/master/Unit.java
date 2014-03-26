package models.master;

import models.*;
import models.master.*;
import models.master.manager.DirectionManager;
import models.utils.*;
import org.codehaus.jackson.JsonNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/13
 * Time: 11:10
 * To change this template use File | Settings | File Templates.
 */
public class Unit extends BaseNamedMaster {

    protected long DirectionNo;
    public long getDirectionNo()    {return DirectionNo;}
    public Unit(JsonNode node){
        super(node);
    }

    public void setData(JsonNode node){
        super.setData(node);
        DirectionNo = JsonUtil.getLong(node, JsonKeyString.DIRECTION,-1);
    }
    public Direction getDirection()
    {
        return DirectionManager.getInstance().getDirection(DirectionNo);
    }

    public void setDirectionNo(long directionNo) {
        DirectionNo = directionNo;
    }
}
