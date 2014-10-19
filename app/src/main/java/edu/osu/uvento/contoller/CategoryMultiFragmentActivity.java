package edu.osu.uvento.contoller;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import edu.osu.uvento.uvento.R;

public class CategoryMultiFragmentActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
    }

}
