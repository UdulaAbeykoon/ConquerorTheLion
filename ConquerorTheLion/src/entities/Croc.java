package entities;

import static utilz.Constants.EnemyConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import static utilz.Constants.Directions.*;

import main.Game;

public class Crocs extends Enemy {

    // Offset for the attack box
    private int attackBoxOffsetX;

    // Constructor to initialize the Crocs enemy
    public Crocs(float x, float y) {
        super(x, y, Crocs_WIDTH, Crocs_HEIGHT, Crocs);
        initHitbox(22, 19); // Initialize the hitbox
        initAttackBox(); // Initialize the attack box
    }

    // Initialize the attack box
    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x, y, (int) (82 * Game.SCALE), (int) (19 * Game.SCALE));
        attackBoxOffsetX = (int) (Game.SCALE * 30);
    }

    // Update the state of the Crocs enemy
    public void update(int[][] lvlData, Player player) {
        updateBehavior(lvlData, player); // Update behavior based on level data and player
        updateAnimationTick(); // Update animation tick
        updateAttackBox(); // Update attack box position
    }

    // Update the attack box position
    private void updateAttackBox() {
        attackBox.x = hitbox.x - attackBoxOffsetX;
        attackBox.y = hitbox.y;
    }

    // Update the behavior of the Crocs enemy
    private void updateBehavior(int[][] lvlData, Player player) {
        if (firstUpdate)
            firstUpdateCheck(lvlData); // Check if it's the first update

        if (inAir)
            updateInAir(lvlData); // Update position if in air
        else {
            switch (state) {
            case IDLE:
                newState(RUNNING); // Change state to running if idle
                break;
            case RUNNING:
                if (canSeePlayer(lvlData, player)) {
                    turnTowardsPlayer(player); // Turn towards the player if visible
                    if (isPlayerCloseForAttack(player))
                        newState(ATTACK); // Change state to attack if player is close
                }

                move(lvlData); // Move the enemy
                break;
            case ATTACK:
                if (aniIndex == 0)
                    attackChecked = false;
                if (aniIndex == 3 && !attackChecked)
                    checkPlayerHit(attackBox, player); // Check if the player is hit
                break;
            case HIT:
                break;
            }
        }
    }

    // Flip the x-coordinate for rendering
    public int flipX() {
        if (walkDir == RIGHT)
            return width;
        else
            return 0;
    }

    // Flip the width for rendering
    public int flipW() {
        if (walkDir == RIGHT)
            return -1;
        else
            return 1;
    }
}