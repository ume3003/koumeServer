package models.master.manager;

import models.BaseMaster;
import models.BaseMasterManager;
import models.Strings;
import models.master.Force;
import org.codehaus.jackson.JsonNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/04/01
 * Time: 19:40
 * To change this template use File | Settings | File Templates.
 */
public class ForceManager extends BaseMasterManager{
    private static ForceManager instance = new ForceManager();
    public static ForceManager getInstance()    { return instance;};
    @Override
    public String getName() {
        return Strings.FORCE;
    }

    @Override
    public String getJsonFileName() {
        return Strings.FORCE_FILE;
    }

    @Override
    protected String getTableKey() {
        return Strings.FORCE;
    }

    public ForceManager() {
        loadMasterData();
    }

    @Override

    public BaseMaster createMaster(JsonNode col) {
        return new Force(col);
    }

    public Force getForce(long no)
    {
        return (Force)getMaster(no);
    }
}
