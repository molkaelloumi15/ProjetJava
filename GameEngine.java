package projetJavaEnsea;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameEngine implements Engine, KeyListener {
    private DynamicSprite hero;
    private boolean controlPressed = false;
    public GameEngine(DynamicSprite hero) {
        this.hero = hero;
    }

    @Override
    public void update() {
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("Key Pressed: " + KeyEvent.getKeyText(e.getKeyCode())); 
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                hero.setDirection(Direction.NORTH);
                break;
            case KeyEvent.VK_DOWN:
                hero.setDirection(Direction.SOUTH);
                break;
            case KeyEvent.VK_LEFT:
                hero.setDirection(Direction.WEST);
                break;
            case KeyEvent.VK_RIGHT:
                hero.setDirection(Direction.EAST);
                break;
            case KeyEvent.VK_CONTROL:
                controlPressed = true; 
                hero.setSpeed(15); 
                break;
        }
        }

    

  
    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_CONTROL:
                controlPressed = false; 
                hero.setSpeed(2); 
                break;
        }
        hero.stopMoving(); 
    }
}