package edu.osu.uvento.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import edu.osu.uvento.model.Event;
import edu.osu.uvento.model.MyEvent;

/**
 * Created by neha on 11/10/2014.
 */
public class MyEventController   extends SQLiteOpenHelper

    {


        public MyEventController(Context applicationcontext) {
        super(applicationcontext, MyEventTable.DATABASE_NAME, null, MyEventTable.DB_VERSION);

    }

        @Override
        public void onCreate(SQLiteDatabase database){
       database.execSQL(MyEventTable.MyEvents.SQL_CREATE_TABLE);
        System.out.println("+++OnCreate()+++ ");
    }

        @Override public void onUpgrade(SQLiteDatabase database, int version_old, int current_version)
        {
            database.execSQL(MyEventTable.MyEvents.SQL_DELETE_TABLE);
            onCreate(database);
        }

        public void insert( int id,String title,String date,/*String place,*/String category,int univ_id,String i_url){

            ContentValues values = new ContentValues();
            values.put(MyEventTable.MyEvents.EVENT_ID, id);
            values.put(MyEventTable.MyEvents.EVENT_TITLE, title);
            values.put(MyEventTable.MyEvents.DATE, date);
            //values.put(MyEventTable.MyEvents.PLACE, place);
            values.put(MyEventTable.MyEvents.CATEGORY, category);
            values.put(MyEventTable.MyEvents.UNIVERSITY_ID, univ_id);
            values.put(MyEventTable.MyEvents.IMAGE_URL,i_url);

            SQLiteDatabase db = getWritableDatabase();
            long newRowId;
            newRowId = db.insert(
                    MyEventTable.MyEvents.TABLE_NAME,
                    null,
                    values);



        }
        public  MyEvent event_details(int id,int univ_id)
        {
            //ArrayList eventList=new ArrayList<MyEvent>();
            //String[] projection = {MyEventTable.MyEvents.EVENT_ID, MyEventTable.MyEvents.EVENT_TITLE,MyEventTable.MyEvents.DATE,MyEventTable.MyEvents.CATEGORY,MyEventTable.MyEvents.UNIVERSITY_ID,MyEventTable.MyEvents.IMAGE_URL};
        //    String[] whr_cause={MyEventTable.MyEvents.EVENT_ID, MyEventTable.MyEvents.UNIVERSITY_ID};
            String[] values={String.valueOf(id),String.valueOf(univ_id)};
            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.query(
                    MyEventTable.MyEvents.TABLE_NAME,  // The table to query
              //      projection,                               // The columns to return
                    null,
                    "EVENT_ID UNIVERSITY_ID",                                // The columns for the WHERE clause
                    values,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null                                 // The sort order
            );
            c.moveToFirst();
            MyEvent event=new MyEvent();
            event.id=id;
            event.univ_id=univ_id;
            event.date=c.getString(c.getColumnIndexOrThrow(MyEventTable.MyEvents.DATE));
            event.i_url=c.getString(c.getColumnIndexOrThrow(MyEventTable.MyEvents.IMAGE_URL));
            event.event_title=c.getString(c.getColumnIndexOrThrow(MyEventTable.MyEvents.EVENT_TITLE));
            event.category=c.getString(c.getColumnIndexOrThrow(MyEventTable.MyEvents.CATEGORY));
            //event.place=c.getString(getColumnIndexOrThrow(MyEventTable.MyEvents.PLACE));
            //eventList.add(event);


            return event;

        }
//Can return cursor
         public   ArrayList<MyEvent>  query( ){
             ArrayList eventList = new ArrayList<MyEvent>();
            //String[] projection = {MyEventTable.MyEvents.EVENT_ID, MyEventTable.MyEvents.EVENT_TITLE,MyEventTable.MyEvents.DATE,MyEventTable.MyEvents.CATEGORY,MyEventTable.MyEvents.UNIVERSITY_ID,MyEventTable.MyEvents.IMAGE_URL};

            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.query(
                    MyEventTable.MyEvents.TABLE_NAME,  // The table to query
              //      projection,                               // The columns to return
                    null,
                    null,                                // The columns for the WHERE clause
                    null,                            // The values for the WHERE clause
                    null,                                     // don't group the rows
                    null,                                     // don't filter by row groups
                    null                                 // The sort order
            );

            c.moveToFirst();
            do {
                MyEvent event=new MyEvent();
                event.id=Integer.parseInt(c.getString(c.getColumnIndexOrThrow(MyEventTable.MyEvents.EVENT_ID)));
                event.univ_id=Integer.parseInt(c.getString(c.getColumnIndexOrThrow(MyEventTable.MyEvents.UNIVERSITY_ID)));
                event.event_title=c.getString(c.getColumnIndexOrThrow(MyEventTable.MyEvents.EVENT_TITLE));
                event.i_url=c.getString(c.getColumnIndexOrThrow(MyEventTable.MyEvents.IMAGE_URL));
                event.date=c.getString(c.getColumnIndexOrThrow(MyEventTable.MyEvents.DATE));
                event.category=c.getString(c.getColumnIndexOrThrow(MyEventTable.MyEvents.CATEGORY));
                //event.place=c.getString(getColumnIndexOrThrow(MyEventTable.MyEvents.PLACE));
                eventList.add(event);
              //  System.out.print(c.getString(0) + " ");

             //   System.out.println (c.getString(c.getColumnIndexOrThrow(MyDbContract.Contact.COLUMN_NAME_SUBTITLE)));

            }while (c.moveToNext());
            return eventList;
        }

        public void delete(int id){

            SQLiteDatabase db = this.getWritableDatabase();
//        String deleteQuery = "DELETE FROM Contact where Tile='"+ title +"'";
//        database.execSQL(deleteQuery);

            // Define 'where' part of query.
            String selection = MyEventTable.MyEvents.EVENT_ID + " LIKE ?";
            // Specify arguments in placeholder order.
            String[] selectionArgs = { String.valueOf(id) };
            // Issue SQL statement.
            db.delete(MyEventTable.MyEvents.TABLE_NAME, selection, selectionArgs);


        }
/*EVENT_ID
TITLE
DATE
CATEGORY
UNIVERSITY_ID
IMAGE_URL
*/

        public void update(int id,String title,String date,/*String place,*/ String category,int univ_id,String i_url){
            SQLiteDatabase db = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(MyEventTable.MyEvents.EVENT_TITLE , title);
            values.put(MyEventTable.MyEvents.DATE , date);
            //values.put(MyEventTable.MyEvents.PLACE , place);
            values.put(MyEventTable.MyEvents.CATEGORY , category);
            values.put(MyEventTable.MyEvents.UNIVERSITY_ID , univ_id);
            values.put(MyEventTable.MyEvents.IMAGE_URL, i_url);


            String selection = MyEventTable.MyEvents.EVENT_ID + " LIKE ?";
            String[] selectionArgs = {String.valueOf(id)};


            int count = db.update(
                    MyEventTable.MyEvents.TABLE_NAME,
                    values,
                    selection,
                    selectionArgs);
        }


    }
