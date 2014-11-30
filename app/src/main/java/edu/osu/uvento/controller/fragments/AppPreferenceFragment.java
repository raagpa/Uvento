package edu.osu.uvento.controller.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

import com.google.gson.Gson;

import java.util.ArrayList;

import edu.osu.uvento.constants.Constants;
import edu.osu.uvento.model.University;
import edu.osu.uvento.uvento.R;

/**
 * Created by chiragpa on 11/10/14.
 */
public class AppPreferenceFragment extends PreferenceFragment  implements SharedPreferences.OnSharedPreferenceChangeListener{

    private ListPreference univListPreference;
    private SwitchPreference notificationPreference;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preference);

        populateUnivListPreference();









    }

    private void populateUnivListPreference() {

        ArrayList<University> univList = new ArrayList<University>();

        if(getArguments() != null && getArguments().containsKey(Constants.JSON_RESPONSE)) {
            String[] JSONStrings = getArguments().getString(Constants.JSON_RESPONSE).split("\\|");
            Gson gson = new Gson();
            for (String s : JSONStrings) {
                System.out.println(s);
                University univ = gson.fromJson(s, University.class);
                univList.add(univ);
            }

        }

        univListPreference = (ListPreference)findPreference("edu.osu.uvento.user_univ_id");

        CharSequence[] entries =  new CharSequence[univList.size()];
        CharSequence[] entryValues =  new CharSequence[univList.size()];
        for(int i=0; i <univList.size();i++){
            entries[i] = univList.get(i).getName();
            entryValues[i] = univList.get(i).getId() +"";
        }
        univListPreference.setEntries(entries);
        univListPreference.setEntryValues(entryValues);
    }

    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                          String key) {
        if (key.equals("edu.osu.uvento.user_univ_id")) {
            Preference connectionPref = findPreference(key);
            // Set summary to be the user-description for the selected value
            connectionPref.setSummary(sharedPreferences.getString(key, ""));
        }
    }


}
