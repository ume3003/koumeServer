package models;

import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/13
 * Time: 10:27
 * To change this template use File | Settings | File Templates.
 */
public class BaseNamedMaster extends BaseMaster {

    protected String Name;
    protected String Detail;
    protected String Image;

    public BaseNamedMaster(JsonNode node)
    {
       super(node);
    }
    public void setData(JsonNode node)
    {
        super.setData(node);
        Name = JsonUtil.getString(node, JsonKeyString.NAME,"");
        Detail = JsonUtil.getString(node,JsonKeyString.DETAIL,"");
        Image = JsonUtil.getString(node,JsonKeyString.IMAGE,"");
    }
    public String getName()     { return Name;}
    public String getDetail()   { return Detail;}
    public String getImage()    { return Image;}

    public ObjectNode toJsonObject()
    {
        ObjectNode result = super.toJsonObject();
        result.put(JsonKeyString.NAME,getName());
        result.put(JsonKeyString.DETAIL,getDetail());
        result.put(JsonKeyString.IMAGE,getImage());
        return result;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public void setImage(String image) {
        Image = image;
    }
}
