package models.master;

import models.BaseMaster;
import models.BaseQuestCondition;
import models.Game;
import models.ID;
import models.master.manager.MinorQuestManager;
import models.master.manager.UnitManager;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/26
 * Time: 11:49
 * To change this template use File | Settings | File Templates.
 */
public class QuestUnit extends BaseQuestCondition{

    public QuestUnit(JsonNode node)
    {
        super(node);
    }

    @Override
    protected void registerToParent() {
        MinorQuest minorQuest = getMinorQuest();
        if(minorQuest != null){
            minorQuest.addQuestUnit(this);
        }

    }

    public long getUnitNo()             { return getKeyNo();}
    public long getAppearanceRate()     { return getValue();}
    public Unit getUnit()               { return UnitManager.getInstance().getUnit(getUnitNo());}


}
