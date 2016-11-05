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
public class Block implements Serializable {
    private int key=0;
    private boolean isVertical;
    private Point head;
   private Point tail;
    
    public Block(Point head,Point tail){
        this.head=head;
        this.tail=tail;
        
      if(Math.abs(head.getX()-tail.getX())>=1 && Math.abs(head.getY()-tail.getY())==0){
       isVertical=true;
       }
       else if(Math.abs(head.getY()-tail.getY())>=1 && Math.abs(head.getX()-tail.getX())==0){
       isVertical=false;
       }
           
        
        
    
    }

    
    
    public Point getHead(){
    return this.head;
    }
    public Point getTail(){
    return this.tail;
    }
    
    
    
    
//    boolean moveLeftOne(Grid g){
//    if(isVertical)
//        return false;
//    else{
//        if(g.canMoveLeft(this))
//    
//        return true;
//    }
//    }
    
    
    
    public int getKey(){
    return key;
    }
    
    
    public void setKey(int key){
    this.key=key;
    }
    
    public boolean isVertical(){
    return this.isVertical;
    }

    /**
     * @param head the head to set
     */
    public void setHead(Point head) {
        this.head = head;
    }

    /**
     * @param tail the tail to set
     */
    public void setTail(Point tail) {
        this.tail = tail;
    }
    
    public boolean sameLocation(Block b2){
    return(this.head.areEqual(b2.head) && this.tail.areEqual(b2.tail));
    }
    
    
    }

