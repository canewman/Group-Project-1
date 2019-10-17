package edu.jsu.mcis.tas_fa19;

public class Shift {
    private String id;
    private String Description;
    private long start;
    private long stop;
    private int interval;
    private int graceperiod;
    private int dock;
    private long lunchstart;
    private long lunchstop;
    private int lunchdeduct;
    private int lunchduration;
    //needed at a later point
    private String adjustmenttype;

    public Shift(String id, String Description, long start, long stop, int interval, int graceperiod, int dock, long lunchstart, long lunchstop, int lunchdeduct) {
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

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getStop() {
        return stop;
    }

    public void setStop(long stop) {
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

    public long getLunchstart() {
        return lunchstart;
    }

    public void setLunchstart(long lunchstart) {
        this.lunchstart = lunchstart;
    }

    public long getLunchstop() {
        return lunchstop;
    }

    public void setLunchstop(long lunchstop) {
        this.lunchstop = lunchstop;
    }

    public int getLunchdeduct() {
        return lunchdeduct;
    }

    public void setLunchdeduct(int lunchdeduct) {
        this.lunchdeduct = lunchdeduct;
    }


 
    
    
    
}
