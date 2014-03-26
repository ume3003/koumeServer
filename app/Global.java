import models.Game;
import play.Application;
import play.GlobalSettings;

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
