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
public class Schedule
{
    private int ScheduleID;
    private String StartDateTime;
    private String EndDateTime;
    private String StartLocation;
    private String Destination;
    private int TrainID;

    
    public Schedule(int ScheduleID,String StartDateTime,String EndDateTime,String StartLocation, String Destination, int TrainID)
    {
        this.ScheduleID = ScheduleID;
        this.StartDateTime = StartDateTime;
        this.EndDateTime = EndDateTime;
        this.StartLocation = StartLocation;
        this.Destination = Destination;
        this.TrainID = TrainID;



    }

    public int getScheduleID() {
        return ScheduleID;
    }


    public String getStartDateTime() {
        return StartDateTime;
    }
    
    public String getEndDateTime() {
        return EndDateTime;
    }
      
      public String getStartLocation() {
        return StartLocation;
    }
      
      public String getDestination() {
        return Destination;
    }
      public int getTrainID() {
        return TrainID;
    }   

    @Override
    public String toString() {
        return "Schedule{" + "ScheduleID=" + ScheduleID + ",StartDateTime=" + StartDateTime + ", EndDateTime=" + EndDateTime + ", StartLocation=" + StartLocation + ", Destination=" + Destination + ", TrainID=" + TrainID + '}';
    }
}
