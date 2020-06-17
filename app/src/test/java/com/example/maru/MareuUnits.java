package com.example.maru;

import com.example.maru.di.DI;
import com.example.maru.model.MeetUp;
import com.example.maru.model.ArrayDB;
import com.example.maru.service.Service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
//essy unit tests used to test service
@RunWith(JUnit4.class)
public class MareuUnits {

    private Service service;

    @Before
    public void setup() {
        service = DI.getService();
    }


    @Test
    public void getTheList() {
        List<MeetUp> expecktedReu = ArrayDB.getTheBisnes();
        assertTrue(service.getAll().equals(expecktedReu));
    }

    @Test
    public void remmouveReu() {
        MeetUp meetup = new MeetUp(2021, 16, 0, 4, 20, "B", "sujet", "mail");
        service.addNew(meetup);
        service.remove(meetup);
        assertFalse(service.getAll().contains(meetup));
    }

    @Test
    public void addNewReu() {
        MeetUp meetup = new MeetUp(2021, 16, 0, 4, 20, "C", "sujet", "mail");
        service.addNew(meetup);
        assertTrue(service.getAll().contains(meetup));
    }

    @Test
    public void filterPlace() {

        List<MeetUp> filter = new ArrayList<>();
        MeetUp mettup0 = new MeetUp(2021, 16, 0, 4, 20, "A", "sujet", "mail");
        MeetUp mettup1 = new MeetUp(2021, 16, 0, 4, 20, "B", "sujet", "mail");
        MeetUp mettup2 = new MeetUp(2021, 16, 0, 4, 20, "C", "sujet", "mail");
        filter.add(mettup0);
        filter.add(mettup1);
        filter.add(mettup2);
        service.filterPlace(mettup2.room, filter);
        assertTrue(filter.size() == 1 & filter.get(0).getRoom().equals(mettup2.getRoom()));
    }

    @Test
    public void filterDate() {
        List<MeetUp> filter = new ArrayList<>();
        MeetUp meetup0 = new MeetUp(2020, 16, 0, 4, 20, "c", "sujet", "mail");
        MeetUp meetup1 = new MeetUp(2021, 12, 64, 4, 20, "dvd", "sujet", "mail");
        MeetUp meetup2 = new MeetUp(2020, 16, 0, 4, 20, "D", "sujet", "mail");
        filter.add(meetup0);
        filter.add(meetup1);
        filter.add(meetup2);
        service.filterDate(meetup1.getDay(), meetup1.getMonth(), meetup1.getYear(), filter);
        assertTrue(filter.size() == 1 & filter.get(0).getDay() == meetup1.getDay() &
                filter.get(0).getMonth() == meetup1.getMonth() &
                filter.get(0).getYear() == meetup1.getYear());
    }

    @Test
    public void filterTime() {
        List<MeetUp> filter = new ArrayList<>();
        MeetUp meetup0 = new MeetUp(2020, 16, 0, 4, 20, "Z", "sujet", "mail");
        MeetUp meetup1 = new MeetUp(2021, 12, 64, 5, 30, "S", "sujet", "mail");
        MeetUp meetup2 = new MeetUp(2020, 16, 0, 7, 50, "Q", "sujet", "mail");
        filter.add(meetup0);
        filter.add(meetup1);
        filter.add(meetup2);
        service.filterTime(meetup0.getHour(), meetup0.getMinut(), filter);
        assertTrue(filter.size() == 1 & filter.get(0).getHour() == meetup0.getHour() & filter.get(0).getMinut() == meetup0.getMinut());
    }

}