package models.master.manager;

import models.BaseMaster;
import models.BaseMasterManager;
import models.Strings;
import models.master.Condition;
import models.utils.JsonKeyString;
import org.codehaus.jackson.JsonNode;
import play.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/26
 * Time: 13:41
 * To change this template use File | Settings | File Templates.
 */
public class ConditionManager extends BaseMasterManager{
    private static ConditionManager instance = new ConditionManager();
    public static ConditionManager getInstance() { return instance;};
    protected ConditionManager()
    {
        Logger.info("Condition Manager created");
        loadMasterData();
    }

    @Override
    public String getName() {
        return Strings.CONDITION;
    }

    @Override
    public String getJsonFileName() {
        return Strings.CONDITION_FILE;
    }

    @Override
    protected String getTableKey() {
        return JsonKeyString.CONDITION;
    }

    @Override
    public BaseMaster createMaster(JsonNode col) {
        return new Condition(col);
    }
    public Condition getCondition(long no)
    {
        return (Condition)getMaster(no);
    }
}
