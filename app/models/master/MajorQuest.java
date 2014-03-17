package models.master;

import models.BaseNamedMaster;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/13
 * Time: 10:40
 * To change this template use File | Settings | File Templates.
 */
public class MajorQuest extends BaseNamedMaster {
    protected long DirectionNo;
    public long getDirectionNo()    {return DirectionNo;}

    public MajorQuest(JsonNode node){
        super(node);
    }
    public void setData(JsonNode node){
        super.setData(node);
        DirectionNo = JsonUtil.getLong(node, JsonKeyString.DIRECTION,-1);
    }
}