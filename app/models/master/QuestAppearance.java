package models.master;

import models.BaseConditionMaster;
import models.BaseMaster;
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
 * Time: 13:42
 * To change this template use File | Settings | File Templates.
 */
public class QuestAppearance extends BaseConditionMaster {
    @Override
    public void setData(JsonNode node) {
        super.setData(node);
        MinorQuest minorQuest = getMinorQuest();
        if(minorQuest != null){
            minorQuest.addQuestAppearance(this);
        }
    }

    public QuestAppearance(JsonNode node)
    {
        super(node);
    }

}
