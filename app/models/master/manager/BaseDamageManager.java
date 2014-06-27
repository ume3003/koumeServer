package models.master.manager;

import models.BaseMaster;
import models.BaseMasterManager;
import models.Strings;
import models.master.BaseDamage;
import models.utils.JsonKeyString;
import org.codehaus.jackson.JsonNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/06/26
 * Time: 17:15
 * To change this template use File | Settings | File Templates.
 */
public class BaseDamageManager extends BaseMasterManager {
    private static BaseDamageManager instance = new BaseDamageManager();
    public static BaseDamageManager getInstance()    { return instance;};

    public BaseDamage getBaseDamage(long no)
    {
        return (BaseDamage)getMaster(no);
    }
    protected BaseDamageManager()
    {
       loadMasterData();
    }
    @Override
    public String getName() {
        return Strings.BASE_DAMAGE;
    }

    @Override
    public String getJsonFileName() {
        return Strings.BASE_DAMAGE_FILE;
    }

    @Override
    protected String getTableKey() {
        return JsonKeyString.BASE_DAMAGE;
    }

    @Override
    public BaseMaster createMaster(JsonNode col) {
        return new BaseDamage(col);
    }
}
