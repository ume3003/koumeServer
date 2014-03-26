package models.master;

import models.BaseMaster;
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
public class QuestUnit extends BaseMaster{

    protected long MinorQuestNo;
    protected long UnitNo;
    protected int AppearanceRate;

    public long getMinorQuestNo() {return MinorQuestNo;}

    public long getUnitNo() {
        return UnitNo;
    }
    public int getAppearanceRate() {
        return AppearanceRate;
    }
    public void setMinorQuestNo(long minorQuestNo) {
        MinorQuestNo = minorQuestNo;
    }
    public void setUnitNo(long unitNo) {
        UnitNo = unitNo;
    }
    public void setAppearanceRate(int appearanceRate) {
        AppearanceRate = appearanceRate;
    }

    public QuestUnit(JsonNode node)
    {
        super(node);
    }

    @Override
    public void setData(JsonNode node) {
        super.setData(node);
        MinorQuestNo = JsonUtil.getLong(node, JsonKeyString.MINOR,-1);
        MinorQuest quest = getMinorQuest();
        if(quest != null){
            quest.addQuestUnit(this);
        }
        UnitNo = JsonUtil.getLong(node,JsonKeyString.UNIT,-1);
        AppearanceRate = JsonUtil.getInt(node,JsonKeyString.APPEARANCE_RATE,-1);

    }

    @Override
    public ObjectNode toJsonObject() {

        ObjectNode result =  super.toJsonObject();

        result.put(JsonKeyString.MINOR,String.valueOf(getMinorQuestNo()));
        result.put(JsonKeyString.UNIT,String.valueOf(getUnitNo()));
        result.put(JsonKeyString.APPEARANCE_RATE,String.valueOf(getAppearanceRate()));

        return result;
    }
    public MinorQuest getMinorQuest()
    {
        return MinorQuestManager.getInstance().getMinorQuest(MinorQuestNo);
    }

    public Unit getUnit()
    {
        return UnitManager.getInstance().getUnit(UnitNo);
    }

}
