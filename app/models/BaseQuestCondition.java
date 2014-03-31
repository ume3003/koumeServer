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
    @Override
    protected int getParentKey() {
        return ID.MASTER_MINOR_QUEST;
    }

    public void setMinorQuestNo(long minorQuestNo) {
        setParentNo(minorQuestNo);
    }

    public long getMinorQuestNo() {
        return getParentNo();
    }

    public MinorQuest getMinorQuest()
    {
        return (MinorQuest)getParent();
    }
}
