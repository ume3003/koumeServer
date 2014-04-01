package models;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/28
 * Time: 19:56
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseConditionOwnerMasterManager extends BaseMasterManager{

    protected ArrayList<Integer> ChildrenNo = new ArrayList<>();

    public ArrayList<Integer> getChildrenNo() {
        return ChildrenNo;
    }
    public void addChildNo(Integer no)
    {
        if(!ChildrenNo.contains(no)){
            ChildrenNo.add(no);
        }
    }
    public BaseConditionMasterManager getConditionManager(Integer no)
    {
        if(ChildrenNo.contains(no)){
            BaseMasterManager manager = Game.getInstance().getMasterManager(no);
            if(BaseConditionMasterManager.class.isInstance(manager)){
                return (BaseConditionMasterManager)manager;
            }
        }
        return null;
    }

    public String getConditionName(Integer no)
    {
        BaseConditionMasterManager manager = getConditionManager(no);
        if(manager != null){
            return manager.getName();
        }
        return "";
    }
}
