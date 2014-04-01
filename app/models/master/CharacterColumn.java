package models.master;

import models.BaseNamedMaster;
import models.ID;
import org.codehaus.jackson.JsonNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/28
 * Time: 17:14
 * To change this template use File | Settings | File Templates.
 */
public class CharacterColumn extends BaseNamedMaster{
    @Override
    public int getMasterKey() {
        return ID.MASTER_CHARACTER_COL;
    }

    public CharacterColumn(JsonNode node) {
        super(node);
    }
}
