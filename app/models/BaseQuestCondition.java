package models;

import models.master.MinorQuest;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/28
 * Time: 10:04
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseQuestCondition extends BaseConditionMaster{
    public BaseQuestCondition(JsonNode node){
        super(node);
    }
    protected long MinorQuestNo;

    public void setMinorQuestNo(long minorQuestNo) {
        MinorQuestNo = minorQuestNo;
    }

    public long getMinorQuestNo() {

        return MinorQuestNo;
    }

    public MinorQuest getMinorQuest()
    {
        return (MinorQuest)getParent();
    }
    @Override
    public long getParentNo() {
        return getMinorQuestNo();
    }

    @Override
    public BaseNamedMaster getParent() {
        return (BaseNamedMaster) Game.getInstance().getMaster(ID.MASTER_MINOR_QUEST,getMinorQuestNo());
    }

    @Override
    public ObjectNode toJsonObject() {
        ObjectNode result = super.toJsonObject();
        result.put(JsonKeyString.MINOR,getMinorQuestNo());
        return result;
    }

    @Override
    public void setData(JsonNode node) {
        super.setData(node);
        MinorQuestNo = JsonUtil.getLong(node, JsonKeyString.MINOR, -1);
        registerToParent();
    }

}
