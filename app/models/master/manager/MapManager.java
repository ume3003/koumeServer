package models.master.manager;

import models.BaseMaster;
import models.BaseMasterManager;
import models.Strings;
import models.master.Map;
import models.utils.JsonKeyString;
import org.codehaus.jackson.JsonNode;
import play.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/26
 * Time: 11:36
 * To change this template use File | Settings | File Templates.
 */
public class MapManager extends BaseMasterManager{

    private static MapManager instance = new MapManager();
    public static MapManager getInstance()  { return instance;};

    protected MapManager()
    {
        Logger.info("MapManager created");
        loadMasterData();
    }

    @Override
    public String getName() {
        return Strings.MAP;
    }

    @Override
    public String getJsonFileName() {
        return Strings.MAP_FILE;
    }

    @Override
    protected String getTableKey() {
        return JsonKeyString.MAP;
    }

    @Override
    public BaseMaster createMaster(JsonNode col) {
        return new Map(col);
    }

    public Map getMap(long no)
    {
        return (Map)getMaster(no);
    }
}
