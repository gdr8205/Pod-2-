/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project6;


/**
 *
 * @author Garrett
 */
public class Pod {
    // Pod class is used to keep track each individual pod location on the screen...
    
    private int xPos            = 0;     // Pod's current X position
    private int yPos            = 0;     // Pod's current Y position
    
    private int width           = 0;     // Amount of usable space on x cord
    private int height          = 0;     // Amount of usable space on y cord
    
    private String dir          = "";    // holds the pods current direction
    
    public Pod(int initX, int initY, String direction, int w, int h) {
        // constructor
        // initiates each of the pods needed values.
        xPos   = initX;
        yPos   = initY;
        dir    = direction;
        height = h;
        width  = w;
    }
    
    public void move(){
        // This method is used to determine how the pod will move 
        // Basically breaks down the String dir by each letter which tells the pod how to handle positioning.
        // This also checks whether or not the pod has hit the wall... If it has then change the appropriate cardinal direction.
        
        // UP DOWN
        if(dir.contains("N")){
            if(yPos < height-1) {
                yPos++;
            }
            else
                dir = dir.replace("N","S");   // hit wall, so lets change direction as needed.
        }
        else if (dir.contains("S")) {
            if(yPos > 0) {
                yPos--;
            }
            else
                dir = dir.replace("S","N");   // hit wall, so lets change direction as needed.
        }
        
        // RIGHT LEFT
        if(dir.contains("E")){
            if(xPos < width-1) {
                xPos++;
            }
            else
                dir = dir.replace("E","W");   // hit wall, so lets change direction as needed.
        }
        else if (dir.contains("W")) {
            if(xPos > 0) {
                xPos--;
            }
            else 
                dir = dir.replace("W","E");   // hit wall, so lets change direction as needed.
        }
    }
    
    public boolean equals(Object obj) {
        // Test if obj is pod.
        // If pod, then cast to pod
        // Test (Pod) against this pods locations
        // If match, return true.
        if(this == obj) return true;
        if(obj instanceof Pod) {
            Pod p = (Pod)obj;
            
            if((xPos == p.xPos) && (yPos == p.yPos)) {
                return true;
            }
        }
        
        return false;
    }
    
}
