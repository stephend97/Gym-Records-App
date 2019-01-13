package com.example.steph.gymrecords;

import android.app.Activity;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewHistory extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_history);
        FirebaseDatabase db = null;
        String id = getIntent().getStringExtra("ID");

        db.getInstance().getReference().child(id).child("workouts")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int totalChildren = (int) dataSnapshot.getChildrenCount();

                        TextView[] viewExercises = new TextView[totalChildren];
                        TextView[] viewDate = new TextView[totalChildren]; // Possibly creates more textviews than necessary
                        LinearLayout SV = findViewById(R.id.SVlayout); // Creates the scrollview for the list of exercises

                        Exercises e;

                        // Retrieves exercise info from firebase and displays it in scrollview
                        for (final DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String name = snapshot.getKey();
                            setDateText(viewDate, name, SV);

                            for(DataSnapshot s : snapshot.getChildren()){
                                e = s.getValue(Exercises.class);
                                String text = e.exercise + ":   " + e.reps;
                                setExerciseText(viewExercises, text, SV);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });
    }

    /* Displays the dates prior to the exercises */
    public void setDateText(TextView[] viewDate, String name, LinearLayout SV){
        viewDate[0] = new TextView(ViewHistory.this);
        viewDate[0].setTypeface(null, Typeface.BOLD);
        viewDate[0].setPaintFlags(Paint.UNDERLINE_TEXT_FLAG);
        viewDate[0].setText("\n" + name);
        SV.addView(viewDate[0]);
    }

    /* Displays the exercises done following the date */
    public void setExerciseText(TextView[] viewExercises, String text, LinearLayout SV){
        viewExercises[0] = new TextView(ViewHistory.this);
        viewExercises[0].setText(text);
        SV.addView(viewExercises[0]);
    }
}