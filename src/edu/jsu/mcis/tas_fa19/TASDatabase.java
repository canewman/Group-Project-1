package edu.jsu.mcis.tas_fa19;

import java.sql.*;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class TASDatabase {

    Badge badgeResults;
    Punch punchResults;
    Shift shiftResults;

    Connection conn = null;
    PreparedStatement pstSelect = null, pstUpdate = null;
    ResultSet resultSet = null;

    String query;

    private boolean hasResults;
    private boolean gotResults = false;

    public TASDatabase() {
        try {
            //Identify the Server
            String server = ("jdbc:mysql://localhost/tas");
            String username = "db_user";
            String password = "CS488";
            System.out.println("Connecting to " + server + "...");

            //Load the MySQL JDBC Driver
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            //Open Connection
            conn = DriverManager.getConnection(server, username, password);

            //Test Connection
            if (conn.isValid(0)) {

                //Connection Open!
                System.out.println("Connected Successfully!");

            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    public Badge getBadge(String badgeID) {
        try {
            //Create new Badge object
            badgeResults = new Badge();

            //Test Connection
            if (conn.isValid(0)) {

                //Prepare Select Query
                query = "SELECT * FROM badge";
                pstSelect = conn.prepareStatement(query);

                //Execute Select Query
                System.out.println("Submitting query for badge information ...");
                hasResults = pstSelect.execute();

                //Get Results
                System.out.println("Getting badge information ...");

                //While there is information, retrieve it
                while (hasResults || pstSelect.getUpdateCount() != -1) {
                    if (hasResults) {

                        //Get ResultSet Metadata
                        resultSet = pstSelect.getResultSet();

                        //Pull the information from the database and store it
                        while (resultSet.next()) {
                            //Add badge information to Badge object
                            if (resultSet.getString("id").equals(badgeID)) {
                                badgeResults.setDescription(resultSet.getString("description"));
                                gotResults = true;
                            }
                        }

                        //Check for More Data
                        hasResults = pstSelect.getMoreResults();
                    }
                }

                //If the database contains the ID the user entered, then it will set the ID in the object and return
                //the completed object
                if (gotResults) {
                    badgeResults.setID(badgeID);
                    return badgeResults;
                } else {
                    System.out.println("Please input a correct ID");
                }
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return null;
    }

    public Punch getPunch(int punchID) {
        try {
            //Create new Punch object
            punchResults = new Punch();

            //Test connection
            if (conn.isValid(0)) {

                //Prepare select query, this one is different, additionally it gets the timestamps as milliseconds
                query = "SELECT *, UNIX_TIMESTAMP(originaltimestamp) * 1000 AS ts FROM punch";
                pstSelect = conn.prepareStatement(query);

                //Execute select query
                System.out.println("Submitting query for punch information ...");
                hasResults = pstSelect.execute();

                //Get results
                System.out.println("Getting punch information ...");

                //While there is information, retrieve it
                while (hasResults || pstSelect.getUpdateCount() != -1) {
                    if (hasResults) {

                        //Get ResultSet Metadata
                        resultSet = pstSelect.getResultSet();

                        //Pull the information from the database and store it
                        while (resultSet.next()) {
                            //Add punch information to Punch object
                            if (resultSet.getInt("id") == punchID) {

                                punchResults.setTerminalID(resultSet.getInt("terminalid"));
                                punchResults.setBadgeid(resultSet.getString("badgeid"));
                                //Get the milliseconds as a "Long"
                                punchResults.setOriginalTimestamp(resultSet.getLong("ts"));
                                punchResults.setPunchTypeID(resultSet.getInt("punchtypeid"));
                                gotResults = true;
                                System.out.println("Got results!");
                            }
                        }

                        //Check for more data
                        hasResults = pstSelect.getMoreResults();
                    }
                }

                //If the database contains the ID the user entered, then it will set the ID in the object and return
                //the completed object
                if (gotResults) {
                    punchResults.setID(punchID);
                    return punchResults;
                } else {
                    System.out.println("Please input a correct ID");
                }
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return null;
    }

    public Shift getShift(int shiftID) {
        try {
            //Create new Shift object
            shiftResults = new Shift();

            //Test connection
            if (conn.isValid(0)) {

                //Prepare select query
                query = "SELECT * FROM shift";
                pstSelect = conn.prepareStatement(query);

                //Execute select query
                System.out.println("Submitting query for shift information ...");
                hasResults = pstSelect.execute();

                //Get results
                System.out.println("Getting shift information ...");

                //While there is information, retrieve it
                while (hasResults || pstSelect.getUpdateCount() != -1) {
                    if (hasResults) {

                        //Get ResultSet Metadata
                        resultSet = pstSelect.getResultSet();

                        //Pull the information from the database and store it
                        while (resultSet.next()) {
                            //Add shift information to Shift object
                            if (resultSet.getInt("id") == shiftID) {
                                shiftResults.setID(resultSet.getInt("id"));
                                shiftResults.setDescription(resultSet.getString("description"));
                                shiftResults.setStart(resultSet.getString("start"));
                                shiftResults.setStop(resultSet.getString("stop"));
                                shiftResults.setInterval(resultSet.getInt("interval"));
                                shiftResults.setGracePeriod(resultSet.getInt("graceperiod"));
                                shiftResults.setDock(resultSet.getInt("dock"));
                                shiftResults.setLunchStart(resultSet.getString("lunchstart"));
                                shiftResults.setLunchStop(resultSet.getString("lunchstop"));
                                shiftResults.setLunchDeduct(resultSet.getInt("lunchdeduct"));
                                gotResults = true;
                            }
                        }

                        //Check for more data
                        hasResults = pstSelect.getMoreResults();
                    }
                }

                //If the database contains the ID the user entered, then it will set the ID in the object and return
                //the completed object
                if (gotResults) {
                    shiftResults.setID(shiftID);
                    return shiftResults;
                } else {
                    System.out.println("Please input a correct ID");
                }
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return null;
    }

    public Shift getShift(Badge badge) {
        try {
            //Create new Shift object
            shiftResults = new Shift();

            int shiftID = 0;

            //Test connection
            if (conn.isValid(0)) {

                //Prepare select query
                query = "SELECT * FROM employee";
                pstSelect = conn.prepareStatement(query);

                //Execute select query
                System.out.println("Submitting query for employee information ...");
                hasResults = pstSelect.execute();

                //Get results
                System.out.println("Getting employee information ...");

                //While there is information, retrieve it
                while (hasResults || pstSelect.getUpdateCount() != -1) {
                    if (hasResults) {

                        //Get ResultSet Metadata
                        resultSet = pstSelect.getResultSet();

                        //Pull the information from the database and store it
                        while (resultSet.next()) {
                            //Add shift information to Shift object
                            if (resultSet.getString("badgeid").equals(badge.getId())) {
                                shiftID = resultSet.getInt("shiftid");
                            }
                        }

                        //Check for more data
                        hasResults = pstSelect.getMoreResults();
                    }
                }

                //Prepare select query
                query = "SELECT * FROM shift";
                pstSelect = conn.prepareStatement(query);

                //Execute select query
                System.out.println("Submitting query for shift information ...");
                hasResults = pstSelect.execute();

                //Get results
                System.out.println("Getting shift information ...");

                //While there is information, retrieve it
                while (hasResults || pstSelect.getUpdateCount() != -1) {
                    if (hasResults) {

                        //Get ResultSet Metadata
                        resultSet = pstSelect.getResultSet();

                        //Pull the information from the database and store it
                        while (resultSet.next()) {
                            //Add shift information to Shift object
                            if (resultSet.getInt("id") == shiftID) {
                                shiftResults.setID(resultSet.getInt("id"));
                                shiftResults.setDescription(resultSet.getString("description"));
                                shiftResults.setStart(resultSet.getString("start"));
                                shiftResults.setStop(resultSet.getString("stop"));
                                shiftResults.setInterval(resultSet.getInt("interval"));
                                shiftResults.setGracePeriod(resultSet.getInt("graceperiod"));
                                shiftResults.setDock(resultSet.getInt("dock"));
                                shiftResults.setLunchStart(resultSet.getString("lunchstart"));
                                shiftResults.setLunchStop(resultSet.getString("lunchstop"));
                                shiftResults.setLunchDeduct(resultSet.getInt("lunchdeduct"));
                                gotResults = true;
                            }
                        }

                        //Check for more data
                        hasResults = pstSelect.getMoreResults();
                    }
                }

                //If the database contains the ID the user entered, then it will set the ID in the object and return
                //the completed object
                if (gotResults) {
                    shiftResults.setID(shiftID);
                    return shiftResults;
                } else {
                    System.out.println("Please input a correct ID");
                }
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
        return null;
    }
    
    
    public ArrayList<Punch> getDailyPunchList(Badge badge, long ts){
    
        try {
            //Create new Punch ArrayList
            ArrayList<Punch> punches = new ArrayList();
            GregorianCalendar gcBegin = new GregorianCalendar(); //begining of the day
            GregorianCalendar gcEnd = new GregorianCalendar(); //end of the day
            gcBegin.setTimeInMillis(ts);
            gcEnd.setTimeInMillis(ts);
            
            gcBegin.set(3, 00); //hours
            gcBegin.set(4, 00); //minutes
            gcBegin.set(5, 00); //seconds
            
            gcEnd.set(3, 23); //hours
            gcEnd.set(4, 59); //minutes
            gcEnd.set(5, 59); //seconds
            
            

            //Test connection
            if (conn.isValid(0)) {

                //Prepare select query, this one is different, additionally it gets the timestamps as milliseconds
                query = "SELECT * from punch where originaltimestamp BETWEEN '" + gcBegin +"' AND '" + gcEnd + "'";
                pstSelect = conn.prepareStatement(query);

                //Execute select query
                System.out.println("Submitting query for punch information ...");
                hasResults = pstSelect.execute();

                //Get results
                System.out.println("Getting punch information ...");

                //While there is information, retrieve it
                while (hasResults || pstSelect.getUpdateCount() != -1) {
                    if (hasResults) {

                        //Get ResultSet Metadata
                        resultSet = pstSelect.getResultSet();

                        //Pull the information from the database and store it
                        while (resultSet.next()) {
                            
                            punchResults = new Punch();
                            //Add punch information to Punch object
                            if (resultSet.getString("badgeid").equals(badge.getId())) {

                                punchResults.setTerminalID(resultSet.getInt("terminalid"));
                                punchResults.setBadgeid(resultSet.getString("badgeid"));
                                //Get the milliseconds as a "Long"
                                punchResults.setOriginalTimestamp(resultSet.getLong("ts"));
                                punchResults.setPunchTypeID(resultSet.getInt("punchtypeid"));
                                gotResults = true;
                                if (gotResults) {
                                    punchResults.setID(resultSet.getInt("id"));                                    
                                    punches.add(punchResults);
                                } else {
                                    System.out.println("Please input a correct ID");
                                }
                                
                            }
                        }

                        //Check for more data
                        hasResults = pstSelect.getMoreResults();
                    }
                }

                //If the database contains the ID the user entered, then it will set the ID in the object and return
                //the completed object
                return punches;
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }        
        return null;
    }


    public void close() {
        try {
            //Close database connection
            conn.close();

            //Close ResultSet object
            if (resultSet != null) {
                try {
                    resultSet.close();
                    resultSet = null;
                } catch (Exception e) {
                }
            }

            //Close PreparedStatementSelect object
            if (pstSelect != null) {
                try {
                    pstSelect.close();
                    pstSelect = null;
                } catch (Exception e) {
                }
            }

            //Close PreparedStatementUpdate object
            if (pstUpdate != null) {
                try {
                    pstUpdate.close();
                    pstUpdate = null;
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
}