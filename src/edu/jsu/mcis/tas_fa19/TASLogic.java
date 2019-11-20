package edu.jsu.mcis.tas_fa19;

import java.util.ArrayList;
import java.util.HashMap;
import org.json.simple.*;

public class TASLogic {

    public static int calculateTotalMinutes(ArrayList<Punch> punches, Shift shift) {
        for (Punch punch : punches) {

            boolean pair = true;

            HashMap<String, Long> shiftTimes = punch.getShiftTimes(shift);
            
            while (!pair) {
                switch(punch.getPunchtypeid()) {
                    //clock out
                    case 0:
                        if (punch.getAdjustedTimeStamp() == shiftTimes.get("shiftStart")) {
                            pair = false;
                        }
                    //clock in
                    case 1:
                    //time out
                    case 2:
                        if (1 == 1) {
                            pair = false;
                        }
                }
            }
        }

        int m = (int) (shift.getShiftDuration(shift.getStart(), shift.getStop()) -
                shift.getLunchDuration(shift.getLunchStart(), shift.getLunchStop()));

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