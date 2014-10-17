package edu.osu.uvento.contoller;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentBreadCrumbs;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import java.util.Objects;

import edu.osu.uvento.uvento.R;

// Drew imports:
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;


public class UniversitySelectionActivity extends Activity {

	// Added by Drew:
	private boolean hasNetworkConnection() {
		ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		boolean isConnected = true;
		boolean isWifiAvailable = networkInfo.isAvailable();
		boolean isWifiConnected = networkInfo.isConnected();

		networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		boolean isMobileAvailable = networkInfo.isAvailable();
		boolean isMobileConnected = networkInfo.isConnected();
		isConnected = (isMobileAvailable && isMobileConnected) || (isWifiAvailable && isWifiConnected);
		return (isConnected);
	}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_selection);

        FragmentManager fm =  getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if (fragment == null) {
            fragment = new UniversitySelectionFragment();
            fm.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }

		// Drew's stuff:
		TextView connectionStatus = (TextView) findViewById(R.id.connection_status);
		if(hasNetworkConnection()) {
			connectionStatus.setText("Connected.");
		} else {
			connectionStatus.setText("Not connected.");
		}

		if(hasNetworkConnection()) {
			String url = "http://www.cbusdesigns.com/evento/evento.php";
			InputStream is = null;

			try {
				// defaultHttpClient
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(url);
				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

    }
    



}
