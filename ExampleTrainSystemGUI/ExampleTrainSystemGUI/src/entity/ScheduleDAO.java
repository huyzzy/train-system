/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import core.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
/**
 *
 * @author Gokhan
 */
public class ScheduleDAO implements DAO<Schedule>
{   
    public ScheduleDAO() {
        
    }
    List<Schedule> schedules;
    /**
     * Get a single customer entity as a customer object
     * @param id
     * @return 
     */
    @Override
    public Optional<Schedule> get(int id) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Schedule WHERE ScheduleID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Schedule schedule = null;
            while (rs.next()) {
                schedule = new Schedule(rs.getInt("ScheduleID"), 
                        rs.getString("StartDateTime"), 
                        rs.getString("EndDateTime"), 
                        rs.getString("StartLocation"),
                        rs.getString("Destination"),
                        rs.getInt("TrainID"));

            }
            return Optional.ofNullable(schedule);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Get all customer entities as a List
     * @return 
     */
    @Override
    public List<Schedule> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        schedules = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Schedule";
            rs = db.executeQuery(sql);
            Schedule schedule = null;
            while (rs.next()) {
                schedule = new Schedule(rs.getInt("ScheduleID"), 
                        rs.getString("StartDateTime"), 
                        rs.getString("EndDateTime"), 
                        rs.getString("StartLocation"),
                        rs.getString("Destination"),
                        rs.getInt("TrainID"));

                schedules.add(schedule);
            }
            return schedules;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Insert a customer object into customer table
     * @param schedule 
     */
    @Override
    public void insert(Schedule schedule)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO Schedule(ScheduleID, "
                    + "StartDateTime, "
                    + "EndDateTime, "
                    + "StartLocation, "
                    + "Destination"
                    + "TrainID) VALUES (?, ?, ?, ?, ?,?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, schedule.getScheduleID());
            stmt.setString(2, schedule.getStartDateTime());
            stmt.setString(3, schedule.getEndDateTime());
            stmt.setString(4, schedule.getStartLocation()); 
            stmt.setString(5, schedule.getDestination()); 
            stmt.setInt(6,schedule.getTrainID());

            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new schedule was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Update a customer entity in database if it exists using a customer object
     * @param schedule
     */
    @Override
    public void update(Schedule schedule) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE Schedule SET StartDateTime=?, "
                    + "EndDateTime=?, "
                    + "StartLocation=?,"
                    + "Destination=?,"
                    + "TrainID=?"
                    + "WHERE ScheduleID=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setString(1, schedule.getStartDateTime());
            stmt.setString(2, schedule.getEndDateTime());
            stmt.setString(3, schedule.getStartLocation());
            stmt.setString(4, schedule.getDestination());
            stmt.setInt(5,schedule.getTrainID());
            stmt.setInt(6, schedule.getScheduleID());

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing Schedule was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Delete a customer from customer table if the entity exists
     * @param schedule 
     */
    @Override
    public void delete(Schedule schedule) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM Schedule WHERE ScheduleID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, schedule.getScheduleID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A Schedule was deleted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Get all column names in a list array
     * @return 
     */
    @Override
    public List<String> getColumnNames() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        List<String> headers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Schedule WHERE ScheduleID = -1";//We just need this sql query to get the column headers
            rs = db.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            //Get number of columns in the result set
            int numberCols = rsmd.getColumnCount();
            for (int i = 1; i <= numberCols; i++) {
                headers.add(rsmd.getColumnLabel(i));//Add column headers to the list
            }
            return headers;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        } 
    }
}
