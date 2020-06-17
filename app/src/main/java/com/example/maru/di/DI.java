package com.example.maru.di;

import com.example.maru.service.Service;
import com.example.maru.service.ServiceImplemented;


public class DI {
    //dependency injection
    private static Service service = new ServiceImplemented();


    public static Service getService() {
        return service;
    }


    public static Service getNewInstanceService() {
        return new ServiceImplemented();
    }
}
