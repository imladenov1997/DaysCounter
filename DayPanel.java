import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.Calendar;

/*
 Created by Ivo Mladenov
 You have just broken Copyright Laws :) Decompilation of this file is infringing Copyright :)
*/

public class DayPanel extends JPanel implements Serializable {
    private Integer days;
    private String message;
    private JLabel label;
    private JLabel text;
    private Timer timer;
    private JButton test = new JButton("test");
    private Calendar calendar;
    private Integer hour;
    private Integer minute;
    private Integer second;

    public DayPanel(String message) {
        super();
        this.days = 0;
        this.message = message;
        this.calendar = Calendar.getInstance();
        this.addLabels(message);
        this.createTimer();
        this.setOpaque(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    private void addLabels(String message) {
        this.label = new JLabel(days.toString());
        this.text = new JLabel(message);

        // Days' label format
        this.label.setForeground(new Color(231, 225, 255));
        this.label.setFont(new Font("Font", Font.BOLD, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/7));
        this.label.setOpaque(false);

        // Text's label format
        this.text.setForeground(new Color(231, 225, 255));
        this.text.setFont(new Font("Font", Font.BOLD, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()/35));
        this.text.setOpaque(false);

        this.label.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.text.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Adding labels
        this.add(label);
        this.add(text);

    }

    private void update() {
        this.label.setText(days.toString());
    }

    protected void createTimer() {
        this.timer = new Timer(1000, new MyActionListener());
        this.timer.start();
    }

    public void setDays(int days) {
        if (days >= 0) {
            this.days = days;
            this.update();
        }
        else {
            this.days = 0;
            this.update();
        }
    }

    private void updateTime() {
        this.calendar = Calendar.getInstance();
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);
    }

    private class MyActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            updateTime();
            if (hour == 00 && minute == 00 && second == 00) {
                days++;
                update();
            }
        }
    }

    public Integer getDays() {
        return this.days;
    }
}
