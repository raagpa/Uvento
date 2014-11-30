package edu.osu.uvento.controller.activities;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;

import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import com.google.android.gms.maps.GoogleMap;


import edu.osu.uvento.uvento.R;

public class GoogleMapsActivity extends Activity {

    private View rootView;

    GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setContentView(R.layout.activity_google_maps);

        this.rootView=findViewById(R.id.map_view);

        FragmentManager myFragmentManager = getFragmentManager();
        //MapFragment mapFragment = (MapFragment)myFragmentManager.findFragmentById(R.id.map);
        //map = mapFragment.getMap();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Rect rect = new Rect();
        rootView.getHitRect(rect);
        if (!rect.contains((int)event.getX(), (int)event.getY())){
            setFinishOnTouchOutside(false);
            return true;
        }
        return false;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {

            finish();
        }
        return true;
    }
}
