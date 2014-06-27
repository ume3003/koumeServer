package models.master.manager;

import models.*;
import models.master.NPCWords;
import models.utils.JsonKeyString;
import org.codehaus.jackson.JsonNode;
import play.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/06/24
 * Time: 17:52
 * To change this template use File | Settings | File Templates.
 */
public class NPCWordsManager extends BaseConditionMasterManager {
    private static NPCWordsManager instance = new NPCWordsManager();
    public static NPCWordsManager getInstance() { return instance;};

    protected NPCWordsManager(){
        Logger.info("NPC Words created");
        loadMasterData();
        addKindNo(ID.MASTER_WORDS);
    }
    @Override
    public BaseMasterManager getParentMasterManager() {
        return Game.getInstance().getMasterManager(ID.MASTER_NPC_DATA);
    }

    @Override
    public String getName() {
        return Strings.NPC_WORDS;
    }

    @Override
    public String getJsonFileName() {
        return Strings.NPC_WORDS_FILE;
    }

    @Override
    protected String getTableKey() {
        return JsonKeyString.NPC_WORDS;
    }

    @Override
    public BaseMaster createMaster(JsonNode col) {
        return new NPCWords(col);
    }

    public NPCWords getNPCWords(long no)
    {
        return (NPCWords)getMaster(no);
    }
}
