package com.example.fypwotablayout.sensorDisplay;


import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.fypwotablayout.R;
import com.example.fypwotablayout.fragment.fragControlFan;
import com.example.fypwotablayout.fragment.fragControlTemp;

public class control extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);

        ImageButton mTherButton = findViewById(R.id.thermoImgButton);
        ImageButton mFanButton = findViewById(R.id.fanImgButton);

        // Attaching the layout to the toolbar object
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        toolbar.setTitle("Control");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Fragment childFragment = new fragControlTemp();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.childFragContainer, childFragment).commit();

        mTherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast toast = Toast.makeText(getActivity(), "ther", Toast.LENGTH_SHORT);
//                toast.show();
                Fragment childFragment = new fragControlTemp();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.childFragContainer, childFragment).commit();
            }
        });

        mFanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment childFragment = new fragControlFan();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.childFragContainer, childFragment).commit();
//                Toast toast = Toast.makeText(getActivity(), "fan", Toast.LENGTH_SHORT);
//                toast.show();
            }
        });


    }


    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
