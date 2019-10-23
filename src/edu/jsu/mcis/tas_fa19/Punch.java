package edu.jsu.mcis.tas_fa19;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Punch {
    //Changed this from "String" to "int" considering Feature1.java tests the Punch object with an integer for "id"
    private int id;
    private String punchtypeid;
    private String terminalid;
    //Changed this from "Badge" to "String" because its not the full object, its just a string that shows the badge ID
    private String badgeid;

    private long originalTimeStamp;
    private GregorianCalendar gc = new GregorianCalendar();

    public Punch() {
        //Constructor
    }

    //Changed "Badge" to "String" type
    //Added "int id" to beginning of argument
    public Punch(int id, String badgeid, String terminalid, String punchtypeid) {
        this.id = id;
        this.punchtypeid = punchtypeid;
        this.terminalid = terminalid;
        this.badgeid = badgeid;
    }

    public void setOriginalTimestamp(long ts)//a punch pulled from sql
    {
       gc.setTimeInMillis(ts);
    }

    public String printOriginalTimestamp() {
        StringBuilder output = new StringBuilder("#");
        output.append(badgeid); // No ".toString()" method needed here since its now a String

        switch (punchtypeid) {
            case "0":
                output.append(" CLOCKED OUT:");
                break;
            case "1":
                output.append(" CLOCKED IN:");
                break;
            case "2":
                output.append(" TIMED OUT:");
                break;
        }
        output.append(" ");

        SimpleDateFormat sdf = new SimpleDateFormat("EEE MM/dd/yyyy HH:mm:ss");
        output.append(sdf.format(getOriginalTimeStamp().getTime()).toUpperCase());

        return output.toString();
    }

    public String getPunchTypeID() {
        return punchtypeid;
    }

    public String getTerminalID() {
        return terminalid;
    }

    //Changed this from "Badge" to "String"
    public String getBadgeID() {
        return badgeid;
    }

    //Changed "String" to "int"
    public int getID() {
        return id;
    }

    public GregorianCalendar getOriginalTimeStamp() {
        return gc;
    }

    //Changed "String" to "int"
    public void setID(int id) {
        this.id = id;
    }

    public void setPunchTypeID(String punchtypeid) {
        this.punchtypeid = punchtypeid;
    }

    public void setTerminalID(String terminalid) {
        this.terminalid = terminalid;
    }

    //Changed from "Badge" to "String"
    public void setBadgeID(String badgeid) {
        this.badgeid = badgeid;
    }
}