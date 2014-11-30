package edu.osu.uvento.controller.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import java.util.HashMap;

import edu.osu.uvento.constants.Constants;
import edu.osu.uvento.controller.fragments.AppPreferenceFragment;
import edu.osu.uvento.services.FragmentDataLoader;
import edu.osu.uvento.services.VolleyController;

/**
 * Created by chiragpa on 11/10/14.
 */
public class AppPreferenceActivity extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm =  getFragmentManager();
        Fragment fragment = new AppPreferenceFragment();

        FragmentDataLoader dataLoader = new FragmentDataLoader(VolleyController.getInstance(this).getRequestQueue());
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(Constants.QUERY_TYPE, Constants.GET_UNIV_LIST);

        dataLoader.loadData(params, android.R.id.content, fm, fragment ,Constants.JSON_RESPONSE,true );

        // Display the fragment as the main content.
//        getFragmentManager().beginTransaction()
//                .replace(android.R.id.content, new AppPreferenceFragment())
//                .commit();
    }

}
