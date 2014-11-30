package edu.osu.uvento.controller.activities;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import edu.osu.uvento.uvento.R;

/**
 * Created by chiragpa on 11/9/14.
 */
public class ActivityWithMenu extends Activity{
    private static final String TAG = "ActivityWithMenu";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        Log.d(TAG, "**********************CreateOptionsMenu*************************");
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.subscribe:
                startActivity(new Intent(getBaseContext(),SubscribeToActivity.class));
                return true;
            case R.id.setting:
                Log.d(TAG, "**********************Settings*************************");
                startActivity(new Intent(getBaseContext(),AppPreferenceActivity.class));
                return true;
//            case R.id.menu_exit:
//                quitApplication();
//                return true;
        }
        return false;
    }


}
