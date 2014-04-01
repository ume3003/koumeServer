package models.master;

import models.BaseNamedMaster;
import models.ID;
import org.codehaus.jackson.JsonNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/04/01
 * Time: 19:37
 * To change this template use File | Settings | File Templates.
 */
public class Force extends BaseNamedMaster{
    @Override
    public int getMasterKey() {
        return ID.MASTER_FORCE;
    }

    public Force(JsonNode node) {
        super(node);
    }
}
