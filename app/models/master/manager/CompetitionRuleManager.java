package models.master.manager;

import models.BaseMaster;
import models.master.*;
import models.BaseMasterManager;
import models.ID;
import models.Strings;
import models.utils.JsonKeyString;
import org.codehaus.jackson.JsonNode;
import play.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/26
 * Time: 15:19
 * To change this template use File | Settings | File Templates.
 */
public class CompetitionRuleManager extends BaseMasterManager
{
    private static CompetitionRuleManager instance = new CompetitionRuleManager();
    public static CompetitionRuleManager getInstance() { return instance;};
    protected CompetitionRuleManager()
    {
        Logger.info("Competition Rule created");
        loadMasterData();
    }



    @Override
    public String getName() {
        return Strings.COMPETITION_RULE;
    }

    @Override
    public String getJsonFileName() {
        return Strings.COMPETITION_RULE_FILE;
    }

    @Override
    protected String getTableKey() {
        return JsonKeyString.COMPETITION_RULE;
    }

    @Override
    public BaseMaster createMaster(JsonNode col) {
        return new CompetitionRule(col);
    }
    public CompetitionRule getCompetitionRule(long no)
    {
        return (CompetitionRule)getMaster(no);
    }
}
