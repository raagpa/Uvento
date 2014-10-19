package edu.osu.uvento.contoller;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

        mainImage = (ImageView) view.findViewById(R.id.mainImage);
        DisplayMetrics metrics = this.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        view.getLayoutParams().height = height/4;

        mainImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),EventListActivity.class);
                startActivity(intent);

            }
        });

        textView = (TextView) view.findViewById(R.id.text_upcoming_events);
        textView.setText("All Upcoming Events");

        return view;
    }


}
