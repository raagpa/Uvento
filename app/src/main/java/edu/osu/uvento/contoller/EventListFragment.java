package edu.osu.uvento.contoller;

import android.app.ListFragment;
import android.os.Bundle;

import java.util.ArrayList;

import edu.osu.uvento.model.Event;

public class EventListFragment extends ListFragment {


    private ArrayList<Event> eventList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventList = Event.getEventList();

       // ArrayAdapter<Event> arrayAdapter = new ArrayAdapter<Event>(getActivity(),android.R.layout.simple_list_item_1,eventList);
       CustomListAdapter adapter = new CustomListAdapter(getActivity(),eventList);

       setListAdapter(adapter);

    }





}
