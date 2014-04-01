package models.master;

import models.BaseNamedMaster;
import models.ID;
import org.codehaus.jackson.JsonNode;

import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/13
 * Time: 10:09
 * To change this template use File | Settings | File Templates.
 */
public class Direction extends BaseNamedMaster {

    protected Vector<MajorQuest> MajorQuests = new Vector<>();
    protected Vector<MinorQuest> MinorQuests = new Vector<>();

    @Override
    public int getMasterKey() {
        return ID.MASTER_DIRECTION;
    }

    public Direction(JsonNode node){
        super(node);
    }
    public Vector<MajorQuest> getMajorQuestNos()   { return MajorQuests;};
    public Vector<MinorQuest> getMinorQuestNos()    { return MinorQuests;};

    public void addMajorQuest(MajorQuest quest)
    {
        MajorQuests.add(quest);
    }
    public void addMinorQuest(MinorQuest quest)
    {
        MinorQuests.add(quest);
    }
}
