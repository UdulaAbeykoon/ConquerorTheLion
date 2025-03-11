package entities;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import levels.Level;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;

public class EnemyManager {

    // Reference to the Playing state
    private Playing playing;
    // 2D array to hold crocodile images
    private BufferedImage[][] CROCODILEArr;
    // List to hold crocodile enemies
    private ArrayList<CROCODILE> Crocs = new ArrayList<>();

    // Constructor to initialize the EnemyManager with the Playing state
    public EnemyManager(Playing playing) {
        this.playing = playing;
        loadEnemyImgs(); // Load enemy images
    }

    // Load enemies from the level
    public void loadEnemies(Level level) {
        Crocs = level.getCrocss();
    }

    // Update the state of all enemies
    public void update(int[][] lvlData, Player player) {
        boolean isAnyActive = false;
        for (CROCODILE c : Crocs)
            if (c.isActive()) {
                c.update(lvlData, player);
                isAnyActive = true;
            }
        if (!isAnyActive)
            playing.setLevelCompleted(true);
    }

    // Draw all enemies
    public void draw(Graphics g, int xLvlOffset) {
        drawCrocss(g, xLvlOffset);
    }

    // Draw crocodile enemies
    private void drawCrocss(Graphics g, int xLvlOffset) {
        for (CROCODILE c : Crocs)
            if (c.isActive()) {
                g.drawImage(CROCODILEArr[c.getState()][c.getAniIndex()], (int) c.getHitbox().x - xLvlOffset - CROCODILE_DRAWOFFSET_X + c.flipX(), (int) c.getHitbox().y - CROCODILE_DRAWOFFSET_Y,
                        CROCODILE_WIDTH * c.flipW(), CROCODILE_HEIGHT, null);
                // c.drawHitbox(g, xLvlOffset);
                // c.drawAttackBox(g, xLvlOffset);
            }
    }

    // Check if an enemy was hit
    public void checkEnemyHit(Rectangle2D.Float attackBox) {
        for (CROCODILE c : Crocs)
            if (c.isActive())
                if (attackBox.intersects(c.getHitbox())) {
                    c.hurt(10);
                    return;
                }
    }

    // Load enemy images
    private void loadEnemyImgs() {
        CROCODILEArr = new BufferedImage[5][9];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.CROCODILE_SPRITE);
        for (int j = 0; j < CROCODILEArr.length; j++)
            for (int i = 0; i < CROCODILEArr[j].length; i++)
                CROCODILEArr[j][i] = temp.getSubimage(i * CROCODILE_WIDTH_DEFAULT, j * CROCODILE_HEIGHT_DEFAULT, CROCODILE_WIDTH_DEFAULT, CROCODILE_HEIGHT_DEFAULT);
    }

    // Reset all enemies to their initial state
    public void resetAllEnemies() {
        for (CROCODILE c : Crocs)
            c.resetEnemy();
    }

}