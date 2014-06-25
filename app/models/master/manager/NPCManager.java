package models.master.manager;

import models.*;
import models.master.NPC;
import org.codehaus.jackson.JsonNode;
import play.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/06/23
 * Time: 11:19
 * To change this template use File | Settings | File Templates.
 */
public class NPCManager extends BaseConditionOwnerMasterManager {

    private static NPCManager instance = new NPCManager();
    public static NPCManager getInstance() { return instance;};

    @Override
    public String getName() {
        return Strings.NPC;
    }

    protected NPCManager() {
        Logger.info("NPC Manager created");
        addChildNo(ID.MASTER_NPC_WORDS);
        loadMasterData();
    }

    @Override
    public String getJsonFileName() {
        return Strings.NPC_FILE;
    }

    @Override
    protected String getTableKey() {
        return Strings.NPC;
    }

    @Override
    public BaseMaster createMaster(JsonNode col) {
        return new NPC(col);
    }

    public NPC getNPCMaster(long no)
    {
        return (NPC)getMaster(no);
    }

}
