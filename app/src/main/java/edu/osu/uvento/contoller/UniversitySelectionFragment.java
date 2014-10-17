package edu.osu.uvento.contoller;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import edu.osu.uvento.model.University;
import edu.osu.uvento.uvento.R;


public class UniversitySelectionFragment extends Fragment {

    private Spinner univeristyListSpinner;
    private Button continueButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(University.isUserUniversityPreferenceSaved(getActivity())){
            launchNextActivity();
        }
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_university_selection, container, false);

        univeristyListSpinner = (Spinner)view.findViewById(R.id.university_spinner);
        populateUniversitySpinner();

        continueButton = (Button)view.findViewById(R.id.continue_button);
        continueButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               University.saveUserUniversityPreference(getActivity(),univeristyListSpinner.getSelectedItem().toString());
               System.out.println(univeristyListSpinner.getSelectedItem().toString());
               launchNextActivity();
            }
        });

        return view;
    }

    private void launchNextActivity() {
            System.out.println("################BYPASS########################");
        Intent i = new Intent(getActivity(),CategoryGridActivity.class);
        startActivity(i);
        getActivity().finish();
    }

    private void populateUniversitySpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_item, new ArrayList<String>());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        List univList = University.getUniversities();
        for(int i=0 ;i< univList.size();i++) {
            adapter.add(univList.get(i).toString());
        }
        univeristyListSpinner.setAdapter(adapter);
    }


}
