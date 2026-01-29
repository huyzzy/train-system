/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
/**
 *
 * @author Gokhan
 */
public class Ticket
{
    private int TicketID;
    private int PassengerID;
    private String PurchaseDateTime;
    private int TicketCost;
    private int ScheduleID;

    
    public Ticket(int TicketID,int PassengerID,String PurchaseDateTime,int TicketCost, int ScheduleID)
    {
        this.TicketID = TicketID;
        this.PassengerID = PassengerID;
        this.PurchaseDateTime = PurchaseDateTime;
        this.TicketCost = TicketCost;
        this.ScheduleID = ScheduleID;




    }

    public int getTicketID() {
        return TicketID;
    }


    public int getPassengerID() {
        return PassengerID;
    }
    
    public String getPurchaseDateTime() {
        return PurchaseDateTime;
    }
      
      public int getTicketCost() {
        return TicketCost;
    }
      public int getScheduleID() {
        return ScheduleID;
    }
    


    @Override
    public String toString() {
        return "Ticket{" + "TicketID=" + TicketID + ", PassengerID=" + PassengerID + ", PurchaseDateTime=" + PurchaseDateTime + ", TicketCost=" + TicketCost + ", ScheduleID=" + ScheduleID + '}';
    }
}
