package projetJavaEnsea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameOverScreen extends JPanel {
    private Image backgroundImage; 

    public GameOverScreen(ActionListener restartAction) {
        backgroundImage = new ImageIcon("src/projetJavaEnsea/img/maze.jpg" ).getImage(); 

        setLayout(new BorderLayout());
        
        JButton restartButton = new JButton("Restart");
        restartButton.addActionListener(restartAction);
        restartButton.setFont(new Font("Arial", Font.BOLD, 24)); 
        restartButton.setPreferredSize(new Dimension(200, 60)); 

        add(restartButton, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); 
        }
    }
}