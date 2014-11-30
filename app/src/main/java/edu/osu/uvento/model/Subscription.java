package edu.osu.uvento.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by chiragpa on 11/11/14.
 */
public class Subscription implements Serializable{

    private static final long serialVersionUID = 42L;

    private int id;
    private String name;
    private String image_url;
    private String type;
    private int univ_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getUniv_id() {
        return univ_id;
    }

    public void setUniv_id(int univ_id) {
        this.univ_id = univ_id;
    }

    public static boolean isSubscribed(List<Subscription>subscriptionList ,int id, String type) {
        Subscription subscription;
        if(subscriptionList != null){
            int size = subscriptionList.size();
            for(int i=0; i< size ; i++){
                subscription = subscriptionList.get(i);
                System.out.println(subscription.getType() + "  :  " + subscription.getId() + " :  "+id);
                if(type.equals(subscription.getType()) && id == subscription.getId()){
                    return true;
                }
            }
        }

        return false;
    }



}
