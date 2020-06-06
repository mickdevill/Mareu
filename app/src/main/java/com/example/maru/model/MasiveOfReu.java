package com.example.maru.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class MasiveOfReu {


    public static List<LaRéunion> BisnesList = Arrays.asList();


    public static List<LaRéunion> getTheBisnes() {
        return new ArrayList<>(BisnesList);
    }
}
