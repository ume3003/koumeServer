package models.master;

import models.BaseNamedMaster;
import org.codehaus.jackson.JsonNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/13
 * Time: 11:12
 * To change this template use File | Settings | File Templates.
 */
public class Item extends BaseNamedMaster{

    public Item(JsonNode node)
    {
        super(node);
    }
}
