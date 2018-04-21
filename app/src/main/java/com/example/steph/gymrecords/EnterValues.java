package com.example.steph.gymrecords;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
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
        dbRef = db.getReference(date);

        //initialize all spinners
        spinner1 = findViewById(R.id.spinner1);

        list = new ArrayList<>();
        addToList();

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(adapter1);
    }

    public void buttonClick(View v){
        String spinner1Str = spinner1.getSelectedItem().toString();

        EditText ET1 = findViewById(R.id.ET1);
        String ET1str = ET1.getText().toString();

        System.out.println(spinner1Str);
        System.out.println(ET1str);

        Exercises e = new Exercises();
        e.setExercise(spinner1Str);
        e.setReps(ET1str);

        dbRef.push().setValue(e);

        Intent i = new Intent(EnterValues.this, EnterValues.class);
        i.putExtra("date", date); //Passes on the date to enter more exercises under it
        startActivity(i);
    }

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


/* might use this in the future */
//        dropDownMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                String itemvalue = adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(EnterValues.this, "Selected: "+itemvalue, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });