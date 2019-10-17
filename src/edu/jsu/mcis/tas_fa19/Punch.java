package edu.jsu.mcis.tas_fa19;

import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.GregorianCalendar;

public class Punch {
    private String id;
    private String terminalid;
    private Badge badgeid;
    private GregorianCalendar originalTimeStamp;
    
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
            case 0:
                output.concat(" CLOCKED OUT: ");
                break;
            case 1:
                output.concat(" CLOCKED IN");
                break;
            case 2:
                output.concat(" TIMEOUT");
                break;
        }
        output.concat(" ");
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss");
        output.concat(dtf.format((TemporalAccessor) this.originalTimeStamp.getTime()));
        
        return output;
    }
}
