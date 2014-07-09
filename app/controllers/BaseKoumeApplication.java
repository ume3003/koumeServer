package controllers;

import models.data.GameCharacter;
import models.data.Login;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import play.Logger;
import play.libs.Json;
import play.mvc.Controller;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/05/07
 * Time: 10:25
 * To change this template use File | Settings | File Templates.
 */
public class BaseKoumeApplication extends Controller {

    private static JedisPool poolSession = null;
    private static JedisPool poolCompetition = null;
    private static Jedis getCompetitionJedis()
    {
        if(poolCompetition == null){
            poolCompetition = new JedisPool(new JedisPoolConfig(),"dev02.tomoeto.com",6379,300,null,5);
        }
        return poolCompetition.getResource();
    }
    private static void returnCompetitionBrokenJedis(Jedis jedis)
    {
        poolCompetition.returnBrokenResource(jedis);
    }
    private static void returnCompetitionJedis(Jedis jedis)
    {
        poolCompetition.returnResource(jedis);
    }

    private static Jedis getSessionJedis(){
        if(poolSession == null){
            poolSession = new JedisPool(new JedisPoolConfig(), "dev02.tomoeto.com",6379,300,null,4);

        }
        return poolSession.getResource();
    }

    public static void returnSessionBrokenJedis(Jedis jedis)
    {
        poolSession.returnBrokenResource(jedis);
    }

    public static void returnSessionJedis(Jedis jedis)
    {
        poolSession.returnResource(jedis);
    }

    public static ObjectNode getJedisForMatch(String competitionUUID)
    {
        Jedis jedis = getCompetitionJedis();
        ObjectNode node = null;
        try{
            String str = (String)jedis.get(competitionUUID);
            if(str != null && str.length() > 0){
                node = (ObjectNode)Json.parse(str);
            }
        }
        catch(Exception e){
            Logger.debug(e.getMessage());
            returnCompetitionBrokenJedis(jedis);
        }
        finally {
            returnCompetitionJedis(jedis);
        }
        return node;

    }

   public static void setJedisForMatch(String competitionUUID,ObjectNode data)
   {
       Jedis jedis = getCompetitionJedis();
       String str = data.toString();
       try{
           jedis.set(competitionUUID,str);
           jedis.expire(competitionUUID,600);
       }
       catch(Exception e){
           Logger.debug(e.getMessage());
           returnCompetitionBrokenJedis(jedis);
       }
       finally {
           returnCompetitionJedis(jedis);
       }

   }
   public static void removeJedisForMatch(String competitionUUID)
   {
       Jedis jedis = getCompetitionJedis();
       try{
           jedis.del(competitionUUID);
       }
       catch(Exception e){
           Logger.debug(e.getMessage());
           returnCompetitionBrokenJedis(jedis);
       }
       finally {
           returnCompetitionJedis(jedis);
       }

   }
    // フレンドマッチデータを登録する
    public static ObjectNode addFriendMatch(Login target,Login my)
    {
        ObjectNode result = Json.newObject();
        String comUUID = UUID.randomUUID().toString();                  // FriendMatch のキー
        result.put(JsonKeyString.FRIEND_COM_ID,comUUID);
        result.put(JsonKeyString.FRIEND_COM_COUNT, 2);
        result.put(JsonKeyString.FRIEND_ME,my.uuid);
        result.put(JsonKeyString.FRIEND_YOU,target.uuid);
        ObjectNode  registered = Json.newObject();
                    registered.put(my.uuid,my.toPublicJsonObject());
                    registered.put(target.uuid,target.toPublicJsonObject());
        result.put(JsonKeyString.FRIEND_REGISTERED,registered);
        setJedisForMatch(JsonKeyString.FRIEND_INVITE + ":" + my.uuid + ":" + target.uuid,result);// 招待された側。複数持てる
        setJedisForMatch(JsonKeyString.FRIEND_INVITE + ":" + my.uuid + ":" + my.uuid,result);   // 招待した側。招待は一人１つ。
        setJedisForMatch(JsonKeyString.FRIEND_COM    + ":" + comUUID,result);
        return result;
    };
    // フレンドマッチデータをキャンセルする
    public static ObjectNode cancelFriendMatch(Login target,Login my,String comUUID)
    {
        ObjectNode result = getFriendMatch(comUUID);
        removeJedisForMatch(JsonKeyString.FRIEND_INVITE + ":" + my.uuid + ":" + target.uuid);
        removeJedisForMatch(JsonKeyString.FRIEND_INVITE + ":" + my.uuid + ":" + my.uuid);
        removeJedisForMatch(JsonKeyString.FRIEND_COM + ":" + comUUID);
        return result;
    };
    // フレンドマッチを開始する。主催者側
    public static ObjectNode startFriendMatchFromMe(Login my,String comUUID)
    {
        ObjectNode result = getFriendMatch(comUUID);
        removeJedisForMatch(JsonKeyString.FRIEND_INVITE + ":" + my.uuid + ":" + my.uuid);
        return result;
    }
    // フレンドマッチを開始する。被招待者側　
    public static ObjectNode startFriendMatchFromYou(Login my,Login you,String comUUID)
    {
        ObjectNode result = getFriendMatch(comUUID);
        removeJedisForMatch(JsonKeyString.FRIEND_INVITE + ":" + my.uuid + ":" + you.uuid);
        return result;
    }
    // 作成されているフレンドマッチデータを取る
    public static ObjectNode getFriendMatch(String comUUID)
    {
        return getJedisForMatch(JsonKeyString.FRIEND_COM + ":" + comUUID);
    }
    //　自分が招待されているかどうかをとる
    public static ObjectNode getInvitedFriendMatch(Login my , Login tgt)
    {
        return getJedisForMatch(JsonKeyString.FRIEND_INVITE + ":" + my.uuid + ":" + tgt.uuid);
    }
    // 　自分が招待しているかをとる
    public static ObjectNode getInviteFriendMatch(Login my)
    {
        return getJedisForMatch(JsonKeyString.FRIEND_INVITE + ":" + my.uuid + ":" + my.uuid);
    }
/*
    public static ObjectNode getCompetition(String competitionUUID)
    {
        Jedis jedis = getCompetitionJedis();
        ObjectNode node = null;
        try{
            String str = (String)jedis.get(competitionUUID);
            if(str != null && str.length() > 0){
                node = (ObjectNode)Json.parse(str);
            }
        }
        catch(Exception e){
            Logger.debug(e.getMessage());
            returnCompetitionBrokenJedis(jedis);
        }
        finally {
           returnCompetitionJedis(jedis);
        }
        return node;
    };

    public static void setPoolCompetition(String competitionUUID,ObjectNode competition)
    {
        Jedis jedis = getCompetitionJedis();
        String str = competition.toString();
        try{
            jedis.set(competitionUUID,str);
            jedis.expire(competitionUUID,600);
        }
        catch(Exception e){
            Logger.debug(e.getMessage());
            returnCompetitionBrokenJedis(jedis);
        }
        finally {
            returnCompetitionJedis(jedis);
        }
    };
    public static void removeCompetition(String competitionUUID)
    {
        Jedis jedis = getCompetitionJedis();
        try{
            jedis.del(competitionUUID);
        }
        catch(Exception e){
            Logger.debug(e.getMessage());
            returnCompetitionBrokenJedis(jedis);
        }
        finally {
            returnCompetitionJedis(jedis);
        }
    };
    public static ObjectNode getFriendInvite(String uuid)
    {
        return getCompetition(JsonKeyString.FRIEND_INVITE + ":" + uuid);
    };
    public static ObjectNode getFriendCompetition(String uuid)
    {
        return getCompetition(JsonKeyString.FRIEND_COM + ":" + uuid);
    }
    public static ObjectNode addFriendInvite(Login target,Login my)
    {
        ObjectNode result = Json.newObject();
        String comUUID = UUID.randomUUID().toString();
        result.put(JsonKeyString.FRIEND_COM_ID,comUUID);
        result.put(JsonKeyString.FRIEND_COM_COUNT, 2);
        ObjectNode registered = Json.newObject();
            registered.put(my.uuid,my.toPublicJsonObject());
            registered.put(target.uuid,target.toPublicJsonObject());
        result.put(JsonKeyString.FRIEND_REGISTERED,registered);
        setPoolCompetition(JsonKeyString.FRIEND_INVITE + ":" + target.uuid,result);
        setPoolCompetition(JsonKeyString.FRIEND_INVITE + ":" + my.uuid,result);
        setPoolCompetition(JsonKeyString.FRIEND_COM + ":" + comUUID,result);
        return result;
    };
    public static ObjectNode cancelFriendInvite(Login target,Login my,String comUUID)
    {
        ObjectNode result = Json.newObject();
        result.put(JsonKeyString.FRIEND_COM_ID,comUUID);
        removeCompetition(JsonKeyString.FRIEND_INVITE + ":" + target.uuid);
        removeCompetition(JsonKeyString.FRIEND_INVITE + ":" + my.uuid);
        removeCompetition(JsonKeyString.FRIEND_COM + ":" + comUUID);
        return result;
    };
*/
    public static ObjectNode getFromCache(String key)
    {
        Jedis jedis = getSessionJedis();
        ObjectNode node = null;
        try{
            String str = (String)jedis.get(key);
            if(str != null && str.length() > 0){
                node = (ObjectNode) Json.parse(str);
            }
        }
        catch(Exception e){
            returnSessionBrokenJedis(jedis);
        }
        finally {
            returnSessionJedis(jedis);
        }
        return node;
    }
    public static void setToCache(String key,ObjectNode node)
    {
        Jedis jedis = getSessionJedis();
        String str = node.toString();
        try{
            jedis.set(key,str);
            jedis.expire(key,60);
        }
        catch(Exception e){
            returnSessionBrokenJedis(jedis);
        }
        finally {
            returnSessionJedis(jedis);
        }
    }
    public static Login getLoginFromSession(JsonNode req)
    {
        //String sessionId = session(JsonKeyString.SESSION_ID);
        String sessionId = JsonUtil.getString(req, JsonKeyString.SESSION_ID, "");
        if(sessionId.length() == 0){
            Logger.info("no session ");
            sessionId = UUID.randomUUID().toString();
        }
        else{
            Logger.info("use exist session " + sessionId);
        }
        session(JsonKeyString.SESSION_ID,sessionId);
        Logger.info("sessionId " + session(JsonKeyString.SESSION_ID));
        Login login = null;
        ObjectNode v = getFromCache(JsonKeyString.LOGIN + ":" + sessionId);
        if(v != null ){
            login = Login.loadFromCache(v);
        }
        //Login login = (Login) Cache.get(JsonKeyString.LOGIN + ":" + sessionId);
        if(login == null){
            login = Login.gppLogin(req);
            if(login == null){
                login = Login.localLogin(req);
                if(login == null){
                    login = Login.createNewLogin(req);
                    GameCharacter chara = GameCharacter.createGameCharacter();
                    login.gameCharacter = chara;
                    chara.login = login;
                }
            }

        }
        if(login != null){
            ObjectNode node = login.toJsonObject();
            setToCache(JsonKeyString.LOGIN + ":" + sessionId,node);
        }
        Login L = null;
        ObjectNode vv = getFromCache(JsonKeyString.LOGIN + ":" + sessionId);
        if(vv != null ){
            L = Login.loadFromCache(vv);
        }
        if(L != null){
            Logger.info("cache login data " + L.id + " " + L.gppUUID + " " + L.uuid );
        }
        else{
            Logger.info("cache login data error");
        }
        return login;
    }

}
