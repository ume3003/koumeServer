package models.master;

import models.*;
import org.codehaus.jackson.JsonNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/26
 * Time: 13:42
 * To change this template use File | Settings | File Templates.
 */
public class QuestAppearance extends BaseQuestCondition {


    public QuestAppearance(JsonNode node)
    {
        super(node);
    }

    @Override
    protected void registerToParent() {
        MinorQuest minorQuest = getMinorQuest();
        if(minorQuest != null){
            minorQuest.addQuestAppearance(this);
        }
    }

}
