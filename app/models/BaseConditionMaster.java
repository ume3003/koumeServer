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
public abstract class BaseConditionMaster extends BaseMaster implements FamilyMaster {
    protected BaseConditionMaster(JsonNode node){
        super(node);
    }
    protected abstract int getParentKey();

    public long getParentNo() {
        return parentNo;
    }

    public void setParentNo(long parentNo) {
        this.parentNo = parentNo;
    }

    public int getConditionNo() {
        return conditionNo;
    }

    public void setConditionNo(int conditionNo) {
        this.conditionNo = conditionNo;
    }

    protected int conditionNo;
    protected long parentNo;
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
        setParentNo(JsonUtil.getLong(node,JsonKeyString.PARENT,getParentNo()));
        setConditionNo(JsonUtil.getInt(node, JsonKeyString.CONDITION, getConditionNo()));
        setKind(JsonUtil.getInt(node, JsonKeyString.KIND,getKind()));
        setKeyNo(JsonUtil.getLong(node, JsonKeyString.KEY_NO, getKeyNo()));
        setValue(JsonUtil.getLong(node, JsonKeyString.VALUE, getValue()));
        registerToParent();
    }

    @Override
    public ObjectNode toJsonObject() {
        ObjectNode result =  super.toJsonObject();
        result.put(JsonKeyString.PARENT,String.valueOf(getParentNo()));
        result.put(JsonKeyString.CONDITION,String.valueOf(getConditionNo()));
        result.put(JsonKeyString.KIND,String.valueOf(getKind()));
        result.put(JsonKeyString.KEY_NO,String.valueOf(getKeyNo()));
        result.put(JsonKeyString.VALUE,String.valueOf(getValue()));
        return result;
    }

    @Override
    public String getParentName() {
        BaseMasterManager manager = getParentManager();
        if(manager != null){
            return manager.getName();
        }
        return null;
    }

    public BaseMaster getKeyData()
    {
        BaseMasterManager manager = Game.getInstance().getMasterManager(Kind);
        if(manager != null){
            return manager.getMaster(getKeyNo());
        }
        return null;
    }
    public String getKeyName()
    {
        BaseMaster master = getKeyData();
        if(BaseNamedMaster.class.isInstance(master)){
            return ((BaseNamedMaster)master).getName();
        }
        return "-";
    }

    public BaseMasterManager getParentManager()
    {
        return Game.getInstance().getMasterManager(getParentKey());
    }
    public BaseNamedMaster getParent()
    {
        BaseMasterManager manager = getParentManager();
        if(manager != null){
            return (BaseNamedMaster)manager.getMaster(getParentNo());
        }
        return null;
    }
}
