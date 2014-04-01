package models.master.manager;

import models.*;
import models.master.Unit;
import models.utils.JsonKeyString;
import org.codehaus.jackson.JsonNode;
import play.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/26
 * Time: 11:37
 * To change this template use File | Settings | File Templates.
 */
public class UnitManager extends BaseConditionOwnerMasterManager {
    private static UnitManager instance = new UnitManager();
    public static UnitManager getInstance() { return instance;};

    protected UnitManager()
    {
        Logger.info("UnitManager created");
        loadMasterData();
        addChildNo(ID.MASTER_UNIT_SKILL);
    }

    @Override
    public String getName() {
        return Strings.UNIT;
    }

    public String getJsonFileName()
    {
        return Strings.UNIT_FILE;
    }
    protected String getTableKey()
    {
        return JsonKeyString.UNIT;
    }
    public BaseMaster createMaster(JsonNode col)
    {
        return new Unit(col);
    }
    public Unit getUnit(long no)
    {
        return (Unit)getMaster(no);
    }

}
