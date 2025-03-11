package objects;

import main.Game;

public class Spike extends GameObject {

    // Constructor to initialize the Spike object
    public Spike(int x, int y, int objType) {
        super(x, y, objType); // Call the superclass constructor

        initHitbox(32, 16); // Initialize the hitbox with width 32 and height 16
        xDrawOffset = 0; // Set the x-axis draw offset to 0
        yDrawOffset = (int) (Game.SCALE * 16); // Set the y-axis draw offset based on the game scale
        hitbox.y += yDrawOffset; // Adjust the hitbox y position by the y-axis draw offset
    }
}