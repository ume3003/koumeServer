package models.master.manager;

import models.*;
import models.master.UnitSkill;
import models.utils.JsonKeyString;
import org.codehaus.jackson.JsonNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/28
 * Time: 19:09
 * To change this template use File | Settings | File Templates.
 */
public class UnitSkillManager extends BaseConditionMasterManager{
    private static UnitSkillManager instance = new UnitSkillManager();
    public static UnitSkillManager getInstance() { return instance;};

    protected UnitSkillManager()
    {
        loadMasterData();
        addKindNo(ID.MASTER_SKILL);
    }
    @Override
    public BaseMasterManager getParentMasterManager() {
        return Game.getInstance().getMasterManager(ID.MASTER_UNIT);
    }

    @Override
    public String getName() {
        return Strings.UNIT_SKILL;
    }

    @Override
    public String getJsonFileName() {
        return Strings.UNIT_SKILL_FILE;
    }

    @Override
    protected String getTableKey() {
        return JsonKeyString.UNIT_SKILL;
    }

    @Override
    public BaseMaster createMaster(JsonNode col) {
        return new UnitSkill(col);
    }
}
