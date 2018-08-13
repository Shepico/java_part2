package gb.j2.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerSocketThread extends Thread {
    private final int port;

    public ServerSocketThread(String name, int port) {
        super(name);
        this.port = port;
        start();
    }

    @Override
    public void run() {
        System.out.println("Server started");
        while (!isInterrupted()) {
            System.out.println("Server socket thread is working");
            Socket socket;
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                interrupt();
                break;
            }
            System.out.println("Server stoped");
        }
    }
}