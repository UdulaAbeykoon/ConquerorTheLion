package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Gamestate;
import main.Game;
import utilz.LoadSave;

public class LevelManager {

    // Reference to the Game instance
    private Game game;
    // Array of level sprites
    private BufferedImage[] levelSprite;
    // List of levels
    private ArrayList<Level> levels;
    // Index of the current level
    private int lvlIndex = 0;

    // Constructor to initialize the LevelManager with the Game instance
    public LevelManager(Game game) {
        this.game = game;
        importOutsideSprites(); // Import level sprites
        levels = new ArrayList<>();
        buildAllLevels(); // Build all levels
    }

    // Load the next level
    public void loadNextLevel() {
        lvlIndex++;
        if (lvlIndex >= levels.size()) {
            lvlIndex = 0;
            System.out.println("You Have Defeated The Game! The King is dead and you have regained your throne!");
            Gamestate.state = Gamestate.MENU;
        }

        Level newLevel = levels.get(lvlIndex);
        game.getPlaying().getEnemyManager().loadEnemies(newLevel);
        game.getPlaying().getPlayer().loadLvlData(newLevel.getLevelData());
        game.getPlaying().setMaxLvlOffset(newLevel.getLvlOffset());
        game.getPlaying().getObjectManager().loadObjects(newLevel);
    }

    // Build all levels by loading level images
    private void buildAllLevels() {
        BufferedImage[] allLevels = LoadSave.GetAllLevels();
        for (BufferedImage img : allLevels)
            levels.add(new Level(img));
    }

    // Import level sprites from the sprite atlas
    private void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        levelSprite = new BufferedImage[48];
        for (int j = 0; j < 4; j++)
            for (int i = 0; i < 12; i++) {
                int index = j * 12 + i;
                levelSprite[index] = img.getSubimage(i * 32, j * 32, 32, 32);
            }
    }

    // Draw the current level
    public void draw(Graphics g, int lvlOffset) {
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++)
            for (int i = 0; i < levels.get(lvlIndex).getLevelData()[0].length; i++) {
                int index = levels.get(lvlIndex).getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], Game.TILES_SIZE * i - lvlOffset, Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
    }

    // Update the current level (currently empty)
    public void update() {
        // Update logic for the current level can be added here
    }

    // Get the current level
    public Level getCurrentLevel() {
        return levels.get(lvlIndex);
    }

    // Get the total number of levels
    public int getAmountOfLevels() {
        return levels.size();
    }

}