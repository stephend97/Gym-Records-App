package com.example.steph.gymrecords;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewHistory extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_history);
        FirebaseDatabase db = null;

        db.getInstance().getReference().child("workouts")
                .addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int totalChildren = (int) dataSnapshot.getChildrenCount();
                        Exercises ex = dataSnapshot.getValue(Exercises.class);
                        System.out.println("--------------------- "+ex+" ------------------------");

                        TextView[] viewExercises = new TextView[totalChildren];
                        TextView[] viewReps = new TextView[totalChildren];
                        LinearLayout SVbuttons = findViewById(R.id.SVlayout);

                        int currentChild = 0;
                        Exercises e;
                        //Toast.makeText(ViewHistory.this, e.toString(), Toast.LENGTH_SHORT).show();

                        for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String name = snapshot.getKey();
                            Toast.makeText(ViewHistory.this, name, Toast.LENGTH_SHORT).show();
                            for(DataSnapshot s : snapshot.getChildren()){
                                e = s.getValue(Exercises.class);
                                System.out.println("--------------------- "+e+" ------------------------");
                                //Toast.makeText(ViewHistory.this, e.getExercise(), Toast.LENGTH_SHORT).show();
                                viewExercises[currentChild] = new TextView(ViewHistory.this);
                                viewExercises[currentChild].setText(e.exercise);
                                SVbuttons.addView(viewExercises[currentChild]);

                                viewReps[currentChild] = new TextView(ViewHistory.this);
                                viewReps[currentChild].setText(e.reps);
                                SVbuttons.addView(viewReps[currentChild]);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });



        /*
        //displays all the exercise data
        FirebaseDatabase.getInstance().getReference().child("workouts")
                .addValueEventListener(new ValueEventListener() {





                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int totalChildren = (int) dataSnapshot.getChildrenCount();
                        //Toast.makeText(ViewHistory.this, "C = " + totalChildren, Toast.LENGTH_SHORT).show();

                        TextView[] views = new TextView[totalChildren];
                        TextView[] views2 = new TextView[totalChildren];
                        LinearLayout SVbuttons = findViewById(R.id.SVlayout);
                        int currentChild = 0;
                        Exercises e = dataSnapshot.getValue(Exercises.class);
                        Toast.makeText(ViewHistory.this, e.toString(), Toast.LENGTH_SHORT).show();

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            //Exercises e = snapshot.getValue(Exercises.class);
                            //Toast.makeText(ViewHistory.this, e.toString(), Toast.LENGTH_LONG).show();

                            //Toast.makeText(ViewHistory.this, e.getExercise(), Toast.LENGTH_SHORT).show();
                            views[currentChild] = new TextView(ViewHistory.this);
                            views[currentChild].setText(e.getExercise());
                            SVbuttons.addView(views[currentChild]);

                            views2[currentChild] = new TextView(ViewHistory.this);
                            views2[currentChild].setText(e.getReps());
                            SVbuttons.addView(views2[currentChild]);
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });*/
    }
}
