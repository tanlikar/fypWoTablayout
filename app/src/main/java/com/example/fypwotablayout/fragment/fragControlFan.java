package com.example.fypwotablayout.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fypwotablayout.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class fragControlFan extends Fragment {

    private Button mLowButton;
    private Button mMedButton;
    private Button mHighButton;
    private Button mAutoButton;
    private Button mQuietButton;
    private Button mTurboButton;

    private DatabaseReference mDatabaseReference;
    private Long fanSpeed;

    public fragControlFan() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_control_fan, container, false);

        mLowButton = root.findViewById(R.id.fanLowButton);
        mMedButton = root.findViewById(R.id.fanMedButton);
        mHighButton = root.findViewById(R.id.fanHighButton);
        mTurboButton = root.findViewById(R.id.fanTurboButton);
        mQuietButton = root.findViewById(R.id.fanQuiteButton);
        mAutoButton = root.findViewById(R.id.fanAutoButton);

        mDatabaseReference = FirebaseDatabase.getInstance().getReference();
        mDatabaseReference.child("room1").child("Control").child("fan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    fanSpeed = (long)dataSnapshot.getValue();
                    if(fanSpeed == 0) {
                        mLowButton.setBackgroundResource(R.drawable.circle);
                        mMedButton.setBackgroundResource(0);
                        mHighButton.setBackgroundResource(0);
                        mQuietButton.setBackgroundResource(0);
                        mAutoButton.setBackgroundResource(0);
                        mTurboButton.setBackgroundResource(0);
                    }else if (fanSpeed == 1){
                        mLowButton.setBackgroundResource(0);
                        mMedButton.setBackgroundResource(R.drawable.circle);
                        mHighButton.setBackgroundResource(0);
                        mQuietButton.setBackgroundResource(0);
                        mAutoButton.setBackgroundResource(0);
                        mTurboButton.setBackgroundResource(0);
                    }else if (fanSpeed == 2){
                        mLowButton.setBackgroundResource(0);
                        mMedButton.setBackgroundResource(0);
                        mHighButton.setBackgroundResource(R.drawable.circle);
                        mQuietButton.setBackgroundResource(0);
                        mAutoButton.setBackgroundResource(0);
                        mTurboButton.setBackgroundResource(0);
                    }else if(fanSpeed == 4){
                        mLowButton.setBackgroundResource(0);
                        mMedButton.setBackgroundResource(0);
                        mHighButton.setBackgroundResource(0);
                        mQuietButton.setBackgroundResource(R.drawable.circle);
                        mAutoButton.setBackgroundResource(0);
                        mTurboButton.setBackgroundResource(0);
                    }else if(fanSpeed == 5){
                        mLowButton.setBackgroundResource(0);
                        mMedButton.setBackgroundResource(0);
                        mHighButton.setBackgroundResource(0);
                        mQuietButton.setBackgroundResource(0);
                        mAutoButton.setBackgroundResource(R.drawable.circle);
                        mTurboButton.setBackgroundResource(0);
                    }else if(fanSpeed == 3){
                        mLowButton.setBackgroundResource(0);
                        mMedButton.setBackgroundResource(0);
                        mHighButton.setBackgroundResource(0);
                        mQuietButton.setBackgroundResource(0);
                        mAutoButton.setBackgroundResource(0);
                        mTurboButton.setBackgroundResource(R.drawable.circle);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mLowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLowButton.setBackgroundResource(R.drawable.circle);
                mMedButton.setBackgroundResource(0);
                mHighButton.setBackgroundResource(0);
                mQuietButton.setBackgroundResource(0);
                mAutoButton.setBackgroundResource(0);
                mTurboButton.setBackgroundResource(0);
                mDatabaseReference.child("room1").child("Control").child("fan").setValue(0);
            }
        });

        mMedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLowButton.setBackgroundResource(0);
                mMedButton.setBackgroundResource(R.drawable.circle);
                mHighButton.setBackgroundResource(0);
                mQuietButton.setBackgroundResource(0);
                mAutoButton.setBackgroundResource(0);
                mTurboButton.setBackgroundResource(0);
                mDatabaseReference.child("room1").child("Control").child("fan").setValue(1);
            }
        });

        mHighButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLowButton.setBackgroundResource(0);
                mMedButton.setBackgroundResource(0);
                mHighButton.setBackgroundResource(R.drawable.circle);
                mQuietButton.setBackgroundResource(0);
                mAutoButton.setBackgroundResource(0);
                mTurboButton.setBackgroundResource(0);
                mDatabaseReference.child("room1").child("Control").child("fan").setValue(2);
            }
        });

        mTurboButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLowButton.setBackgroundResource(0);
                mMedButton.setBackgroundResource(0);
                mHighButton.setBackgroundResource(0);
                mQuietButton.setBackgroundResource(0);
                mAutoButton.setBackgroundResource(0);
                mTurboButton.setBackgroundResource(R.drawable.circle);
                mDatabaseReference.child("room1").child("Control").child("fan").setValue(3);
            }
        });

        mQuietButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLowButton.setBackgroundResource(0);
                mMedButton.setBackgroundResource(0);
                mHighButton.setBackgroundResource(0);
                mQuietButton.setBackgroundResource(R.drawable.circle);
                mAutoButton.setBackgroundResource(0);
                mTurboButton.setBackgroundResource(0);
                mDatabaseReference.child("room1").child("Control").child("fan").setValue(4);
            }
        });

        mAutoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLowButton.setBackgroundResource(0);
                mMedButton.setBackgroundResource(0);
                mHighButton.setBackgroundResource(0);
                mQuietButton.setBackgroundResource(0);
                mAutoButton.setBackgroundResource(R.drawable.circle);
                mTurboButton.setBackgroundResource(0);
                mDatabaseReference.child("room1").child("Control").child("fan").setValue(5);
            }
        });

        return root;
    }
}
