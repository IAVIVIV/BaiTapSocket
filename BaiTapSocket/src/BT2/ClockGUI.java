package BT2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

public class ClockGUI extends JFrame {
    private JLabel timeLabel;

    public ClockGUI() {
        setTitle("Clock");
        setSize(200, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        timeLabel = new JLabel();
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        add(timeLabel);

        Timer timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });
        timer.start();
    }

    private void updateTime() {
        try {
            Socket socket = new Socket("localhost", 12345);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("time");
            String response = in.readLine();
            timeLabel.setText(response);

            out.close();
            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ClockGUI clock = new ClockGUI();
                clock.setVisible(true);
            }
        });
    }
}
