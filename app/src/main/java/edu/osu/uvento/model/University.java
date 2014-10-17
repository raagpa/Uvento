package edu.osu.uvento.model;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.List;

import edu.osu.uvento.Constants.Constants;
import edu.osu.uvento.uvento.R;

/**
 * Created by chiragpa on 10/13/14.
 */
public class University {
    private String name;

    public University(String name){
        this.name = name;
    }

    public static List<University> getUniversities(){
        //TODO @Drew -  Add code to get University names from external database using webservice
         return mockGetUniversitiesResponse();
    }

    public static void saveUserUniversityPreference(Activity activity, String universityName){
        //TODO @Neha -  Add code to save user's university preference in phone's local DB.
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(Constants.USER_UNIV_PREFERENCE, universityName);
        editor.commit();
    }

    public static String getUserSavedUniversityPreference(Activity activity){
        //TODO @Neha -  Add code to get user's university preference from phone's local DB.
        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        String universityName = sharedPref.getString(Constants.USER_UNIV_PREFERENCE,"");
        return universityName;
    }

    public String toString(){
        return name;
    }

    private static List<University> mockGetUniversitiesResponse() {
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

    public static boolean isUserUniversityPreferenceSaved(Activity activity) {
        String universityName = getUserSavedUniversityPreference(activity);
        if(null != universityName && ! "".equals(universityName)){
            return true;
        }
        return false;
    }
}
