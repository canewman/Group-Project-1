package edu.jsu.mcis.tas_fa19;

import java.time.LocalTime;
import java.util.GregorianCalendar;

public class Shift {
    private String id;
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

    public Shift(String id, String Description, LocalTime start, LocalTime stop, int interval, int graceperiod, int dock, LocalTime lunchstart, LocalTime lunchstop, int lunchdeduct) {
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
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public int getGraceperiod() {
        return graceperiod;
    }

    public void setGraceperiod(int graceperiod) {
        this.graceperiod = graceperiod;
    }

    public int getDock() {
        return dock;
    }

    public void setDock(int dock) {
        this.dock = dock;
    }

    public LocalTime getLunchstart() {
        return lunchstart;
    }

    public void setLunchstart(LocalTime lunchstart) {
        this.lunchstart = lunchstart;
    }

    public LocalTime getLunchstop() {
        return lunchstop;
    }

    public void setLunchstop(LocalTime lunchstop) {
        this.lunchstop = lunchstop;
    }

    public int getLunchdeduct() {
        return lunchdeduct;
    }

    public void setLunchdeduct(int lunchdeduct) {
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
