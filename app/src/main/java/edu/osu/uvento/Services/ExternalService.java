package edu.osu.uvento.Services;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ArrayAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by chiragpa on 10/18/14.
 */
public class ExternalService {



    public boolean hasNetworkConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isConnected = false;
        boolean isWifiAvailable = networkInfo.isAvailable();
        boolean isWifiConnected = networkInfo.isConnected();

        networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileAvailable = networkInfo.isAvailable();
        boolean isMobileConnected = networkInfo.isConnected();
        isConnected = (isMobileAvailable && isMobileConnected) || (isWifiAvailable && isWifiConnected);
        return (isConnected);
    }




    public static void getUniversities(Context context , final ArrayAdapter adapter){

        System.out.println("#################Inside getUniversities###############");
        final ArrayList universityList = new ArrayList();
        RequestQueue queue = VolleyController.getInstance(context).getRequestQueue();
        String url = "http://www.cbusdesigns.com/evento/evento.php";


        Response.Listener successResponse = new Response.Listener<String>() {
            public void onResponse(String response) {
                Gson gson = new Gson();
                String[] responseAsStringArray = gson.fromJson(response, String[].class);
                String flattenedResponse = "";
                for (String s: responseAsStringArray) {
                    flattenedResponse += s + "\n";

                    adapter.add(s);
                }
            }
        };

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, successResponse
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // mResponse.setText("That didn't work!");
            }
        });

        queue.add(stringRequest);

        System.out.println("#################Exiting getUniversities###############");

    }
}
