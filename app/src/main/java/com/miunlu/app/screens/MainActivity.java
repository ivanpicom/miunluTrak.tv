package com.miunlu.app.screens;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.WindowManager;

import com.miunlu.app.R;
import com.miunlu.app.fragments.MiunluListFragment;

public class MainActivity extends Activity {


    // Fragment map and custom google map class
    private FragmentManager fragmentManager;
    private MiunluListFragment miunluListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        miunluListFragment = new MiunluListFragment();


        fragmentTransaction.add(R.id.fragment_container, miunluListFragment);

        fragmentTransaction.commit();


    }

    @Override
    public void onPause() {
        super.onPause();  // Always call the superclass method first

        // stop all activity com.miunlu.app.network


    }

}
