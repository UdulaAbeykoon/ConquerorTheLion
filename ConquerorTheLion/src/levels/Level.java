package levels;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import entities.CROCODILE;
import main.Game;
import objects.GameContainer;
import objects.Potion;
import objects.Spike;
import utilz.HelpMethods;

import static utilz.HelpMethods.GetLevelData;
import static utilz.HelpMethods.GetCrabs;
import static utilz.HelpMethods.GetPlayerSpawn;

public class Level {

    // Image representing the level
    private BufferedImage img;
    // 2D array representing the level data
    private int[][] lvlData;
    // Lists to hold various game objects
    private ArrayList<CROCODILE> crocs;
    private ArrayList<Potion> potions;
    private ArrayList<Spike> spikes;
    private ArrayList<GameContainer> containers;
    // Level dimensions and offsets
    private int lvlTilesWide;
    private int maxTilesOffset;
    private int maxLvlOffsetX;
    // Player spawn point
    private Point playerSpawn;

    // Constructor to initialize the level with an image
    public Level(BufferedImage img) {
        this.img = img;
        createLevelData(); // Create level data
        createEnemies(); // Create enemies
        createPotions(); // Create potions
        createContainers(); // Create containers
        createSpikes(); // Create spikes
        calcLvlOffsets(); // Calculate level offsets
        calcPlayerSpawn(); // Calculate player spawn point
    }

    // Create spikes from the level image
    private void createSpikes() {
        spikes = HelpMethods.GetSpikes(img);
    }

    // Create containers from the level image
    private void createContainers() {
        containers = HelpMethods.GetContainers(img);
    }

    // Create potions from the level image
    private void createPotions() {
        potions = HelpMethods.GetPotions(img);
    }

    // Calculate the player spawn point from the level image
    private void calcPlayerSpawn() {
        playerSpawn = GetPlayerSpawn(img);
    }

    // Calculate level offsets based on the image width
    private void calcLvlOffsets() {
        lvlTilesWide = img.getWidth();
        maxTilesOffset = lvlTilesWide - Game.TILES_IN_WIDTH;
        maxLvlOffsetX = Game.TILES_SIZE * maxTilesOffset;
    }

    // Create enemies from the level image
    private void createEnemies() {
        crocs = GetCrabs(img);
    }

    // Create level data from the level image
    private void createLevelData() {
        lvlData = GetLevelData(img);
    }

    // Get the sprite index at a specific position
    public int getSpriteIndex(int x, int y) {
        return lvlData[y][x];
    }

    // Get the level data
    public int[][] getLevelData() {
        return lvlData;
    }

    // Get the maximum level offset
    public int getLvlOffset() {
        return maxLvlOffsetX;
    }

    // Get the list of crocodiles
    public ArrayList<CROCODILE> getCrocs() {
        return crocs;
    }

    // Get the player spawn point
    public Point getPlayerSpawn() {
        return playerSpawn;
    }

    // Get the list of potions
    public ArrayList<Potion> getPotions() {
        return potions;
    }

    // Get the list of containers
    public ArrayList<GameContainer> getContainers() {
        return containers;
    }

    // Get the list of spikes
    public ArrayList<Spike> getSpikes() {
        return spikes;
    }

}