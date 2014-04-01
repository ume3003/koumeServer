package models.master;

import models.BaseNamedMaster;
import models.ID;
import org.codehaus.jackson.JsonNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/28
 * Time: 18:57
 * To change this template use File | Settings | File Templates.
 */
public class Skill extends BaseNamedMaster{
    @Override
    public int getMasterKey() {
        return ID.MASTER_SKILL;
    }

    public Skill(JsonNode node) {
        super(node);
    }
}
