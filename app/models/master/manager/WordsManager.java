package models.master.manager;

import models.BaseMaster;
import models.BaseMasterManager;
import models.Strings;
import models.master.Words;
import org.codehaus.jackson.JsonNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/06/24
 * Time: 17:53
 * To change this template use File | Settings | File Templates.
 */
public class WordsManager extends BaseMasterManager{
    private static WordsManager instance = new WordsManager();
    public static WordsManager getInstance() { return instance;};

    @Override
    public BaseMaster createMaster(JsonNode col) {
        return new Words(col);
    }

    @Override
    public String getName() {
        return Strings.WORDS;
    }

    @Override
    public String getJsonFileName() {
        return Strings.WORDS_FILE;
    }

    @Override
    protected String getTableKey() {
        return Strings.WORDS;
    }

    protected WordsManager() {
        loadMasterData();;
    }
}
