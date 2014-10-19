package edu.osu.uvento.contoller;


import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import edu.osu.uvento.uvento.R;



public class CategoryGridFragment extends Fragment {




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category_grid, container, false);

        GridView gridview = (GridView) view.findViewById(R.id.gridview);
        gridview.setBackgroundColor(Color.WHITE);
        gridview.setVerticalSpacing(2);
        gridview.setHorizontalSpacing(2);
        gridview.setAdapter(new CustomGridAdapter(getActivity()));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText( getActivity(),"" + position, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(),EventListActivity.class);
                startActivity(i);

            }
        });
        return view;
    }







}
