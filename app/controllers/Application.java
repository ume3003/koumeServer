package controllers;

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
        String sessionId = session(JsonKeyString.SESSION_ID);
        if(sessionId == null){
            sessionId = UUID.randomUUID().toString();
            session(JsonKeyString.SESSION_ID,sessionId);
        }
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
                    chara.save();
                    login.save();
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
        Login login = getLoginFromSession(req);
        if(login != null){
            GameCharacter chara = login.gameCharacter;
            result.put(JsonKeyString.SESSION,session(JsonKeyString.SESSION_ID));
            result.put(JsonKeyString.UUID,login.uuid);
            result.put(JsonKeyString.GAMECHARACTER,chara.toJsonObject());
        }

        return ok(result);
    }
}
