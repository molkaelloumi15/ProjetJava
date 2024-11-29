package projetJavaEnsea;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameRender extends JPanel {
    private ArrayList<Displayable> renderList;

    public GameRender() {
        renderList = new ArrayList<>();
    }

    public ArrayList<Displayable> getRenderList() {
        return renderList;
    }

    public void addToRenderList(Displayable displayable) {
        renderList.add(displayable);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Displayable item : renderList) {
            item.draw(g);
        }
    }

    public void update() {
        repaint();
    }
}