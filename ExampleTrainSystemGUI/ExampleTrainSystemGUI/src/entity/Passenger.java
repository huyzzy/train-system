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
public class Passenger
{
    private int PassengerID;
    private String PassengerFirstName;
    private String PassengerLastName;
    private String PassengerEmail;

    
    public Passenger(int PassengerID,String PassengerFirstName,String PassengerLastName,String PassengerEmail)
    {
        this.PassengerID = PassengerID;
        this.PassengerFirstName = PassengerFirstName;
        this.PassengerLastName = PassengerLastName;
        this.PassengerEmail = PassengerEmail;


    }

    public int getPassengerID() {
        return PassengerID;
    }


    public String getPassengerFirstName() {
        return PassengerFirstName;
    }
    
    public String getPassengerLastName() {
        return PassengerLastName;
    }
      
      public String getPassengerEmail() {
        return PassengerEmail;
    }


    @Override
    public String toString() {
        return "Passenger{" + "PassengerID=" + PassengerID + ", PassengerFirstName=" + PassengerFirstName + ", PassengerLastName=" + PassengerLastName + ", PassengerEmail=" + PassengerEmail +  '}';
    }
}
