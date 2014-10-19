package edu.osu.uvento.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by chiragpa on 10/15/14.
 */
public class Event {

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private int eventId;
    private String eventName;
    private java.util.Date date;


    public static ArrayList<Event>  getEventList(){
        return getMockEvents();
    }

    private static ArrayList<Event> getMockEvents() {
        ArrayList eventList = new ArrayList<Event>();

        for (int i=1 ; i<20;i++){
            Event event = new Event();
            event.eventName = "Event : " + i;
            event.date = new Date();
            eventList.add(event);
        }
        return eventList;
    }

    public String toString(){

        return eventName;
    }

}
