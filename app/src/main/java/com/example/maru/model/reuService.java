package com.example.maru.model;

import android.icu.text.Edits;

import java.util.Iterator;
import java.util.List;

public interface reuService{



    List<laReunion> getAll();

    void addNew(laReunion reunion);

    void removeReu(laReunion reunion);

    void filterP(laReunion reunion, int roomNum,  List<laReunion> filter);

    void filterD(laReunion reunion, int year, int mouth, int day,  List<laReunion> filter);

    void filterPD(laReunion reunion,  int roomNum, int year, int mouth, int day, int hour, int minut,  List<laReunion> filter);

}
