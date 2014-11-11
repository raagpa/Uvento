package edu.osu.uvento.database;

import android.provider.BaseColumns;

/**
 * Created by neha on 11/10/2014.
 */
public class MyEventTable {
    public static final String DATABASE_NAME = "LOCAL_DATABASE";
    public static final int DB_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String OPEN_PARENTHESIS = "( ";
    private static final String CLOSE_PARENTHESIS = " )";
    private static final String PRIMARY_KEY = " PRIMARY KEY";

    public static abstract class MyEvents implements BaseColumns {

            public static final String TABLE_NAME = "my_events";
            //public static final String COLUMN_NAME_ENTRY_ID = "entryid";
            public static final String  EVENT_ID   = "EVENT_ID";
            public static final String EVENT_TITLE = "EVENT_TITLE";
            public static final String DATE = "DATE";
            public static final String CATEGORY = "CATEGORY";
            public static final String UNIVERSITY_ID = "UNIVERSITY_ID";
            public static final String IMAGE_URL = "IMAGE_URL";
      //      public static final String PLACE="PLACE";





            public static final String SQL_CREATE_TABLE =
                    "CREATE TABLE " + MyEvents.TABLE_NAME
                            + OPEN_PARENTHESIS
                            + EVENT_ID + INTEGER_TYPE + COMMA_SEP
                            + EVENT_TITLE + TEXT_TYPE
                            + DATE + TEXT_TYPE
                           //+PLACE+TEXT_TYPE
                            + CATEGORY + TEXT_TYPE
                            +UNIVERSITY_ID + INTEGER_TYPE
                            +IMAGE_URL + TEXT_TYPE

                            +CLOSE_PARENTHESIS;
/*DATE
CATEGORY
UNIVERSITY_ID
IMAGE_URL
*/
            public static final String SQL_DELETE_TABLE=
                    "DROP TABLE IF EXISTS " + TABLE_NAME;

        }



}
