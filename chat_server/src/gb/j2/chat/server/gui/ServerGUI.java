package gb.j2.chat.server.gui;

import gb.j2.chat.server.core.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ServerGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {
    private final int POS_X = 800;
    private final int POS_Y = 400;
    private final int WIDTH = 250;
    private final int HEIGHT = 100;

    private final ChatServer CHATSERVER = new ChatServer();
    private final JButton BTNSTART = new JButton("Start");
    private final JButton BTNSTOP = new JButton("Stop");

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
        BTNSTART.addActionListener(this);
        BTNSTOP.addActionListener(this);
        add(BTNSTART);
        add(BTNSTOP);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        Object src = e.getSource();
        if (src == BTNSTART) {
            CHATSERVER.start(8189);
        //    throw new ArithmeticException();
        }else if (src == BTNSTOP) {
            CHATSERVER.stop();
            System.exit(0);
        }

    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        String msg = "Что то пошло не так";
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, msg, "Alert", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }
}
