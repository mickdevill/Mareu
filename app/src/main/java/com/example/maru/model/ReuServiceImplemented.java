package com.example.maru.model;


import java.util.Iterator;
import java.util.List;

//la classe implementant l'interface
public class ReuServiceImplemented implements ReuService {

    private List<LaRéunion> reuMasiv = MasiveOfReu.getTheBisnes();

    public List<LaRéunion> getAll() {
        return reuMasiv;
    }

    public void addNew(LaRéunion reunion) {
        reuMasiv.add(reunion);
    }

    public void removeReu(LaRéunion reunion) {
        reuMasiv.remove(reunion);
    }


    public void filterPlace(int roomNum, List<LaRéunion> filter) {
        LaRéunion reunion;
        for (Iterator<LaRéunion> iterator = filter.iterator(); iterator.hasNext(); ) {
            reunion = iterator.next();
            if (reunion.getRoomNum() != roomNum) {
                iterator.remove();
            }
        }
    }

    public void filterDate(int day, int mouth, int year, List<LaRéunion> filter) {
        LaRéunion reunion;
        for (Iterator<LaRéunion> iterator = filter.iterator(); iterator.hasNext(); ) {
            reunion = iterator.next();
            if (reunion.getJour() != day || reunion.getMois() != mouth || reunion.getAné() != year) {
                iterator.remove();
            }

        }
    }


    public void filterTime(int hour, int minut, List<LaRéunion> filter) {
        LaRéunion reunion;
        for (Iterator<LaRéunion> iterator = filter.iterator(); iterator.hasNext(); ) {
            reunion = iterator.next();
            if (reunion.getHeur() != hour || reunion.getMinut() != minut) {
                iterator.remove();
            }
        }
    }


}