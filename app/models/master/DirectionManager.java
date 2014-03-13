package models.master;

import models.BaseMasterManager;
import models.BaseMaster;
import models.master.Direction;
import models.utils.JsonKeyString;
import org.codehaus.jackson.JsonNode;

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
    protected String getJsonFileName()
    {
        return "direction.json";
    }
    protected String getTableKey()
    {
        return JsonKeyString.DIRECTION;
    }
    public BaseMaster createMaster(JsonNode col)
    {
        return new Direction(col);
    }

}
