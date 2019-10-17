package edu.jsu.mcis.tas_fa19;

import java.time.LocalDate;

public class Punch {
    private String id;
    private String punchtypeid;
    private String terminalid;
    private Badge badgeid;
    private LocalDate OriginalTimeStamp;
    
    public Punch(Badge badgeid, String terminalid, String punchtypeid) {
        this.punchtypeid = punchtypeid;
        this.terminalid = terminalid;
        this.badgeid = badgeid;
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
