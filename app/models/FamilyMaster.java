package models;

import org.codehaus.jackson.JsonNode;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/27
 * Time: 18:23
 * To change this template use File | Settings | File Templates.
 */
public interface FamilyMaster {
    public abstract long getParentNo();
    public abstract String getParentName();
    public abstract BaseNamedMaster getParent();

}
