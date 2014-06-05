package controllers;

import com.avaje.ebean.Ebean;
import models.data.GameCharacter;
import models.data.Login;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
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
     */
    public static Result initialFriendCompetition()
    {
        ObjectNode result = Json.newObject();
        JsonNode req = JsonUtil.getJsonFromRequest(request());
        Login login = getLoginFromSession(req);
        if(login != null){
            result.put(JsonKeyString.FRIEND,login.friendList());
            result.put(JsonKeyString.FRIEND_INVITE,getFriendInvite(login.uuid));
        }
        return ok(result);
    }
    /*
        対戦データ、招待データを作成し、かえします
     */
    public static Result createFriendCompetition()
    {
        ObjectNode result = Json.newObject();
        JsonNode req = JsonUtil.getJsonFromRequest(request());
        Login login = getLoginFromSession(req);
        if(login != null){
            Login tgt = Login.findUser(JsonUtil.getString(req,JsonKeyString.FRIEND_COM,""));
            if(tgt != null){
                ObjectNode compData = getFriendInvite(login.uuid);
                if(compData == null){
                    compData = addFriendInvite(tgt,login);
                }
                else{
                    compData.put(JsonKeyString.ERROR,"already has");
                }
                result.put(JsonKeyString.FRIEND_COM,compData);
            }
        }
        return ok(result);

    }
    /*
       招待データを検索し、対戦データをかえします
     */
    public static Result getFriendCompetition()
    {
        ObjectNode result = Json.newObject();
        JsonNode req = JsonUtil.getJsonFromRequest(request());
        Login login = getLoginFromSession(req);
        if(login != null){
            result.put(JsonKeyString.FRIEND_COM_ID,getFriendInvite(login.uuid));
        }
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


}
