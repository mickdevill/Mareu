package com.example.maru.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MasiveOfReu {
// la classe qui va contenir tt les réunions

    public static List<LaRéunion> BisnesList = Arrays.asList();


    public static List<LaRéunion> getTheBisnes() {
        return new ArrayList<>(BisnesList);
    }
}
