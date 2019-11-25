package edu.jsu.mcis.tas_fa19;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;

public class TASLogic {

    /*public static int calculateTotalMinutes(ArrayList<Punch> punches, Shift shift) {

        boolean pair = false;
        long shiftStart = 0;
        long shiftStop = 0;
        long lunchStart = 0;
        long lunchStop = 0;

        for (Punch punch : punches) {

            HashMap<String, Long> shiftTimes = punch.getShiftTimes(shift);

            while (!pair) {
                switch(punch.getPunchtypeid()) {
                    // Clock-Out
                    case 0:
                        if (punch.getAdjustedTimeStamp() == shiftTimes.get("shiftStop")) {
                            shiftStop = punch.getAdjustedTimeStamp();
                            //Gives Minutes
                            shiftStop = (shiftStop / 1000) * 60;
                        }
                        else if (punch.getAdjustedTimeStamp() == shiftTimes.get("lunchStart")) {
                            lunchStart = punch.getAdjustedTimeStamp();
                            //Gives Minutes
                            lunchStart = (lunchStart / 1000) * 60;
                        }
                        pair = true;
                    // Clock-In
                    case 1:
                        if (punch.getAdjustedTimeStamp() == shiftTimes.get("shiftStart")) {
                            shiftStart = punch.getAdjustedTimeStamp();
                            //Gives Minutes
                            shiftStart = (shiftStart / 1000) * 60;
                        }
                        else if (punch.getAdjustedTimeStamp() == shiftTimes.get("lunchStop")) {
                            lunchStop = punch.getAdjustedTimeStamp();
                            //Gives Minutes
                            lunchStop = (lunchStop / 1000) * 60;
                        }
                    // Time-Out
                    case 2:
                        pair = true;
                }
            }
        }

        System.out.println((lunchStart - shiftStart));
        System.out.println((shiftStop - lunchStop));

        int m = (int) ((lunchStart - shiftStart) + (shiftStop - lunchStop));

        return m;
    }*/

    public static int calculateTotalMinutes(ArrayList<Punch> punches, Shift shift) {

        long in = 0;
        long out = 0;
        long beforeLunch = 0;
        long afterLunch = 0;

        for (Punch punch : punches) {

            switch(punch.getPunchtypeid()) {
                // Clock-Out
                case 0:
                    out = punch.getAdjustedTimeStamp();
                    //Gives Minutes
                    out = (out / 1000) * 60;
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

        //Calculate the total minutes worked
        int m = (int) ((((beforeLunch) + (afterLunch)) / 60) / 60);

        return m;
    }

}