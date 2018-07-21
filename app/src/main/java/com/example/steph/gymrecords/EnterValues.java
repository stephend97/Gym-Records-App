package com.example.steph.gymrecords;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class EnterValues extends Activity {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbRef;
    Spinner spinner1;
    String date;
    List<String> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_values);

        date = getIntent().getStringExtra("date");
        dbRef = db.getReference().child("workouts").child(date); // Passes the date for reference for database

        //initialize all spinners
        spinner1 = findViewById(R.id.spinner1);

        list = new ArrayList<>();
        addToList(); // Adds exercise names to the spinner

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
    }

    public void buttonClick(View v){
        if(v.getId() == R.id.Bsubmit){
            String spinner1Str = spinner1.getSelectedItem().toString();
            EditText ET1 = findViewById(R.id.ET1);
            String ET1str = ET1.getText().toString();

            Exercises e = new Exercises(spinner1Str, ET1str);

            dbRef.push().setValue(e);

            Intent i = new Intent(EnterValues.this, EnterValues.class);
            i.putExtra("date", date); //Passes on the date to enter more exercises under it
            startActivity(i);
        }
        else if(v.getId() == R.id.Bfinish){
            Intent i = new Intent(EnterValues.this, MainActivity.class);
            startActivity(i);
        }
    }

    // Adds exercise names to the spinner
    public void addToList(){
        list.add("");
        list.add("Flat dumbbell bench");
        list.add("Incline dumbbell bench");
        list.add("Flat bench");
        list.add("Incline bench");
        list.add("Dumbbell chest flies");
        list.add("Dips");
        list.add("Tricep extensions");
        list.add("Pullups");
    }
}