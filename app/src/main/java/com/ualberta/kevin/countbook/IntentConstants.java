/**
 * IntentConstants Class
 *
 * Copyright 2017 Kevin Wang
 *
 * @author weixiang
 * @version 1.0
 * @created 2017-09-27
 */

package com.ualberta.kevin.countbook;

/**
 * IntentConstants stores the constants for passing data
 * between activities using intents.
 */

public class IntentConstants {
    public final static int ADD_COUNTER_INTENT_REQUEST = 1;
    public final static int ADD_COUNTER_INTENT_RESULT = 2;
    public final static int EDIT_COUNTER_INTENT_REQUEST = 3;
    public final static int EDIT_COUNTER_INTENT_RESPONSE = 4;
    public final static int DELETE_COUNTER_INTENT_RESPONSE = 5;

    public final static String INTENT_COUNTER_TITLE = "INTENT_COUNTER_TITLE";
    public final static String INTENT_COUNTER_DATE = "INTENT_COUNTER_DATE";
    public final static String INTENT_COUNTER_INITIAL_VALUE = "INTENT_COUNTER_INITIAL_VALUE";
    public final static String INTENT_COUNTER_COMMENT = "INTENT_COUNTER_COMMENT";
    public final static String INTENT_COUNTER_CURRENT_VALUE = "INTENT_COUNTER_CURRENT_VALUE";
    public final static String INTENT_COUNTER_INDEX = "INTENT_COUNTER_INDEX";
}
