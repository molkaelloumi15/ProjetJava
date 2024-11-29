package projetJavaEnsea;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends DynamicSprite implements Engine{
    private ArrayList<Sprite> environment; 
    private Random random; 
    private long lastDirectionChangeTime = 0; 
    private int directionChangeInterval = 2000; 

    public Enemy(int x, int y, Image image, int width, int height, ArrayList<Sprite> environment) {
        super(x, y, image, width, height);
        this.environment = environment;
        this.random = new Random();
        setDirection(randomDirection()); 
    }

    @Override
    public void update() {
        long currentTime = System.currentTimeMillis();

        if (currentTime - lastDirectionChangeTime >= directionChangeInterval) {
            setDirection(randomDirection());
            lastDirectionChangeTime = currentTime;
        }

        moveIfPossible(environment);
    }

    private Direction randomDirection() {
        Direction[] directions = Direction.values();
        return directions[random.nextInt(directions.length)];
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g); 
        g.setColor(Color.RED); 
        g.fillRect((int) x, (int) y, width, height); 
    }
}
