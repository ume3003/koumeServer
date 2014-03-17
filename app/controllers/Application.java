package controllers;

import com.avaje.ebean.Ebean;
import models.data.GameCharacter;
import models.data.Login;
import models.master.DirectionManager;
import models.master.Direction;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import play.*;
import play.cache.Cache;
import play.libs.Json;
import play.mvc.*;

import views.html.index;
import views.html.main;

import java.util.UUID;

public class Application extends Controller {
  
    public static Result index() {
        DirectionManager directionManager = DirectionManager.getInstance();
        long size = directionManager.size();
        for(int i = 0;i < size;i++){
            Direction d = (Direction)directionManager.getMaster(i);
            Logger.info(d.getMasterNo() + " " + d.getName() + " " + d.getDetail() + " " + d.getImage());
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
                result.put(JsonKeyString.GAMECHARACTER,chara.toJsonObject());
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
        return Results.TODO;
    }
}
