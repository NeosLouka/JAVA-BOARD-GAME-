package View;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    // Constructor to load the background image
    public BackgroundPanel(String imagePath) {
        backgroundImage = new ImageIcon(imagePath).getImage();
        setLayout(new GridLayout(4, 9)); // Set layout for the board grid
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image scaled to fit the panel
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
