package models;

import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/26
 * Time: 20:09
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseConditionMaster extends FamilyMaster {
    protected BaseConditionMaster(JsonNode node){
        super(node);
    }
    protected int conditionNo;

    public int getConditionNo() {
        return conditionNo;
    }

    public void setConditionNo(int conditionNo) {
        this.conditionNo = conditionNo;
    }

    protected int Kind;
    protected long KeyNo;
    protected long Value;

    public int getKind() {
        return Kind;
    }

    public long getKeyNo() {
        return KeyNo;
    }

    public long getValue() {
        return Value;
    }

    public void setKind(int kind) {
        Kind = kind;
    }

    public void setKeyNo(long keyNo) {
        KeyNo = keyNo;
    }

    public void setValue(long value) {
        Value = value;
    }
    protected abstract void registerToParent();

    @Override
    public void setData(JsonNode node) {
        super.setData(node);
        conditionNo = JsonUtil.getInt(node,JsonKeyString.CONDITION,-1);
        Kind = JsonUtil.getInt(node, JsonKeyString.KIND,-1);
        KeyNo = JsonUtil.getLong(node,JsonKeyString.KEY_NO,-1);
        Value = JsonUtil.getLong(node,JsonKeyString.VALUE,-1);
    }

    @Override
    public ObjectNode toJsonObject() {
        ObjectNode result =  super.toJsonObject();
        result.put(JsonKeyString.CONDITION,String.valueOf(getConditionNo()));
        result.put(JsonKeyString.KIND,String.valueOf(getKind()));
        result.put(JsonKeyString.KEY_NO,String.valueOf(getKeyNo()));
        result.put(JsonKeyString.VALUE,String.valueOf(getValue()));
        return result;
    }

    public BaseMaster getKeyData()
    {
        BaseMasterManager manager = Game.getInstance().getMasterManager(Kind);
        if(manager != null){
            return manager.getMaster(getKeyNo());
        }
        return null;
    }

}
