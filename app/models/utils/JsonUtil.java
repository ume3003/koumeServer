package models.utils;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ArrayNode;
import play.mvc.Http;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/13
 * Time: 10:11
 * To change this template use File | Settings | File Templates.
 */
public class JsonUtil {

    public static JsonNode getJsonFromRequest(Http.Request request)
    {
        if(request != null){
            Http.RequestBody body = request.body();
            if(body != null){
                return  body.asJson();
            }
        }
        return null;
    }
    public static int getInt(JsonNode node,String key,int defaultValue)
    {
        if(node != null){
           JsonNode o = node.findPath(key);
            if(o != null){
                return o.getIntValue();
            }
        }
        return defaultValue;
    }

    public static long getLong(JsonNode node,String key,long defaultValue)
    {
        if(node != null){
            JsonNode o = node.findPath(key);
            if(o != null){
                return o.getLongValue();
            }
        }
        return defaultValue;
    }
    public static String getString(JsonNode node,String key,String defaultValue)
    {
        if(node != null){
            JsonNode o = node.findPath(key);
            if(o != null && !o.isMissingNode()){
                return o.getTextValue();
            }
        }
        return defaultValue;
    }

    public static ArrayNode getArray(JsonNode node,String key){
        if(node != null){
            JsonNode o = node.findPath(key);
            if(o != null && o.isArray()){
                return (ArrayNode)o;
            }
        }
        return null;
    }
}
