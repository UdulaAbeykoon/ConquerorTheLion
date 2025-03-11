package main;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import inputs.KeyboardInputs;
import inputs.MouseInputs;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

public class GamePanel extends JPanel {

    // Input handlers for mouse and keyboard
    private MouseInputs mouseInputs;
    private Game game;

    // Constructor to initialize the game panel with the game instance
    public GamePanel(Game game) {
        mouseInputs = new MouseInputs(this);
        this.game = game;
        setPanelSize(); // Set the size of the panel
        addKeyListener(new KeyboardInputs(this)); // Add keyboard input listener
        addMouseListener(mouseInputs); // Add mouse input listener
        addMouseMotionListener(mouseInputs); // Add mouse motion listener
    }

    // Set the size of the panel
    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
    }

    // Update the game state
    public void updateGame() {
        // Update game logic here
    }

    // Paint the game components
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g); // Render the game
    }

    // Get the game instance
    public Game getGame() {
        return game;
    }

}