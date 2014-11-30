package edu.osu.uvento.controller.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import edu.osu.uvento.database.MyEventController;
import edu.osu.uvento.model.Event;
import edu.osu.uvento.model.University;
import edu.osu.uvento.services.GPSTracker;
import edu.osu.uvento.uvento.R;

public class EventDetailsOptionsFragment extends Fragment {


    private ImageView calender;
    private ImageView compass;
    private ImageView favorite;
    Event event = null;

    private ArrayList myEventList ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyEventController myEventController =  new MyEventController(getActivity());
        myEventList = myEventController.query(University.getUserSavedUniversityId(getActivity()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        event = (Event)getArguments().getSerializable("EVENT_DETAILS");

        View view=inflater.inflate(R.layout.fragment_event_options, container, false);
        calender=(ImageView)view.findViewById(R.id.calender);
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addEventToCalendar(event);
            }
        });

        compass=(ImageView)view.findViewById(R.id.comapass);
        compass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGoogleMapsWithDirection();

            }
        });


        favorite=(ImageView)view.findViewById(R.id.favorite);

        if(event.isInMyEvents(getActivity())){
            ImageView image= (ImageView) view.findViewById(R.id.favorite);
            image.setImageResource(R.drawable.favorite_click);
        }else{
            ImageView image= (ImageView) view.findViewById(R.id.favorite);
            image.setImageResource(R.drawable.favorite_not_selected);
        }

        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(event.isInMyEvents(getActivity())){
                    deleteFromMyEvents(event);
                    ImageView image= (ImageView) view.findViewById(R.id.favorite);
                    image.setImageResource(R.drawable.favorite_not_selected);
                }else {
                    addEventToMyEvents(event);
                    ImageView image= (ImageView) view.findViewById(R.id.favorite);
                    image.setImageResource(R.drawable.favorite_click);
                }
            }
        });

        return view;
    }

    private void openGoogleMapsWithDirection() {
        //String uri = String.format(Locale.ENGLISH, "geo:%f,%f"+"z:%f", 40.008037, -83.028652,14);
        GPSTracker gps = new GPSTracker(getActivity());
        String saddr = "";
        String daddr = "daddr=" + event.getLatitude() +"," + event.getLongitude() ;

        // check if GPS enabled
        if(gps.canGetLocation()) {

            double latitude = gps.getLatitude();
            double longitude = gps.getLongitude();

            saddr = "saddr=" + latitude + "," + longitude;

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?&" + saddr + "&" + daddr +"&z=14"));
            startActivity(intent);
        }else{
            gps.showSettingsAlert();
        }

    }


    private void addEventToCalendar(Event event ) {
        String date = event.getDate();
        String time = event.getTime();
//        String date="12-19-2014";
//        String time = "17:30";

        String[]day=date.split("-");

        int mm=Integer.parseInt(day[0])-1;
        int dd= Integer.parseInt(day[1]);
        int yy=Integer.parseInt(day[2]);
        String hrmm []= time.split(":");
        int hr=Integer.parseInt(hrmm[0]);
        int mt=Integer.parseInt(hrmm[1]);

        Calendar beginTime = Calendar.getInstance();


        beginTime.set(yy, Calendar.JANUARY,dd,hr,mt);
        beginTime.set(Calendar.MONTH,mm);

        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                .putExtra(CalendarContract.Events.TITLE, event.getTitle())
                .putExtra(CalendarContract.Events.EVENT_LOCATION, event.getLocation())
                .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);

        startActivity(intent);

    }



    private void addEventToMyEvents(Event event) {

        MyEventController myEventController = new MyEventController(getActivity());
        myEventController.insert(event);
    }


    private void deleteFromMyEvents(Event event){
        MyEventController myEventController = new MyEventController(getActivity());
        myEventController.delete(event.getId());

    }




}
