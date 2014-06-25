package models.master;

import models.BaseConditionMaster;
import models.ID;
import models.utils.JsonKeyString;
import models.utils.JsonUtil;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/06/24
 * Time: 17:52
 * To change this template use File | Settings | File Templates.
 */
public class NPCWords extends BaseConditionMaster {
    public NPCWords(JsonNode node) {
        super(node);
    }
    private String Word;
    private String Image;

    public String getImage() {
        return Image;
    }

    @Override
    public void setData(JsonNode node) {
        setWord(JsonUtil.getString(node, JsonKeyString.WORD,""));
        setImage(JsonUtil.getString(node,JsonKeyString.IMAGE,""));
        super.setData(node);
    }

    @Override
    public ObjectNode toJsonObject() {
        ObjectNode result = super.toJsonObject();
        result.put(JsonKeyString.WORD,getWord());
        result.put(JsonKeyString.IMAGE,getImage());
        return result;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getWord() {
        return Word;
    }

    public void setWord(String word) {
        Word = word;
    }

    @Override
    protected int getParentKey() {
        return ID.MASTER_NPC_DATA;
    }

    @Override
    protected void registerToParent() {
        NPC npc = (NPC)getParent();
        if(npc != null){
            npc.addNPCWords(this);
        }
    }

    @Override
    public int getMasterKey() {
        return ID.MASTER_NPC_WORDS;
    }
}
