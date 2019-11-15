package edu.jsu.mcis.tas_fa19;

import java.time.Duration;
import java.time.LocalTime;

public class Shift {
    //Changed this from "String" to "int" considering Feature1.java tests the Shift object with an integer for "id"
    private int id;

    private String Description;
    private LocalTime start;
    private LocalTime stop;
    private long shiftduration;
    private int interval;
    private int graceperiod;
    private int dock;
    private LocalTime lunchstart;
    private LocalTime lunchstop;
    private int lunchdeduct;
    private long lunchduration;
    private LocalTime hms; //hours minutes seconds

    public Shift() {
        //Constructor
    }

    public Shift(int id, String Description, LocalTime start, LocalTime stop, int interval,
                 int graceperiod, int dock, LocalTime lunchstart, LocalTime lunchstop, int lunchdeduct) {
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

    public void setStart(String start) {
        this.start = LocalTime.parse(start);
    }

    public LocalTime getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = LocalTime.parse(stop);
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;}
    

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

    public void setLunchStart(String lunchstart) {
        this.lunchstart = LocalTime.parse(lunchstart);
    }

    public LocalTime getLunchStop() {
        return lunchstop;
    }

    public void setLunchStop(String lunchstop) {
        this.lunchstop = LocalTime.parse(lunchstop);
    }

    public int getLunchDeduct() {
        return lunchdeduct;
    }

    public void setLunchDeduct(int lunchdeduct) {
        this.lunchdeduct = lunchdeduct;
    }

    //Used "Duration" to find the duration of the shift in minutes
    public long getShiftDuration(LocalTime start, LocalTime stop) {
        return this.shiftduration = Duration.between(start, stop).toMinutes();
    }

    //Used "Duration" to find the duration of the lunch in minutes
    public long getLunchDuration(LocalTime lunchstart, LocalTime lunchstop) {
        return this.lunchduration = Duration.between(lunchstart, lunchstop).toMinutes();
    }


    @Override
    public String toString()
    {
        StringBuilder output = new StringBuilder();
        output.append(Description +": ");
        output.append(start + " - ");
        output.append(stop);
        output.append(" (" + getShiftDuration(start, stop) + " minutes); ");
        output.append("Lunch: " + lunchstart + " - ");
        output.append(lunchstop);
        output.append(" (" + getLunchDuration(lunchstart, lunchstop) + " minutes)");

        return output.toString();
    }
}