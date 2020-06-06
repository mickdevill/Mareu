package com.example.maru.model;

import java.util.List;

public interface ReuService {

    //l'interface avec les méthode principale de l'application
    List<LaRéunion> getAll();

    void addNew(LaRéunion reunion);

    void removeReu(LaRéunion reunion);

    void filterPlace(int roomNum, List<LaRéunion> filter);

    void filterDate(int day, int mouth, int year, List<LaRéunion> filter);

    void filterTime(int hour, int minut, List<LaRéunion> filter);


}
