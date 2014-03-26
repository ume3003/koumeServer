package models.master;

import models.BaseConditionMaster;
import org.codehaus.jackson.JsonNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/26
 * Time: 13:42
 * To change this template use File | Settings | File Templates.
 */
public class QuestClear extends BaseConditionMaster{
    public QuestClear(JsonNode node){
        super(node);
    }

    @Override
    public void setData(JsonNode node) {
        super.setData(node);
        MinorQuest minorQuest = getMinorQuest();
        if(minorQuest != null){
            minorQuest.addQuestClear(this);
        }
    }
}
