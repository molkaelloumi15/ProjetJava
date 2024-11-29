package projetJavaEnsea;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Playground {
    private ArrayList<Sprite> environment = new ArrayList<>();
    ArrayList<Sprite> solidSpriteArrayList = new ArrayList<>();
    private TrapManager trapManager;
    public Playground(String pathName) {
        try {
            final Image imageTree = ImageIO.read(new File("src/projetJavaEnsea/img/tree.png"));
            final Image imageGrass = ImageIO.read(new File("src/projetJavaEnsea/img/grass.png"));
            final Image imageRock = ImageIO.read(new File("src/projetJavaEnsea/img/rock.png"));


            BufferedReader bufferedReader = new BufferedReader(new FileReader(pathName));
            String line;
            int lineNumber = 0;
            while ((line = bufferedReader.readLine()) != null) {
                for (int columnNumber = 0; columnNumber < line.length(); columnNumber++) {
                    char element = line.charAt(columnNumber);
                    switch (element) {
                        case 'T':
                            environment.add(new SolidSprite(columnNumber * imageTree.getWidth(null),
                                    lineNumber * imageTree.getHeight(null), imageTree,
                                    imageTree.getWidth(null), imageTree.getHeight(null)));
                            break;
                        case ' ':
                            environment.add(new Sprite(columnNumber * imageGrass.getWidth(null),
                                    lineNumber * imageGrass.getHeight(null), imageGrass,
                                    imageGrass.getWidth(null), imageGrass.getHeight(null)));
                            break;
                        case 'R':
                            environment.add(new SolidSprite(columnNumber * imageRock.getWidth(null),
                                    lineNumber * imageRock.getHeight(null), imageRock,
                                    imageRock.getWidth(null), imageRock.getHeight(null)));
                            break;
                        
                    }
                }
                lineNumber++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Sprite> getSolidSpriteList() {
        for (Sprite sprite : environment) {
            if (sprite instanceof SolidSprite) solidSpriteArrayList.add(sprite);
        }
        return solidSpriteArrayList;
    }

    public ArrayList<Displayable> getSpriteList() {
        ArrayList<Displayable> displayableArrayList = new ArrayList<>();
        for (Sprite sprite : environment) {
            displayableArrayList.add((Displayable) sprite);
        }
        return displayableArrayList;
    }
}