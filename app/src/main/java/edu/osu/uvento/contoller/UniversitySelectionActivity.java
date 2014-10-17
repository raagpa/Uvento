package edu.osu.uvento.contoller;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;

import edu.osu.uvento.uvento.R;


public class UniversitySelectionActivity extends SingleFragmentActivity {

   public Fragment createNewFragment(){
       return new UniversitySelectionFragment();
   }
    



}
