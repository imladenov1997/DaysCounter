import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

/*
 Created by Ivo Mladenov
 You have just broken Copyright Laws :) Decompilation of this file is infringing Copyright :)
*/
public class MainFrame extends JFrame {
    private BackgroundPanel panelContainer;
    private JPanel container;
    private DayPanel upperPanel;
    private DayPanel lowerPanel;
    private ResetButton reset;
    private JPanel buttonPanel;
    private JTextField field;
    private JButton setDaysButton;
    private BufferedImage background;
    private Timer timer;
    private ImageIcon icon;

    public MainFrame() {
        super("Day Counter");
        this.init();
    }

    private void init() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.createPanelContainer();
        this.createContainer();
        this.addDayPanels();
        this.createButton();
        this.createTextField();
        this.createSetDaysButton();
        this.addButtonsToContainer();
        this.createBackground();
        // this.createTimer();
        this.createImageIcon("magna");

        this.addWindowListener(new WindowCloser(upperPanel, lowerPanel));
        this.setVisible(true);
    }

    private void createPanelContainer() {
        panelContainer = new BackgroundPanel(createBackground());
        panelContainer.setLayout(new GridLayout(2, 1));
        panelContainer.setBackground(Color.GRAY);
    }

    private void createContainer() {
        this.container = new JPanel();
        container.setLayout(new BorderLayout());
        container.setOpaque(false);

        if (panelContainer != null) {
            container.add(panelContainer);
        }

        this.setContentPane(container);
    }

    private void addDayPanels() {

        try {
            this.checkExistingObjects();
        } catch (IOException e) {
            upperPanel = new DayPanel("DAYS WITHOUT AN ACCIDENT");
            lowerPanel = new DayPanel("ДНИ БЕЗ ИНЦИДЕНТ");
            if (panelContainer != null) {
                panelContainer.add(upperPanel);
                panelContainer.add(lowerPanel);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void createButton() {
        this.reset = new ResetButton();
        this.buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(Color.BLACK);
    }

    private void createTextField() {
        this.field = new JTextField("0", 15);
        this.field.setPreferredSize(new Dimension(50, 10));
    }

    private void createSetDaysButton() {
        this.setDaysButton = new JButton("Set specified days");
        setDaysButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int days = Integer.parseInt(field.getText());
                    upperPanel.setDays(days);
                    lowerPanel.setDays(days);
                } catch (Exception e1) {
                    field.setText("Please enter a number!");
                }
            }
        });
    }

    private void addButtonsToContainer() {
        if (container != null) {
            int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/8;
            buttonPanel.add(Box.createHorizontalGlue());
            buttonPanel.add(Box.createRigidArea(new Dimension(3*width, 10)));
            buttonPanel.add(field);
            buttonPanel.add(Box.createRigidArea(new Dimension(10, 10)));
            buttonPanel.add(setDaysButton);
            buttonPanel.add(Box.createRigidArea(new Dimension(10, 10)));
            buttonPanel.add(reset);
            buttonPanel.add(Box.createRigidArea(new Dimension(3*width, 10)));
            container.add(buttonPanel, BorderLayout.SOUTH);
        }
    }

    private void checkExistingObjects() throws IOException, ClassNotFoundException {
        File upperPanelFile = new File("upperPanel.im");
        FileInputStream fisUp = new FileInputStream(upperPanelFile);
        ObjectInputStream oisUp = new ObjectInputStream(fisUp);

        File lowerPanelFile = new File("lowerPanel.im");
        FileInputStream fisLow = new FileInputStream(lowerPanelFile);
        ObjectInputStream oisLow = new ObjectInputStream(fisLow);

        this.upperPanel = (DayPanel) oisUp.readObject();
        oisUp.close();
        this.upperPanel.createTimer();
        this.lowerPanel = (DayPanel) oisLow.readObject();
        oisLow.close();
        this.lowerPanel.createTimer();

        if (panelContainer != null) {
            panelContainer.add(upperPanel);
            panelContainer.add(lowerPanel);
        }
    }

    private void createTimer() {
        this.timer = new Timer(1000*60*5, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(!isVisible());
            }
        });
        this.timer.start();
    }
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
        int height = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        if (background != null) {
           // g2d.drawImage(background, 0, 0, width, height, null);
        }
    }

    private void createImageIcon(String path) {
        this.icon = new ImageIcon(path + ".png");
        this.setIconImage(icon.getImage());
    }

    private BufferedImage createBackground() {
        File file = new File("background.png");
        try {
            background = ImageIO.read(file);
            repaint();
        } catch (IOException e) {
            container.setBackground(Color.BLUE);
        }
        return this.background;
    }

    private class ResetButton extends JButton {
        private ResetButton() {
            super("Reset");
            this.addActionListener(new MyActionListener());
        }
    }

    private class MyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            upperPanel.setDays(0);
            lowerPanel.setDays(0);
        }
    }
}
