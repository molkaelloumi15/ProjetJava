package projetJavaEnsea;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

public class Enemy extends DynamicSprite implements Engine{
    private ArrayList<Sprite> environment; // Référence à l'environnement pour détecter les collisions
    private Random random; // Générateur pour choisir une direction aléatoire
    private long lastDirectionChangeTime = 0; // Temps de la dernière modification de direction
    private int directionChangeInterval = 2000; // Intervalle pour changer de direction (en ms)

    public Enemy(int x, int y, Image image, int width, int height, ArrayList<Sprite> environment) {
        super(x, y, image, width, height);
        this.environment = environment;
        this.random = new Random();
        setDirection(randomDirection()); // Choisir une direction initiale
    }

    @Override
    public void update() {
        long currentTime = System.currentTimeMillis();

        // Changer de direction à intervalle régulier
        if (currentTime - lastDirectionChangeTime >= directionChangeInterval) {
            setDirection(randomDirection());
            lastDirectionChangeTime = currentTime;
        }

        // Déplacement dans la direction choisie si possible
        moveIfPossible(environment);
    }

    // Génère une direction aléatoire
    private Direction randomDirection() {
        Direction[] directions = Direction.values();
        return directions[random.nextInt(directions.length)];
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g); // Dessine l'ennemi
        g.setColor(Color.RED); // Couleur visible pour l'ennemi
        g.fillRect((int) x, (int) y, width, height); // Rectangle pour représenter l'ennemi
    }
}
