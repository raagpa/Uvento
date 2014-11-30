package edu.osu.uvento.controller.fragments;

import android.app.Fragment;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import android.location.Address;

import java.io.IOException;
import java.util.List;




import edu.osu.uvento.constants.Constants;
import edu.osu.uvento.model.Event;
import edu.osu.uvento.services.GetImageService;
import edu.osu.uvento.uvento.R;


public class EventDetailsDescFragment extends Fragment {


    private Event eventDetails;
    private GoogleMap googleMap;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_description, container, false);

        populateEventDetails(view);
        initilizeMap();

        return view;
    }

    /**
     * function to load map. If map is not created it will create it for you
     * */
    private void initilizeMap() {
        if (googleMap == null) {
            googleMap = ((MapFragment) getFragmentManager().findFragmentById(
                    R.id.map)).getMap();

            // check if map is created successfully or not
            if (googleMap == null) {
                Toast.makeText(getActivity(),
                        "Sorry! unable to create maps", Toast.LENGTH_SHORT)
                        .show();
            }

            //googleMap.setMyLocationEnabled(true);

//            try {
//                System.out.println("Location : "+ eventDetails.getLocation());
//                List<Address> result = new Geocoder(getActivity()).getFromLocationName(eventDetails.getLocation(), 10);
//                if(result !=null && result.size() >0) {
//                    double lat = result.get(0).getLatitude();
//                    double longitude = result.get(0).getLongitude();
//                    googleMap.addMarker(new MarkerOptions()
//                            .position(new LatLng(lat, longitude))
//                            .title("Event"));
//                    System.out.println("Zoom Level" + googleMap.getMaxZoomLevel());
            googleMap.addMarker(new MarkerOptions()
                            .position(new LatLng(eventDetails.getLatitude(),eventDetails.getLongitude()))
                            .title(eventDetails.getTitle()));
                    googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(eventDetails.getLatitude(), eventDetails.getLongitude()),
                            14));
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
       }
    }


    private void populateEventDetails(View view) {

        if(getArguments() != null && getArguments().containsKey(Constants.JSON_RESPONSE)) {
            String[] JSONStrings = getArguments().getString(Constants.JSON_RESPONSE).split("\\|");
            Gson gson = new Gson();
            for (String s : JSONStrings) {
                System.out.println(s);
                eventDetails = gson.fromJson(s, Event.class);

            }

        }


        if(eventDetails != null){
            System.out.println("POPULATED1");
            TextView eventTitle = (TextView)view.findViewById(R.id.event_title_value);
            eventTitle.setText(eventDetails.getTitle());
            getActivity().setTitle(eventDetails.getTitle());
            System.out.println("POPULATED2");
            TextView eventDesc = (TextView)view.findViewById(R.id.event_desc_value);
            eventDesc.setText(eventDetails.getDescription());
            TextView eventDate = (TextView)view.findViewById(R.id.event_date_value);
            eventDate.setText(eventDetails.getDate());
            System.out.println("POPULATED3");
            TextView eventTime = (TextView) view.findViewById(R.id.event_time_value);
            eventTime.setText(eventDetails.getTime());
            TextView eventLocation = (TextView)view.findViewById(R.id.event_location_value);
            eventLocation.setText(eventDetails.getLocation());
            TextView contactName = (TextView)view.findViewById(R.id.event_contact_name_value);
            contactName.setText(eventDetails.getContact_name());
            System.out.println("POPULATED4");
            TextView contactPhone = (TextView)view.findViewById(R.id.event_contact_phone_value);
            contactPhone.setText(eventDetails.getPhone());
            TextView contactEmail = (TextView)view.findViewById(R.id.event_contact_email_value);
            contactEmail.setText(eventDetails.getEmail());
            System.out.println("POPULATED5");
            TextView category = (TextView)view.findViewById(R.id.event_category_value);
            category.setText(eventDetails.getName());
            TextView type = (TextView) view.findViewById(R.id.event_type_value);
            type.setText(eventDetails.getType());

            System.out.println("POPULATED6");
            ImageView eventImageView = ( ImageView) view.findViewById(R.id.event_image);
            String url = eventDetails.getImage_url();

            if(url !=null && !"".equals(url)){
                System.out.println("URL " +url);
                GetImageService imageService = new GetImageService();
                imageService.getImage(getActivity(), eventImageView, url);

            }

        }
    }


}
