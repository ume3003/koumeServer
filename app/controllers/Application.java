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
import play.cache.Cache;
import play.libs.Json;
import play.mvc.*;

import views.html.index;

import java.util.UUID;

public class Application extends Controller {
  
    public static Result index() {
        for(int i = 0; i < ID.MASTER_COUNT;i++){
            BaseMasterManager manager = Game.getInstance().getMasterManager(i);
            if(manager != null){
                Logger.info("master " + i + " version " + manager.getVersion());
                long size = manager.size();
                for(int j = 0 ;j < size;j++){
                    BaseMaster master = manager.getMaster(j);
                    if(master != null){
                        Logger.info(master.toJsonObject().toString());
                    }
                }
            }
        }

        return ok(index.render("Your new application is ready."));
    }

    public static Login getLoginFromSession(JsonNode req)
    {
        //String sessionId = session(JsonKeyString.SESSION_ID);
        String sessionId = JsonUtil.getString(req,JsonKeyString.SESSION_ID,"");
        if(sessionId.length() == 0){
            sessionId = UUID.randomUUID().toString();
        }
        else{
            Logger.info("use exist session " + sessionId);
        }
        session(JsonKeyString.SESSION_ID,sessionId);
        Logger.info("sessionId " + session(JsonKeyString.SESSION_ID));
        Login login = (Login) Cache.get(sessionId + JsonKeyString.LOGIN);
        if(login == null){
            login = Login.gppLogin(req);
            if(login == null){
                login = Login.localLogin(req);
                if(login == null){
                    login = Login.createNewLogin(req);
                    GameCharacter chara = GameCharacter.createGameCharacter();
                    login.gameCharacter = chara;
                    chara.login = login;
                    login.save();
                    chara.save();
                }
            }

        }
        if(login != null){
            Cache.set(sessionId + JsonKeyString.LOGIN,login);
        }
        Login L = (Login)(Cache.get(sessionId + JsonKeyString.LOGIN));
        Logger.info("cache login data " + L.id + " " + L.gppUUID + " " + L.uuid );
        return login;
    }
    public static Result login() {
        ObjectNode result = Json.newObject();
        JsonNode req = JsonUtil.getJsonFromRequest(request());
        try{
            Ebean.beginTransaction();
            Login login = getLoginFromSession(req);
            if(login != null){
                GameCharacter chara = login.gameCharacter;
                result.put(JsonKeyString.SESSION_ID,session(JsonKeyString.SESSION_ID));
                result.put(JsonKeyString.UUID,login.uuid);
                result.put(JsonKeyString.GAME_CHARACTER,chara.toJsonObject());
                result.put(JsonKeyString.UNIX_TIME,String.valueOf(System.currentTimeMillis() / 1000));
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
}
