package models.master;

import models.BaseNamedMaster;
import models.Game;
import models.ID;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/13
 * Time: 10:40
 * To change this template use File | Settings | File Templates.
 */
public class MajorQuest extends BaseNamedMaster {
    protected long DirectionNo;
    public long getDirectionNo()    {return DirectionNo;}
    protected Vector<Long> m_minorQuestNos = new Vector<>();

    public MajorQuest(JsonNode node){
        super(node);
    }
    public void setData(JsonNode node){
        super.setData(node);
        DirectionNo = JsonUtil.getLong(node, JsonKeyString.DIRECTION,-1);
        Direction direction = (Direction)Game.getInstance().getMasterManager(ID.MASTER_DIRECTION).getMaster(DirectionNo);
        direction.addMajorQuestNo(getMasterNo());
    }
    public Vector<Long> getMinorQuestNos()    { return m_minorQuestNos;};

    public void addMinorQuestNo(long No)
    {
        m_minorQuestNos.add(No);
    }
    public ObjectNode toJsonObject()
    {
        ObjectNode result = super.toJsonObject();
        result.put(JsonKeyString.DIRECTION,getDirectionNo());
        return result;
    }
}
