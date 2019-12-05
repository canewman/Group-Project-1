package edu.jsu.mcis.tas_fa19;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

public class Punch {    
    private int id;
    private int punchtypeid;
    private int terminalid;
    private String badgeid;
    private GregorianCalendar adjustedtimestamp = new GregorianCalendar();
    private long adjustedTimeStampInMill;
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
       this.originalTimeStamp = ts;
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
    
    public String getAdjustmentType(){
        return this.adjustmenttype;
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
            adjustedtimestamp.setTimeInMillis(gc.getTimeInMillis());            
            adjustmenttype = "";

            int dayOfWeek = adjustedtimestamp.get(Calendar.DAY_OF_WEEK);
            int shiftinterval = s.getInterval();//in minutes
            
            HashMap<String, Long> values = getShiftTimes(s);
            
            long shiftStart = values.get("shiftStart");
            long shiftStop = values.get("shiftStop");
            long lunchStart = values.get("lunchStart");
            long lunchStop = values.get("lunchStop");
            long startGrace = values.get("startGrace");
            long stopGrace = values.get("stopGrace");
            long startDock = values.get("startDock");
            long stopDock = values.get("stopDock");
            long startInterval = values.get("startInterval");
            long stopInterval = values.get("stopInterval");
            
            
            
            switch(this.punchtypeid){
            
                case 0://clock out
                    if((originalTimeStamp >= lunchStart) && (originalTimeStamp <= lunchStop)){//start of lunch
                        this.adjustmenttype = "Lunch Start";
                        this.adjustedTimeStampInMill = lunchStart;
                        this.adjustedtimestamp.setTimeInMillis(adjustedTimeStampInMill);
                    }
                    else if((originalTimeStamp < stopGrace) && (originalTimeStamp >= stopDock)){//stop dock
                        this.adjustmenttype = "Shift Dock";
                        this.adjustedTimeStampInMill = stopDock;
                        this.adjustedtimestamp.setTimeInMillis(adjustedTimeStampInMill);
                    }
                    else if((originalTimeStamp >= stopGrace) && (originalTimeStamp <= shiftStop)){//stop grace
                        this.adjustmenttype = "Shift Stop";
                        this.adjustedTimeStampInMill = shiftStop; 
                        this.adjustedtimestamp.setTimeInMillis(adjustedTimeStampInMill);
                    }
                    else if((originalTimeStamp >= shiftStop) && (originalTimeStamp <= stopInterval)){//stop interval
                        this.adjustmenttype = "Shift Stop";
                        this.adjustedTimeStampInMill = shiftStop;
                        this.adjustedtimestamp.setTimeInMillis(adjustedTimeStampInMill);
                    }  
                    else{
                        this.adjustedtimestamp.setTimeInMillis(gc.getTimeInMillis());
                        this.adjustmenttype = "None";
                        this.adjustedtimestamp.set(Calendar.SECOND, 0);
                    }
                    break;
                case 1://clock in
                    
                    if((originalTimeStamp >= startInterval) && (originalTimeStamp <= shiftStart)){//start interval
                        this.adjustmenttype = "Shift Start";
                        this.adjustedTimeStampInMill = shiftStart; 
                        this.adjustedtimestamp.setTimeInMillis(adjustedTimeStampInMill);
                    }
                    else if((originalTimeStamp >= shiftStart) && (originalTimeStamp <= startGrace)){////start grace
                        this.adjustmenttype = "Shift Start";
                        this.adjustedTimeStampInMill = shiftStart;
                        this.adjustedtimestamp.setTimeInMillis(adjustedTimeStampInMill);
                    }
                    else if((originalTimeStamp > startGrace) && (originalTimeStamp <= startDock)){//start dock
                        this.adjustmenttype = "Shift Dock";
                        this.adjustedTimeStampInMill = startDock;
                        this.adjustedtimestamp.setTimeInMillis(adjustedTimeStampInMill);
                    }
                    else if((originalTimeStamp >= lunchStart) && (originalTimeStamp <= lunchStop)){//stop lunch
                        this.adjustmenttype = "Lunch Stop";
                        this.adjustedTimeStampInMill = lunchStop;
                        this.adjustedtimestamp.setTimeInMillis(adjustedTimeStampInMill);  
                    }   
                    else{
                        this.adjustedtimestamp.setTimeInMillis(gc.getTimeInMillis());
                        this.adjustmenttype = "None";
                        this.adjustedtimestamp.set(Calendar.SECOND, 0);
                    }
                    break;
            }            
            
            if(((adjustedtimestamp.get(Calendar.MINUTE) % shiftinterval) != 0) ||
                    (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY)){
                adjustedtimestamp.setTimeInMillis(gc.getTimeInMillis());
                
                if(gc.get(Calendar.MINUTE) % s.getInterval() >= s.getInterval() / 2){ //rounding up               
                    while((adjustedtimestamp.get(Calendar.MINUTE) % shiftinterval) != 0){
                        adjustedtimestamp.add(Calendar.MINUTE, 1);
                    }                    
                }
                else{//rounding down                        
                    while((adjustedtimestamp.get(Calendar.MINUTE) % shiftinterval) != 0){
                            adjustedtimestamp.add(Calendar.MINUTE, -1);
                    }
                }                
                adjustedtimestamp.set(Calendar.SECOND, 0);
                this.adjustmenttype = "Interval Round";            
            }            
        }
    
    public HashMap<String, Long> getShiftTimes(Shift s){
        
            int startHour = s.getStart().getHour();
            int startMinute = s.getStart().getMinute();
            
            long intervalMill = s.getInterval() * 60000;
            long graceMill = s.getGracePeriod() * 60000;
            long dockMill = s.getDock() * 60000;            
            
            //shift start
            GregorianCalendar start = new GregorianCalendar();
            start.setTimeInMillis(gc.getTimeInMillis());
            start.set(Calendar.HOUR_OF_DAY, startHour);
            start.set(Calendar.MINUTE, startMinute);
            start.set(Calendar.SECOND, 0);
                   
            
            long shiftStart = start.getTimeInMillis();
            long startInterval = shiftStart - intervalMill;//parentheses converts the 15 minute interval to mill
            long startGrace = shiftStart + graceMill;//parentheses converts the 5 minute grace period to mill
            long startDock = shiftStart + dockMill;//parentheses converts the 15 minute dock to mill
            
            //getting end of shift parameters in mill
            int stopHour = s.getStop().getHour();
            int stopMinute = s.getStop().getMinute(); 
            
            //Shift stop
            GregorianCalendar stop = new GregorianCalendar();
            stop.setTimeInMillis(shiftStart);//set stop shift to punch day
            stop.set(Calendar.HOUR_OF_DAY, stopHour);
            stop.set(Calendar.MINUTE, stopMinute);
            stop.set(Calendar.SECOND, 0);              
            
            long shiftStop = stop.getTimeInMillis();
            long stopInterval = shiftStop + intervalMill;
            long stopGrace = shiftStop - graceMill;
            long stopDock = shiftStop - dockMill;
            
            //lunch start
            GregorianCalendar lunchstart = new GregorianCalendar();
            lunchstart.setTimeInMillis(shiftStart);//sets lunch to the punch day
            lunchstart.set(Calendar.HOUR_OF_DAY, s.getLunchStart().getHour());
            lunchstart.set(Calendar.MINUTE, s.getLunchStart().getMinute());
            lunchstart.set(Calendar.SECOND, 0);
            
            long lunchStart = lunchstart.getTimeInMillis();
            
            //lunch stop
            GregorianCalendar lunchstop = new GregorianCalendar();
            lunchstop.setTimeInMillis(shiftStart);//sets lunch to the punch day
            lunchstop.set(Calendar.HOUR_OF_DAY, s.getLunchStop().getHour());
            lunchstop.set(Calendar.MINUTE, s.getLunchStop().getMinute());
            lunchstop.set(Calendar.SECOND, 0);
            
            long lunchStop = lunchstop.getTimeInMillis();
            
            HashMap<String, Long> values = new HashMap();
            values.put("shiftStart", shiftStart);
            values.put("shiftStop", shiftStop);
            values.put("lunchStart", lunchStart);
            values.put("lunchStop", lunchStop);
            values.put("startDock", startDock);
            values.put("stopDock", stopDock);
            values.put("startInterval", startInterval);
            values.put("stopInterval", stopInterval);
            values.put("startGrace", startGrace);
            values.put("stopGrace", stopGrace);
            
            return values;
    }
    
    public String printAdjustedTimestamp(){ 
        
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
            output.append(sdf.format(adjustedtimestamp.getTime()).toUpperCase());
            output.append( " (" + this.adjustmenttype + ")");
            
            return output.toString();
    }
    
    public long getAdjustedTimeStamp(){
        return this.adjustedtimestamp.getTimeInMillis();
    }
    
    public String getAdjustmentType(){
        return this.adjustmenttype;
    }
}