package models.master;

import models.BaseMaster;
import models.BaseNamedMaster;
import org.codehaus.jackson.JsonNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/26
 * Time: 15:17
 * To change this template use File | Settings | File Templates.
 */
public class CompetitionRule extends BaseNamedMaster
{
    public CompetitionRule(JsonNode node)
    {
        super(node);
    }
}
