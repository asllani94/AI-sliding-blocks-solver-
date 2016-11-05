/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiuniformed;

import java.io.Serializable;

/**
 *
 * @author arnold
 */
public class Point implements Serializable{
   private int x;
   private int y;

  public Point(int x,int y){
  this.x=x;
  this.y=y;
  }
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
    
    public boolean areEqual(Point p2){
    return this.x==p2.x && this.y==p2.y;
        
    
    }
   
}
