package com.example.skiline11.pierwszaaplikacja.model;

import com.example.skiline11.pierwszaaplikacja.database.DataBaseHelper;

/**
 * Created by skiline11 on 19.01.17.
 */

public class Run {
    private String name, date, result;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Run(String name, String date, int hours, int minutes, int seconds, int miliseconds) {
        this.id = DataBaseHelper.numberOfRun() + 1;

        String h, m, s, ms;
        if(hours < 10) h = "0" + hours;
        else h = "" + hours;
        if(minutes < 10) m = "0" + minutes;
        else m = "" + minutes;
        if(seconds < 10) s = "0" + seconds;
        else s = "" + seconds;
        if(miliseconds < 10) ms = "0" + miliseconds;
        else ms = "" + miliseconds;
        this.result = h + ":" + m + ":" + s + "." + ms;
        this.date = date;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
