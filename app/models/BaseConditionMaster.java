package models;

import models.BaseMaster;
import models.master.Condition;
import models.master.MinorQuest;
import models.master.manager.ConditionManager;
import models.master.manager.MinorQuestManager;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/26
 * Time: 20:09
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseConditionMaster extends BaseMaster {
    protected BaseConditionMaster(JsonNode node){
        super(node);
    }

    protected long MinorQuestNo;
    protected long ConditionNo;

    public long getMinorQuestNo() {
        return MinorQuestNo;
    }

    public long getConditionNo() {
        return ConditionNo;
    }

    public void setMinorQuestNo(long minorQuestNo) {
        MinorQuestNo = minorQuestNo;
    }

    public void setConditionNo(long conditionNo) {
        ConditionNo = conditionNo;
    }

    @Override
    public void setData(JsonNode node) {
        super.setData(node);
        MinorQuestNo = JsonUtil.getLong(node, JsonKeyString.MINOR, -1);
        ConditionNo = JsonUtil.getLong(node,JsonKeyString.CONDITION,-1);
    }

    @Override
    public ObjectNode toJsonObject() {
        ObjectNode result =  super.toJsonObject();
        result.put(JsonKeyString.MINOR,String.valueOf(getMinorQuestNo()));
        result.put(JsonKeyString.CONDITION,String.valueOf(getConditionNo()));
        return result;
    }

    public MinorQuest getMinorQuest()
    {
        return MinorQuestManager.getInstance().getMinorQuest(getMinorQuestNo());
    }
    public Condition getCondition()
    {
        return ConditionManager.getInstance().getCondition(getConditionNo());
    }


}
