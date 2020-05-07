package com.example.maru.model;

import android.content.Intent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public abstract class masiveOfReu {


public static List<laReunion> BisnesList = Arrays.asList(
        new laReunion(2020,5,6,4,20,1,"la marijane","les rasta du quartier"),
        new laReunion(228,22,22,22,22,2,"gggg","dddd"),
        new laReunion(228,22,22,22,22,3,"gggg","dddd")
);



     static List<laReunion> getTheBisnes(){return new ArrayList<>(BisnesList);}
}
