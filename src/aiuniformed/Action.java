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
public class Action implements Serializable{
    
    private int blockId;
    private Direction d;
    
    public Action(int blockId,Direction d){
    this.blockId=blockId;
    this.d=d;
    }

    /**
     * @return the b
     */
    public int getBlockID() {
        return this.blockId;
    }

    /**
     * @param b the b to set
     */
    public void setBlockID(int blockId) {
        this.blockId = blockId;
    }

    /**
     * @return the d
     */
    public Direction getDirection() {
        return d;
    }

    /**
     * @param d the d to set
     */
    public void setDirection(Direction d) {
        this.d = d;
    }
}
