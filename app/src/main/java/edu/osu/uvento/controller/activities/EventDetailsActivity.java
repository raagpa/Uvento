package edu.osu.uvento.controller.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import java.util.HashMap;

import edu.osu.uvento.constants.Constants;
import edu.osu.uvento.controller.fragments.EventDetailsDescFragment;
import edu.osu.uvento.controller.fragments.EventDetailsOptionsFragment;
import edu.osu.uvento.model.Event;
import edu.osu.uvento.services.FragmentDataLoader;
import edu.osu.uvento.services.VolleyController;
import edu.osu.uvento.uvento.R;

public class EventDetailsActivity extends ActivityWithMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        Event event = (Event)getIntent().getSerializableExtra("EVENT_DETAILS");

        FragmentManager fm = getFragmentManager();

        Fragment eventDescFragment = fm.findFragmentById(R.id.event_description);
        if (eventDescFragment == null) {
            eventDescFragment = new EventDetailsDescFragment();
            FragmentDataLoader dataLoader = new FragmentDataLoader(VolleyController.getInstance(this).getRequestQueue());
            HashMap<String, String> params = new HashMap<String, String>();
            params.put(Constants.QUERY_TYPE, Constants.GET_EVENT_DETAILS);
            params.put(Constants.ID, event.getId()+"");

            dataLoader.loadData(params, R.id.event_description, fm, eventDescFragment, Constants.JSON_RESPONSE, true);

        }


        Fragment optionFragment = fm.findFragmentById(R.id.event_options);
        if (optionFragment == null) {
            optionFragment = new EventDetailsOptionsFragment();
            Bundle bundle =  new Bundle();
            bundle.putSerializable("EVENT_DETAILS", event);
            optionFragment.setArguments(bundle);
            fm.beginTransaction().add(R.id.event_options, optionFragment).commit();
        }



    }
}
