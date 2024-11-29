package projetJavaEnsea;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class Trap extends Sprite {
    private Image image;

    public Trap(int x, int y, Image image, int width, int height) {
        super(x, y, image, width, height);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, (int) x, (int) y, width, height, null); 
    }
    public Rectangle getHitBox() {
        return new Rectangle((int)x, (int)y, width, height);
    }
    
}