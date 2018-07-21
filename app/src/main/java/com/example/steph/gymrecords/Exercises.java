package com.example.steph.gymrecords;
import java.util.HashMap;

public class Exercises {

    //HashMap<String, String> map = new HashMap<>();
    String exercise;
    String reps;

    public Exercises(){
        this.exercise = "N/A";
        this.reps = "N/A";
    }

    public Exercises(String exercise, String reps){
        this.exercise = exercise;
        this.reps = reps;
    }

    public void setExercise(String e){
        this.exercise = e;
    }
    public String getExercise(){
        return this.exercise;
    }

    public void setReps(String r){
        this.reps = r;
    }
    public String getReps(){
        return this.reps;
    }
}
