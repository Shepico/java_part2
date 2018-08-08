package lesson4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.KeyAdapter;
//import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;

public class ClientGUI extends JFrame implements ActionListener, Thread.UncaughtExceptionHandler {
    private final int Width = 1000;
    private final int Height= 600;

    private final JTextArea Log = new JTextArea();
    private final JPanel Panel_Top = new JPanel(new GridLayout(2,3));
    private final JTextField tfIPadress = new JTextField("127.0.0.1");
    private final JTextField tfPort = new JTextField("8891");
    private final JCheckBox cbAlwaysOnTop = new JCheckBox("Alaways top");
    private final JTextField tfLogin = new JTextField("Pavel");
    private final JPasswordField tfPass = new JPasswordField("123");
    private final JButton btnLogin = new JButton("Login");

    private final JPanel Panel_Bottom = new JPanel(new BorderLayout());
    private final JButton btnDisconect = new JButton("Disconnect");
    private final JTextField tfMessage = new JTextField();
    private final JButton btnSend = new JButton("Send");

    private final JList<String> userList = new JList();

    private FillLogFile fs;


    public static void main(String[] args) throws IOException{
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientGUI();
            }
        });
    }

    private ClientGUI () {
        Thread.setDefaultUncaughtExceptionHandler(this);
        String[] users = {"user1", "user2", "user3"};
        userList.setListData(users);
        //
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(new Dimension(Width, Height));
        setTitle("Chat client");
        setLocationRelativeTo(null);
        //
        btnSend.addActionListener(this);
        btnDisconect.addActionListener(this);
        btnLogin.addActionListener(this);
        cbAlwaysOnTop.addActionListener(this);
        /*tfMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        sendFile();
                    }catch (IOException er) {
                        throw new RuntimeException("Error output");
                    }
                    sendMessage();
                }
            }
        });*/
        tfMessage.addActionListener(this);
        //
        Log.setEditable(false);
        JScrollPane scrollLog= new JScrollPane(Log);
        JScrollPane scrollUsers = new JScrollPane(userList);
        scrollUsers.setPreferredSize(new Dimension(200,0));
        //
        Panel_Top.add(tfIPadress);
        Panel_Top.add(tfPort);
        Panel_Top.add(cbAlwaysOnTop);
        Panel_Top.add(btnLogin);
        Panel_Top.add(tfPass);
        Panel_Top.add(tfLogin);
        Panel_Bottom.add(btnDisconect,BorderLayout.WEST);
        Panel_Bottom.add(tfMessage,BorderLayout.CENTER);
        Panel_Bottom.add(btnSend, BorderLayout.EAST);
        //
        add(Panel_Top,BorderLayout.NORTH);
        add(scrollLog, BorderLayout.CENTER);
        add(scrollUsers, BorderLayout.EAST);
        add(Panel_Bottom, BorderLayout.SOUTH);
        //
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if(src == cbAlwaysOnTop) {
            setAlwaysOnTop(cbAlwaysOnTop.isSelected());
        }else if (src == btnSend || src == tfMessage) {
            /*if (LOG.getText().equals("")){
                LOG.append(tfMessage.getText());
            }else {
                LOG.append("\n" + tfMessage.getText());
            }
            tfMessage.setText("");*/
            try {
                sendFile();
            } catch (IOException er) {
                //er.printStackTrace();
                throw new RuntimeException("Error output ");
            }
            sendMessage();


        }else {
            throw new RuntimeException("Unknown source: " + src);
        }

    }

    private  void sendFile() throws IOException {
        String msg = tfMessage.getText();
        if ("".equals(msg)) return;  //Стандартный оборот, проверка на пустую строку
        try(FileWriter out  = new FileWriter("log.txt",true)){
            out.write(msg +"\n");
            out.flush();

        }catch (IOException e) {
            JOptionPane.showMessageDialog(this,"Output error", "Alert",JOptionPane.ERROR_MESSAGE);
        }

        //можно проще
        /*fs = new FillLogFile();
        fs.saveFile(msg);
        fs.close();*/

    }

    private void sendMessage(){
        //if (LOG.getText().equals("")){ //через LineSeparator сделал
        String msg = tfMessage.getText();
        if ("".equals(msg)) return;  //Стандартный оборот, проверка на пустую строку
            Log.append(msg+System.lineSeparator());
        /*}else {
            LOG.append("\n" + tfMessage.getText());
        }*/
        tfMessage.setText("");
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        String msg = e.getMessage();
        JOptionPane.showMessageDialog(this, msg, "Alert", JOptionPane.ERROR_MESSAGE);
        System.exit(1);
    }

}
