package com.example.fypwotablayout.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fypwotablayout.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sdsmdg.harjot.crollerTest.Croller;
import com.sdsmdg.harjot.crollerTest.OnCrollerChangeListener;

public class fragControlTemp extends Fragment {

    private Long crollerProgress;
    private Croller mCroller;
    private ImageButton plusButton;
    private ImageButton minusButton;
    private  ImageButton powerButton;
    private DatabaseReference mDatabase;

    public fragControlTemp() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_control_temp, container, false);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mCroller = root.findViewById(R.id.croller);
        plusButton = root.findViewById(R.id.plusButton);
        minusButton = root.findViewById(R.id.minusButton);
        powerButton = root.findViewById(R.id.powerButton);

        init();

        mCroller.setOnCrollerChangeListener(new OnCrollerChangeListener() {
            @Override
            public void onProgressChanged(Croller croller, int progress) {
                crollerProgress = mCroller.getProgress() + (long)15;
                mCroller.setLabel(crollerProgress.toString());
            }

            @Override
            public void onStartTrackingTouch(Croller croller) {

            }

            @Override
            public void onStopTrackingTouch(Croller croller) {
                mDatabase.child("room1").child("Control").child("Temperature").setValue(crollerProgress);
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(crollerProgress>=30 || mCroller.getProgress() >= mCroller.getMax()) {
                    crollerProgress = (long)30;
                    mCroller.setProgress(mCroller.getMax());
                }else{
                    crollerProgress ++;
                    mCroller.setProgress(mCroller.getProgress()+ 1);
                }
                mDatabase.child("room1").child("Control").child("Temperature").setValue(crollerProgress);
                mCroller.setLabel(crollerProgress.toString());
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(crollerProgress<=16 || mCroller.getProgress() <= mCroller.getMin()) {
                    crollerProgress = (long)16;
                    mCroller.setProgress(mCroller.getMin());
                }else{
                    crollerProgress --;
                    mCroller.setProgress(mCroller.getProgress()- 1);
                }
                mDatabase.child("room1").child("Control").child("Temperature").setValue(crollerProgress);
                mCroller.setLabel(crollerProgress.toString());
            }
        });

        powerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("room1").child("Control").child("on").setValue(1);
            }
        });

        return root;
    }

    private void init(){
        mDatabase.child("room1").child("Control").child("Temperature").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    crollerProgress = (long)dataSnapshot.getValue();
                    mCroller.setProgress((int)(crollerProgress-15));
                    mCroller.setLabel(crollerProgress.toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
