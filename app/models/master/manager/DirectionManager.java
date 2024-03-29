package models.master.manager;

import models.BaseMasterManager;
import models.BaseMaster;
import models.Strings;
import models.master.Direction;
import models.utils.JsonKeyString;
import org.codehaus.jackson.JsonNode;
import play.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/13
 * Time: 14:00
 * To change this template use File | Settings | File Templates.
 */
public class DirectionManager extends BaseMasterManager {

    private static DirectionManager Instance = new DirectionManager();
    public static DirectionManager getInstance()    {return Instance;}

    @Override
    public String getName() {
        return Strings.DIRECTION;
    }

    public String getJsonFileName()
    {
        return Strings.DIRECTION_FILE;
    }
    protected String getTableKey()
    {
        return JsonKeyString.DIRECTION;
    }
    public BaseMaster createMaster(JsonNode col)
    {
        return new Direction(col);
    }

    protected DirectionManager()
    {
        Logger.info("DirectionManager created");
        loadMasterData();
    }
    public Direction getDirection(long no)
    {
        return (Direction)getMaster(no);
    }
}
