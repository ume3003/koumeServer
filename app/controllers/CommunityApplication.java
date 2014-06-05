package controllers;

import com.avaje.ebean.Ebean;
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
 * Date: 2014/05/12
 * Time: 11:38
 * To change this template use File | Settings | File Templates.
 */
public class CommunityApplication extends BaseKoumeApplication {

    public static Result friendList()
    {
        ObjectNode result = Json.newObject();
        JsonNode req = JsonUtil.getJsonFromRequest(request());
        Login login = getLoginFromSession(req);
        if(login != null){
            result.put(JsonKeyString.FRIEND,login.friendList());
        }
        result.put(JsonKeyString.SESSION_ID,session(JsonKeyString.SESSION_ID));
        return ok(result);
    }

    public static Result addFriend()
    {
        ObjectNode result = Json.newObject();
        JsonNode req = JsonUtil.getJsonFromRequest(request());
        try{
            Ebean.beginTransaction();
            Login login = getLoginFromSession(req);
            Login friend = Login.findUser(JsonUtil.getString(req,JsonKeyString.MAIL_ADDRESS,""));
            if(login != null && friend != null){
                int friendsCount =login.addFriend(friend);
                if(friendsCount > 0){
//                    login.save();
                    result.put(JsonKeyString.FRIEND,login.friendList());
                }
            }
            Ebean.commitTransaction();
        }
        catch(Exception e){
            Logger.debug(e.getMessage());
            Ebean.rollbackTransaction();
            result.put(JsonKeyString.ERROR, "exception in addFriend " + e.getMessage());
        }
        finally {
            result.put(JsonKeyString.SESSION_ID,session(JsonKeyString.SESSION_ID));
            Ebean.endTransaction();
        }
        return ok(result);
    }

}
