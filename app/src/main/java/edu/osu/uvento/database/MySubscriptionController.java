package edu.osu.uvento.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by neha on 11/10/2014.
 */
public class MySubscriptionController extends SQLiteOpenHelper {


    public MySubscriptionController(Context applicationcontext) {
        super(applicationcontext, MySubscriptionTable.DATABASE_NAME, null, MySubscriptionTable.DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase database){
        database.execSQL(MySubscriptionTable.MySubScriptions.SQL_CREATE_TABLE);
        System.out.println("+++OnCreate()+++ ");
            }

    @Override public void onUpgrade(SQLiteDatabase database, int version_old, int current_version)
    {
        database.execSQL(MySubscriptionTable.MySubScriptions.SQL_DELETE_TABLE);
        onCreate(database);
    }

    public void insert( int id,int univ_id,String i_url , String type){

        ContentValues values = new ContentValues();
        values.put(MySubscriptionTable.MySubScriptions.ID, id);
        values.put(MySubscriptionTable.MySubScriptions.UNIVERISTY_ID,univ_id);
        values.put(MySubscriptionTable.MySubScriptions.IMAGE_URL,i_url);
        values.put(MySubscriptionTable.MySubScriptions.TYPE,type);


        SQLiteDatabase db = getWritableDatabase();
        long newRowId;
        newRowId = db.insert(
                MySubscriptionTable.MySubScriptions.TABLE_NAME,
                null,
                values);



    }

    public void query(){

        String[] projection = {MySubscriptionTable.MySubScriptions.ID, MySubscriptionTable.MySubScriptions.UNIVERISTY_ID,MySubscriptionTable.MySubScriptions.IMAGE_URL,MySubscriptionTable.MySubScriptions.TYPE};

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(
                MySubscriptionTable.MySubScriptions.TABLE_NAME,  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        c.moveToFirst();
        do {
            System.out.print(c.getString(0) + " ");
            System.out.println (c.getString(c.getColumnIndexOrThrow(MySubscriptionTable.MySubScriptions.ID)));
        }while (c.moveToNext());

    }

    public void delete(int id){

        SQLiteDatabase db = this.getWritableDatabase();
//        String deleteQuery = "DELETE FROM Contact where Tile='"+ title +"'";
//        database.execSQL(deleteQuery);

        // Define 'where' part of query.
        String selection = MySubscriptionTable.MySubScriptions.ID + " LIKE ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = { String.valueOf(id) };
        // Issue SQL statement.
        db.delete(MySubscriptionTable.MySubScriptions.TABLE_NAME, selection, selectionArgs);


    }

/*Not needed
    public void update(String title, String subTitle){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MySubscriptionTable.MySubScriptions.COLUMN_NAME_SUBTITLE, subTitle);

        String selection = MyDbContract.Contact.COLUMN_NAME_TITLE + " LIKE ?";
        String[] selectionArgs = {title};

        int count = db.update(
                MyDbContract.Contact.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }
*/

}
