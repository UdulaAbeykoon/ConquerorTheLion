package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.Gamestate;
import main.GamePanel;

public class KeyboardInputs implements KeyListener {

    // Reference to the GamePanel
    private GamePanel gamePanel;

    // Constructor to initialize KeyboardInputs with the GamePanel
    public KeyboardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Handle key typed event (currently empty)
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (Gamestate.state) {
        case MENU:
            gamePanel.getGame().getMenu().keyReleased(e);
            break;
        case PLAYING:
            gamePanel.getGame().getPlaying().keyReleased(e);
            break;
        default:
            break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (Gamestate.state) {
        case MENU:
            gamePanel.getGame().getMenu().keyPressed(e);
            break;
        case PLAYING:
            gamePanel.getGame().getPlaying().keyPressed(e);
            break;
        default:
            break;
        }
    }
}