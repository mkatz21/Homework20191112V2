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
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    // Part A, Identify all of the edit texts and buttons
    Button buttonSubmit;
    EditText editTextBirdName, editTextZipcode, editTextPersonName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     //Part B find buttons
     buttonSubmit = findViewById(R.id.buttonSubmit);

     editTextBirdName = findViewById(R.id.editTextBirdName);
     editTextZipcode = findViewById(R.id.editTextZipcode);
     editTextPersonName = findViewById(R.id.editTextPersonName);

     buttonSubmit.setOnClickListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.itemReport){
            Toast.makeText(this, "You are already on the Report page", Toast.LENGTH_SHORT).show();

    }else if(item.getItemId() == R.id.itemSearch) {

            Intent searchIntent = new Intent(this, SearchActivity.class);
            startActivity(searchIntent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("Birds");


        if (view == buttonSubmit) {

        String BirdName = editTextBirdName.getText().toString();
        String Zipcode = editTextZipcode.getText().toString();
        String PersonName = editTextPersonName.getText().toString();

        Bird createBird = new Bird(BirdName, Zipcode, PersonName);
        myRef.push().setValue(createBird);





        }

        }
}
