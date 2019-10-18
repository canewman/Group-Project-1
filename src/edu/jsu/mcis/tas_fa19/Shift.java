package edu.jsu.mcis.tas_fa19;

import java.time.LocalTime;
import java.util.GregorianCalendar;

public class Shift {
    //Changed this from "String" to "int" considering Feature1.java tests the Shift object with an integer for "id"
    private int id;

    private String Description;
    private LocalTime start;
    private LocalTime stop;
    private int interval;
    private int graceperiod;
    private int dock;
    private LocalTime lunchstart;
    private LocalTime lunchstop;
    private int lunchdeduct;
    private int lunchduration;
    //needed at a later point
    private String adjustmenttype;
    private LocalTime hms; //hours minutes seconds

    public Shift() {
        //Constructor
    }

    public Shift(int id, String Description, LocalTime start, LocalTime stop, int interval, int graceperiod, int dock, LocalTime lunchstart, LocalTime lunchstop, int lunchdeduct) {
        this.id = id;
        this.Description = Description;
        this.start = start;
        this.stop = stop;
        this.interval = interval;
        this.graceperiod = graceperiod;
        this.dock = dock;
        this.lunchstart = lunchstart;
        this.lunchstop = lunchstop;
        this.lunchdeduct = lunchdeduct;
    }


    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getStop() {
        return stop;
    }

    public void setStop(LocalTime stop) {
        this.stop = stop;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getGracePeriod() {
        return graceperiod;
    }

    public void setGracePeriod(int graceperiod) {
        this.graceperiod = graceperiod;
    }

    public int getDock() {
        return dock;
    }

    public void setDock(int dock) {
        this.dock = dock;
    }

    public LocalTime getLunchStart() {
        return lunchstart;
    }

    public void setLunchStart(LocalTime lunchstart) {
        this.lunchstart = lunchstart;
    }

    public LocalTime getLunchStop() {
        return lunchstop;
    }

    public void setLunchStop(LocalTime lunchstop) {
        this.lunchstop = lunchstop;
    }

    public int getLunchDeduct() {
        return lunchdeduct;
    }

    public void setLunchDeduct(int lunchdeduct) {
        this.lunchdeduct = lunchdeduct;
    }


    @Override
    public String toString()
    {
        String output = "";
        output.concat(id + ", ");
        output.concat(Description + ", ");
        output.concat(start.toString() + ", ");
        output.concat(stop.toString() + ", ");
        output.concat(Integer.toString(interval) + ", ");
        output.concat(Integer.toString(graceperiod) + ", ");
        output.concat(Integer.toString(dock) + ", ");
        output.concat(lunchstart.toString() + ", ");
        output.concat(lunchstop.toString() + ", ");
        output.concat(Integer.toString(lunchdeduct) + ", ");

        return output;
    }



}