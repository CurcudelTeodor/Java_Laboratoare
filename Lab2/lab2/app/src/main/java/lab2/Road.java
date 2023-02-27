/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2;

/**
 *
 * @author Teo
 */
public class Road 
{
    private String type;
    private int length;
    private int speedLimit;
    
    public Road() {
    }

    public String getType() {
        return type;
    }

    public int getLength() {
        return length;
    }

    public int getSpeedLimit() {
        return speedLimit;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setSpeedLimit(int speedLimit) {
        this.speedLimit = speedLimit;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Road{" + "type=" + type + '}';
    }
    
    
    
}
