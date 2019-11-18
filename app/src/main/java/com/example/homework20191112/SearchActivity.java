package com.example.homework20191112;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;


public class SearchActivity extends AppCompatActivity implements View.OnClickListener{

    // Part A, Identify all of the edit texts and buttons
    Button buttonSearch;
    EditText editTextSearchZip;
    TextView textViewShowBirds;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //Part B find buttons
        buttonSearch = findViewById(R.id.buttonSearch);

        editTextSearchZip = findViewById(R.id.editTextSearchZip);

        textViewShowBirds = findViewById(R.id.textViewShowBirds);


        buttonSearch.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.itemReport) {

            Intent reportIntent = new Intent(this, MainActivity.class);
            startActivity(reportIntent);

        } else if (item.getItemId() == R.id.itemSearch) {

            Toast.makeText(this, "You are already on the Search page", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Birds");

        if (view == buttonSearch) {

            String findZipcode = editTextSearchZip.getText().toString();
            myRef.orderByChild("zipcode").equalTo(findZipcode).addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    Bird findBird = dataSnapshot.getValue(Bird.class);
                    String findbirdname = findBird.birdname;

                    textViewShowBirds.setText(findbirdname);
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

    }
}
