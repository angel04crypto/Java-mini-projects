import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Stopwatch extends JFrame {
    private JLabel hoursLabel, minutesLabel, secondsLabel, millisecondsLabel;
    private JButton startButton, stopButton, resetButton;
    private int hours = 0, minutes = 0, seconds = 0, milliseconds = 0;
    private boolean running = false;
    private Thread stopwatchThread;

    public Stopwatch() {
        setTitle("Stopwatch");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        hoursLabel = new JLabel("00");
        minutesLabel = new JLabel("00");
        secondsLabel = new JLabel("00");
        millisecondsLabel = new JLabel("00");

        Font font = new Font("Arial", Font.PLAIN, 30);
        hoursLabel.setFont(font);
        minutesLabel.setFont(font);
        secondsLabel.setFont(font);
        millisecondsLabel.setFont(font);

        add(hoursLabel);
        add(new JLabel(":"));
        add(minutesLabel);
        add(new JLabel(":"));
        add(secondsLabel);
        add(new JLabel("."));
        add(millisecondsLabel);

        startButton = new JButton("Start");
        stopButton = new JButton("Stop");
        resetButton = new JButton("Reset");

        startButton.addActionListener(new StartButtonListener());
        stopButton.addActionListener(new StopButtonListener());
        resetButton.addActionListener(new ResetButtonListener());

        add(startButton);
        add(stopButton);
        add(resetButton);

        setVisible(true);
    }

    private void startStopwatch() {
        if (stopwatchThread == null || !stopwatchThread.isAlive()) {
            stopwatchThread = new Thread(new StopwatchRunnable());
            stopwatchThread.start();
        }
    }

    private void stopStopwatch() {
        running = false;
    }

    private void resetStopwatch() {
        running = false;
        hours = 0;
        minutes = 0;
        seconds = 0;
        milliseconds = 0;
        updateLabels();
    }

    private void updateLabels() {
        hoursLabel.setText(String.format("%02d", hours));
        minutesLabel.setText(String.format("%02d", minutes));
        secondsLabel.setText(String.format("%02d", seconds));
        millisecondsLabel.setText(String.format("%02d", milliseconds));
    }

    private class StopwatchRunnable implements Runnable {
        public void run() {
            running = true;
            while (running) {
                try {
                    Thread.sleep(10);
                    milliseconds++;
                    if (milliseconds >= 100) {
                        milliseconds = 0;
                        seconds++;
                    }
                    if (seconds >= 60) {
                        seconds = 0;
                        minutes++;
                    }
                    if (minutes >= 60) {
                        minutes = 0;
                        hours++;
                    }
                    updateLabels();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class StartButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (!running) {
                startStopwatch();
            }
        }
    }

    private class StopButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            stopStopwatch();
        }
    }

    private class ResetButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            resetStopwatch();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Stopwatch());
    }
}
