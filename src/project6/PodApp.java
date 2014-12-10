/*
 * This class represents a visual application for the "pod chase" game.
 */
package project6;

/**
 *
 * @author jrsullins
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PodApp extends JFrame implements ActionListener, KeyListener {

    // Member variables for visual objects.
    private JLabel[][] board; // 2D array of labels. Displays either # for player,
                              // * for pod, or empty space
    private JButton northButton, // player presses to move up
                    southButton, // player presses to move down
                    eastButton,  // player presses to move right
                    westButton;  // player presses to move left
    
    // Current width and height of board (will make static later).
    private int width = 15;
    private int height = 9;
    
    // Current location of player
    private int playerX = 7;
    private int playerY = 4;
    
    // PodList object
    private PodList pods;
    
    public PodApp() {
        
        // Construct a panel to put the board on and another for the buttons
        JPanel boardPanel = new JPanel(new GridLayout(height, width));
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        
        // Use a loop to construct the array of labels, adding each to the
        // board panel as it is constructed. Note that we create this in
        // "row major" fashion by making the y-coordinate the major 
        // coordinate. We also make sure that increasing y means going "up"
        // by building the rows in revers order.
        board = new JLabel[height][width];
        for (int y = height-1; y >= 0; y--) {
            for (int x = 0; x < width; x++) {
                
                // Construct a label to represent the tile at (x, y)
                board[y][x] = new JLabel(" ", JLabel.CENTER);
                
                // Add it to the 2D array of labels representing the visible board
                boardPanel.add(board[y][x]);
            }
        }
        
        // Construct the buttons, register to listen for their events,
        // and add them to the button panel
        northButton = new JButton("N");
        southButton = new JButton("S");
        eastButton = new JButton("E");
        westButton = new JButton("W");
        
        // Listen for events on each button
        northButton.addActionListener(this);
        southButton.addActionListener(this);
        eastButton.addActionListener(this);
        westButton.addActionListener(this);
        
        
        
        // Add each to the panel of buttons
        buttonPanel.add(northButton); 
        buttonPanel.add(southButton); 
        buttonPanel.add(eastButton); 
        buttonPanel.add(westButton);
        
        // Add everything to a main panel attached to the content pane
        JPanel mainPanel = new JPanel(new BorderLayout());
        getContentPane().add(mainPanel);
        mainPanel.add(boardPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // allow arrow-key usage
        setFocusable(true);
        addKeyListener(this);
        
        // Size the app and make it visible
        setSize(300, 200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Construct the PodList with 4 pods initially
        pods = new PodList(width, height);
        
        // Draw the initial board        
        drawBoard();
    }
        
    
    // Auxiliary method to display player and pods in labels.
    public void drawBoard() {
        
        // Determine what to display in each square
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Is the player at this (x, y) location?
                if (playerX == x && playerY == y) {
                    board[y][x].setText("#");
                }
                // Is there a pod at this (x, y) location?
                else if (pods.isPod(x, y)) {
                    board[y][x].setText("*");
                }
                // Otherwise, draw a space there.
                else {
                    board[y][x].setText(" ");
                }
            }
        }
    }    
    
    public void actionPerformed(ActionEvent e) {
        
        // Move the pods
        pods.moveAll();
        
        // Determine which button was pressed, and move player in that
        // direction (making sure they don't leave the board).
        if (e.getSource() == southButton && playerY > 0) {
            playerY--;
        }
        if (e.getSource() == northButton && playerY < height-1) {
            playerY++;
        }
        if (e.getSource() == eastButton && playerX < width-1) {
            playerX++;
        }
        if (e.getSource() == westButton && playerX > 0) {
            playerX--;
        }
        
        // Notify the pod list about player location
        pods.playerAt(playerX, playerY);           
        
        // Possibly generate another pod
        pods.generate();
        
        // Redraw the board
        drawBoard();
        
        // Allow user to still use arrow keys AFTER they press n,s,e or w jbuttons
        requestFocus();
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        PodApp a = new PodApp();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // Arrow key detection
    @Override
    public void keyReleased(KeyEvent e) {
        int keycode = e.getKeyCode();
        
        pods.moveAll();
        
        switch(e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if(playerY < height-1) {
                    playerY++;
                }
                break;
            case KeyEvent.VK_DOWN:
                if(playerY > 0) {
                    playerY--;
                }
                break;
            case KeyEvent.VK_LEFT:
                if(playerX > 0) {
                    playerX--;
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(playerX < width-1) {
                    playerX++;
                }
                break;
        }
        
        // Notify the pod list about player location
        pods.playerAt(playerX, playerY);           
        
        // Possibly generate another pod
        pods.generate();
        
        // Redraw the board
        drawBoard();
    }
}