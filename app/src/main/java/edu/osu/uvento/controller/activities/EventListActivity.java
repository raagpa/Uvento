package edu.osu.uvento.controller.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;

import edu.osu.uvento.constants.Constants;
import edu.osu.uvento.controller.fragments.EventListFragment;
import edu.osu.uvento.controller.fragments.FilterDialogFragment;
import edu.osu.uvento.database.MyEventController;
import edu.osu.uvento.model.Event;
import edu.osu.uvento.model.Subscription;
import edu.osu.uvento.model.University;
import edu.osu.uvento.services.FragmentDataLoader;
import edu.osu.uvento.services.VolleyController;
import edu.osu.uvento.uvento.R;

/**
 * Created by chiragpa on 10/15/14.
 */
public class EventListActivity extends ActivityWithMenu {


    private static final String TAG = "SingleFragmentActivity";

    private Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);

        FragmentManager fm =  getFragmentManager();
        fragment = fm.findFragmentById(R.id.fragmentContainer);



        boolean isMyEventClicked = getIntent().getBooleanExtra("MY_EVENT_CLICKED", false);

        if (fragment == null) {
            fragment = createNewFragment();

            FragmentDataLoader dataLoader = new FragmentDataLoader(VolleyController.getInstance(this).getRequestQueue());
            HashMap<String, String> params = new HashMap<String, String>();

            if(isMyEventClicked){
                MyEventController eventController = new MyEventController(this);
                ArrayList<Event> eventList = eventController.query(University.getUserSavedUniversityId(this));

                Bundle bundle = new Bundle();
                bundle.putSerializable("eventList",eventList);
                bundle.putBoolean("MY_EVENT_CLICKED", true);
                fragment.setArguments(bundle);
                fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();

            }else {
                boolean getAllUpcomingEvents = getIntent().getBooleanExtra("ALL_UPCOMING_EVENTS",false);
                if(getAllUpcomingEvents){
                    params.put(Constants.QUERY_TYPE, Constants.GET_EVENTS_BY_UNIV);
                    params.put(Constants.ID, University.getUserSavedUniversityId(this)+"");

                }else {
                    Subscription subscription = (Subscription) getIntent().getSerializableExtra("SUBSCRIPTION");
                    if ("EVENT_TYPE".equals(subscription.getType())) {
                        params.put(Constants.QUERY_TYPE, Constants.GET_EVENTS_BY_TYPE);
                        params.put(Constants.EVENT_TYPE_ID, subscription.getId()+"");

                    } else {
                        params.put(Constants.QUERY_TYPE, Constants.GET_EVENTS_BY_CATEGORY);
                        params.put(Constants.CAT_ID, subscription.getId()+"");

                    }
                    System.out.println("Calling Volley " + subscription.getName());
                }

                dataLoader.loadData(params, R.id.fragmentContainer, fm, fragment, Constants.JSON_RESPONSE, true);
            }




        }
    }

    public Fragment createNewFragment() {

        return new EventListFragment();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuItem menuItem = menu.getItem(0);
        Menu subMenu= menuItem.getSubMenu();
        subMenu.add(0, 2, 0, "Filter");

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case 2:
                System.out.println("Filter Clicked");
                FilterDialogFragment newFragment = FilterDialogFragment.newInstance();

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                newFragment.setTargetFragment(fragment,0);
                newFragment.show(ft, "dialog");
                return true;

        }
        return false;
    }




}
