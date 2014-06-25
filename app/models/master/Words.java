package models.master;

import models.BaseNamedMaster;
import models.ID;
import org.codehaus.jackson.JsonNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/06/24
 * Time: 17:52
 * To change this template use File | Settings | File Templates.
 */
public class Words extends BaseNamedMaster{

    @Override
    public int getMasterKey() {
        return ID.MASTER_WORDS;
    }

    public Words(JsonNode node) {
        super(node);
    }
}
