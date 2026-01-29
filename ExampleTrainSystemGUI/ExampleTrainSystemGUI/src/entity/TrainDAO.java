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
public class TrainDAO implements DAO<Train>
{   
    public TrainDAO() {
        
    }
    List<Train> trains;
    /**
     * Get a single customer entity as a customer object
     * @param id
     * @return 
     */
    @Override
    public Optional<Train> get(int id) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Train WHERE TrainID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Train train = null;
            while (rs.next()) {
                train = new Train(rs.getInt("TrainID"), 
                        rs.getString("TrainType"), 
                        rs.getString("TrainName"), 
                        rs.getInt("MaxSpeed")); 
            }
            return Optional.ofNullable(train);
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
    public List<Train> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        trains = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Train";
            rs = db.executeQuery(sql);
            Train train = null;
            while (rs.next()) {
                train = new Train(rs.getInt("TrainID"), 
                        rs.getString("TrainType"), 
                        rs.getString("TrainName"), 
                        rs.getInt("MaxSpeed")); 
                trains.add(train);
            }
            return trains;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Insert a customer object into customer table
     * @param train 
     */
    @Override
    public void insert(Train train)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO Train(TrainID, "
                    + "TrainType, "
                    + "TrainName, "
                    + "MaxSpeed) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, train.getTrainID());
            stmt.setString(2, train.getTrainType());
            stmt.setString(3, train.getTrainName());
            stmt.setInt(4, train.getMaxSpeed());            
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new train was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Update a customer entity in database if it exists using a customer object
     * @param train
     */
    @Override
    public void update(Train train) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE Train SET TrainType=?, "
                    + "TrainName=?, "
                    + "MaxSpeed=?,"
                    + "WHERE TrainID=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setString(1, train.getTrainType());
            stmt.setString(2, train.getTrainName());
            stmt.setInt(3, train.getMaxSpeed());
            stmt.setInt(4, train.getTrainID());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing train was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Delete a customer from customer table if the entity exists
     * @param train 
     */
    @Override
    public void delete(Train train) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM Train WHERE TrainID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, train.getTrainID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A train was deleted successfully!");
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
            String sql = "SELECT * FROM Train WHERE TrainID = -1";//We just need this sql query to get the column headers
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
