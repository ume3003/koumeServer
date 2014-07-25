package controllers;

import com.avaje.ebean.Ebean;
import models.*;
import models.data.GameCharacter;
import models.data.Login;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import play.*;
import play.libs.Json;
import play.mvc.*;


public class Application extends BaseKoumeApplication {

    public static Result login() {
        ObjectNode result = Json.newObject();
        JsonNode req = JsonUtil.getJsonFromRequest(request());
        try{
            Ebean.beginTransaction();
            Login login = getLoginFromSession(req);
            if(login != null){
                GameCharacter chara = login.gameCharacter;
                login.accessCnt = login.accessCnt + 1;
                login.save();
                chara.save();
                result.put(JsonKeyString.SESSION_ID,session(JsonKeyString.SESSION_ID));
                result.put(JsonKeyString.UUID,login.uuid);
                result.put(JsonKeyString.GAME_CHARACTER,chara.toJsonObject());
                result.put(JsonKeyString.UNIX_TIME,String.valueOf(System.currentTimeMillis() / 1000));
                result.put(JsonKeyString.ID,String.valueOf((login.id)));
            }
            Ebean.commitTransaction();
        }
        catch(RuntimeException e){
            result.put(JsonKeyString.ERROR, "exception in login " + e.getMessage());
            Logger.info("exception in login() " + e.getMessage());
            Ebean.rollbackTransaction();
        }
        finally
        {
            Ebean.endTransaction();
        }
        return ok(result);
    }

    public static Result master() {
        ObjectNode result = Json.newObject();
        JsonNode req = JsonUtil.getJsonFromRequest(request());
        Login login = getLoginFromSession(req);
        if(req != null && login != null){
            int masterNo = JsonUtil.getInt(req,JsonKeyString.MASTER_NO,-1);
            result.put(JsonKeyString.MASTER_NO,String.valueOf(masterNo));
            result.put(JsonKeyString.SESSION_ID,session(JsonKeyString.SESSION_ID));
            if(masterNo >= 0){
                Game game = Game.getInstance();
                BaseMasterManager manager = game.getMasterManager(masterNo);
                if(manager != null){
                    result.putAll(manager.toJsonObject());
                    return ok(result);
                }
            }
        }
        result.put(JsonKeyString.ERROR,"request is bad");
        return ok(result);
    }

    public static Result masterVersion() {
        ObjectNode result = Json.newObject();
        JsonNode req = JsonUtil.getJsonFromRequest(request());
        Login login = getLoginFromSession(req);
        if(req != null && login != null){
            result.put(JsonKeyString.SESSION_ID,session(JsonKeyString.SESSION_ID));
            Game game = Game.getInstance();
            result.putAll(game.getMasterVersions());
            return ok(result);
        }
        result.put(JsonKeyString.ERROR,"request is bad");
        return ok(result);
    }

    public static Result checkTime() {
        ObjectNode result = Json.newObject();
        JsonNode req = JsonUtil.getJsonFromRequest(request());
        Login login = getLoginFromSession(req);
        if(req != null && login != null){
            result.put(JsonKeyString.UNIX_TIME,String.valueOf(System.currentTimeMillis()/1000L ));
            result.put(JsonKeyString.SESSION_ID,session(JsonKeyString.SESSION_ID));
        }
        return ok(result);
    }
    public static Result puzzleInit()
    {
        ObjectNode result = Json.newObject();
        JsonNode req = JsonUtil.getJsonFromRequest(request());
        Login login = getLoginFromSession(req);
        if(req != null && login != null){
            if(login.gameCharacter == null){
                login = Login.reloadLogin(login);
                Logger.info("character is null");
            }
            GameCharacter chara = login.gameCharacter;
            if(chara == null){
                Logger.info("character is null");
            }
            result.put(JsonKeyString.RANDOM_SEED,String.valueOf(System.currentTimeMillis()/1000L ));
            result.put(JsonKeyString.SESSION_ID,session(JsonKeyString.SESSION_ID));
        }
        return ok(result);

    }
}
