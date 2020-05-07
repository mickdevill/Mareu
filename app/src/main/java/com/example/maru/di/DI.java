package com.example.maru.di;

import com.example.maru.model.reuService;
import com.example.maru.model.reuServiceImplemented;


public class DI {

    private static reuService service = new reuServiceImplemented();


    public static reuService getService() {
        return service;
    }


    public static reuService getNewInstanceService() {
        return new reuServiceImplemented();
    }
}
