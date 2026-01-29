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
public class TicketDAO implements DAO<Ticket>
{   
    public TicketDAO() {
        
    }
    List<Ticket> tickets;
    /**
     * Get a single customer entity as a customer object
     * @param id
     * @return 
     */
    @Override
    public Optional<Ticket> get(int id) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Ticekt WHERE TicketID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Ticket ticket = null;
            while (rs.next()) {
                ticket = new Ticket(rs.getInt("TicketID"), 
                        rs.getInt("PassengerID"), 
                        rs.getString("PurchaseDateTime"), 
                        rs.getInt("TicketCost"),
                        rs.getInt("ScheduleID"));

            }
            return Optional.ofNullable(ticket);
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
    public List<Ticket> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        tickets = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Ticket";
            rs = db.executeQuery(sql);
            Ticket ticket = null;
            while (rs.next()) {
                ticket = new Ticket(rs.getInt("TicketID"), 
                        rs.getInt("PassengerID"), 
                        rs.getString("PurchaseDateTime"), 
                        rs.getInt("TicketCost"),
                        rs.getInt("ScheduleID"));

                tickets.add(ticket);
            }
            return tickets;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Insert a customer object into customer table
     * @param ticket 
     */
    @Override
    public void insert(Ticket ticket)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO Ticket(TicketID, "
                    + "PassengerID, "
                    + "PurchaseDateTime, "
                    + "TicketCost) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, ticket.getTicketID());
            stmt.setInt(2, ticket.getPassengerID());
            stmt.setString(3, ticket.getPurchaseDateTime());
            stmt.setInt(4, ticket.getTicketCost()); 
            
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new ticket was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Update a customer entity in database if it exists using a customer object
     * @param ticket
     */
    @Override
    public void update(Ticket ticket) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE Ticket SET PassengerID=?, "
                    + "PurchaseDateTime=?, "
                    + "TicketCost=?,"
                    + "WHERE TicketID=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, ticket.getPassengerID());
            stmt.setString(2, ticket.getPurchaseDateTime());
            stmt.setInt(3, ticket.getTicketCost());
            stmt.setInt(5, ticket.getTicketID());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing Ticket was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Delete a customer from customer table if the entity exists
     * @param ticket 
     */
    @Override
    public void delete(Ticket ticket) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM Ticekt WHERE TicketID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, ticket.getTicketID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A Ticket was deleted successfully!");
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
            String sql = "SELECT * FROM Ticket WHERE TicketID = -1";//We just need this sql query to get the column headers
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
