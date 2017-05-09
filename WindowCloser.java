import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;

/*
 Created by Ivo Mladenov
 You have just broken Copyright Laws :) Decompilation of this file is infringing Copyright :)
*/

public class WindowCloser extends WindowAdapter {
    DayPanel upperPanel;
    DayPanel lowerPanel;

    public WindowCloser(DayPanel upperPanel, DayPanel lowerPanel) {
        super();
        this.upperPanel = upperPanel;
        this.lowerPanel = lowerPanel;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        super.windowClosing(e);

        // Serialize
        try {
            this.serialisePanels();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    private void serialisePanels() throws IOException {
        File upperPanelFile = new File("upperPanel.im");
        FileOutputStream fosUp = new FileOutputStream(upperPanelFile);
        ObjectOutputStream oosUp = new ObjectOutputStream(fosUp);
        oosUp.writeObject(upperPanel);

        File lowerPanelFile = new File("lowerPanel.im");
        FileOutputStream fosLow = new FileOutputStream(lowerPanelFile);
        ObjectOutputStream oosLow = new ObjectOutputStream(fosLow);
        oosLow.writeObject(lowerPanel);
    }
}
