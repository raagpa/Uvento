package edu.osu.uvento.contoller;

import android.app.Fragment;

/**
 * Created by chiragpa on 10/15/14.
 */
public class EventListActivity extends SingleFragmentActivity {


    @Override
    public Fragment createNewFragment() {
        return new EventListFragment();
    }
}
