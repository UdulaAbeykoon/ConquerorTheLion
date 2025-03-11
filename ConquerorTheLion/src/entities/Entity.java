package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import main.Game;

public abstract class Entity {

    // Position and dimensions of the entity
    protected float x, y;
    protected int width, height;
    // Hitbox for collision detection
    protected Rectangle2D.Float hitbox;
    // Animation tick and index
    protected int aniTick, aniIndex;
    // Current state of the entity
    protected int state;
    // Air speed for jumping/falling
    protected float airSpeed;
    // Flag to check if the entity is in the air
    protected boolean inAir = false;
    // Maximum and current health of the entity
    protected int maxHealth;
    protected int currentHealth;
    // Attack box for detecting attacks
    protected Rectangle2D.Float attackBox;
    // Walking speed of the entity
    protected float walkSpeed;

    // Constructor to initialize the entity
    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    // Draw the attack box for debugging purposes
    protected void drawAttackBox(Graphics g, int xLvlOffset) {
        g.setColor(Color.red);
        g.drawRect((int) (attackBox.x - xLvlOffset), (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
    }

    // Draw the hitbox for debugging purposes
    protected void drawHitbox(Graphics g, int xLvlOffset) {
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    // Initialize the hitbox for collision detection
    protected void initHitbox(int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, (int) (width * Game.SCALE), (int) (height * Game.SCALE));
    }

    // Get the hitbox of the entity
    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    // Get the current state of the entity
    public int getState() {
        return state;
    }

    // Get the current animation index
    public int getAniIndex() {
        return aniIndex;
    }

}