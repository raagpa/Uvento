package edu.osu.uvento.controller.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.osu.uvento.constants.Constants;
import edu.osu.uvento.controller.adapters.ExpandableSubsribeToListAdapter;
import edu.osu.uvento.database.MySubscriptionController;
import edu.osu.uvento.model.Category;
import edu.osu.uvento.model.EventType;
import edu.osu.uvento.model.Subscription;
import edu.osu.uvento.model.University;
import edu.osu.uvento.uvento.R;

/**
 * Created by chiragpa on 11/9/14.
 */
public class SubscribeToFragment extends Fragment {

    private ExpandableSubsribeToListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;
    private HashMap<String, List> categoryMap;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.subscribe_to_fragment, container, false);

        expListView = (ExpandableListView) view.findViewById(R.id.lvExp);
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int headerPos, int childPos, long l) {
                System.out.println("Child Clicked");
                String header = listDataHeader.get(headerPos);

                MySubscriptionController controller = new MySubscriptionController(getActivity());
                Subscription subscription;

                if(headerPos == 2){
                    List <EventType> list  = categoryMap.get(header);
                    EventType eventType =  list.get(childPos);
                    subscription = new Subscription();
                    subscription.setId(eventType.getId());
                    subscription.setName(eventType.getType());
                    subscription.setUniv_id(eventType.getUni_id());
                    subscription.setImage_url(eventType.getImage_url());
                    subscription.setType("EVENT_TYPE");

                }else {
                    List <Category> list  = categoryMap.get(header);
                    Category category =  list.get(childPos);
                    subscription = new Subscription();
                    subscription.setId(category.getId());
                    subscription.setName(category.getName());
                    subscription.setUniv_id(category.getUni_id());
                    subscription.setImage_url(category.getImage_url());
                    subscription.setType("CATEGORY_TYPE");

                }


                if(view.getTag().equals("NOT_SUBSCRIBED")) {
                    controller.insert(subscription);
                    ImageView image= (ImageView) view.findViewById(R.id.subscribe);
                    image.setImageResource(R.drawable.sub_selected);
                    listAdapter.getSubscriptionList().add(subscription);
                    view.setTag("SUBSCRIBED");

                }else{
                    controller.delete(subscription.getId());
                    ImageView image= (ImageView) view.findViewById(R.id.subscribe);
                    image.setImageResource(R.drawable.sub_not_selected);
                    view.setTag("NOT_SUBSCRIBED");
                    listAdapter.removeSubscription(subscription);
                    //listAdapter.getSubscriptionList().remove(subscription);
                }

                return false;
            }
        });

        // preparing list data
        populateListData();

        MySubscriptionController controller = new MySubscriptionController(getActivity());
        List<Subscription> subscriptionList = controller.query(University.getUserSavedUniversityId(getActivity()));
        listAdapter = new ExpandableSubsribeToListAdapter(getActivity(), listDataHeader, listDataChild , subscriptionList , categoryMap);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        return view;
    }


    private void populateListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();
        categoryMap = new HashMap<String, List>();

        // Adding child data
        listDataHeader.add("Schools & Colleges");
        listDataHeader.add("Groups & Associations");
        listDataHeader.add("Event Types");

        List<String> schoolsColleges = new ArrayList<String>();
        List<Category> schoolCollegesCategory =  new ArrayList<Category>();
        List<String> groups = new ArrayList<String>();
        List<Category> groupsCategory =  new ArrayList<Category>();
        List<String> eventTypes = new ArrayList<String>();
        List<EventType> eventCategory =  new ArrayList<EventType>();

        if (getArguments() != null && getArguments().containsKey(Constants.JSON_RESPONSE_CAT_BY_UNIV)) {
            String[] JSONStrings = getArguments().getString(Constants.JSON_RESPONSE_CAT_BY_UNIV).split("\\|");
            Gson gson = new Gson();
            System.out.println("*****Entered*********");
            for (String s : JSONStrings) {
                System.out.println("Not Null**********");
                Category category = gson.fromJson(s, Category.class);
                if(category != null) {
                    System.out.println("Univ NAme *******" + category.getName());
                    schoolsColleges.add(category.getName());
                    schoolCollegesCategory.add(category);
                }
            }

        }


        if (getArguments() != null && getArguments().containsKey(Constants.JSON_RESPONSE_CAT_BY_GROUP)) {
            String[] JSONStrings = getArguments().getString(Constants.JSON_RESPONSE_CAT_BY_GROUP).split("\\|");
            Gson gson = new Gson();
            System.out.println("*****Entered1*********");
            for (String s : JSONStrings) {
                System.out.println("Not Null**********");
                Category category = gson.fromJson(s, Category.class);
                if(category != null) {
                    System.out.println("Univ NAme *******" + category.getName());
                    groups.add(category.getName());
                    groupsCategory.add(category);
                }
            }

        }


        if (getArguments() != null && getArguments().containsKey(Constants.JSON_RESPONSE_TYPES)) {
            String[] JSONStrings = getArguments().getString(Constants.JSON_RESPONSE_TYPES).split("\\|");
            Gson gson = new Gson();

            for (String s : JSONStrings) {
                System.out.println("Not Null**********");
                EventType type = gson.fromJson(s, EventType.class);
                if(type !=null) {
                    System.out.println("Univ NAme *******" + type.getType());
                    eventTypes.add(type.getType());
                    eventCategory.add(type);
                }
            }

        }

        listDataChild.put(listDataHeader.get(0), schoolsColleges); // Header, Child data
        listDataChild.put(listDataHeader.get(1), groups);
        listDataChild.put(listDataHeader.get(2), eventTypes);

        categoryMap.put(listDataHeader.get(0),schoolCollegesCategory);
        categoryMap.put(listDataHeader.get(1),groupsCategory);
        categoryMap.put(listDataHeader.get(2),eventCategory);


    }


    /*
     * Preparing the list data
     */
  /*  private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Schools & Colleges");
        listDataHeader.add("Groups & Associations");
        listDataHeader.add("Event Types");

        // Adding child data
        List<String> schoolsColleges = new ArrayList<String>();
        schoolsColleges.add("College of Engineering");
        schoolsColleges.add("Computer Science & Engineering");
        schoolsColleges.add("School of Music");
        schoolsColleges.add("Art & Science College");
        schoolsColleges.add("School of Medicine");
        schoolsColleges.add("School of Architecture");
        schoolsColleges.add("Departments of Physics");

        List<String> groupsAssociations = new ArrayList<String>();
        groupsAssociations.add("Ohio Union Activity Board");
        groupsAssociations.add("RPAC");
        groupsAssociations.add("Indian Student Association");
        groupsAssociations.add("Android Club");
        groupsAssociations.add("iOS Club");
        groupsAssociations.add("International Students Association");

        List<String> eventTypes = new ArrayList<String>();
        eventTypes.add("Sports");
        eventTypes.add("Concerts");
        eventTypes.add("Lectures");
        eventTypes.add("Plays");
        eventTypes.add("Seminars");

        listDataChild.put(listDataHeader.get(0), schoolsColleges); // Header, Child data
        listDataChild.put(listDataHeader.get(1), groupsAssociations);
        listDataChild.put(listDataHeader.get(2), eventTypes);
    }*/
}
