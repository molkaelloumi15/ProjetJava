package projetJavaEnsea;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class TrapManager {
    private ArrayList<Trap> traps;

    public TrapManager() {
        traps = new ArrayList<>();
    }

    public void addTrap(int x, int y, Image image, int width, int height) {
        traps.add(new Trap(x, y, image, width, height));
    }

    public ArrayList<Trap> getTraps() {
        return traps; 
    }

    public void drawTraps(Graphics g) {
        for (Trap trap : traps) {
            trap.draw(g); 
        }
    }
    public void removeTrap(Trap trap) {
        traps.remove(trap);
    }
}