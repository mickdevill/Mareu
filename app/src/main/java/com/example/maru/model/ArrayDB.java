package com.example.maru.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ArrayDB {

    //the class to contains all added meetups
    public static List<MeetUp> BisnesList = Arrays.asList();


    public static List<MeetUp> getTheBisnes() {
        return new ArrayList<>(BisnesList);
    }
}
