package controllers;

import models.*;
import models.master.Direction;
import models.master.manager.*;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;
import views.html.list;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/26
 * Time: 20:38
 * To change this template use File | Settings | File Templates.
 */
public class MasterApplication extends Controller
{
    public static Result index()
    {
        return ok(index.render(Game.getInstance().getMenu()));
    }

    public static Result list(int key) {
        BaseMasterManager masterManager = Game.getInstance().getMasterManager(key);
        switch(key){
            case ID.MASTER_MAJOR_QUEST:
                return ok(views.html.MajorQuest.render(key, MajorQuestManager.getInstance(), Game.getInstance().getMenu()));
            case ID.MASTER_MINOR_QUEST:
                return ok(views.html.MinorQuest.render(key, MinorQuestManager.getInstance(), Game.getInstance().getMenu()));
            case ID.MASTER_CONDITION_KIND:
                return ok(views.html.Kind.render(key, KindManager.getInstance(), Game.getInstance().getMenu()));
            case ID.MASTER_UNIT:
                return ok(views.html.Unit.render(key, UnitManager.getInstance(),Game.getInstance().getMenu()));
            case ID.MASTER_SCENARIO:
                return ok(views.html.Scenario.render(key,ScenarioManager.getInstance(),Game.getInstance().getMenu()));
            default:
                break;
        }
        return ok(list.render(key, masterManager, Game.getInstance().getMenu()));
    }

    public static Result condition(int parentKey,int conditionKey,int parentNo) {
        BaseMasterManager parentMasterManager = Game.getInstance().getMasterManager(parentKey);
        BaseNamedMaster parentMaster = (BaseNamedMaster)parentMasterManager.getMaster(parentNo);
        Result result = ok("");
        switch(parentKey){
            case ID.MASTER_MINOR_QUEST:
            case ID.MASTER_SCENARIO:
            case ID.MASTER_UNIT_SKILL:
                result = ok(views.html.Condition.render(parentKey,conditionKey,parentNo,parentMaster, Game.getInstance().getMenu()));
            default:
                break;
        }
        return result;
    }
    public static Result json(int key) {
        BaseMasterManager masterManager = Game.getInstance().getMasterManager(key);
        return ok(masterManager.toJsonObject());
    }
    public static Result updateMaster()
    {
        JsonNode json = JsonUtil.getJsonFromRequest(request());
        int key = JsonUtil.getInt(json, "master", -1);
        long no = JsonUtil.getLong(json, JsonKeyString.NO, -1);
        BaseMasterManager manager = Game.getInstance().getMasterManager(key);
        ObjectNode result = manager.updateAndInsertData(json);
        return ok(result);
    }
    public static Result conditionUpdate()
    {
        JsonNode json = JsonUtil.getJsonFromRequest(request());
        int parentKey = JsonUtil.getInt(json,JsonKeyString.PARENT_KEY,-1);
        int conditionKey = JsonUtil.getInt(json,JsonKeyString.CONDITION_KEY,-1);

        BaseConditionOwnerMasterManager parentManager = (BaseConditionOwnerMasterManager)Game.getInstance().getMasterManager(parentKey);
        BaseConditionMasterManager conditionManager = parentManager.getConditionManager(conditionKey);
        Logger.info("parentKey " + parentKey + "-" + parentManager.getName() + " conditionKey " + conditionKey + "-" + conditionManager.getName());
        ObjectNode result = conditionManager.updateAndInsertConditionData(json);
        return ok(result);
    }
    public static Result conditionDelete()
    {
        JsonNode json = JsonUtil.getJsonFromRequest(request());
        Logger.info(json.toString());
        return ok(json.toString());
    }
    public static Result kindList(int editMasterKey,String term)
    {
        BaseConditionMasterManager manager = (BaseConditionMasterManager)Game.getInstance().getMasterManager(editMasterKey);
        ArrayNode result = Json.newObject().arrayNode();
        if(manager != null){
            result = manager.makeKindNameMatchList(term);
        }
        return ok(result);
    }
    public static Result keyList(int editMasterKey,int kind,String term)
    {
        BaseConditionMasterManager manager = (BaseConditionMasterManager)Game.getInstance().getMasterManager(editMasterKey);
        ArrayNode result = Json.newObject().arrayNode();
        if(manager != null){
            result = manager.makeKeyNameMatchList(kind,term);
        }
        return ok(result);

    }
    public static Result masterSub(int key,int parentKey,String term)
    {
        BaseMasterManager manager = Game.getInstance().getMasterManager(key);
        ArrayNode result = Json.newObject().arrayNode();
        if(manager != null){
            HashMap<Long, BaseMaster> namedMasters = manager.getData();
            Set<Long> set = namedMasters.keySet();
            Iterator<Long> it = set.iterator();
            while (it.hasNext()){
                Long k = it.next();
                BaseNamedMaster m = (BaseNamedMaster)namedMasters.get(k);
                if(m != null && ((FamilyMaster)m).getParentNo() == parentKey){
                    String name = m.getName();
                    if(name != null && name.contains(term)){
                        ObjectNode o = Json.newObject();
                        o.put("label",name);
                        o.put("no",k);
                        result.add(o);
                    }
                }
            }
        }
        return ok(result);
    }
    public static Result master(int key,String term)
    {
        BaseMasterManager manager = Game.getInstance().getMasterManager(key);
        ArrayNode result = Json.newObject().arrayNode();
        if(manager != null){
            HashMap<Long, BaseMaster> namedMasters = manager.getData();
            Set<Long> set = namedMasters.keySet();
            Iterator<Long> it = set.iterator();
            while (it.hasNext()){
                Long k = it.next();
                BaseNamedMaster m = (BaseNamedMaster)namedMasters.get(k);
                if(m != null){
                    String name = m.getName();
                    if(name != null && name.contains(term)){
                        ObjectNode o = Json.newObject();
                        o.put("label",name);
                        o.put("no",k);
                        result.add(o);
                    }
                }
            }
        }
        else{
            Set<Integer> set = Game.getInstance().getMenu().keySet();
            Iterator<Integer> it = set.iterator();
            while(it.hasNext()){
                Integer i = it.next();
                BaseMasterManager m = Game.getInstance().getMenu().get(i);
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

        }
        return ok(result);
    }
}
