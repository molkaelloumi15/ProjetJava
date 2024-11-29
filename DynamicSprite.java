package projetJavaEnsea;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

public class DynamicSprite extends SolidSprite {
    private double speed = 2;
    private final int spriteSheetNumberOfColumn = 10;
    private int timeBetweenFrame = 200;
    private Direction direction;
    private boolean isMoving = false; 
    private long lastFrameTime; 
    private int currentFrameIndex = 0; 

    public DynamicSprite(int x, int y, Image image, int width, int height) {
        super(x, y, image, width, height);
        this.direction = Direction.SOUTH;  
        this.lastFrameTime = System.currentTimeMillis();
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
        this.isMoving = true; 
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }
   

    public void stopMoving() {
        this.isMoving = false; 
        this.currentFrameIndex = 0; 
    }

    public void moveIfPossible(ArrayList<Sprite> environment) {
        if (isMoving) {
            double newX = this.x;
            double newY = this.y;

            switch (direction) {
                case NORTH:
                    newY -= speed;
                    break;
                case SOUTH:
                    newY += speed;
                    break;
                case EAST:
                    newX += speed;
                    break;
                case WEST:
                    newX -= speed;
                    break;
            }

            if (isMovingPossible(newX, newY, environment)) {
                
            	this.x = newX;
                this.y = newY;
            }

            updateAnimation();
        } else {
            currentFrameIndex = 0;
        }
    }

    private void updateAnimation() {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFrameTime >= timeBetweenFrame) {
            currentFrameIndex = (currentFrameIndex + 1) % spriteSheetNumberOfColumn;
            lastFrameTime = currentTime;
        }
    }

    private boolean isMovingPossible(double newX, double newY, ArrayList<Sprite> environment) {
        Rectangle newHitBox = new Rectangle((int)newX, (int)newY, width, height);
        
        for (Sprite sprite : environment) {
            if (sprite instanceof SolidSprite) {
                SolidSprite solidSprite = (SolidSprite) sprite;
                if (newHitBox.intersects(solidSprite.getHitBox())) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void draw(Graphics g) {
        int frameY = direction.getFrameLineNumber() * height; 
        int frameX = currentFrameIndex * width; 

        g.drawImage(image, (int) x, (int) y, (int) (x + width), (int) (y + height),
                frameX, frameY, frameX + width, frameY + height, null);
    }

	

	
}