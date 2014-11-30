package edu.osu.uvento.controller.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import edu.osu.uvento.uvento.R;

/**
 * Created by chiragpa on 10/15/14.
 */
public abstract class SingleFragmentActivity extends ActivityWithMenu {

    private static final String TAG = "SingleFragmentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);

        FragmentManager fm =  getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if (fragment == null) {
            fragment = createNewFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }

    public abstract Fragment createNewFragment();




}
