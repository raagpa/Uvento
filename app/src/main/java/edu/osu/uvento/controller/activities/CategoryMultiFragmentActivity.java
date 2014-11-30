package edu.osu.uvento.controller.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import edu.osu.uvento.constants.Constants;
import edu.osu.uvento.controller.activities.ActivityWithMenu;
import edu.osu.uvento.controller.fragments.CategoryGridFragment;
import edu.osu.uvento.controller.fragments.UpcomingEventCategoryFragment;
import edu.osu.uvento.database.MyEventController;
import edu.osu.uvento.database.MySubscriptionController;
import edu.osu.uvento.model.Event;
import edu.osu.uvento.model.Subscription;
import edu.osu.uvento.model.University;
import edu.osu.uvento.services.FragmentDataLoader;
import edu.osu.uvento.services.VolleyController;
import edu.osu.uvento.uvento.R;

public class CategoryMultiFragmentActivity extends ActivityWithMenu {

    private static final String TAG = "CategoryMultiFragmentActivity";
    private int noOfSubscriptions;
    private boolean isMyEventSubscribed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"+++++Enter onCreate++++++");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_fragment);
        setTitle("Uvento");

        String univ_id = University.getUserSavedUniversityId(this);
        MySubscriptionController subscriptionController = new MySubscriptionController(this);
        ArrayList<Subscription> subscriptionList = subscriptionController.query(univ_id);

        noOfSubscriptions =subscriptionList.size();
        System.out.println("No. of Subscriptions "+ subscriptionList.size());

        MyEventController myEventController = new MyEventController(this);
        ArrayList<Event> eventList = myEventController.query(univ_id);

        if(eventList == null || eventList.size() == 0){
            isMyEventSubscribed = false;
        }

        loadFragments(subscriptionList, eventList);

        Log.d(TAG,"++++++Exit onCreate+++++");
    }

    private void loadFragments(ArrayList<Subscription> subscriptionList , ArrayList<Event> eventList) {
        FragmentManager fm1 =  getFragmentManager();
        Fragment fragment1 = fm1.findFragmentById(R.id.fragmentContainer1);

        Bundle bundle =  new Bundle();
        bundle.putSerializable("subscriptionList",subscriptionList);
        bundle.putSerializable("eventList", eventList);


        // if (fragment1 == null) {
        fragment1 = new UpcomingEventCategoryFragment();
        fragment1.setArguments(bundle);
//        fm1.beginTransaction()
//                .add(R.id.fragmentContainer1, fragment1)
//                .commit();
        //}

        FragmentDataLoader dataLoader = new FragmentDataLoader(VolleyController.getInstance(this).getRequestQueue());
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(Constants.QUERY_TYPE, Constants.GET_UNIV_BY_ID);
        params.put(Constants.ID,University.getUserSavedUniversityId(this));

        dataLoader.loadData(params, R.id.fragmentContainer1, fm1, fragment1 ,Constants.JSON_RESPONSE,true );

        FragmentManager fm2 =  getFragmentManager();
        Fragment fragment2 = fm2.findFragmentById(R.id.fragmentContainer2);

        //if (fragment2 == null) {
        fragment2 = new CategoryGridFragment();
        fragment2.setArguments(bundle);
        fm2.beginTransaction()
                .add(R.id.fragmentContainer2, fragment2)
                .commit();
        //}
    }


    @Override
    protected void onStart() {
        Log.d(TAG,"++++++Enter onStart+++++++");
        super.onStart();
        Log.d(TAG,"Exit onStart");
        // The activity is about to become visible.
    }
    @Override
    protected void onResume() {
        Log.d(TAG,"++++++Enter onResume++++++");
        super.onResume();
        Log.d(TAG,"+++++Exit onResume+++++");
        // The activity has become visible (it is now "resumed").
    }
    @Override
    protected void onPause() {
        Log.d(TAG,"++++++Enter onPause+++++++");
        super.onPause();
        Log.d(TAG,"+++++++Exit onPause++++++");
        // Another activity is taking focus (this activity is about to be "paused").
    }
    @Override
    protected void onStop() {
        Log.d(TAG,"+++++Enter onStop++++");
        super.onStop();
        Log.d(TAG,"+++++Enter onStop+++++");
        // The activity is no longer visible (it is now "stopped")
    }
    @Override
    protected void onDestroy() {
        Log.d(TAG,"++++Enter onDestroy++++");
        super.onDestroy();
        Log.d(TAG,"+++++Exit onDestroy+++++");
        // The activity is about to be destroyed.
    }

    @Override
    protected void onRestart(){
        super.onRestart();

        System.out.println("REstarting");
        String univ_id = University.getUserSavedUniversityId(this);
        MySubscriptionController subscriptionController = new MySubscriptionController(this);
        ArrayList<Subscription> subscriptionList = subscriptionController.query(univ_id);

        MyEventController myEventController = new MyEventController(this);
        ArrayList<Event> eventList = myEventController.query(univ_id);

        if(subscriptionList.size() != noOfSubscriptions || (isMyEventSubscribed == false && eventList != null && eventList.size() >0) || (isMyEventSubscribed && eventList.size()==0)){
            System.out.println("Changed");

            noOfSubscriptions = subscriptionList.size();
            if(eventList != null && eventList.size() > 0){
                isMyEventSubscribed = true;
            }else{
                isMyEventSubscribed = false;
            }
            replaceFragments(subscriptionList ,eventList);
        }

    }

    private void replaceFragments(ArrayList<Subscription> subscriptionList , ArrayList<Event> eventList) {
        FragmentManager fm1 =  getFragmentManager();
        Fragment fragment1 = fm1.findFragmentById(R.id.fragmentContainer1);

        Bundle bundle =  new Bundle();
        bundle.putSerializable("subscriptionList",subscriptionList);
        bundle.putSerializable("eventList", eventList);


        // if (fragment1 == null) {
        fragment1 = new UpcomingEventCategoryFragment();
        fragment1.setArguments(bundle);
        FragmentDataLoader dataLoader = new FragmentDataLoader(VolleyController.getInstance(this).getRequestQueue());
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(Constants.QUERY_TYPE, Constants.GET_UNIV_BY_ID);
        params.put(Constants.ID,University.getUserSavedUniversityId(this));

        dataLoader.loadData(params, R.id.fragmentContainer1, fm1, fragment1 ,Constants.JSON_RESPONSE,true );
//        fm1.beginTransaction()
//                .replace(R.id.fragmentContainer1, fragment1)
//                .commit();
//        //}


        FragmentManager fm2 =  getFragmentManager();
        Fragment fragment2 = fm2.findFragmentById(R.id.fragmentContainer2);

        //if (fragment2 == null) {
        fragment2 = new CategoryGridFragment();
        fragment2.setArguments(bundle);
        fm2.beginTransaction()
                .replace(R.id.fragmentContainer2, fragment2)
                .commit();
    }

}
