package ui;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import utilz.LoadSave;
import static utilz.Constants.UI.Buttons.*;

public class MenuButton {
    // Position and state variables
    private int xPos, yPos, rowIndex, index;
    private int xOffsetCenter = B_WIDTH / 2;
    private Gamestate state;
    private BufferedImage[] imgs;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;

    // Constructor to initialize the MenuButton
    public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImgs();
        initBounds();
    }

    // Initialize the bounds of the button
    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT);
    }

    // Load the button images
    private void loadImgs() {
        imgs = new BufferedImage[3];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS);
        for (int i = 0; i < imgs.length; i++)
            imgs[i] = temp.getSubimage(i * B_WIDTH_DEFAULT, rowIndex * B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
    }

    // Draw the button
    public void draw(Graphics g) {
        g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, B_WIDTH, B_HEIGHT, null);
    }

    // Update the button state
    public void update() {
        index = 0;
        if (mouseOver)
            index = 1;
        if (mousePressed)
            index = 2;
    }

    // Check if the mouse is over the button
    public boolean isMouseOver() {
        return mouseOver;
    }

    // Set the mouse over state
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    // Check if the mouse is pressed on the button
    public boolean isMousePressed() {
        return mousePressed;
    }

    // Set the mouse pressed state
    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    // Get the bounds of the button
    public Rectangle getBounds() {
        return bounds;
    }
 
    // Apply the game state associated with the button
    public void applyGamestate() {
        Gamestate.state = state;
    }

    // Reset the mouse over and mouse pressed states
    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }
}