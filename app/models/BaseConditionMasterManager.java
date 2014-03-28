package models;

import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import play.libs.Json;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/28
 * Time: 11:21
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseConditionMasterManager extends BaseMasterManager{
    public abstract BaseMasterManager getParentMasterManager();
    protected HashMap<Integer,BaseMasterManager> Kinds = new HashMap<>();

    public HashMap<Integer, BaseMasterManager> getKinds() {
        return Kinds;
    }
    public void addKind(int no,BaseMasterManager manager)
    {
        Kinds.put(no,manager);
    }
    // 種別のマッチングリストを作る
    public ArrayNode makeKindNameMatchList(String term)
    {
        ArrayNode result = Json.newObject().arrayNode();
        Set<Integer> set = Kinds.keySet();
        Iterator<Integer> it = set.iterator();
        while(it.hasNext()){
            Integer i = it.next();
            BaseMasterManager m = Kinds.get(i);
            if(m != null){
                String name = m.getName();
                if(name != null && name.contains(term)){
                    ObjectNode o = Json.newObject();
                    o.put("label",name);
                    o.put("no",i);
                    result.add(o);
                }
            }
        }
        return result;
    }
    // 種別に応じたキーアイテムのマッチングリストを作る
    public ArrayNode makeKeyNameMatchList(int kind,String term)
    {
        ArrayNode result = Json.newObject().arrayNode();
        Set<Long> set = Game.getInstance().getMasterManager(kind).getData().keySet();
        Iterator<Long> it = set.iterator();
        while(it.hasNext()){
            Long i = it.next();
            BaseMasterManager m = Kinds.get(i);
            if(m != null){
                String name = m.getName();
                if(name != null && name.contains(term)){
                    ObjectNode o = Json.newObject();
                    o.put("label",name);
                    o.put("no",i);
                    result.add(o);
                }
            }
        }
        return result;

    }

    public ObjectNode updateAndInsertConditionData(JsonNode json)
    {
        if(json == null){
            throw new RuntimeException("node is null");
        }
        int conditionType = JsonUtil.getInt(json, JsonKeyString.CONDITION_TYPE, -1);
        long parentNo = JsonUtil.getLong(json, JsonKeyString.PARENT, -1);             // 条件をもってる親のNO
        int conditionNo = JsonUtil.getInt(json, JsonKeyString.CONDITION, -1);              // 条件内の番号
        ObjectNode result = Json.newObject();
        if(parentNo >=0 && conditionNo >= 0 && conditionType >= 0){
            BaseMaster parentMaster = getParentMasterManager().getMaster(parentNo);
            if(parentMaster != null){
                HashMap<Integer,BaseMaster> masters = parentMaster.getConditions(conditionType);
                BaseMaster master = masters.get(conditionNo);
                if(master == null){
                    master = createMaster(json);
                    master.setMasterNo(size());
                    setMaster(master.getMasterNo(), master);
                    masters.put(conditionNo,master);
                }
                else{
                    master.setData(json);
                }
                saveMasterData();

            }
            result.put(JsonKeyString.SUCCESS,0);
            return result;
        }
        result.put(JsonKeyString.SUCCESS,1);
        return result;

    }
}
