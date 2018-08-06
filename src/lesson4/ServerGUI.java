package lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {
    private final int POS_X = 800;
    private final int POS_Y = 400;
    private final int WIDTH = 250;
    private final int HEIGHT = 100;

    private final ChatServer chatServer = new ChatServer();
    private final JButton btnStart = new JButton("Start");
    private final JButton btnStop = new JButton("Stop");

    public static void main (String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ServerGUI();
            }
        });
    }

    private ServerGUI() {
        Thread.setDefaultUncaughtExceptionHandler(this);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setResizable(false);
        setTitle("Chat server");
        setAlwaysOnTop(true);
        setLayout(new GridLayout(1,2));
        btnStart.addActionListener(this);
        btnStop.addActionListener(this);
        add(btnStart);
        add(btnStop);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        Object src = e.getSource();
        if (src == btnStart) {
            chatServer.start(8189);
        //    throw new ArithmeticException();
        }else if (src == btnStop) {
            chatServer.stop();
            System.exit(0);
        }

    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        String msg = "Что то пошло не так";
        JOptionPane.showMessageDialog(this, msg, "Alert", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
}
