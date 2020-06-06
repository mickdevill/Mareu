package com.example.maru;

import com.example.maru.di.DI;
import com.example.maru.model.LaRéunion;
import com.example.maru.model.MasiveOfReu;
import com.example.maru.model.ReuService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class MareuUnits {

    private ReuService service;

    @Before
    public void setup() {
        service = DI.getService();
    }


    @Test
    public void getTheList() {
        List<LaRéunion> expecktedReu = MasiveOfReu.getTheBisnes();
        assertTrue(service.getAll().equals(expecktedReu));
    }

    @Test
    public void remmouveReu() {
        LaRéunion reunion = new LaRéunion(2021, 16, 0, 4, 20, 5, "sujet", "mail");
        service.addNew(reunion);
        service.removeReu(reunion);
        assertFalse(service.getAll().contains(reunion));
    }

    @Test
    public void addNewReu() {
        LaRéunion reunion = new LaRéunion(2021, 16, 0, 4, 20, 5, "sujet", "mail");
        service.addNew(reunion);
        assertTrue(service.getAll().contains(reunion));
    }

    @Test
    public void filterPlace() {
        LaRéunion reunion = null;
        List<LaRéunion> filter = new ArrayList<>();
        LaRéunion reunion0 = new LaRéunion(2021, 16, 0, 4, 20, 5, "sujet", "mail");
        LaRéunion reunion1 = new LaRéunion(2021, 16, 0, 4, 20, 6, "sujet", "mail");
        LaRéunion reunion2 = new LaRéunion(2021, 16, 0, 4, 20, 7, "sujet", "mail");
        filter.add(reunion0);
        filter.add(reunion1);
        filter.add(reunion2);
        service.filterPlace(reunion2.roomNum, filter);
        assertTrue(filter.size() == 1 & filter.get(0).roomNum == reunion2.roomNum);
    }

    @Test
    public void filterDate() {
        LaRéunion reunion = null;
        List<LaRéunion> filter = new ArrayList<>();
        LaRéunion reunion0 = new LaRéunion(2020, 16, 0, 4, 20, 5, "sujet", "mail");
        LaRéunion reunion1 = new LaRéunion(2021, 12, 64, 4, 20, 6, "sujet", "mail");
        LaRéunion reunion2 = new LaRéunion(2020, 16, 0, 4, 20, 7, "sujet", "mail");
        filter.add(reunion0);
        filter.add(reunion1);
        filter.add(reunion2);
        service.filterDate(reunion1.getJour(), reunion1.getMois(), reunion1.getAné(), filter);
        assertTrue(filter.size() == 1 & filter.get(0).getJour() == reunion1.getJour() &
                filter.get(0).getMois() == reunion1.getMois() &
                filter.get(0).getAné() == reunion1.getAné());
    }

    @Test
    public void filterTime() {
        LaRéunion reunion = null;
        List<LaRéunion> filter = new ArrayList<>();
        LaRéunion reunion0 = new LaRéunion(2020, 16, 0, 4, 20, 5, "sujet", "mail");
        LaRéunion reunion1 = new LaRéunion(2021, 12, 64, 5, 30, 6, "sujet", "mail");
        LaRéunion reunion2 = new LaRéunion(2020, 16, 0, 7, 50, 7, "sujet", "mail");
        filter.add(reunion0);
        filter.add(reunion1);
        filter.add(reunion2);
        service.filterTime(reunion0.getHeur(), reunion0.getMinut(), filter);
        assertTrue(filter.size() == 1 & filter.get(0).getHeur() == reunion0.getHeur() & filter.get(0).getMinut() == reunion0.getMinut());
    }

}