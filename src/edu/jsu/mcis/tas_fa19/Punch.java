package edu.jsu.mcis.tas_fa19;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.GregorianCalendar;
import java.time.LocalDate;


public class Punch {
    //Changed this from "String" to "int" considering Feature1.java tests the Punch object with an integer for "id"
    private int id;
    private String punchtypeid;
    private String terminalid;
    //Changed this from "Badge" to "String" because its not the full object, its just a string that shows the badge ID
    private String badgeid;

    private GregorianCalendar originalTimeStamp;
    private LocalDate OriginalTimeStamp;

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

    public void setOriginalTimestamp(String info)//a punch pulled from sql
    {
        String parts[] = info.split(" ");
        String day[] = parts[0].split("-");
        String time[] = parts[1].split(":");
        this.originalTimeStamp = new GregorianCalendar(Integer.parseInt(day[0]), Integer.parseInt(day[1]), Integer.parseInt(day[2]), Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2]));
    }

    public void setOriginalTimestamp()//a new punch using current time
    {
        this.originalTimeStamp = new GregorianCalendar();
    }

    public String printOriginalTimestamp()
    {
        String output = "";
        output.concat(badgeid); // No ".toString()" method needed here since its now a String

        switch(punchtypeid)
        {
            case "0":
                output.concat(" CLOCKED OUT: ");
                break;
            case "1":
                output.concat(" CLOCKED IN");
                break;
            case "2":
                output.concat(" TIMEOUT");
                break;
        }
        output.concat(" ");

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        output.concat(dtf.format((TemporalAccessor) this.originalTimeStamp.getTime()));

        return output;
    }

    public String getPunchTypeID(){
        return punchtypeid;
    }

    public String getTerminalID(){
        return terminalid;
    }

    //Changed this from "Badge" to "String"
    public String getBadgeID(){
        return badgeid;
    }

    //Changed "String" to "int"
    public int getID() {
        return id;
    }

    public LocalDate getOriginalTimeStamp(){
        return OriginalTimeStamp;
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

    public void setOriginalTimeStamp(LocalDate OriginalTimeStamp){
        this.OriginalTimeStamp = OriginalTimeStamp;
    }
}