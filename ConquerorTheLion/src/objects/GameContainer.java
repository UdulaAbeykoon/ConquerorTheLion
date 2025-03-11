package objects;

import static utilz.Constants.ObjectConstants.*;

import main.Game;

public class GameContainer extends GameObject {

    // Constructor to initialize the GameContainer object
    public GameContainer(int x, int y, int objType) {
        super(x, y, objType); // Call the superclass constructor
        createHitbox(); // Create the hitbox for the container
    }

    // Create the hitbox for the container
    private void createHitbox() {
        if (objType == BOX) {
            initHitbox(25, 18); // Initialize the hitbox with width 25 and height 18

            xDrawOffset = (int) (7 * Game.SCALE); // Set the x-axis draw offset based on the game scale
            yDrawOffset = (int) (12 * Game.SCALE); // Set the y-axis draw offset based on the game scale

        } else {
            initHitbox(23, 25); // Initialize the hitbox with width 23 and height 25
            xDrawOffset = (int) (8 * Game.SCALE); // Set the x-axis draw offset based on the game scale
            yDrawOffset = (int) (5 * Game.SCALE); // Set the y-axis draw offset based on the game scale
        }

        hitbox.y += yDrawOffset + (int) (Game.SCALE * 2); // Adjust the hitbox y position by the y-axis draw offset
        hitbox.x += xDrawOffset / 2; // Adjust the hitbox x position by half the x-axis draw offset
    }

    // Update the state of the container
    public void update() {
        if (doAnimation)
            updateAnimationTick(); // Update the animation tick if animation is enabled
    }
}