package models.master;

import models.BaseConditionMaster;
import models.BaseMaster;
import org.codehaus.jackson.JsonNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/26
 * Time: 13:42
 * To change this template use File | Settings | File Templates.
 */
public class QuestReward extends BaseConditionMaster{
    public QuestReward(JsonNode node){
        super(node);
    }

    @Override
    public void setData(JsonNode node) {
        super.setData(node);
        MinorQuest minorQuest = getMinorQuest();
        if(minorQuest != null){
            minorQuest.addQuestReward(this);
        }
    }
}
