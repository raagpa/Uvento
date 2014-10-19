package edu.osu.uvento.Services;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by chiragpa on 10/19/14.
 */
public class VolleyController  {
    private static RequestQueue mRequestQueue;
    private static VolleyController volleyController;

    private VolleyController(Context context){
        mRequestQueue = Volley.newRequestQueue(context);
    }

    public static synchronized VolleyController getInstance(Context context) {
        if(volleyController == null) {
            return new VolleyController(context);
        }
        return volleyController;
    }

    public RequestQueue getRequestQueue() {

        return mRequestQueue;
    }




}
