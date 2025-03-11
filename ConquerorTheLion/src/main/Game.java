package main;

import java.awt.Graphics;
import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;
import utilz.LoadSave;

/**
 * The main Game class that initializes and runs the game.
 */
public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    private Playing playing;
    private Menu menu;

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 2f;
    public final static int TILES_IN_WIDTH = 26;
    public final static int TILES_IN_HEIGHT = 14;
    public final static int TILES_SIZE = (int) (TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    /**
     * Constructor to initialize the game.
     */
    public Game() {
        initClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel);
        gamePanel.setFocusable(true);
        gamePanel.requestFocus();

        startGameLoop();
    }

    /**
     * Initialize the game states.
     */
    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);
    }

    /**
     * Start the game loop in a new thread.
     */
    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Update the game state based on the current game state.
     */
    public void update() {
        switch (Gamestate.state) {
        case MENU:
            menu.update();
            break;
        case PLAYING:
            playing.update();
            break;
        case OPTIONS:
        case QUIT:
        default:
            System.exit(0);
            break;
        }
    }

    /**
     * Render the game state based on the current game state.
     * 
     * @param g the Graphics object to draw on
     */
    public void render(Graphics g) {
        switch (Gamestate.state) {
        case MENU:
            menu.draw(g);
            break;
        case PLAYING:
            playing.draw(g);
            break;
        default:
            break;
        }
    }

    /**
     * The main game loop that updates and renders the game.
     */
    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0;
        double deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                // Reset frame and update counters
                frames = 0;
                updates = 0;
            }
        }
    }

    /**
     * Handle the event when the game window loses focus.
     */
    public void windowFocusLost() {
        if (Gamestate.state == Gamestate.PLAYING)
            playing.getPlayer().resetDirBooleans();
    }

    /**
     * Get the Menu instance.
     * 
     * @return the Menu instance
     */
    public Menu getMenu() {
        return menu;
    }

    /**
     * Get the Playing instance.
     * 
     * @return the Playing instance
     */
    public Playing getPlaying() {
        return playing;
    }
}