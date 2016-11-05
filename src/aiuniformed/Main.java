/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiuniformed;

/**
 *
 * @author arnold
 */
public class Main {
   
    public static void main(String[] args) {
      //Each block has its own unique key which indentifies him from others. 
        //I used numbers to indetify blocks for example three 1's in a row means a block 1x3 [][][]
      
        //Three 1's in a colume means a block 3x1 []
        //                                        []
        //                                        []
        
        
        
        
    //build and grid and its initial state
     Grid initial=new Grid(6,6);
     
     
     //initial placement of blocks 
        Block b1=new Block(new Point(0,0),new Point(0,2));
        Block b2=new Block(new Point(2,0),new Point(2,1));
        
        Block b3=new Block(new Point(3,0),new Point(4,0));
        Block b4=new Block(new Point(5,0),new Point(5,2));
        Block b5=new Block(new Point(1,2),new Point(3,2));
        Block b6=new Block(new Point(0,5),new Point(2,5));
        Block b7=new Block(new Point(3,4),new Point(3,5));
        Block b8=new Block(new Point(4,4),new Point(5,4));
     
        
        
        
//add all created block to grid
            initial.addBlock(b1);
            initial.addBlock(b2);
            initial.addBlock(b3);
            initial.addBlock(b4);
            initial.addBlock(b5);
            initial.addBlock(b6);
            initial.addBlock(b7);
            initial.addBlock(b8);
            
            
            //display actual grid
            initial.display();

       
       
        //Perform bfs search
     
        System.out.println("Performing BFS....");
        long startTime = System.currentTimeMillis();
       

        //To start seach we need the current grid state and the target blocks key 
        SearchBFS BFS=new SearchBFS(initial,b2.getKey());
        BFS.doSearch();
        
        
        //Information about the running time of BFS search
        System.out.println("Number of nodes generated: "+BFS.generatedNodes());
        System.out.println("Number of nodes expanded: "+BFS.expandedNodes());
      System.out.println("Max nodes in memory: "+BFS.getMaxNodesInMemory());
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Running time in milliseconds: "+totalTime);
     
        
        
        
     //Perform dfs search
//        System.out.println("Performing DFS....");
//        startTime=System.currentTimeMillis();
//        SearchDFS DFS=new SearchDFS(initial);
//        DFS.doSearch();
//        System.out.println("Number of nodes generated: "+DFS.generatedNodes());
//        System.out.println("Number of nodes expanded: "+DFS.expandedNodes());
//        System.out.println("Max nodes in memory: "+DFS.getMaxNodesInMemory());
//         endTime   = System.currentTimeMillis();
//         totalTime = endTime - startTime;
//        System.out.println("Running time in milliseconds: "+totalTime);
        
    }
    
}
