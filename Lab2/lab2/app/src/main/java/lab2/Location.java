/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package lab2;

import java.util.Objects;

/**
 *
 * @author Teo
 */
public class Location 
{
    private String name;
    private String type;
    private int x;
    private int y;

    public Location() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public boolean hasSameName(String name1)
    {
        if(this.name.equals(name1))
            return true;
        else return false;
        
    }

    @Override
    public String toString() {
        return "Location{" + "name=" + name + '}';
    }

    
    
//    @Override
//    public String toString() {
//        return super.toString(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
//    }

    @Override
    public int hashCode() {
        return super.hashCode(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
    }
    
    
}
