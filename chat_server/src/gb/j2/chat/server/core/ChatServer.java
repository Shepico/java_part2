package gb.j2.chat.server.core;

import gb.j2.network.*;
import javafx.scene.input.DataFormat;

import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class ChatServer implements ServerSocketThreadListener, SocketThreadListener{

    private ServerSocketThread server;
    private final DateFormat DATEFORMAT = new SimpleDateFormat("HH:mm:ss: ");


    public void start(int port){
        if (server !=null && server.isAlive()) {
            putLog("Server is running");
        }else{
            server = new ServerSocketThread(this,"Chat server", port, 3000);
        }

    }

    public void stop(){
        if (server == null || !server.isAlive()){
            putLog("Server is not running");
        }else {
            server.interrupt();
        }
    }

    private void putLog(String msg){
        msg = DATEFORMAT.format(System.currentTimeMillis()) + Thread.currentThread().getName() + ":" + msg;
            System.out.println(msg);
    }
    /**
     * Server socket thread events
     * **/
    @Override
    public void onServerThreadStart(ServerSocketThread thread) {
        putLog("Server thread start");
    }

    @Override
    public void onServerThreadStop(ServerSocketThread thread) {
        putLog("Server thread stop");
    }

    @Override
    public void onSocketAccepted(ServerSocketThread thread, Socket socket) {
        putLog("Socket accepted");
        String name = "SocketThread" + ":" + socket.getInetAddress() + socket.getPort();
        new SocketThread(this,name,socket);
    }

    @Override
    public void onAcceptTimeout(ServerSocketThread thread, ServerSocket server) {
        putLog("Accept timeout");
    }

    @Override
    public void onServerSockedCreated(ServerSocketThread thread, ServerSocket server) {
        putLog("Server socket created");
    }

    @Override
    public void onServerThreadException(ServerSocketThread thread, Exception e) {
        putLog("Server thread exception");
    }

    /*
     * Socket thred events
     * */

    @Override
    public synchronized void onStartSocketThread(SocketThread thread, Socket socket) {
        putLog("Start socket thread");
    }

    @Override
    public synchronized void onStopSocketThread(SocketThread thread) {
        putLog("Stop socket thread");
    }

    @Override
    public synchronized void onSocketThreadIsReady(SocketThread thread, Socket socket) {
        putLog("Socket thread is ready");
    }

    @Override
    public synchronized void onReceiveString(SocketThread thread, Socket socket, String msg) {
        thread.sendString("echo: " + msg);
    }

    @Override
    public synchronized void onSocketThreadException(SocketThread thread, Exception e) {
        putLog("Socket thread exception");
    }



}
