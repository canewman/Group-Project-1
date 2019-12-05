package edu.jsu.mcis.tas_fa19;

import java.util.*;
import org.json.simple.JSONValue;

public class TASLogic {
    public static int calculateTotalMinutes(ArrayList<Punch> punches, Shift shift) {

        long in = 0;//in mill
        long out = 0;
        long beforeLunch = 0;
        long afterLunch = 0;
        boolean timeOut = false;
        int lunchDeduct = 0;       
        
        for (Punch punch : punches) {         
            switch(punch.getPunchtypeid()) {
                // Clock-Out
                case 0:
                    out = punch.getAdjustedTimeStamp();                    
                    out = (out / 1000) * 60;//Gives Minutes
                    break;
                //Clock-In
                case 1:
                    in = punch.getAdjustedTimeStamp();
                    //Gives Minutes
                    in = (in / 1000) * 60;
                    
                    break;
                //Time-Out
                case 2:
                    //Case 2 Logic
                    timeOut = true;
                    break;
            }

            //If the employee clocked out, check if it was before or after lunch, and then store those
            //to be added together for the total minutes worked later
            if (out != 0 && beforeLunch == 0) {
                beforeLunch = (out - in);
            }
            else if (out != 0 && afterLunch == 0) {
                afterLunch = (out - in);
            }

            out = 0;

        }
        
        boolean tookLunch = false;
        for(Punch punch : punches){//determines if they took a lunch
            if((punch.getAdjustmentType() == "Lunch Start") || (punch.getAdjustmentType() == "Lunch Stop")){
                tookLunch = true;
            }
        }       

        //Calculate the total minutes worked
        int m = (int) ((((beforeLunch) + (afterLunch)) / 60) / 60);
        
        if(tookLunch == false && m > shift.getLunchDeduct()){lunchDeduct = 30;}
        if(timeOut){lunchDeduct = 0;}        
        if(!tookLunch){m = m - lunchDeduct;}
        
        return m;
    }
    
    public static String getPunchListAsJSON(ArrayList<Punch> dailypunchlist){
  
        ArrayList<HashMap<String, String>> jsonData = new ArrayList();
        
        for(Punch p : dailypunchlist){
            
            HashMap<String, String>  punchData = new HashMap<>();
            punchData.put("punchdata", p.getAdjustmentType());
            punchData.put("originaltimestamp", String.valueOf(p.getOriginaltimestamp()));
            punchData.put("badgeid", p.getBadgeid());
            punchData.put("adjustedtimestamp", String.valueOf(p.getAdjustedTimeStamp()));
            punchData.put("punchtypeid", String.valueOf(p.getPunchtypeid()));
            punchData.put("terminalid", String.valueOf(p.getTerminalid()));
            punchData.put("id", String.valueOf(p.getID()));
            
            jsonData.add(punchData);
        }
        
        String json = JSONValue.toJSONString(jsonData);

        return json;
    }
}