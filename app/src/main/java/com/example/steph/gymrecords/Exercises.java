package com.example.steph.gymrecords;
import java.util.HashMap;

public class Exercises {

    HashMap<String, String> map = new HashMap<>();
    String exercise;
    String reps;
//    String exercise1="", exercise2="", exercise3="", exercise4="", exercise5="", exercise6="",
//            exercise7="", exercise8="", exercise9="";
//    String rep1 = "", rep2 = "", rep3 = "", rep4 = "", rep5 = "", rep6 = "", rep7 = "",
//            rep8 = "", rep9 = "";

    public Exercises(){
        this.exercise = "N/A";
        this.reps = "N/A";
    }

    public Exercises(String exercise, String reps){
        this.exercise = exercise;
        this.reps = reps;
//        this.exercise1 = e1;// + "->" + r1;
//        this.exercise2 = e2;// + "->" + r2;
//        this.exercise3 = e3;// + "->" + r3;
//        this.exercise4 = e4;// + "->" + r4;
//        this.exercise5 = e5;// + "->" + r5;
//        this.exercise6 = e6;// + "->" + r6;
//        this.exercise7 = e7;// + "->" + r7;
//        this.exercise8 = e8;// + "->" + r8;
//        this.exercise9 = e9;// + "->" + r9
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
