package models.master.manager;

import models.*;
import models.master.QuestUnit;
import models.utils.JsonKeyString;
import org.codehaus.jackson.JsonNode;
import play.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/26
 * Time: 13:04
 * To change this template use File | Settings | File Templates.
 */
public class QuestUnitManager extends BaseConditionMasterManager
{
    private static QuestUnitManager instance = new QuestUnitManager();
    public static QuestUnitManager getInstance()   { return instance;};
    protected QuestUnitManager()
    {
        Logger.info("QuestUnitMaster created");
        loadMasterData();
        addKindNo(ID.MASTER_UNIT);
    }

    @Override
    public String getName() {
        return Strings.QUEST_UNIT;
    }

    @Override
    public String getJsonFileName() {
        return Strings.QUEST_UNIT_FILE;
    }

    @Override
    protected String getTableKey() {
        return JsonKeyString.QUEST_UNIT;
    }

    @Override
    public BaseMaster createMaster(JsonNode col) {
        return new QuestUnit(col);
    }
    public QuestUnit getQuestUnit(long no)
    {
        return (QuestUnit)getMaster(no);
    }

    @Override
    public BaseMasterManager getParentMasterManager() {
        return Game.getInstance().getMasterManager(ID.MASTER_MINOR_QUEST);
    }
}
