package models.utils;

/**
 * Created with IntelliJ IDEA.
 * User: shouzouueno
 * Date: 2014/03/13
 * Time: 10:24
 * To change this template use File | Settings | File Templates.
 */
public class JsonKeyString {
    // field keys
    final static public String NO               = "a001";
    final static public String NAME             = "a002";
    final static public String DETAIL           = "a003";
    final static public String IMAGE            = "a004";
    final static public String VERSION          = "a005";
    final static public String APPEARANCE_RATE  = "a006";
    final static public String KEY_NO           = "a007";
    final static public String VALUE            = "a008";
    final static public String KIND             = "a009";
    final static public String CONDITION_TYPE   = "a010";
    final static public String PARENT           = "a011";
    final static public String REFERENCE        = "a012";
    final static public String FRIEND           = "a013";
    final static public String ENEMY_FORCE      = "a014";

    // master keys
    final static public String DIRECTION        = "b001";
    final static public String MAJOR            = "b002";
    final static public String MINOR            = "b003";
    final static public String QUEST_UNIT       = "b004";
    final static public String QUEST_APPEARANCE = "b005";
    final static public String QUEST_CLEAR      = "b006";
    final static public String QUEST_REWARD     = "b007";
    final static public String UNIT             = "b008";
    final static public String MAP              = "b009";
    final static public String ITEM             = "b010";
    final static public String SCENARIO         = "b011";
    final static public String SCENARIO_RULE    = "b012";
    final static public String COMPETITION_RULE = "b013";
    final static public String CONDITION        = "b014";
    final static public String SCENARIO_UNIT    = "b015";
    final static public String CONDITION_KIND   = "b016";
    final static public String RANK             = "b017";
    final static public String CHARACTER_COLUMN = "b018";
    final static public String SKILL            = "b019";
    final static public String UNIT_SKILL       = "b020";
    final static public String FORCE            = "b021";

    // json structure Key
    final static public String DATA             = "c001";
    final static public String SESSION          = "c002";
    final static public String SESSION_ID       = "c003";
    final static public String ERROR            = "c004";
    final static public String MASTER_NO        = "c005";
    final static public String UNIX_TIME        = "c006";
    final static public String SUCCESS          = "c007";
    final static public String PARENT_KEY       = "c008";
    final static public String CONDITION_KEY    = "c009";
    final static public String RANDOM_SEED      = "c010";
    final static public String ACTION_NO		= "c011";
    final static public String POSITION		    = "c012";
    final static public String TAG_NO           = "c013";
    final static public String DURATION         = "c014";
    final static public String COLOR            = "c015";
    final static public String FRAME            = "c016";
    final static public String SCORE_TYPE       = "c017";

    // db table key
    final static public String LOGIN            = "d001";
    final static public String GAME_CHARACTER   = "d002";
    final static public String FRIENDS          = "d003";

    // db field key
    final static public String ID               = "d101";
    final static public String GPP_UUID         = "d102";
    final static public String MAIL_ADDRESS     = "d103";
    final static public String DISPLAY_NAME     = "d104";
    final static public String IMAGE_URL        = "d105";
    final static public String UUID             = "d106";
    final static public String ACCESS_COUNT     = "d107";
    final static public String CREATE_DATE      = "d108";
    final static public String UPDATE_DATE      = "d109";

    final static public String STAMINA          = "d110";
    final static public String MAX_STAMINA      = "d111";
    final static public String MONEY            = "d112";
    final static public String GOLD             = "d113";
    final static public String LAST_COMMAND     = "d114";


    // COMPETITION
    final static public String FRIEND_COM       = "e001";
    final static public String FRIEND_INVITE    = "e002";
    final static public String FRIEND_COM_ID    = "roomUUID";
    final static public String FRIEND_COM_COUNT = "registeredUserCount";
    final static public String FRIEND_REGISTERED= "registered";
}
