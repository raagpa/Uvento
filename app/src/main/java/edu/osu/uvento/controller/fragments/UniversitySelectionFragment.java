package edu.osu.uvento.controller.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import edu.osu.uvento.constants.Constants;
import edu.osu.uvento.controller.activities.CategoryMultiFragmentActivity;
import edu.osu.uvento.model.University;
import edu.osu.uvento.uvento.R;


public class UniversitySelectionFragment extends Fragment {

    private Spinner univeristyListSpinner;
    private Button continueButton;
    private HashMap<String , University> univMap =  new HashMap<String , University>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_university_selection, container, false);

        univeristyListSpinner = (Spinner)view.findViewById(R.id.university_spinner);
        populateUniversitySpinner();

        continueButton = (Button)view.findViewById(R.id.continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               // need to change
               University.saveUserUniversityPreference(getActivity(),univeristyListSpinner.getSelectedItem().toString(),univMap.get(univeristyListSpinner.getSelectedItem()));
               System.out.println(univeristyListSpinner.getSelectedItem().toString());
               launchNextActivity();
            }
        });


        return view;
    }

    private void launchNextActivity() {
        System.out.println("################BYPASS########################");
        Intent i = new Intent(getActivity(),CategoryMultiFragmentActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    public void populateUniversitySpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, new ArrayList<String>());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        if(getArguments() != null && getArguments().containsKey(Constants.JSON_RESPONSE)) {
            String[] JSONStrings = getArguments().getString(Constants.JSON_RESPONSE).split("\\|");
            Gson gson = new Gson();
            for (String s : JSONStrings) {
                System.out.println("Not Null**********");
                University univ = gson.fromJson(s, University.class);
                System.out.println("Univ NAme *******" +  univ.getName());
                adapter.add(univ.getName());
                univMap.put(univ.getName(),univ);
            }

            univeristyListSpinner.setAdapter(adapter);
        }
    }





}
