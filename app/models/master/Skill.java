package models.master;

import models.BaseNamedMaster;
import org.codehaus.jackson.JsonNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/28
 * Time: 18:57
 * To change this template use File | Settings | File Templates.
 */
public class Skill extends BaseNamedMaster{
    public Skill(JsonNode node) {
        super(node);
    }
}
