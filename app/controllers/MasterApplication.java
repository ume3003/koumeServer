package controllers;

import models.*;
import models.master.Direction;
import models.master.manager.DirectionManager;
import models.master.manager.MajorQuestManager;
import models.master.manager.MinorQuestManager;
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
        return ok(index.render(Game.getInstance().getData()));
    }

    public static Result list(int key) {
        BaseMasterManager masterManager = Game.getInstance().getMasterManager(key);
        switch(key){
            case ID.MASTER_UNIT:
            case ID.MASTER_MAJOR_QUEST:
                return ok(views.html.MajorQuest.render(key, MajorQuestManager.getInstance(), Game.getInstance().getData()));
            case ID.MASTER_MINOR_QUEST:
                return ok(views.html.MinorQuest.render(key, MinorQuestManager.getInstance(), Game.getInstance().getData()));
            default:
                break;
        }
        return ok(list.render(key, masterManager, Game.getInstance().getData()));
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
                FamilyMaster m = (FamilyMaster)namedMasters.get(k);
                if(m != null && m.getParentNo() == parentKey){
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
        return ok(result);
    }
}
