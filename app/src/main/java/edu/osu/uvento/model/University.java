package edu.osu.uvento.model;

import java.util.ArrayList;
import java.util.List;

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

    public static void saveUserUniversityPreference(String universityName){
        //TODO @Neha -  Add code to save user's university preference in phone's local DB.
    }

    public static String getUserSavedUniversityPreference(){
        //TODO @Neha -  Add code to get user's university preference from phone's local DB.
        return "Ohio State Univ.";
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

    public static boolean isUserUniversityPreferenceSaved() {
        String universityName = getUserSavedUniversityPreference();
        if(null != universityName && ! "".equals(universityName)){
            return true;
        }
        return false;
    }
}
