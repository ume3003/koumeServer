package models;

import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;
import play.Logger;
import play.libs.Json;
import java.io.*;

import java.io.File;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/13
 * Time: 11:15
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseMasterManager {


    protected long Version;
    protected JsonNode Node;
    protected HashMap<Long,BaseMaster> Data = new HashMap<Long,BaseMaster>();

    public JsonNode getNode()       { return Node;}
    public void setNode(JsonNode node)  { Node = node;}
    public long getVersion()        { return Version;}

    public HashMap<Long, BaseMaster> getData() {
        return Data;
    }
    public void setVersion(long v)  { Version = v;}

    public BaseMaster getMaster(long no) { return Data.get(no);}

    public void setMaster(long no,BaseMaster master)
    {
        Data.put(no,master);
    }

    public long size()
    {
        return Data.size();
    }
    public abstract String getName();
    public abstract String getJsonFileName();
    protected abstract String getTableKey();
    public abstract  BaseMaster createMaster(JsonNode col);

    public void loadMasterData()
    {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File(getJsonFileName());
        if(file.exists()){
            try{
                JsonNode node = mapper.readValue(file, JsonNode.class);
                Logger.info("loading " + getJsonFileName());
                loadFromJson(node);
            }
            catch(Exception e){
                Logger.debug("load error " + e.getMessage());
                throw new RuntimeException(e);
            }
            finally {
            }
        }
        else{
            Logger.debug("file not found");
        }
    }

    public void saveMasterData()
    {
        setVersion(System.currentTimeMillis() / 1000);
        ObjectNode result = toJsonObject();
        File file = new File(getJsonFileName());
        PrintWriter pw = null;
        try{
            if(!file.exists()){
                file.createNewFile();
            }
            pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(pw, result);
        }
        catch(IOException e){
            Logger.info(e.getMessage());
        }
        finally {
            if(pw != null){
                pw.close();
            }
        }
    }
    public void loadFromJson(JsonNode node)
    {
        if(node == null){
            throw new RuntimeException("node is null");
        }
        setNode(node.get(getTableKey()));
        setVersion(JsonUtil.getLong(node, JsonKeyString.VERSION,0L));
        ArrayNode array = JsonUtil.getArray(node,JsonKeyString.DATA);
        for(int i = 0 ; i < array.size() ;i++){
            JsonNode col = array.get(i);
            BaseMaster m = createMaster(col);
            setMaster(i,m);
        }
    }

    public ObjectNode toJsonObject()
    {
        ObjectNode result = Json.newObject();
        ArrayNode array = Json.newObject().arrayNode();
        result.put(JsonKeyString.VERSION, String.valueOf(getVersion()));
        for(java.util.Map.Entry<Long,BaseMaster> e:Data.entrySet()){
            BaseMaster m = e.getValue();
            array.add(m.toJsonObject());
        }
        result.put(JsonKeyString.DATA,array);
        return result;
    }
    public ObjectNode updateAndInsertData(JsonNode json)
    {
        if(json == null){
            throw new RuntimeException("node is null");
        }
        long no = JsonUtil.getLong(json,JsonKeyString.NO,-1);
        ObjectNode result = Json.newObject();
        if(no >=0){
            BaseMaster master = getMaster(no);
            if(master == null){
               master = createMaster(json);
               setMaster(no,master);
            }
            else{
                master.setData(json);
            }
            saveMasterData();
            result.put(JsonKeyString.SUCCESS,0);
            return result;
        }
        result.put(JsonKeyString.SUCCESS,1);
        return result;
    }
}
