package edu.jsu.mcis.tas_fa19;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.GregorianCalendar;
import java.time.LocalDate;


public class Punch {
    private String id;
    private String punchtypeid;
    private String terminalid;
    private Badge badgeid;
    private GregorianCalendar originalTimeStamp;
    private LocalDate OriginalTimeStamp;
    
    public Punch(Badge badgeid, String terminalid, String punchtypeid) {
        this.punchtypeid = punchtypeid;
        this.terminalid = terminalid;
        this.badgeid = badgeid;
    }
    
    public void setOriginalTimeStamp(String info)//a punch pulled from sql
    {
        String parts[] = info.split(" ");
        String day[] = parts[0].split("-");
        String time[] = parts[1].split(":");
        this.originalTimeStamp = new GregorianCalendar(Integer.parseInt(day[0]), Integer.parseInt(day[1]), Integer.parseInt(day[2]), Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2]));
    }
    
    public void setOriginalTimeStamp()//a new punch using current time
    {
        this.originalTimeStamp = new GregorianCalendar();
    }
    
    public String printOriginalTimeStamp()
    {     
        String output = "";
        output.concat(badgeid.toString());
        
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
    
    public Badge getBadgeID(){
        return badgeid;
    }
    
    public String getID() {
        return id;
    }
    
    public LocalDate getOriginalTimeStamp(){
        return OriginalTimeStamp;
    }
    
    public void setID(String id) {
        this.id = id;
    }
    
    public void setPunchTypeID(String punchtypeid) {
        this.punchtypeid = punchtypeid;
    }
    
    public void setTerminalID(String terminalid) {
        this.terminalid = terminalid;
    }
    
    public void setBadgeID(Badge badgeid) {
        this.badgeid = badgeid;
    }
    
    public void setOriginalTimeStamp(LocalDate OriginalTimeStamp){
        this.OriginalTimeStamp = OriginalTimeStamp;
    }
}
