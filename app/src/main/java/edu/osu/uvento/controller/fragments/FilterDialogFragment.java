package edu.osu.uvento.controller.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.app.DialogFragment;

import edu.osu.uvento.uvento.R;


public  class FilterDialogFragment extends DialogFragment {
    Button filterButton;

    /**
     * Create a new instance of MyDialogFragment, providing "num"
     * as an argument.
     */
    public static FilterDialogFragment newInstance() {
        FilterDialogFragment f = new FilterDialogFragment();



        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Holo_Light_Dialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_filter_dialog, container, false);
        getDialog().setTitle("Filter By");



        filterButton = (Button)v.findViewById(R.id.filter_button);
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getDialog().hide();

                RadioGroup radioGroup = (RadioGroup)v.findViewById(R.id.filterRadioGroup);
                int checkRadioButtonID = radioGroup.getCheckedRadioButtonId();

                if(R.id.byDateRadio == checkRadioButtonID){
                    DatePicker datePicker = (DatePicker)v.findViewById(R.id.datePicker);
                    String date =  datePicker.getMonth() +1 + "-"+ datePicker.getDayOfMonth() +"-"+datePicker.getYear();
                    System.out.println("DAte is : "+ date);
                    ((EventListFragment)getTargetFragment()).filterByDate(date);
                }else{

                    System.out.println("Spinner");
                }

            }
        });
        return v;
    }



}