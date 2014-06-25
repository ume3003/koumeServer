package models.master;

import models.BaseMaster;
import models.BaseNamedMaster;
import models.ID;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/06/23
 * Time: 11:09
 * To change this template use File | Settings | File Templates.
 */
public class NPC extends BaseNamedMaster{
    protected long HP;
    protected long Level;


    @Override
    public void setData(JsonNode node) {

        super.setData(node);
        HP = JsonUtil.getLong(node, JsonKeyString.HP, -1);
        Level = JsonUtil.getLong(node, JsonKeyString.LEVEL,-1);
    }

    @Override
    public ObjectNode toJsonObject() {
        ObjectNode result = super.toJsonObject();
        result.put(JsonKeyString.HP,String.valueOf(getHP()));
        result.put(JsonKeyString.LEVEL,String.valueOf(getLevel()));
        return result;
    }

    public long getHP() {
        return HP;
    }

    public void setHP(long HP) {
        this.HP = HP;
    }

    public long getLevel() {
        return Level;
    }

    public void setLevel(long level) {
        Level = level;
    }

    public NPC(JsonNode node) {
        super(node);
        setConditions(ID.MASTER_NPC_WORDS,npcWords);
    }

    @Override
    public int getMasterKey() {
        return ID.MASTER_NPC_DATA;

    }

    protected HashMap<Integer, BaseMaster> npcWords = new HashMap<>();
    public HashMap<Integer,BaseMaster> getNPCWords() {
        return npcWords;
    }
    public void addNPCWords(NPCWords words)
    {
        npcWords.put(words.getConditionNo(),words);
    }
}
