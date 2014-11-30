package edu.osu.uvento.controller.fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import edu.osu.uvento.constants.Constants;
import edu.osu.uvento.controller.activities.EventListActivity;
import edu.osu.uvento.model.Subscription;
import edu.osu.uvento.model.University;
import edu.osu.uvento.services.GetImageService;
import edu.osu.uvento.uvento.R;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class UpcomingEventCategoryFragment extends Fragment {

    ImageView mainImage;
    TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_upcoming_event_category, container, false);

        Bundle bundle = getArguments();
        ArrayList <Subscription> subscriptionList = (ArrayList<Subscription>)bundle.getSerializable("subscriptionList");
        int size = subscriptionList.size();
        System.out.println("Size " + size);
        changeUnivPreferences();

        mainImage = (ImageView) view.findViewById(R.id.mainImage);
        String url = University.getUserSavedUniversityImageURL(getActivity());
        if(url != null && !"".equals(url)) {
            GetImageService imageService = new GetImageService();
            imageService.getImage(getActivity(), mainImage, url);
        }

        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        //size = 2;
//        if(size == 0){
//            view.getLayoutParams().height = 3*height/4;
//        }else
        if (size < 3){
            view.getLayoutParams().height = height/2;
        }else{
            view.getLayoutParams().height = height / 4;
        }

        mainImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),EventListActivity.class);
                intent.putExtra("ALL_UPCOMING_EVENTS",true);
                startActivity(intent);

            }
        });

        textView = (TextView) view.findViewById(R.id.text_upcoming_events);
        textView.setText("All Upcoming Events");

        return view;
    }


    public void changeUnivPreferences() {

        University univ = null;
        if(getArguments() != null && getArguments().containsKey(Constants.JSON_RESPONSE)) {
            String[] JSONStrings = getArguments().getString(Constants.JSON_RESPONSE).split("\\|");
            Gson gson = new Gson();
            for (String s : JSONStrings) {
                System.out.println("Not Null**********");
                univ = gson.fromJson(s, University.class);
                System.out.println("Univ NAme *******" +  univ.getName());

            }

            University.saveUserUniversityPreference(getActivity(),univ.getName(),univ);

        }
    }


}
