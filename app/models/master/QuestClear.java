package models.master;

import models.BaseConditionMaster;
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
public class QuestClear extends BaseQuestCondition{
    @Override
    public int getMasterKey() {
        return ID.MASTER_QUEST_CLEAR;
    }

    public QuestClear(JsonNode node){
        super(node);
    }

    @Override
    protected void registerToParent() {
        MinorQuest minorQuest = getMinorQuest();
        if(minorQuest != null){
            minorQuest.addQuestClear(this);
        }
    }
}
