package com.example.fypwotablayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.fypwotablayout.Storage.PrefKey;
import com.example.fypwotablayout.viewAdapter.CustomAdapter;
import com.google.android.gms.auth.api.signin.internal.Storage;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements PrefKey {

    ArrayList<String> mRoomName = new ArrayList<>();
    ArrayList<Integer> imageId = new ArrayList<>();
    AppPreferences mPref;

    GridView mGridView;
    private String mInput = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

               mPref = new AppPreferences(this);

        try{
            mRoomName = mPref.getListString(ROOM_KEY);

            for(int x = 0; x<mRoomName.size(); x++){
                imageId.add(R.drawable.icon_room);
            };

        }catch (Exception ignored){
        }

        mGridView = findViewById(R.id.gridview);
        CustomAdapter adapter = new CustomAdapter(MainActivity.this, mRoomName, imageId);
        mGridView.setAdapter(adapter);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, display.class);
                intent.putExtra(BUNDLE_ROOM, mRoomName.get(i));
                intent.putExtra(BUNDLE_POSITION, i);
                startActivity(intent);
                //Toast.makeText(MainActivity.this, "You Clicked at " +mRoomName.get(i), Toast.LENGTH_SHORT).show();
            }
        });

        // Attaching the layout to the toolbar object
        Toolbar toolbar = (Toolbar) findViewById(R.id.tool_bar);
        // Setting toolbar as the ActionBar with setSupportActionBar() call
        toolbar.setTitle("Rooms");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Key in Room Name");

// Set up the input
            final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

// Set up the buttons
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    mInput = input.getText().toString();
                    mRoomName.add(mInput);
                    imageId.add(R.drawable.icon_room);
                    CustomAdapter adapter = new CustomAdapter(MainActivity.this, mRoomName, imageId);
                    mGridView.setAdapter(adapter);
                    mPref.putListString(ROOM_KEY, mRoomName);

                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });

            builder.show();

            Toast toast = Toast.makeText(getApplicationContext(), mInput, Toast.LENGTH_SHORT);
            toast.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
