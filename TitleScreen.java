package projetJavaEnsea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class TitleScreen extends JPanel {
    private Image backgroundImage; 

    public TitleScreen(ActionListener startGameAction) {
        backgroundImage = new ImageIcon("src/projetJavaEnsea/img/maze1.jpg").getImage(); 

        setLayout(new BorderLayout());
        JButton startButton = new JButton("Start");
        startButton.addActionListener(startGameAction);
        startButton.setFont(new Font("Arial", Font.BOLD, 24)); 
        startButton.setPreferredSize(new Dimension(200, 60)); 

        add(startButton, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this); 
        }
    }
}