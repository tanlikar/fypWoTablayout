package com.example.fypwotablayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import com.example.fypwotablayout.Storage.AppPreferences;
import com.example.fypwotablayout.Storage.ChildConstants;
import com.example.fypwotablayout.Storage.PrefKey;
import com.example.fypwotablayout.helper.generalData;
import com.example.fypwotablayout.sensorDisplay.control;
import com.example.fypwotablayout.sensorDisplay.graphDisplay;
import com.example.fypwotablayout.viewAdapter.CustomAdapter;
import com.example.fypwotablayout.viewAdapter.SensorGridAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class display extends AppCompatActivity implements PrefKey, ChildConstants {

    String[] sensorReading = new String[7];
    ArrayList<String> mRoomArray = new ArrayList<>();
    AppPreferences mPref;

    final int[] sensorIcon ={
        R.drawable.ic_air_conditioner_remote_control_free_icon_1,
        R.drawable.thermostat_black,
        R.drawable.ic_humidity_svgrepo_com,
        R.drawable.co2,
        R.drawable.pm10,
        R.drawable.pm25,
        R.drawable.voc
    };

    String roomName;
    int roomPosition;
    String roomNum;

    GridView mGridView;
    generalData mGeneralData = new generalData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display2);

        mPref = new AppPreferences(this);

        try {
            mRoomArray = mPref.getListString(ROOM_KEY);
        }catch(Exception ignored){

        }

        roomName = getIntent().getStringExtra(BUNDLE_ROOM);
        roomPosition = getIntent().getIntExtra(BUNDLE_POSITION, 0) + 1;

        roomNum = "room" + roomPosition;

        sensorReading[0] = "Control";
        getData();

        mGridView = findViewById(R.id.gridview);
        SensorGridAdapter adapter = new SensorGridAdapter(display.this, sensorReading, sensorIcon);
        mGridView.setAdapter(adapter);

        // Attaching the layout to the toolbar object
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        toolbar.setTitle(roomName);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                if(i == 0){
                    Intent intent = new Intent(display.this, control.class);
                    intent.putExtra(ROOM_POSITION_DISPLAY, roomNum);
                    intent.putExtra(SENSOR_TYPE, CONTROLCHILD);
                    startActivity(intent);
                }else if (i == 1){
                    Intent intent = new Intent(display.this, graphDisplay.class);
                    intent.putExtra(ROOM_POSITION_DISPLAY, roomNum);
                    intent.putExtra(SENSOR_TYPE, TEMPCHILD);
                    startActivity(intent);

                }else if (i == 2){
                    Intent intent = new Intent(display.this, graphDisplay.class);
                    intent.putExtra(ROOM_POSITION_DISPLAY, roomNum);
                    intent.putExtra(SENSOR_TYPE, HUMICHILD);
                    startActivity(intent);

                }else if (i == 3){
                    Intent intent = new Intent(display.this, graphDisplay.class);
                    intent.putExtra(ROOM_POSITION_DISPLAY, roomNum);
                    intent.putExtra(SENSOR_TYPE, CO2CHILD);
                    startActivity(intent);

                }else if (i == 4){
                    Intent intent = new Intent(display.this, graphDisplay.class);
                    intent.putExtra(ROOM_POSITION_DISPLAY, roomNum);
                    intent.putExtra(SENSOR_TYPE, PM10CHILD);
                    startActivity(intent);

                }else if (i == 5){
                    Intent intent = new Intent(display.this, graphDisplay.class);
                    intent.putExtra(ROOM_POSITION_DISPLAY, roomNum);
                    intent.putExtra(SENSOR_TYPE, PM25CHILD);
                    startActivity(intent);

                }else if (i == 6){
                    Intent intent = new Intent(display.this, graphDisplay.class);
                    intent.putExtra(ROOM_POSITION_DISPLAY, roomNum);
                    intent.putExtra(SENSOR_TYPE, VOCCHILD);
                    startActivity(intent);

                }

            }
        });
    }

    void getData(){

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child(roomNum).child(TEMPCHILD).orderByKey().limitToLast(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()){
                    sensorReading[1] = dataSnapshot.getValue(generalData.class).getData().toString();
                    SensorGridAdapter adapter = new SensorGridAdapter(display.this, sensorReading, sensorIcon);
                    mGridView.setAdapter(adapter);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        mDatabase.child(roomNum).child(HUMICHILD).orderByKey().limitToLast(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()){
                    sensorReading[2] = dataSnapshot.getValue(generalData.class).getData().toString();
                    SensorGridAdapter adapter = new SensorGridAdapter(display.this, sensorReading, sensorIcon);
                    mGridView.setAdapter(adapter);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child(roomNum).child(CO2CHILD).orderByKey().limitToLast(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()){
                    sensorReading[3] = dataSnapshot.getValue(generalData.class).getData().toString();
                    SensorGridAdapter adapter = new SensorGridAdapter(display.this, sensorReading, sensorIcon);
                    mGridView.setAdapter(adapter);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child(roomNum).child(PM10CHILD).orderByKey().limitToLast(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()){
                    sensorReading[4] = dataSnapshot.getValue(generalData.class).getData().toString();
                    SensorGridAdapter adapter = new SensorGridAdapter(display.this, sensorReading, sensorIcon);
                    mGridView.setAdapter(adapter);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child(roomNum).child(PM25CHILD).orderByKey().limitToLast(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()){
                    sensorReading[5] = dataSnapshot.getValue(generalData.class).getData().toString();
                    SensorGridAdapter adapter = new SensorGridAdapter(display.this, sensorReading, sensorIcon);
                    mGridView.setAdapter(adapter);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mDatabase.child(roomNum).child(VOCCHILD).orderByKey().limitToLast(1).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                if(dataSnapshot.exists()){
                    sensorReading[6] = dataSnapshot.getValue(generalData.class).getData().toString();
                    SensorGridAdapter adapter = new SensorGridAdapter(display.this, sensorReading, sensorIcon);
                    mGridView.setAdapter(adapter);
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_remove) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Are you sure ?");

// Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mRoomArray.remove(roomPosition - 1);
                    mPref.putListString(ROOM_KEY, mRoomArray);

                    Intent intent = new Intent(display.this, MainActivity.class);
                    startActivity(intent);
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();

            Toast toast = Toast.makeText(getApplicationContext(), roomName + " removed", Toast.LENGTH_SHORT);
            toast.show();

            return true;
        }

        return super.onOptionsItemSelected(item);
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
