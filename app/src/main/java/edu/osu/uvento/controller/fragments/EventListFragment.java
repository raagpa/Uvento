package edu.osu.uvento.controller.fragments;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

import edu.osu.uvento.constants.Constants;
import edu.osu.uvento.controller.adapters.CustomListAdapter;
import edu.osu.uvento.controller.activities.EventDetailsActivity;
import edu.osu.uvento.controller.others.OnFilterListener;
import edu.osu.uvento.model.Event;


public class EventListFragment extends ListFragment implements OnFilterListener {


    private ListView eventListView ;
    private ArrayList<Event> eventList = new ArrayList<Event>();
    private CustomListAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if(getArguments().getBoolean("MY_EVENT_CLICKED",false)){
            eventList = (ArrayList<Event>)getArguments().getSerializable("eventList");
        }else {
            populateEventList();
        }
        adapter = new CustomListAdapter(getActivity(),eventList);
        setListAdapter(adapter);


    }


    private void populateEventList() {

        if(getArguments() != null && getArguments().containsKey(Constants.JSON_RESPONSE)) {
            String[] JSONStrings = getArguments().getString(Constants.JSON_RESPONSE).split("\\|");
            Gson gson = new Gson();
            for (String s : JSONStrings) {
                System.out.println(s);
                Event event = gson.fromJson(s, Event.class);
                eventList.add(event);
            }

        }
    }


    @Override
    public void onListItemClick(ListView listView, View view, int position , long d){
        if(eventList != null) {
            Event event = eventList.get(position);
            System.out.println(" Event Title : "+ event.getTitle());
//            MyEventController eventController =  new MyEventController(getActivity());
//            eventController.insert(event);

            Intent intent = new Intent(getActivity(),EventDetailsActivity.class);
            intent.putExtra("EVENT_DETAILS",event);
            startActivity(intent);
//            System.out.println("Inserted");
        }
    }



    @Override
    public void filterByDate(String date){
        System.out.println("filterByDate CAlled");
        adapter.getFilter().filter(date);
    }

}
