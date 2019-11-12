package edu.jsu.mcis.tas_fa19;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Punch {    
    private int id;
    private int punchtypeid;
    private int terminalid;
    private String badgeid;
    private String adjustedtimestamp;
    private String adjustmenttype;
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

            LocalTime gcLocalTime = LocalTime.of((int)gc.get(Calendar.HOUR_OF_DAY), (int)gc.get(Calendar.MINUTE));

            long shiftMinutesStart = s.getStart().toSecondOfDay() / (60);  
            long shiftMinutesStop = s.getStop().toSecondOfDay() / (60);
            long punchMinutes = ((gc.get(Calendar.HOUR_OF_DAY) * 60) + (gc.get(Calendar.MINUTE)) + (gc.get(Calendar.SECOND) / 60));     

            LocalTime startGraceTime = s.getStart().plusMinutes(s.getGracePeriod());
            LocalTime startIntervalTime = s.getStart().minusMinutes(s.getInterval());
            LocalTime startDockTime = s.getStart().plusMinutes(s.getDock());

            LocalTime stopGraceTime = s.getStop().plusMinutes(s.getGracePeriod());
            LocalTime stopIntervalTime = s.getStop().minusMinutes(s.getInterval());
            LocalTime stopDockTime = s.getStop().plusMinutes(s.getDock());

            LocalTime lunchStart = s.getLunchStart();
            LocalTime lunchStop = s.getLunchStop();
           
            if(gcLocalTime.isAfter(startIntervalTime) && gcLocalTime.isBefore(s.getStart())){//start interval
            gcLocalTime = s.getStart();
            this.adjustmenttype = "Shift Start";
            }
            else if(gcLocalTime.isAfter(s.getStart()) && gcLocalTime.isBefore(startGraceTime)){//start grace period
            gcLocalTime = s.getStart();
            this.adjustmenttype = "Shift Start";
            }
            else if(gcLocalTime.isAfter(startGraceTime) && gcLocalTime.isBefore(startDockTime)){//start dock period
            gcLocalTime = startDockTime;
            this.adjustmenttype = "Shift Dock";
            }
            else if(punchtypeid == 1 && gcLocalTime.isAfter(lunchStart) && gcLocalTime.isBefore(lunchStop)){//start lunch
            gcLocalTime = s.getLunchStart();
            this.adjustmenttype = "Lunch Start";
            }
            else if(punchtypeid == 0 && gcLocalTime.isAfter(lunchStart) && gcLocalTime.isBefore(lunchStop)){//stop lunch
            gcLocalTime = s.getLunchStop();
            this.adjustmenttype = "Lunch Stop";
            }
            else if(gcLocalTime.isAfter(stopDockTime) && gcLocalTime.isBefore(stopGraceTime)){//stop dock period
            gcLocalTime = stopDockTime;
            this.adjustmenttype = "Shift Dock";
            }
            else if(gcLocalTime.isAfter(stopGraceTime) && gcLocalTime.isBefore(s.getStop())){//stop grace period
            gcLocalTime = s.getStop();
            this.adjustmenttype = "Shift Stop";
            }
            else if(gcLocalTime.isAfter(s.getStop()) && gcLocalTime.isBefore(stopIntervalTime)){//stop interval period
            gcLocalTime = s.getStop();
            this.adjustmenttype = "Shift Stop";
            }

            gc.set(Calendar.HOUR_OF_DAY, gcLocalTime.get(ChronoField.HOUR_OF_DAY));
            gc.set(Calendar.MINUTE, gcLocalTime.get(ChronoField.MINUTE_OF_HOUR));
            gc.set(Calendar.SECOND, gcLocalTime.get(ChronoField.SECOND_OF_MINUTE));

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
            output.append(this.adjustmenttype);
            
            this.adjustedtimestamp = output.toString();
        }
    
    
    public String printAdjustedTimestamp(){        
        return this.adjustedtimestamp;
    }
}