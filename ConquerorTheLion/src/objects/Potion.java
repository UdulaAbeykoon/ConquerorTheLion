package objects;

import main.Game;

public class Potion extends GameObject {

    // Variables for hover animation
    private float hoverOffset;
    private int maxHoverOffset, hoverDir = 1;

    // Constructor to initialize the Potion object
    public Potion(int x, int y, int objType) {
        super(x, y, objType); // Call the superclass constructor
        doAnimation = true; // Enable animation for the potion

        initHitbox(7, 14); // Initialize the hitbox with width 7 and height 14

        xDrawOffset = (int) (3 * Game.SCALE); // Set the x-axis draw offset based on the game scale
        yDrawOffset = (int) (2 * Game.SCALE); // Set the y-axis draw offset based on the game scale

        maxHoverOffset = (int) (10 * Game.SCALE); // Set the maximum hover offset based on the game scale
    }

    // Update the potion state
    public void update() {
        updateAnimationTick(); // Update the animation tick
        updateHover(); // Update the hover animation
    }

    // Update the hover animation
    private void updateHover() {
        hoverOffset += (0.075f * Game.SCALE * hoverDir); // Update the hover offset

        // Reverse the hover direction if the offset exceeds the maximum or minimum
        if (hoverOffset >= maxHoverOffset)
            hoverDir = -1;
        else if (hoverOffset < 0)
            hoverDir = 1;

        hitbox.y = y + hoverOffset; // Adjust the hitbox y position by the hover offset
    }
}