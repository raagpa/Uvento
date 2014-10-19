package edu.osu.uvento.contoller;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;

import edu.osu.uvento.uvento.R;

public class CategoryMultiFragmentActivity extends Activity {

    private static final String TAG = "CategoryMultiFragmentActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG,"Enter onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_fragment);
        setTitle("Subscriptions");

        FragmentManager fm1 =  getFragmentManager();
        Fragment fragment1 = fm1.findFragmentById(R.id.fragmentContainer1);

        if (fragment1 == null) {
            fragment1 = new UpcomingEventCategoryFragment();
            fm1.beginTransaction()
                    .add(R.id.fragmentContainer1, fragment1)
                    .commit();
        }


        FragmentManager fm2 =  getFragmentManager();
        Fragment fragment2 = fm2.findFragmentById(R.id.fragmentContainer2);

        if (fragment2 == null) {
            fragment2 = new CategoryGridFragment();
            fm2.beginTransaction()
                    .add(R.id.fragmentContainer2, fragment2)
                    .commit();
        }

        Log.d(TAG,"Exit onCreate");
    }

    @Override
    protected void onStart() {
        Log.d(TAG,"Enter onStart");
        super.onStart();
        Log.d(TAG,"Exit onStart");
        // The activity is about to become visible.
    }
    @Override
    protected void onResume() {
        Log.d(TAG,"Enter onResume");
        super.onResume();
        Log.d(TAG,"Exit onResume");
        // The activity has become visible (it is now "resumed").
    }
    @Override
    protected void onPause() {
        Log.d(TAG,"Enter onPause");
        super.onPause();
        Log.d(TAG,"Exit onPause");
        // Another activity is taking focus (this activity is about to be "paused").
    }
    @Override
    protected void onStop() {
        Log.d(TAG,"Enter onStop");
        super.onStop();
        Log.d(TAG,"Enter onStop");
        // The activity is no longer visible (it is now "stopped")
    }
    @Override
    protected void onDestroy() {
        Log.d(TAG,"Enter onDestroy");
        super.onDestroy();
        Log.d(TAG,"Exit onDestroy");
        // The activity is about to be destroyed.
    }

}
