package edu.osu.uvento.model;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.io.Serializable;

import edu.osu.uvento.constants.Constants;

/**
 * Created by chiragpa on 10/13/14.
 */
public class University implements Serializable {


    private String name;
    private int id;
    private String image_url;

    public University(String name){
        this.name = name;
    }



    public static void saveUserUniversityPreference(Activity activity, String universityName , University univ){

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Constants.USER_UNIV_NAME, universityName);
        editor.putString(Constants.USER_UNIV_ID, univ.getId() + "");
        editor.putString(Constants.USER_UNIV_IMAGE_URL,univ.getImage_url());
        editor.commit();
    }

    public static String getUserSavedUniversityName(Activity activity){

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(activity);
        String universityName = sharedPref.getString(Constants.USER_UNIV_NAME,"");
        return universityName;
    }

    public static String getUserSavedUniversityId(Activity activity){

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(activity);
        String univ_id = sharedPref.getString(Constants.USER_UNIV_ID, "");
        return univ_id;
    }


    public static String getUserSavedUniversityImageURL(Activity activity){

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(activity);
        String image_url = sharedPref.getString(Constants.USER_UNIV_IMAGE_URL, "");
        return image_url;
    }
    public String toString(){
        return name;
    }

   /* private static List<University> mockGetUniversitiesResponse() {
        ArrayList<University> universityList = new ArrayList<University>();

        University univ1 =  new University("Ohio State Univ.");
        University univ2 =  new University("Univ. Of Michigan");
        University univ3 =  new University("Purdue Univ.");
        University univ4 =  new University("Univ. Of Texas Austin");
        University univ5 =  new University("Michigan State Univ.");
        University univ6 =  new University("Ohio Univ.");
        University univ7 =  new University("Univ. Of Southern California");
        University univ8 =  new University("Stanford");
        University univ9 =  new University("Arizona State Univ.");
        University univ10 =  new University("Carnegie Mellon Univ.");

        universityList.add(univ1);
        universityList.add(univ2);
        universityList.add(univ3);
        universityList.add(univ4);
        universityList.add(univ5);
        universityList.add(univ6);
        universityList.add(univ7);
        universityList.add(univ8);
        universityList.add(univ9);
        universityList.add(univ10);

        return universityList;
    }
*/
    public static boolean isUserUniversityPreferenceSaved(Activity activity) {
        String universityName = getUserSavedUniversityName(activity);
        if(null != universityName && ! "".equals(universityName)){
            return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
