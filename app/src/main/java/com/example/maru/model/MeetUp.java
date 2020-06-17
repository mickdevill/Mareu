package com.example.maru.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

//the class of meetup
public class MeetUp implements Parcelable {


    private int year;

    private int month;

    private int day;

    private int hour;

    private int minut;


    public String room;


    private String sujet;


    private String membersMail;

    public MeetUp(int year, int month, int day, int hour, int minut, String room, String sujet, String membersMail) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minut = minut;
        this.room = room;
        this.sujet = sujet;
        this.membersMail = membersMail;
    }


    protected MeetUp(Parcel in) {

        year = in.readInt();
        month = in.readInt();
        day = in.readInt();
        hour = in.readInt();
        minut = in.readInt();
        room = in.readString();
        sujet = in.readString();
        membersMail = in.readString();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MeetUp)) return false;
        MeetUp laReunion = (MeetUp) o;
        return year == laReunion.year &&
                month == laReunion.month &&
                day == laReunion.day &&
                hour == laReunion.hour &&
                minut == laReunion.minut &&
                room == laReunion.room &&
                sujet.equals(laReunion.sujet) &&
                membersMail.equals(laReunion.membersMail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day, hour, minut, room, sujet, membersMail);
    }

    public static final Creator<MeetUp> CREATOR = new Creator<MeetUp>() {
        @Override
        public MeetUp createFromParcel(Parcel in) {
            return new MeetUp(in);
        }

        @Override
        public MeetUp[] newArray(int size) {
            return new MeetUp[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(year);
        dest.writeInt(month);
        dest.writeInt(day);
        dest.writeInt(hour);
        dest.writeInt(minut);
        dest.writeString(room);
        dest.writeString(sujet);
        dest.writeString(membersMail);
    }

    // geters and seters
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinut() {
        return minut;
    }

    public void setMinut(int minut) {
        this.minut = minut;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
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
