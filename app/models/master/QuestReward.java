package models.master;

import models.BaseConditionMaster;
import models.BaseMaster;
import models.BaseQuestCondition;
import models.ID;
import org.codehaus.jackson.JsonNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/26
 * Time: 13:42
 * To change this template use File | Settings | File Templates.
 */
public class QuestReward extends BaseQuestCondition {
    @Override
    public int getMasterKey() {
        return ID.MASTER_QUEST_REWARD;
    }

    public QuestReward(JsonNode node){
        super(node);
    }

    @Override
    protected void registerToParent() {
        MinorQuest minorQuest = getMinorQuest();
        if(minorQuest != null){
            minorQuest.addQuestReward(this);
        }

    }
}
