package models.master.manager;

import models.BaseMaster;
import models.BaseMasterManager;
import models.Strings;
import models.master.Rank;
import models.utils.JsonKeyString;
import org.codehaus.jackson.JsonNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/28
 * Time: 17:16
 * To change this template use File | Settings | File Templates.
 */
public class RankManager extends BaseMasterManager{
    private static RankManager instance = new RankManager();
    public static RankManager getInstance() { return instance;};
    @Override
    public String getName() {
        return Strings.RANK;
    }

    @Override
    public String getJsonFileName() {
        return Strings.RANK_FILE;
    }

    @Override
    protected String getTableKey() {
        return JsonKeyString.RANK;
    }

    @Override
    public BaseMaster createMaster(JsonNode col) {
        return new Rank(col);
    }

    protected RankManager() {
        loadMasterData();
    }
}
