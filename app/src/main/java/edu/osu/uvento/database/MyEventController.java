package edu.osu.uvento.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import edu.osu.uvento.model.Event;


/**
 * Created by neha on 11/10/2014.
 */
public class MyEventController extends DatabaseOpenHelper

{

    public MyEventController(Context applicationcontext){
        super(applicationcontext);
    }



    public void insert(Event event) {

        ContentValues values = new ContentValues();
        values.put(UventoDBContract.MyEvents.EVENT_ID, event.getId());
        values.put(UventoDBContract.MyEvents.EVENT_TITLE, event.getTitle());
        values.put(UventoDBContract.MyEvents.DATE, event.getDate());
        //values.put(MyEventTable.MyEvents.PLACE, place);
        values.put(UventoDBContract.MyEvents.CATEGORY, event.getName());
        values.put(UventoDBContract.MyEvents.UNIVERSITY_ID, event.getUni_id());
        values.put(UventoDBContract.MyEvents.IMAGE_URL, event.getImage_url());

        SQLiteDatabase db = getWritableDatabase();
        long newRowId;
        newRowId = db.insert(
                UventoDBContract.MyEvents.TABLE_NAME,
                null,
                values);

        db.close();


    }

    public Event getEventDetails(int event_id, int univ_id) {

        String whereClause = UventoDBContract.MyEvents.EVENT_ID + " = ? AND " + UventoDBContract.MyEvents.UNIVERSITY_ID + " =?";
        String[] values = {String.valueOf(event_id), String.valueOf(univ_id)};
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(
                UventoDBContract.MyEvents.TABLE_NAME,  // The table to query
                //      projection,                               // The columns to return
                null,
                "EVENT_ID UNIVERSITY_ID",                                // The columns for the WHERE clause
                values,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );
        Event event = null;
        if (c.moveToFirst()) {
            event = new Event();
            event.setId(event_id);
            event.setUni_id(univ_id);
            event.setDate(c.getString(c.getColumnIndexOrThrow(UventoDBContract.MyEvents.DATE)));
            event.setImage_url(c.getString(c.getColumnIndexOrThrow(UventoDBContract.MyEvents.IMAGE_URL)));
            event.setTitle(c.getString(c.getColumnIndexOrThrow(UventoDBContract.MyEvents.EVENT_TITLE)));
            event.setName(c.getString(c.getColumnIndexOrThrow(UventoDBContract.MyEvents.CATEGORY)));
        }


        return event;

    }

    //Can return cursor
    public ArrayList<Event> query(String univ_id) {
        ArrayList eventList = new ArrayList<Event>();
        //String[] projection = {MyEventTable.MyEvents.EVENT_ID, MyEventTable.MyEvents.EVENT_TITLE,MyEventTable.MyEvents.DATE,MyEventTable.MyEvents.CATEGORY,MyEventTable.MyEvents.UNIVERSITY_ID,MyEventTable.MyEvents.IMAGE_URL};
        String whereClause = UventoDBContract.MyEvents.UNIVERSITY_ID + " =?";
        String[] whereClauseValues = {univ_id + ""};
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query(
                UventoDBContract.MyEvents.TABLE_NAME,  // The table to query
                null,                                   // The columns to return
                whereClause,                                // The columns for the WHERE clause
                whereClauseValues,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                null                                 // The sort order
        );

        if(c.moveToFirst()) {
            do {
                Event event = new Event();

                event.setId(Integer.parseInt(c.getString(c.getColumnIndexOrThrow(UventoDBContract.MyEvents.EVENT_ID))));
                event.setUni_id(Integer.parseInt(c.getString(c.getColumnIndexOrThrow(UventoDBContract.MyEvents.UNIVERSITY_ID))));
                event.setDate(c.getString(c.getColumnIndexOrThrow(UventoDBContract.MyEvents.DATE)));
                event.setImage_url(c.getString(c.getColumnIndexOrThrow(UventoDBContract.MyEvents.IMAGE_URL)));
                event.setTitle(c.getString(c.getColumnIndexOrThrow(UventoDBContract.MyEvents.EVENT_TITLE)));

                System.out.println("From DB : " + event.getTitle());
                event.setName(c.getString(c.getColumnIndexOrThrow(UventoDBContract.MyEvents.CATEGORY)));
                eventList.add(event);
            } while (c.moveToNext());
        }

        c.close();
        db.close();

        return eventList;
    }

    public void delete(int id) {

        SQLiteDatabase db = this.getWritableDatabase();

        // Define 'where' part of query.
        String selection = UventoDBContract.MyEvents.EVENT_ID + " = ?";
        // Specify arguments in placeholder order.
        String[] selectionArgs = {String.valueOf(id)};
        // Issue SQL statement.
        db.delete(UventoDBContract.MyEvents.TABLE_NAME, selection, selectionArgs);
        db.close();


    }
/*EVENT_ID
TITLE
DATE
CATEGORY
UNIVERSITY_ID
IMAGE_URL
*/

    public void update(int id, String title, String date,/*String place,*/ String category, int univ_id, String i_url) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UventoDBContract.MyEvents.EVENT_TITLE, title);
        values.put(UventoDBContract.MyEvents.DATE, date);
        //values.put(MyEventTable.MyEvents.PLACE , place);
        values.put(UventoDBContract.MyEvents.CATEGORY, category);
        values.put(UventoDBContract.MyEvents.UNIVERSITY_ID, univ_id);
        values.put(UventoDBContract.MyEvents.IMAGE_URL, i_url);


        String selection = UventoDBContract.MyEvents.EVENT_ID + " LIKE ?";
        String[] selectionArgs = {String.valueOf(id)};


        int count = db.update(
                UventoDBContract.MyEvents.TABLE_NAME,
                values,
                selection,
                selectionArgs);
    }


}
