package com.example.steph.gymrecords;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EnterDate extends Activity {

    FirebaseDatabase db = FirebaseDatabase.getInstance();
    DatabaseReference dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_date);
        dbRef = db.getReference();
    }

    public void onButtonClick(View v){

        EditText ETdate = findViewById(R.id.ETdate);
        String ETdateStr = ETdate.getText().toString();

        //dbRef.push().setValue(ETdateStr);

        Intent i = new Intent(EnterDate.this, EnterValues.class);
        i.putExtra("date", ETdateStr); //Passes the date to the next class
        startActivity(i);
    }
}
