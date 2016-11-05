/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiuniformed;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class SearchBFS {

    Grid currentState;//internal state

   

    //queue for Breadth Fist Search
    Queue<Grid> queue;
    ArrayList<Grid> CLOSED;
    private int nodesExpanded;
    private int nodesGenerated;
    private int nodesInMemory;
    private int target;

    
    //constructor
    public SearchBFS(Grid initialState,int target) {

        this.currentState = initialState;

       
        queue = new LinkedList<>();
        CLOSED = new ArrayList<>();

        this.nodesExpanded = 0;
        this.nodesGenerated = 0;
        this.nodesInMemory=0;
        this.target=target;

    }

    
    void doSearch() {

        //add our initial state to queueu
    queue.add(currentState);

        //start expansion and generation
        while (!queue.isEmpty()) {

            //state to work with next
            Grid currentSt = (Grid) queue.remove();

            
            //check if we reached goal state
            if (this.isGoal(currentSt)) {
                System.out.println("FINALLY FOUND");
                currentSt.display();
                System.out.println("To solve the problem follow the current sequence");
                this.displayActions(currentSt);
                break;
            }

           
            //get the set  of next states
            
            if (!this.isAlreadyExpanded(currentSt, CLOSED)) {
                
                //we are expanding the current node
                this.nodesExpanded++;
                
                //calling the function succersor state brings all feasible states from current state
                ArrayList<Grid> states = succesorStateSet(currentSt); 

                //increase the number of generated nodes
                this.nodesGenerated += states.size();

                //add current node to CLOSED list since we finished with it
                CLOSED.add(currentSt);

                //add set of states to the queue
                for (Grid g : states) {
                    queue.add(g);

                }

            }
            if(queue.size()>this.nodesInMemory)
                nodesInMemory=queue.size();

        }

    }

    //returns a set of all states that can be reached from current node
    public ArrayList<Grid> succesorStateSet(Grid currentState) {

        ArrayList<Grid> nextStates = new ArrayList<>();

        //for each piece of block I try every single action and I put the changed state to set of states
        for (Block b : currentState.getBlockList()) {

            //copy current state so we can make changes to the copy
            Grid cloneUP = (Grid) currentState.deepClone(currentState);

            //if current block piece can be moved up then do it. Write down the action taken and add the resulted state to the set
            if (cloneUP.moveBlock(b.getKey(), Direction.UP, cloneUP)) {
                cloneUP.setParent(currentState);
                cloneUP.setActionTaken(new Action(b.getKey(), Direction.UP));
                nextStates.add(cloneUP);
            }

            //copy current state so we can make changes to the copy
            Grid cloneDOWN = (Grid) currentState.deepClone(currentState);
            //if current block piece can be moved down then do it. Write down the action taken and add the resulted state to the set
            if (cloneDOWN.moveBlock(b.getKey(), Direction.DOWN, cloneDOWN)) {
                cloneDOWN.setParent(currentState);
                cloneDOWN.setActionTaken(new Action(b.getKey(), Direction.DOWN));
                nextStates.add(cloneDOWN);
            }

            //copy current state so we can make changes to the copy
            Grid cloneLEFT = (Grid) currentState.deepClone(currentState);
            //if current block piece can be moved left then do it. Write down the action taken and add the resulted state to the set
            if (cloneLEFT.moveBlock(b.getKey(), Direction.LEFT, cloneLEFT)) {
                cloneLEFT.setParent(currentState);
                cloneLEFT.setActionTaken(new Action(b.getKey(), Direction.LEFT));
                nextStates.add(cloneLEFT);
            }
            //copy current state so we can make changes to the copy
            Grid cloneRIGHT = (Grid) currentState.deepClone(currentState);
            //if current block piece can be moved right then do it. Write down the action taken and add the resulted state to the set
            if (cloneRIGHT.moveBlock(b.getKey(), Direction.RIGHT, cloneRIGHT)) {
                cloneRIGHT.setParent(currentState);
                cloneRIGHT.setActionTaken(new Action(b.getKey(), Direction.RIGHT));
                nextStates.add(cloneRIGHT);
            }

        }
        return nextStates;

    }

    //method to check if we reached the goal
    public boolean isGoal(Grid g) {
        return g.getBlockByKey(this.target).getTail().getX() == 2 && g.getBlockByKey(this.target).getTail().getY() == 5;

    }

    
    
    private boolean isAlreadyExpanded(Grid current, ArrayList<Grid> states) {

        if (states.isEmpty()) {
            return false;
        }

        for (Grid g : states) {//wrong

            if (areSameState(current, g)) {
                return true;
            }

        }
        return false;
    }

    
    //comparing two states,grids
    public static boolean areSameState(Grid a, Grid b) {

        for (int i = 0; i < a.getBlockList().size(); i++) {

            if (!a.getBlockList().get(i).sameLocation(b.getBlockList().get(i))) {
                return false;
            }

        }
        return true;
    }

    
    public int generatedNodes() {
        return this.nodesGenerated;
    }

    public int expandedNodes() {
        return this.nodesExpanded;
    }
    
    
    //doeas a backtracking from goal node to initial node and displays all action sequence to be taken
    private void displayActions(Grid goal){
    LinkedList<String> s=new LinkedList<>();
        
        while(goal.getParent() !=null){
           s.push(this.getStringAction(goal.getActionTaken()));
            goal=goal.getParent();
    
    }
        
        while(!s.isEmpty()){
        
            System.out.println(s.pop());
        }
    
    }
    
    //Convert action to string
    private String getStringAction(Action a){
    String msg="Move block nr "+a.getBlockID();
   if(a.getDirection()==Direction.DOWN)
       msg+=" one unit down";
 else if(a.getDirection()==Direction.UP)
       msg+=" one unit up";
   else if(a.getDirection()==Direction.LEFT)
       msg+=" one unit left";
   else
       msg+=" one unit right";
   
    return msg;
    
    }
    
      public int getMaxNodesInMemory(){
    return this.nodesInMemory;
    }
    

}
