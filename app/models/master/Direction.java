package models.master;

import models.BaseNamedMaster;
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

    protected Vector<Long> m_majorQuestNos = new Vector<>();
    protected Vector<Long> m_minorQuestNos = new Vector<>();

    public Direction(JsonNode node){
        super(node);
    }
    public Vector<Long> getMajorQuestNos()   { return m_majorQuestNos;};
    public Vector<Long> getMinorQuestNos()    { return m_minorQuestNos;};

    public void addMajorQuestNo(long No)
    {
        m_majorQuestNos.add(No);
    }
    public void addMinorQuestNo(long No)
    {
        m_minorQuestNos.add(No);
    }
}
