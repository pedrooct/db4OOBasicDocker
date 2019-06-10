/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codedb4o;

/**
 *
 * @author pedro
 */
public class Pilot {    
    private String name;
    private int points;  
    
    public Pilot(String name,int points) {
        this.name=name;
        this.points=points;
    }
        
    public int getPoints() {
        return points;
    }
    
    public void addPoints(int points) {
        this.points+=points;
    }
    
    public String getName() {
        return name;
    }
    
    public String toString() {
        return name+"/"+points;
    }
}
