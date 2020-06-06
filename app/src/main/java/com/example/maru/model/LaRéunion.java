package com.example.maru.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

//la classe de la réunion avec les outils permétant de agrandir le project
public class LaRéunion implements Parcelable {


    //le gestion du temps
    private int ané;

    private int mois;

    private int jour;

    private int heur;

    private int minut;
    //le sale

    public int roomNum;
    //le sujet

    private String sujet;
    //les participan

    private String membersMail;

    public LaRéunion(int ané, int mois, int jour, int heur, int minut, int roomNum, String sujet, String membersMail) {
        this.ané = ané;
        this.mois = mois;
        this.jour = jour;
        this.heur = heur;
        this.minut = minut;
        this.roomNum = roomNum;
        this.sujet = sujet;
        this.membersMail = membersMail;
    }


    protected LaRéunion(Parcel in) {

        ané = in.readInt();
        mois = in.readInt();
        jour = in.readInt();
        heur = in.readInt();
        minut = in.readInt();
        roomNum = in.readInt();
        sujet = in.readString();
        membersMail = in.readString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LaRéunion)) return false;
        LaRéunion laReunion = (LaRéunion) o;
        return ané == laReunion.ané &&
                mois == laReunion.mois &&
                jour == laReunion.jour &&
                heur == laReunion.heur &&
                minut == laReunion.minut &&
                roomNum == laReunion.roomNum &&
                sujet.equals(laReunion.sujet) &&
                membersMail.equals(laReunion.membersMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ané, mois, jour, heur, minut, roomNum, sujet, membersMail);
    }

    public static final Creator<LaRéunion> CREATOR = new Creator<LaRéunion>() {
        @Override
        public LaRéunion createFromParcel(Parcel in) {
            return new LaRéunion(in);
        }

        @Override
        public LaRéunion[] newArray(int size) {
            return new LaRéunion[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ané);
        dest.writeInt(mois);
        dest.writeInt(jour);
        dest.writeInt(heur);
        dest.writeInt(minut);
        dest.writeInt(roomNum);
        dest.writeString(sujet);
        dest.writeString(membersMail);
    }

    // les geteur est les seteur
    public int getAné() {
        return ané;
    }

    public void setAné(int ané) {
        this.ané = ané;
    }

    public int getMois() {
        return mois;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public int getJour() {
        return jour;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public int getHeur() {
        return heur;
    }

    public void setHeur(int heur) {
        this.heur = heur;
    }

    public int getMinut() {
        return minut;
    }

    public void setMinut(int minut) {
        this.minut = minut;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getMembersMail() {
        return membersMail;
    }

    public void setMembersMail(String membersMail) {
        this.membersMail = membersMail;
    }

}
