package models.master;

import models.BaseMasterManager;
import models.BaseNamedMaster;
import models.Game;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/28
 * Time: 17:15
 * To change this template use File | Settings | File Templates.
 */
public class Kind extends BaseNamedMaster{
    protected int RefNo;

    public int getRefNo() {
        return RefNo;
    }

    public void setRefNo(int refNo) {
        this.RefNo = refNo;
    }

    public Kind(JsonNode node) {
        super(node);
    }

    @Override
    public ObjectNode toJsonObject() {
        ObjectNode result = super.toJsonObject();
        result.put(JsonKeyString.REFERENCE,String.valueOf(getRefNo()));
        return result;
    }

    @Override
    public void setData(JsonNode node) {
        super.setData(node);
        RefNo = JsonUtil.getInt(node,JsonKeyString.REFERENCE,-1);
    }

    public BaseMasterManager getReferences()
    {
        return Game.getInstance().getMasterManager(getRefNo());
    }
    public String getReferenceName()
    {
        BaseMasterManager manager = getReferences();
        if(manager != null){
            return manager.getName();
        }
        return "";
    }
}
