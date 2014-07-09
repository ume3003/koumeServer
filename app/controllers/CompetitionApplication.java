package controllers;

import com.avaje.ebean.Ebean;
import models.data.Friend;
import models.data.GameCharacter;
import models.data.Login;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import play.Logger;
import play.libs.Json;
import play.mvc.Result;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/05/07
 * Time: 10:44
 * To change this template use File | Settings | File Templates.
 */
public class CompetitionApplication extends BaseKoumeApplication{

    /*
            対戦用フレンドリストと
            招待データがあればかえします
            FRIEND          array login
            FRIEND_INVITE   array competition data

     */
    public static Result initialFriendCompetition()
    {
        ObjectNode result = Json.newObject();
        JsonNode req = JsonUtil.getJsonFromRequest(request());
        Login login = getLoginFromSession(req);
        if(login != null){
            login = Login.reloadLogin(login);
            result.put(JsonKeyString.FRIEND,login.friendList());
            ArrayNode arr = getFriendInvitedList(login);
            if(arr.size() > 0){
                result.put(JsonKeyString.FRIEND_INVITE,arr);
            }
        }
        result.put(JsonKeyString.SESSION_ID,session(JsonKeyString.SESSION_ID));
        return ok(result);
    }
    /*
        対戦データ、招待データを作成し、かえします
        FRIEND_COM competition data
     */
    public static Result createFriendCompetition()
    {
        ObjectNode result = Json.newObject();
        JsonNode req = JsonUtil.getJsonFromRequest(request());
        Login login = getLoginFromSession(req);
        if(login != null){
            login = Login.reloadLogin(login);
            // 自分がすでに招待してるかチェック
            ObjectNode ownInvite = getInviteFriendMatch(login);
            if(ownInvite == null){
                Login tgt = Login.findUser(JsonUtil.getString(req,JsonKeyString.FRIEND_COM,""));
                if(tgt != null){
                    ownInvite = addFriendMatch(tgt,login);
                }
                else{
                    ownInvite.put(JsonKeyString.ERROR,"no such login data");
                }
            }
            result.put(JsonKeyString.FRIEND_COM,ownInvite);
        }
        result.put(JsonKeyString.SESSION_ID,session(JsonKeyString.SESSION_ID));
        return ok(result);

    }
    /*
        FRIEND_COM competition data
     */
    public static Result getMyFriendCompetition()
    {
        ObjectNode result = Json.newObject();
        JsonNode req = JsonUtil.getJsonFromRequest(request());
        Login login = getLoginFromSession(req);
        if(login != null){
            login = Login.reloadLogin(login);
            // 自分がすでに招待してるかチェック
            ObjectNode ownInvite = getInviteFriendMatch(login);
            result.put(JsonKeyString.FRIEND_COM,ownInvite);
        }
        result.put(JsonKeyString.SESSION_ID,session(JsonKeyString.SESSION_ID));
        return ok(result);

    }
    /*
       招待データを検索し、対戦データをかえします
       FRIEND_INVITE array competition data
     */
    public static Result getFriendCompetition()
    {
        ObjectNode result = Json.newObject();
        JsonNode req = JsonUtil.getJsonFromRequest(request());
        Login login = getLoginFromSession(req);
        if(login != null){
            login = Login.reloadLogin(login);
            ArrayNode arr = getFriendInvitedList(login);
            if(arr.size() > 0){
                result.put(JsonKeyString.FRIEND_INVITE,arr);
            }
        }
        result.put(JsonKeyString.SESSION_ID,session(JsonKeyString.SESSION_ID));
        return ok(result);

    }
    /*
        FRIEND_COM competition data
     */
    public static Result cancelFriendCompetition()
    {
        ObjectNode result = Json.newObject();
        JsonNode req = JsonUtil.getJsonFromRequest(request());
        Login login = getLoginFromSession(req);
        if(login != null){
            login = Login.reloadLogin(login);
            String comUUID = JsonUtil.getString(req,JsonKeyString.FRIEND_COM_ID,"");
            Login tgt = Login.findUser(JsonUtil.getString(req, JsonKeyString.FRIEND_COM, ""));
            if(tgt != null){
                result.put(JsonKeyString.FRIEND_COM, cancelFriendMatch(tgt, login, comUUID));
            }
        }
        result.put(JsonKeyString.SESSION_ID,session(JsonKeyString.SESSION_ID));
        return ok(result);

    }

    /*
        FRIEND_COM competition data
     */
    public static Result startFriendCompetition()
    {
        ObjectNode result = Json.newObject();
        JsonNode req = JsonUtil.getJsonFromRequest(request());
        Login login = getLoginFromSession(req);
        if(login != null){
            login = Login.reloadLogin(login);
            String comUUID = JsonUtil.getString(req,JsonKeyString.FRIEND_COM_ID,"");
            Login tgt = Login.findUser(JsonUtil.getString(req, JsonKeyString.FRIEND_COM, ""));
            if(tgt == null){
                result.put(JsonKeyString.FRIEND_ME,login.uuid);
                result.put(JsonKeyString.FRIEND_COM,startFriendMatchFromMe(login,comUUID));
            }
            else{
                result.put(JsonKeyString.FRIEND_YOU,tgt.uuid);
               result.put(JsonKeyString.FRIEND_COM,startFriendMatchFromYou(tgt,login,comUUID));
            }
        }
        result.put(JsonKeyString.SESSION_ID,session(JsonKeyString.SESSION_ID));
        return ok(result);
    }
    /*
        対戦結果をうけとりDBに記載
     */
    public static Result resultFriendCompetition()
    {
        ObjectNode result = Json.newObject();
        JsonNode req = JsonUtil.getJsonFromRequest(request());
        Login login = getLoginFromSession(req);
        if(login != null){
        }
        return ok(result);
    }

    public static ArrayNode getFriendInvitedList(Login login)
    {
        ArrayNode result = Json.newObject().arrayNode();
        if(login != null){
           for(int i = 0; i < login.friends.size(); i++){
               Friend f = login.friends.get(i);
               ObjectNode ar = getInvitedFriendMatch(f.login,login);
               if(ar != null){
                   result.add(ar);
               }
           }
        }
        return result;
    }
}
