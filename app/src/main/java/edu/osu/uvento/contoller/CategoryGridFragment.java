package edu.osu.uvento.contoller;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import edu.osu.uvento.uvento.R;



public class CategoryGridFragment extends Fragment {

    private static final String TAG = "CategoryGridFragment";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Enter onCreate");
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Enter onCreate");



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG,"Enter onCreateView");

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

        Log.d(TAG,"Exit onCreateView");
        return view;
    }


    @Override
    public void onResume() {
        Log.d(TAG, "Enter onResume");
        super.onResume();
        Log.d(TAG,"Exit onResume");

    }
    @Override
    public void onPause() {
        Log.d(TAG,"Enter onPause");
        super.onPause();
        Log.d(TAG,"Exit onPause");

    }
    @Override
    public void onStop() {
        Log.d(TAG,"Enter onStop");
        super.onStop();
        Log.d(TAG,"Enter onStop");

    }
    @Override
    public void onDestroy() {
        Log.d(TAG,"Enter onDestroy");
        super.onDestroy();
        Log.d(TAG,"Exit onDestroy");

    }

    @Override
    public void onDestroyView() {
        Log.d(TAG,"Enter onDestroyView");
        super.onDestroyView();
        Log.d(TAG,"Exit onDestroyView");

    }

    @Override
    public void onAttach(Activity activity) {
        Log.d(TAG,"Enter onAttach");
        super.onAttach(activity);
        Log.d(TAG,"Exit onAttach");

    }

    @Override
    public void onActivityCreated(Bundle savedBundleInstanceState) {
        Log.d(TAG,"Enter onActivityCreated");
        super.onActivityCreated(savedBundleInstanceState);
        Log.d(TAG,"Exit onActivityCreated");

    }

    @Override
    public void onDetach() {
        Log.d(TAG,"Enter onDetach");
        super.onDetach();
        Log.d(TAG,"Exit onDetach");

    }





}
