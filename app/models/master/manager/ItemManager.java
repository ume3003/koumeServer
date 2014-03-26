package models.master.manager;

import models.BaseMaster;
import models.BaseMasterManager;
import models.Strings;
import models.master.Item;
import models.utils.JsonKeyString;
import org.codehaus.jackson.JsonNode;
import play.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/26
 * Time: 15:19
 * To change this template use File | Settings | File Templates.
 */
public class ItemManager extends BaseMasterManager{
    private static ItemManager instance = new ItemManager();
    public static ItemManager getInstance() { return instance;};
    protected ItemManager()
    {
        Logger.info("Item created");
        loadMasterData();
    }
    @Override
    protected String getJsonFileName() {
        return Strings.ITEM_FILE;

    }

    @Override
    protected String getTableKey() {
        return JsonKeyString.ITEM;
    }

    @Override
    public BaseMaster createMaster(JsonNode col) {
        return new Item(col);
    }
    public Item getItem(long no)
    {
        return (Item)getMaster(no);
    }
}
