package com.example.maru.service;

import com.example.maru.model.MeetUp;

import java.util.List;

public interface Service {

    //interface used for DI, it contains all technical mission methods
    List<MeetUp> getAll();

    void addNew(MeetUp meetUp);

    void remove(MeetUp meetUp);

    void filterPlace(String room, List<MeetUp> filter);

    void filterDate(int day, int mouth, int year, List<MeetUp> filter);

    void filterTime(int hour, int minut, List<MeetUp> filter);


}
