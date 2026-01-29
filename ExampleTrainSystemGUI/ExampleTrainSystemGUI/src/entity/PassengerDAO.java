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
public class PassengerDAO implements DAO<Passenger>
{   
    public PassengerDAO() {
        
    }
    List<Passenger> passengers;
    /**
     * Get a single customer entity as a customer object
     * @param id
     * @return 
     */
    @Override
    public Optional<Passenger> get(int id) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Passenger WHERE Passenger_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Passenger passenger = null;
            while (rs.next()) {
                passenger = new Passenger(rs.getInt("PassengerID"), 
                        rs.getString("PassengerFirstName"), 
                        rs.getString("PassengerLastName"), 
                        rs.getString("PassengerEmail")); 
            }
            return Optional.ofNullable(passenger);
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
    public List<Passenger> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        passengers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Passenger";
            rs = db.executeQuery(sql);
            Passenger passenger = null;
            while (rs.next()) {
                passenger = new Passenger(rs.getInt("PassengerID"), 
                        rs.getString("PassengerFirstName"), 
                        rs.getString("PassengerLastName"), 
                        rs.getString("PassengerEmail")); 
                passengers.add(passenger);
            }
            return passengers;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Insert a customer object into customer table
     * @param passenger 
     */
    @Override
    public void insert(Passenger passenger)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO Passenger(PassengerID, "
                    + "PassengerFirstName, "
                    + "PassengerLastName, "
                    + "PassengerEmail) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, passenger.getPassengerID());
            stmt.setString(2, passenger.getPassengerFirstName());
            stmt.setString(3, passenger.getPassengerLastName());
            stmt.setString(4, passenger.getPassengerEmail());            
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new passenger was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Update a customer entity in database if it exists using a customer object
     * @param passenger
     */
    @Override
    public void update(Passenger passenger) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE Passenger SET PassengerFirstName=?, "
                    + "PassengerLastName=?, "
                    + "PassengerEmail=?,"
                    + "WHERE PassengerID=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setString(1, passenger.getPassengerFirstName());
            stmt.setString(2, passenger.getPassengerLastName());
            stmt.setString(3, passenger.getPassengerEmail());
            stmt.setInt(4, passenger.getPassengerID());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing passenger was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Delete a customer from customer table if the entity exists
     * @param passenger 
     */
    @Override
    public void delete(Passenger passenger) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM Passenger WHERE PassengerID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, passenger.getPassengerID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A passenger was deleted successfully!");
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
            String sql = "SELECT * FROM Passenger WHERE PassengerID = -1";//We just need this sql query to get the column headers
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
