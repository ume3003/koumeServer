package models;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/28
 * Time: 19:56
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseConditionOwnerMasterManager extends BaseMasterManager{

    protected HashMap<Integer,BaseConditionMasterManager> Children = new HashMap<>();

    public HashMap<Integer, BaseConditionMasterManager> getChildren() {
        return Children;
    }

    public void addChild(int no, BaseConditionMasterManager manager)
    {
        Children.put(no, manager);
    }
    public BaseConditionMasterManager getConditionManager(int no)
    {
        return Children.get(no);
    }

    public String getConditionName(int no)
    {
        BaseConditionMasterManager manager = getConditionManager(no);
        if(manager != null){
            return manager.getName();
        }
        return "";
    }
}
