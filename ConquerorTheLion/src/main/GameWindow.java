package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;

import javax.swing.JFrame;

public class GameWindow {
    // JFrame to represent the game window
    private JFrame jframe;

    // Constructor to initialize the game window with the game panel
    public GameWindow(GamePanel gamePanel) {

        jframe = new JFrame(); // Create a new JFrame

        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Set default close operation
        jframe.add(gamePanel); // Add the game panel to the frame
        
        jframe.setResizable(false); // Make the window non-resizable
        jframe.pack(); // Pack the components within the window
        jframe.setLocationRelativeTo(null); // Center the window on the screen
        jframe.setVisible(true); // Make the window visible
        jframe.addWindowFocusListener(new WindowFocusListener() {

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamePanel.getGame().windowFocusLost(); // Handle window losing focus
            }

            @Override
            public void windowGainedFocus(WindowEvent e) {
                // Handle window gaining focus (no action needed)
            }
        });

    }

}