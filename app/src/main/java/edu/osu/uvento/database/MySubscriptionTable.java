package edu.osu.uvento.database;

import android.provider.BaseColumns;

/**
 * Created by neha on 11/10/2014.
 */
public class MySubscriptionTable {


    public static final String DATABASE_NAME = "LOCAL_DATABASE";
    public static final int DB_VERSION = 1;
    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ",";
    private static final String OPEN_PARENTHESIS = "( ";
    private static final String CLOSE_PARENTHESIS = " )";
    private static final String PRIMARY_KEY = " PRIMARY KEY";
    public static abstract class MySubScriptions implements BaseColumns {

        public static final String TABLE_NAME = "my_subscriptions";
        //public static final String COLUMN_NAME_ENTRY_ID = "entryid";
        public static final String ID  = "ID";
        public static final String UNIVERISTY_ID                = "UNIVERISTY_ID";
        public static final String IMAGE_URL="IMAGE_URL";
        public static final String TYPE="TYPE";




        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + MySubScriptions.TABLE_NAME
                        + OPEN_PARENTHESIS

                        + ID+ INTEGER_TYPE + COMMA_SEP
                        + UNIVERISTY_ID + INTEGER_TYPE
                        + IMAGE_URL + TEXT_TYPE
                        + TYPE + TEXT_TYPE
                        + CLOSE_PARENTHESIS;

        public static final String SQL_DELETE_TABLE=
                "DROP TABLE IF EXISTS " + TABLE_NAME;

    }

}
