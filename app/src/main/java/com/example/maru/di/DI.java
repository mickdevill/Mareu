package com.example.maru.di;

import com.example.maru.model.ReuService;
import com.example.maru.model.ReuServiceImplemented;


public class DI {
    //l'ingection des dépendance
    private static ReuService service = new ReuServiceImplemented();


    public static ReuService getService() {
        return service;
    }


    public static ReuService getNewInstanceService() {
        return new ReuServiceImplemented();
    }
}
