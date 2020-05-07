package com.example.maru.model;


import java.util.Iterator;
import java.util.List;


public class reuServiceImplemented implements reuService {

    private List<laReunion> reuMasiv = masiveOfReu.getTheBisnes();


    public List<laReunion> getAll() {
        return reuMasiv;
    }

    public void addNew(laReunion reunion) {
        reuMasiv.add(reunion);
    }

    public void removeReu(laReunion reunion) {
        reuMasiv.remove(reunion);
    }






    public void filterP(laReunion reunion, int roomNum, List<laReunion> filter) {
        for (Iterator<laReunion> iterator = filter.iterator(); iterator.hasNext();){
reunion = iterator.next();

if (reunion.getRoomNum() != roomNum){
    iterator.remove();
}
}
    }

    public void filterD(laReunion reunion, int year, int mouth, int day,List<laReunion> filter) {
        for (Iterator<laReunion> iterator = filter.iterator(); iterator.hasNext();){
            reunion = iterator.next();

            if (reunion.getAné() != year & reunion.getMois() != mouth & reunion.getJour() != day) {
               iterator.remove();
            }
        }
    }


    public void filterPD(laReunion reunion, int roomNum, int year, int mouth, int day, int hour, int minut, List<laReunion> filter) {
        for (Iterator<laReunion> iterator = filter.iterator(); iterator.hasNext();){
            reunion = iterator.next();

            if (reunion.getRoomNum() != roomNum & reunion.getAné() != year & reunion.getMois() != mouth &
                    reunion.getJour() != day & reunion.getHeur() != hour & reunion.getMinut() != minut) {
                iterator.remove();
            }
        }


    }

}
