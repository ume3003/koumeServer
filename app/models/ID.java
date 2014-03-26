package models;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/18
 * Time: 9:28
 * To change this template use File | Settings | File Templates.
 */
public class ID {
    public static final int MASTER_DIRECTION = 0;
    public static final int MASTER_MAJOR_QUEST = 1;
    public static final int MASTER_MINOR_QUEST = 2;
    public static final int MASTER_MAP = 3;
    public static final int MASTER_UNIT = 4;
    public static final int MASTER_ITEM = 5;
    public static final int MASTER_CONDITION = 6;

    public static final int MASTER_QUEST_UNIT = 7;
    public static final int MASTER_QUEST_APPEARANCE = 8;
    public static final int MASTER_QUEST_CLEAR = 9;
    public static final int MASTER_QUEST_REWARD = 10;

    public static final int MASTER_SCENARIO = 11;
    public static final int MASTER_SCENARIO_RULE = 12;
    public static final int MASTER_COMPETITION_RULE = 13;
    public static final int MASTER_SCENARIO_UNIT = 14;

    public static final int MASTER_COUNT = MASTER_SCENARIO_UNIT + 1;
}
