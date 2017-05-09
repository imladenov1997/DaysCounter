import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/*
 Created by Ivo Mladenov
 You have just broken Copyright Laws :) Decompilation of this file is infringing Copyright :)
*/

public class BackgroundPanel extends JPanel {
    private BufferedImage background;

    public BackgroundPanel(BufferedImage background) {
        super();
        this.background = background;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        if (background != null) {
            g2d.drawImage(background, 0, 0, width, height, null);
        }
    }
}
