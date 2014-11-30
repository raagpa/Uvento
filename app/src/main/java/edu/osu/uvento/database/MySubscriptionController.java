package edu.osu.uvento.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import edu.osu.uvento.model.Subscription;

/**
 * Created by neha on 11/10/2014.
 */
public class MySubscriptionController extends DatabaseOpenHelper {


    public MySubscriptionController(Context applicationcontext) {
        super(applicationcontext);

    }





    public void insert( Subscription subscription){

        ContentValues values = new ContentValues();
        values.put(UventoDBContract.MySubScriptions.ID, subscription.getId());
        values.put(UventoDBContract.MySubScriptions.UNIVERSITY_ID,subscription.getUniv_id());
        values.put(UventoDBContract.MySubScriptions.IMAGE_URL,subscription.getImage_url());
        values.put(UventoDBContract.MySubScriptions.TYPE,subscription.getType());
        values.put(UventoDBContract.MySubScriptions.NAME,subscription.getName());


        SQLiteDatabase db = getWritableDatabase();
        long newRowId;
        newRowId = db.insert(
                UventoDBContract.MySubScriptions.TABLE_NAME,
                null,
                values);

        System.out.println("Successfully Inserted : " + subscription.getName() + "  :  "+subscription.getId());

        db.close();

    }

    public ArrayList<Subscription> query(String uni_id) {


        String whereClause = UventoDBContract.MySubScriptions.UNIVERSITY_ID + " = ?";
        String[] whereClauseValues = {uni_id+""};
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(
                UventoDBContract.MySubScriptions.TABLE_NAME,  // The table to query
                null,                               // The columns to return
                whereClause,                                // The columns for the WHERE clause
                whereClauseValues,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        //c.moveToFirst();
        ArrayList<Subscription> subscriptionsList = new ArrayList<Subscription>();
        Subscription subscription ;
        if(c.moveToFirst()) {
            do {
                subscription = new Subscription();
                subscription.setId(c.getInt(c.getColumnIndexOrThrow(UventoDBContract.MySubScriptions.ID)));

                subscription.setName(c.getString(c.getColumnIndexOrThrow(UventoDBContract.MySubScriptions.NAME)));
                subscription.setType(c.getString(c.getColumnIndexOrThrow(UventoDBContract.MySubScriptions.TYPE)));
                subscription.setImage_url(c.getString(c.getColumnIndexOrThrow(UventoDBContract.MySubScriptions.IMAGE_URL)));
                subscription.setUniv_id(c.getColumnIndexOrThrow(UventoDBContract.MySubScriptions.UNIVERSITY_ID));
                subscriptionsList.add(subscription);

            } while (c.moveToNext());
        }

        c.close();
        db.close();

        return subscriptionsList;

    }

    public void delete(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
//        String deleteQuery = "DELETE FROM Contact where Tile='"+ title +"'";
//        database.execSQL(deleteQuery);

        // Define 'where' part of query.
        String selection = UventoDBContract.MySubScriptions.ID + " = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {String.valueOf(id)};
        // Issue SQL statement.
        db.delete(UventoDBContract.MySubScriptions.TABLE_NAME, selection, selectionArgs);
        db.close();


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
