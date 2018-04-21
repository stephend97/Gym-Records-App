package com.example.steph.gymrecords;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonClick(View v){
        if(v.getId() == R.id.BenterData){
            Intent i = new Intent(MainActivity.this, EnterDate.class);
            startActivity(i);
        }
        else if(v.getId() == R.id.BviewHistory){
            Intent i = new Intent(MainActivity.this, ViewHistory.class);
            startActivity(i);
        }
    }
}
