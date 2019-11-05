package edu.jsu.mcis.tas_fa19;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Punch {    
    private int id;
    private int punchtypeid;
    private int terminalid;
    private String badgeid;
    private String adjustedtimestamp;
    private long originalTimeStamp;
    private GregorianCalendar gc = new GregorianCalendar();

    public Punch() {
        //Constructor
    }

    //Changed "Badge" to "String" type
    //Added "int id" to beginning of argument
    public Punch(int id, String badgeid, int terminalid, int punchtypeid) {
        this.id = id;
        this.punchtypeid = punchtypeid;
        this.terminalid = terminalid;
        this.badgeid = badgeid;
    }

    public Punch(Badge badge, int terminal, int punchtype) {
        this.badgeid = badge.getId();
        this.terminalid = terminal;
        this.punchtypeid = punchtype;
    }

    public void setOriginalTimestamp(long ts)//a punch pulled from sql
    {
       gc.setTimeInMillis(ts);
    }

    public String printOriginalTimestamp() {
        StringBuilder output = new StringBuilder("#");
        output.append(badgeid); // No ".toString()" method needed here since its now a String

        switch (punchtypeid) {
            case 0:
                output.append(" CLOCKED OUT:");
                break;
            case 1:
                output.append(" CLOCKED IN:");
                break;
            case 2:
                output.append(" TIMED OUT:");
                break;
        }
        output.append(" ");

        SimpleDateFormat sdf = new SimpleDateFormat("EEE MM/dd/yyyy HH:mm:ss");
        output.append(sdf.format(gc.getTime()).toUpperCase());

        return output.toString();
    }

    public int getPunchtypeid() {
        return punchtypeid;
    }

    public int getTerminalid() {
        return terminalid;
    }

    //Changed this from "Badge" to "String"
    public String getBadgeid() {
        return badgeid;
    }

    //Changed "String" to "int"
    public int getID() {
        return id;
    }

    public long getOriginaltimestamp() {
        return originalTimeStamp;
    }

    //Changed "String" to "int"
    public void setID(int id) {
        this.id = id;
    }

    public void setPunchTypeID(int punchtypeid) {
        this.punchtypeid = punchtypeid;
    }

    public void setTerminalID(int terminalid) {
        this.terminalid = terminalid;
    }

    //Changed from "Badge" to "String"
    public void setBadgeid(String badgeid) {
        this.badgeid = badgeid;
    }
    
    public void adjust(Shift s){
    
    }
    
    public String printAdjustedTimestamp(){        
        return this.adjustedtimestamp;
    }
}