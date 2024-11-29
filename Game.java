package projetJavaEnsea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Game extends JPanel implements ActionListener {
    private DynamicSprite hero;
    private PhysicEngine physicEngine;
    private GameEngine gameEngine;
    private Playground playground;
    private Timer timer;
    private JFrame frame;
    private TitleScreen titleScreen;
    private int timeLimit = 60; 
    private int elapsedTime = 0;
    private int frameCount = 0; 
    private long lastFpsTime = System.currentTimeMillis(); 
    private int fps = 0;
    private ArrayList<Enemy> enemies; 
    private int hearts = 3; 
    private Image heartImage; 
    private TrapManager trapManager;

    public Game() {
        frame = new JFrame("Jeu");
        titleScreen = new TitleScreen(e -> startGame()); 

        heartImage = new ImageIcon("src/projetJavaEnsea/img/heart.png").getImage();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.add(titleScreen); // Ajouter l'écran d'accueil
        frame.setVisible(true); 
        trapManager = new TrapManager();
        trapManager.addTrap(300, 300, new ImageIcon("src/projetJavaEnsea/img/trap.png").getImage(), 46, 46);
        trapManager.addTrap(400, 250, new ImageIcon("src/projetJavaEnsea/img/trap.png").getImage(), 46, 46);
        
        
    }

    private void startGame() {
        playground = new Playground("src/projetJavaEnsea/Data/level1.txt");
        hero = new DynamicSprite(250, 250, new ImageIcon("src/projetJavaEnsea/img/heroTileSheetLowRes.png").getImage(), 49, 49);

        physicEngine = new PhysicEngine();
        physicEngine.addToMovingSpriteList(hero);
        physicEngine.setEnvironment(playground.getSolidSpriteList());

        gameEngine = new GameEngine(hero);
        
        enemies = new ArrayList<>();
        createEnemies();

        timer = new Timer(16, this);
        timer.start();

        frame.getContentPane().removeAll(); 
        frame.add(this); 
        frame.revalidate();
        frame.repaint();
        this.addKeyListener(gameEngine); 
        this.requestFocusInWindow();
    }

    private void createEnemies() {
        enemies.add(new Enemy(100, 300, new ImageIcon("src/projetJavaEnsea/img/enemy.png").getImage(), 46, 46, playground.getSolidSpriteList()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        elapsedTime += 16; 
        frameCount++; 

        if (elapsedTime >= timeLimit * 1000) { 
            timer.stop(); 
            showGameOverScreen(); 
        } else {
            gameEngine.update(); 
            physicEngine.update(); 
            updateEnemies(); 
            updateTraps(); 

            repaint(); 
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastFpsTime >= 1000) {
            fps = frameCount; 
            frameCount = 0; 
            lastFpsTime = currentTime; 
        }
    }

    private void updateEnemies() {
        for (Enemy enemy : enemies) {
            enemy.update(); 

            if (hero.getHitBox().intersects(enemy.getHitBox())) {
                showGameOverScreen(); 

            }
        }
    }

  

    private void showGameOverScreen() {
        frame.getContentPane().removeAll(); 
        GameOverScreen gameOverScreen = new GameOverScreen(e -> restartGame()); 
        frame.add(gameOverScreen); 
        frame.revalidate();
        frame.repaint();
    }

    private void restartGame() {
        elapsedTime = 0; 
        hearts = 3; 
        startGame(); 
        trapManager.addTrap(300, 300, new ImageIcon("src/projetJavaEnsea/img/trap.png").getImage(), 46, 46);
        trapManager.addTrap(400, 250, new ImageIcon("src/projetJavaEnsea/img/trap.png").getImage(), 46, 46);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        for (Displayable sprite : playground.getSpriteList()) {
            sprite.draw(g);
        }
        
        hero.draw(g);
        trapManager.drawTraps(g); 


        for (Enemy enemy : enemies) {
            enemy.draw(g); 
        }
        
        int heartSpacing = 10;
        int heartWidth = heartImage.getWidth(null); 
        int heartHeight = heartImage.getHeight(null); 
        int startX = frame.getWidth() - (hearts * (heartWidth + heartSpacing)) - heartSpacing; 

        for (int i = 0; i < hearts; i++) {
            g.drawImage(heartImage, startX + i * (heartWidth + heartSpacing), 10, null); 
        }

        int remainingTime = timeLimit - (elapsedTime / 1000); 
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString("Temps restant: " + remainingTime + "s", 10, 30);

        g.setColor(Color.WHITE);
        g.drawString("FPS: " + fps, 10, 60); 
    }
    private void updateTraps() {
        for (Trap trap : trapManager.getTraps()) {
            if (hero.getHitBox().intersects(trap.getHitBox())) {
                decreaseHeart(); 
                trapManager.removeTrap(trap); 
                break; 
            }
        }
    }

    private void decreaseHeart() {
        hearts--;
        if (hearts <= 0) {
            timer.stop(); 
            showGameOverScreen(); 
        }
    }

 

    public static void main(String[] args) {
        new Game();
    }
}