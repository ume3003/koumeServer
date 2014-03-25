package models.master;

import models.BaseNamedMaster;
import models.Game;
import models.ID;
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
        MapNo = JsonUtil.getLong(node,JsonKeyString.MAP,-1);

        DirectionNo = JsonUtil.getLong(node, JsonKeyString.DIRECTION,-1);
        Direction direction = (Direction)Game.getInstance().getMasterManager(ID.MASTER_DIRECTION).getMaster(DirectionNo);
        if(direction != null){
            direction.addMajorQuestNo(getMasterNo());
        }

        MajorNo = JsonUtil.getLong(node,JsonKeyString.MAJOR,-1);
        MajorQuest major = (MajorQuest)Game.getInstance().getMasterManager(ID.MASTER_MAJOR_QUEST).getMaster(MajorNo);
        if(major != null){
            major.addMinorQuestNo(getMasterNo());
        }
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
