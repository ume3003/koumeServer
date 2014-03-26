package controllers;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/26
 * Time: 20:38
 * To change this template use File | Settings | File Templates.
 */
public class MasterApplication extends Controller
{
    public static Result index()
    {
        return ok(index.render(""));
    }
}
