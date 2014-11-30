package edu.osu.uvento.controller.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import java.util.HashMap;

import edu.osu.uvento.constants.Constants;
import edu.osu.uvento.controller.fragments.SubscribeToFragment;
import edu.osu.uvento.model.University;
import edu.osu.uvento.services.FragmentDataLoader;
import edu.osu.uvento.services.VolleyController;
import edu.osu.uvento.uvento.R;

public class SubscribeToActivity extends ActivityWithMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);



        FragmentManager fm =  getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if (fragment == null) {
            fragment = createNewFragment();

            FragmentDataLoader dataLoader = new FragmentDataLoader(VolleyController.getInstance(this).getRequestQueue());
            HashMap<String, String> params = new HashMap<String, String>();

            String uni_id = University.getUserSavedUniversityId(this);
            params.put(Constants.QUERY_TYPE, Constants.GET_CAT_BY_CAT_TYPE);
            params.put(Constants.ID, uni_id +"");
            params.put(Constants.CAT_TYPE,"COLLEGE");

            dataLoader.loadData(params, R.id.fragmentContainer, fm, fragment, Constants.JSON_RESPONSE_CAT_BY_UNIV,false );

            HashMap<String, String> params1 = new HashMap<String, String>();

            params1.put(Constants.QUERY_TYPE, Constants.GET_CAT_BY_CAT_TYPE);
            params1.put(Constants.ID, uni_id+"");

            params1.put(Constants.CAT_TYPE,"GROUPS");
            dataLoader.loadData(params1, R.id.fragmentContainer, fm, fragment, Constants.JSON_RESPONSE_CAT_BY_GROUP,false );

            HashMap<String, String> params2 = new HashMap<String, String>();
            params2.put(Constants.ID, uni_id+"");
            params2.put(Constants.QUERY_TYPE, Constants.GET_ALL_TYPES_BY_UNIV);

            dataLoader.loadData(params2, R.id.fragmentContainer, fm, fragment, Constants.JSON_RESPONSE_TYPES, true);

        }
    }


    public Fragment createNewFragment() {

        return new SubscribeToFragment();
    }


}
