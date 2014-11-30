package edu.osu.uvento.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by chiragpa on 11/13/14.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    public DatabaseOpenHelper(Context applicationcontext) {
        super(applicationcontext, UventoDBContract.DATABASE_NAME, null, UventoDBContract.DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(UventoDBContract.MyEvents.SQL_CREATE_TABLE);
        database.execSQL(UventoDBContract.MySubScriptions.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
        database.execSQL(UventoDBContract.MyEvents.SQL_DELETE_TABLE);
        database.execSQL(UventoDBContract.MySubScriptions.SQL_DELETE_TABLE);
        onCreate(database);
    }
}
