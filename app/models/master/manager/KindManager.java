package models.master.manager;

import models.BaseMaster;
import models.BaseMasterManager;
import models.Strings;
import models.master.Kind;
import models.utils.JsonKeyString;
import org.codehaus.jackson.JsonNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/28
 * Time: 17:21
 * To change this template use File | Settings | File Templates.
 */
public class KindManager extends BaseMasterManager{
    private static KindManager instance = new KindManager();
    public static KindManager getInstance()     { return instance;}
    @Override
    public String getName() {
        return Strings.CONDITION_KIND;
    }

    @Override
    public String getJsonFileName() {
        return Strings.CONDITION_KIND_FILE;
    }

    @Override
    protected String getTableKey() {
        return JsonKeyString.CONDITION_KIND;
    }

    @Override
    public BaseMaster createMaster(JsonNode col) {
        return new Kind(col);
    }

    protected KindManager() {
        loadMasterData();
    }
}
