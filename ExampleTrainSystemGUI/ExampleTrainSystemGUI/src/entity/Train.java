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
public class Train
{
    private int TrainID;
    private String TrainType;
    private String TrainName;
    private int MaxSpeed;

    
    public Train(int TrainID,String TrainType,String TrainName,int MaxSpeed)
    {
        this.TrainID = TrainID;
        this.TrainType = TrainType;
        this.TrainName = TrainName;
        this.MaxSpeed = MaxSpeed;


    }

    public int getTrainID() {
        return TrainID;
    }


    public String getTrainType() {
        return TrainType;
    }
    
    public String getTrainName() {
        return TrainName;
    }
      
      public int getMaxSpeed() {
        return MaxSpeed;
    }


    @Override
    public String toString() {
        return "Train{" + "TrainID=" + TrainID + ", TrainType=" + TrainType + ", TrainName=" + TrainName + ", MaxSpeed=" + MaxSpeed +  '}';
    }
}
