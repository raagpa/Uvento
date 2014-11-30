package edu.osu.uvento.controller.activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import java.util.HashMap;

import edu.osu.uvento.constants.Constants;
import edu.osu.uvento.controller.fragments.UniversitySelectionFragment;
import edu.osu.uvento.model.University;
import edu.osu.uvento.services.FragmentDataLoader;
import edu.osu.uvento.services.VolleyController;
import edu.osu.uvento.uvento.R;


public class UniversitySelectionActivity extends Activity {


    private static final String TAG = "UniversitySelectionActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        String syncConnPref = sharedPref.getString("notify", "");
        System.out.println("Notify " + syncConnPref);

        if(University.isUserUniversityPreferenceSaved(this)){
            launchNextActivity();
        }else {

            FragmentManager fm = getFragmentManager();
            Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

            if (fragment == null) {
                fragment = createNewFragment();

                FragmentDataLoader dataLoader = new FragmentDataLoader(VolleyController.getInstance(this).getRequestQueue());
                HashMap<String, String> params = new HashMap<String, String>();
                params.put(Constants.QUERY_TYPE, Constants.GET_UNIV_LIST);

                dataLoader.loadData(params, R.id.fragmentContainer, fm, fragment ,Constants.JSON_RESPONSE,true );
//            fm.beginTransaction()
//                    .add(R.id.fragmentContainer, fragment)
//                    .commit();
            }
        }




    }

    public Fragment createNewFragment(){
       return new UniversitySelectionFragment();
   }

    private void launchNextActivity() {
        System.out.println("################BYPASS########################");
        Intent i = new Intent(this,CategoryMultiFragmentActivity.class);
        startActivity(i);
        this.finish();
    }
    



}
