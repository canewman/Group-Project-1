package edu.jsu.mcis.tas_fa19;

import java.util.ArrayList;

public class TASLogic {

    public static int calculateTotalMinutes(ArrayList<Punch> punches, Shift shift) {
        for (Punch punch : punches) {

            boolean pair = true;
            String id = "";

            while (pair) {
                switch(punch.getPunchtypeid()) {
                    case 0:
                        if (id == punch.getBadgeid()) {
                            shift.setStop(punch.getAdjustedTimestamp().toString());
                            pair = false;
                        }
                    case 1:
                        id = punch.getBadgeid();
                        shift.setStart(punch.getAdjustedTimestamp().toString());
                    case 2:
                        if (id == punch.getBadgeid()) {
                            shift.setStop(punch.getAdjustedTimestamp().toString());
                            pair = false;
                        }
                }
            }
        }

        int m = (int) (shift.getShiftDuration(shift.getStart(), shift.getStop()) -
                shift.getLunchDuration(shift.getLunchStart(), shift.getLunchStop()));

        return m;
    }

}