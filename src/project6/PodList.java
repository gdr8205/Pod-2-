/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project6;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author garrettrichards
 */

// Pod List is used as a container for Pod Objects
// Controls when to move all pods, player/pod detection
// Also determines when to generate new pods
public class PodList {
    private ArrayList<Pod> pods;
    
    int w, h;
    
    public PodList(int width, int height) {
        pods = new ArrayList<Pod>();                                            // hold all the pods
        
        w = width;                                                              // width of board
        h = height;                                                             // height of board
        
        // Add the pods to the ArrayList
        pods.add(new Pod(1, 5, "NE", width, height));
        pods.add(new Pod(2, 1, "SW", width, height));
        pods.add(new Pod(12, 2, "NW", width, height));
        pods.add(new Pod(13, 6, "SE", width, height));
    }
    
    public boolean isPod(int x, int y) {
        // Checks all pod locations to determine where to show pods
        // Does so by comparing a new pod against known pod locations
        
        Pod test = new Pod(x ,y, "NE", w, h);                                   // Test pod
        
        return pods.contains(test);
    }
    
    public void moveAll() {
        // Will go through all known pods and move them at the same time
        
        for(int x = 0; x < pods.size(); x++) {
            pods.get(x).move();
        }
    }
    
    public void playerAt(int x, int y) {
        // Test with new pod to determine if player is at that location,
        // if so, remove pod
        Pod test = new Pod(x ,y, "NE", w, h);                                   //  Test pod
        
        if(pods.contains(test)) {
            pods.remove(test);
        }
    }
    
    public void generate() {
        // Determines when to generate a new random pod to the list/board
        
        Random randomGen = new Random();
        
        // 10% chance
        int randomChance = randomGen.nextInt(10);

        if(randomChance == 0) {
            int randomX      = randomGen.nextInt(w);                            // x constraint
            int randomY      = randomGen.nextInt(h);                            // y constraint
                           
            String[] dir = {"NE", "SW", "NW", "SE"};                            // Possible directions to choose from
            int randomDir    = randomGen.nextInt(dir.length);                   // Choose random direction
            
            // Make new known pod on board
            pods.add(new Pod(randomX, randomY, dir[randomDir], w, h));          
        }
    }
}
