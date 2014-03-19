import models.Game;
import models.master.Direction;
import models.master.DirectionManager;
import models.utils.JsonKeyString;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.node.ObjectNode;
import play.Application;
import play.GlobalSettings;
import play.libs.Json;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/13
 * Time: 14:07
 * To change this template use File | Settings | File Templates.
 */
public class Global extends GlobalSettings {

    @Override
    public void onStart(Application app) {
        super.onStart(app);    //To change body of overridden methods use File | Settings | File Templates.
        Game.getInstance().init();

    }
}
