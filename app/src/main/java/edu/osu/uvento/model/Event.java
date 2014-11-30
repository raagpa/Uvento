package edu.osu.uvento.model;

import android.app.Activity;

import java.io.Serializable;
import java.util.ArrayList;

import edu.osu.uvento.database.MyEventController;

/**
 * Created by chiragpa on 10/15/14.
 */
public class Event implements Serializable{


    private int id;
    private String title;
    private String description;
    private String image_url;
    private String location;
    private String date;
    private String time;
    private String name;
    private String type;
    private String contact_name;
    private String email;
    private String phone;
    private int uni_id;
    private int cat_id;
    private int type_id;
    private double latitude;
    private double longitude;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getUni_id() {
        return uni_id;
    }

    public void setUni_id(int uni_id) {
        this.uni_id = uni_id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }


    public boolean isInMyEvents(Activity context){
        MyEventController myEventController =  new MyEventController(context);
        ArrayList<Event> myEventList = myEventController.query(University.getUserSavedUniversityId(context));

        if(myEventList != null){
            for(int i=0; i< myEventList.size();i++){
                if(getId() == myEventList.get(i).getId()){
                    return true;
                }
            }
        }
        return false;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}

