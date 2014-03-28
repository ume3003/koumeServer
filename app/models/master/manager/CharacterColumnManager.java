package models.master.manager;

import models.BaseMaster;
import models.BaseMasterManager;
import models.Strings;
import models.master.CharacterColumn;
import models.utils.JsonKeyString;
import org.codehaus.jackson.JsonNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/28
 * Time: 17:20
 * To change this template use File | Settings | File Templates.
 */
public class CharacterColumnManager extends BaseMasterManager{
    private static CharacterColumnManager instance = new CharacterColumnManager();
    public  static CharacterColumnManager getInstance() { return instance;};
    @Override
    public String getName() {
        return Strings.CHARACTER_COLUMN;
    }

    @Override
    public String getJsonFileName() {
        return Strings.CHARACTER_COLUMN_FILE;
    }

    @Override
    protected String getTableKey() {
        return JsonKeyString.CHARACTER_COLUMN;
    }

    @Override
    public BaseMaster createMaster(JsonNode col) {
        return new CharacterColumn(col);
    }

    protected CharacterColumnManager() {
        loadMasterData();
    }
}
