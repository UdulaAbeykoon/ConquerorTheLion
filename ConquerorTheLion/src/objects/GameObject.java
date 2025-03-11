package objects;

import static utilz.Constants.ANI_SPEED;
import static utilz.Constants.ObjectConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import main.Game;

public class GameObject {

    // Position and type of the game object
    protected int x, y, objType;
    // Hitbox for collision detection
    protected Rectangle2D.Float hitbox;
    // Animation and active state flags
    protected boolean doAnimation, active = true;
    // Animation tick and index
    protected int aniTick, aniIndex;
    // Draw offsets for rendering
    protected int xDrawOffset, yDrawOffset;

    // Constructor to initialize the game object
    public GameObject(int x, int y, int objType) {
        this.x = x;
        this.y = y;
        this.objType = objType;
    }

    // Update the animation tick
    protected void updateAnimationTick() {
        aniTick++;
        if (aniTick >= ANI_SPEED) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(objType)) {
                aniIndex = 0;
                if (objType == BARREL || objType == BOX) {
                    doAnimation = false;
                    active = false;
                }
            }
        }
    }

    // Reset the game object to its initial state
    public void reset() {
        aniIndex = 0;
        aniTick = 0;
        active = true;

        if (objType == BARREL || objType == BOX)
            doAnimation = false;
        else
            doAnimation = true;
    }

    // Initialize the hitbox for collision detection
    protected void initHitbox(int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, (int) (width * Game.SCALE), (int) (height * Game.SCALE));
    }

    // Draw the hitbox for debugging purposes
    public void drawHitbox(Graphics g, int xLvlOffset) {
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    // Get the type of the game object
    public int getObjType() {
        return objType;
    }

    // Get the hitbox of the game object
    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    // Check if the game object is active
    public boolean isActive() {
        return active;
    }

    // Set the active state of the game object
    public void setActive(boolean active) {
        this.active = active;
    }

    // Set the animation state of the game object
    public void setAnimation(boolean doAnimation) {
        this.doAnimation = doAnimation;
    }

    // Get the x-axis draw offset
    public int getxDrawOffset() {
        return xDrawOffset;
    }

    // Get the y-axis draw offset
    public int getyDrawOffset() {
        return yDrawOffset;
    }

    // Get the current animation index
    public int getAniIndex() {
        return aniIndex;
    }
}