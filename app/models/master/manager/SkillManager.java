package models.master.manager;

import models.BaseMaster;
import models.BaseMasterManager;
import models.Strings;
import models.master.Skill;
import models.utils.JsonKeyString;
import org.codehaus.jackson.JsonNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/28
 * Time: 19:08
 * To change this template use File | Settings | File Templates.
 */
public class SkillManager extends BaseMasterManager{
    private static SkillManager instance = new SkillManager();
    public static SkillManager getInstance() { return instance;};

    @Override
    public String getName() {
        return Strings.SKILL;
    }

    @Override
    public String getJsonFileName() {
        return Strings.SKILL_FILE;
    }

    @Override
    protected String getTableKey() {
        return JsonKeyString.SKILL;
    }

    protected SkillManager() {
        loadMasterData();
    }

    @Override
    public BaseMaster createMaster(JsonNode col) {
        return new Skill(col);
    }
}
