package com.example.maru.service;


import com.example.maru.model.MeetUp;
import com.example.maru.model.ArrayDB;

import java.util.Iterator;
import java.util.List;

//the service implemented methods
public class ServiceImplemented implements Service {

    private List<MeetUp> reuMasiv = ArrayDB.getTheBisnes();

    public List<MeetUp> getAll() {
        return reuMasiv;
    }

    public void addNew(MeetUp meetUp) {
        reuMasiv.add(meetUp);
    }

    public void remove(MeetUp meetUp) {
        reuMasiv.remove(meetUp);
    }


    public void filterPlace(String room, List<MeetUp> filter) {
        MeetUp meetup;
        for (Iterator<MeetUp> iterator = filter.iterator(); iterator.hasNext(); ) {
            meetup = iterator.next();
            if (meetup.getRoom() != room) {
                iterator.remove();
            }
        }
    }

    public void filterDate(int day, int mouth, int year, List<MeetUp> filter) {
        MeetUp meetup;
        for (Iterator<MeetUp> iterator = filter.iterator(); iterator.hasNext(); ) {
            meetup = iterator.next();
            if (meetup.getDay() != day || meetup.getMonth() != mouth || meetup.getYear() != year) {
                iterator.remove();
            }

        }
    }


    public void filterTime(int hour, int minut, List<MeetUp> filter) {
        MeetUp meetup;
        for (Iterator<MeetUp> iterator = filter.iterator(); iterator.hasNext(); ) {
            meetup = iterator.next();
            if (meetup.getHour() != hour || meetup.getMinut() != minut) {
                iterator.remove();
            }
        }
    }


}