package models.master;

import models.BaseNamedMaster;
import models.ID;
import org.codehaus.jackson.JsonNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/13
 * Time: 11:11
 * To change this template use File | Settings | File Templates.
 */
public class Map extends BaseNamedMaster{
    @Override
    public int getMasterKey() {
        return ID.MASTER_MAP;
    }

    public Map(JsonNode node){
        super(node);
    }
}
