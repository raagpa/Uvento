package edu.osu.uvento.database;

import android.provider.BaseColumns;

/**
 * Created by neha on 11/10/2014.
 */
public class UventoDBContract {
    public static final String DATABASE_NAME = "uvento";
    public static final int DB_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String OPEN_PARENTHESIS = "( ";
    private static final String CLOSE_PARENTHESIS = " )";
    private static final String PRIMARY_KEY = " PRIMARY KEY";

    public static abstract class MyEvents implements BaseColumns {

        public static final String TABLE_NAME = "MY_EVENT";
        public static final String EVENT_ID = "EVENT_ID";
        public static final String EVENT_TITLE = "EVENT_TITLE";
        public static final String DATE = "DATE";
        public static final String CATEGORY = "CATEGORY";
        public static final String UNIVERSITY_ID = "UNIVERSITY_ID";
        public static final String IMAGE_URL = "IMAGE_URL";



        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + MyEvents.TABLE_NAME
                        + OPEN_PARENTHESIS
                        + EVENT_ID + INTEGER_TYPE + PRIMARY_KEY + COMMA_SEP
                        + EVENT_TITLE + TEXT_TYPE + COMMA_SEP
                        + DATE + TEXT_TYPE + COMMA_SEP
                        + CATEGORY + TEXT_TYPE + COMMA_SEP
                        + UNIVERSITY_ID + INTEGER_TYPE + COMMA_SEP
                        + IMAGE_URL + TEXT_TYPE
                        + CLOSE_PARENTHESIS;

        public static final String SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;

    }


    public static abstract class MySubScriptions implements BaseColumns {

        public static final String TABLE_NAME = "MY_SUBSCRIPTIONS";
        public static final String ID = "SUBSCRIPTION_ID";
        public static final String UNIVERSITY_ID = "UNIVERSITY_ID";
        public static final String IMAGE_URL = "IMAGE_URL";
        public static final String TYPE = "TYPE";
        public static final String NAME = "NAME";


        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + MySubScriptions.TABLE_NAME
                        + OPEN_PARENTHESIS

                        + ID + INTEGER_TYPE  + COMMA_SEP
                        + NAME + TEXT_TYPE + COMMA_SEP
                        + UNIVERSITY_ID + INTEGER_TYPE + COMMA_SEP
                        + IMAGE_URL + TEXT_TYPE + COMMA_SEP
                        + TYPE + TEXT_TYPE + COMMA_SEP
                        + PRIMARY_KEY + OPEN_PARENTHESIS + ID + COMMA_SEP + TYPE + CLOSE_PARENTHESIS
                        + CLOSE_PARENTHESIS;

        public static final String SQL_DELETE_TABLE =
                "DROP TABLE IF EXISTS " + TABLE_NAME;


    }
}
