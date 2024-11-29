package projetJavaEnsea;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Graphics;

public class Sprite implements Displayable {
    protected double x, y;
    protected Image image;
    protected int width, height;

    public Sprite(int x, int y, Image image, int width, int height) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.width = width;
        this.height = height;
    }

    public Rectangle getHitBox() {
        return new Rectangle((int) x, (int) y, width, height);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, (int) x, (int) y, null);
    }
 
}