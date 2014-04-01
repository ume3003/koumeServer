package models;

import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import play.libs.Json;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/28
 * Time: 11:21
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseConditionMasterManager extends BaseMasterManager{
    public abstract BaseMasterManager getParentMasterManager();
    protected ArrayList<Integer> KindNos = new ArrayList<>();

    public ArrayList<Integer> getKindNos() {
        return KindNos;
    }
    public void addKindNo(Integer no){
        if(!KindNos.contains(no)){
            KindNos.add(no);
        }
    }
    public BaseMasterManager getKindManager(Integer no)
    {
        if(KindNos.contains(no)){
            return Game.getInstance().getMasterManager(no);
        }
        return null;
    }
    public String getKindName(Integer no)
    {
        BaseMasterManager manager = getKindManager(no);
        if(manager != null){
            return manager.getName();
        }
        return "";
    }
    // 種別のマッチングリストを作る
    public ArrayNode makeKindNameMatchList(String term)
    {
        ArrayNode result = Json.newObject().arrayNode();
        Iterator<Integer> it = KindNos.iterator();
        while(it.hasNext()){
            Integer no = it.next();
            String name = getKindName(no);
            if(name.length() > 0 && name.contains(term)){
                ObjectNode o = Json.newObject();
                o.put("label",name);
                o.put("no",no);
                result.add(o);
            }
        }
        return result;
    }
    // 種別に応じたキーアイテムのマッチングリストを作る
    public ArrayNode makeKeyNameMatchList(int kind,String term)
    {
        ArrayNode result = Json.newObject().arrayNode();
        BaseMasterManager manager = getKindManager(kind);
        if(manager != null && manager.getData() != null){
            Set<Long> set = manager.getData().keySet();
            Iterator<Long> it = set.iterator();
            while(it.hasNext()){
                Long i = it.next();
                if(manager.getData() != null){
                    BaseMaster m = manager.getData().get(i);
                    if(m != null && BaseNamedMaster.class.isInstance(m)){
                        String name = ((BaseNamedMaster)m).getName();
                        if(name != null && name.contains(term)){
                            ObjectNode o = Json.newObject();
                            o.put("label",name);
                            o.put("no",i);
                            result.add(o);
                        }
                    }
                }
            }
        }
        return result;

    }

    /*
        親のマスターデータがもっている条件の配列からデータを探し、
        ない場合は新規に本データ列及び、親の条件の配列に追加
        持ち方はユニークIDと親ID+条件内NOがともにユニーク。ただしユニークIDは使用していない。
     */
    public ObjectNode updateAndInsertConditionData(JsonNode json)
    {
        if(json == null){
            throw new RuntimeException("node is null");
        }
        int conditionKey = JsonUtil.getInt(json, JsonKeyString.CONDITION_KEY, -1);
        long parentNo = JsonUtil.getLong(json, JsonKeyString.PARENT, -1);             // 条件をもってる親のNO
        int conditionNo = JsonUtil.getInt(json, JsonKeyString.CONDITION, -1);              // 条件内の番号
        ObjectNode result = Json.newObject();
        if(parentNo >=0 && conditionNo >= 0 && conditionKey >= 0){
            BaseMaster parentMaster = getParentMasterManager().getMaster(parentNo);
            if(parentMaster != null){
                HashMap<Integer,BaseMaster> masters = parentMaster.getConditions(conditionKey);
                BaseMaster master = masters.get(conditionNo);
                if(!masters.containsKey(conditionNo)){
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

    public void registerAllCondition()
    {
        HashMap<Long,BaseMaster> data = getData();
        Set<Long> keySet = data.keySet();
        Iterator<Long> it = keySet.iterator();
        while(it.hasNext()){
            long key = it.next();
            BaseConditionMaster master = (BaseConditionMaster)data.get(key);
            master.registerToParent();
        }
    }
}
