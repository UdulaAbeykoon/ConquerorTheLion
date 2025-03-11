package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import ui.MenuButton;
import utilz.LoadSave;

public class Menu extends State implements Statemethods {

    // Array of menu buttons
    private MenuButton[] buttons = new MenuButton[3];
    // Background images for the menu
    private BufferedImage backgroundImg, backgroundImgPink;
    // Position and size of the menu
    private int menuX, menuY, menuWidth, menuHeight;

    // Constructor to initialize the Menu state with the Game instance
    public Menu(Game game) {
        super(game);
        loadButtons(); // Load the menu buttons
        loadBackground(); // Load the background images
        backgroundImgPink = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG);
    }

    // Load the background images
    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);
        menuWidth = (int) (backgroundImg.getWidth() * Game.SCALE);
        menuHeight = (int) (backgroundImg.getHeight() * Game.SCALE);
        menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = (int) (45 * Game.SCALE);
    }

    // Load the menu buttons
    private void loadButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (150 * Game.SCALE), 0, Gamestate.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (220 * Game.SCALE), 1, Gamestate.OPTIONS);
        buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (290 * Game.SCALE), 2, Gamestate.QUIT);
    }

    @Override
    public void update() {
        for (MenuButton mb : buttons)
            mb.update(); // Update each menu button
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backgroundImgPink, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);

        for (MenuButton mb : buttons)
            mb.draw(g); // Draw each menu button
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Handle mouse clicked event (currently empty)
    }

    @Override
    public void mousePressed(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                mb.setMousePressed(true); // Set the button as pressed if the mouse is in the button
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                if (mb.isMousePressed())
                    mb.applyGamestate(); // Apply the game state if the button was pressed
                break;
            }
        }
        resetButtons(); // Reset the buttons after release
    }

    // Reset the state of all buttons
    private void resetButtons() {
        for (MenuButton mb : buttons)
            mb.resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for (MenuButton mb : buttons)
            mb.setMouseOver(false); // Set all buttons to not mouse over

        for (MenuButton mb : buttons)
            if (isIn(e, mb)) {
                mb.setMouseOver(true); // Set the button as mouse over if the mouse is in the button
                break;
            }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            Gamestate.state = Gamestate.PLAYING; // Start playing if the Enter key is pressed
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Handle key released event (currently empty)
    }
}