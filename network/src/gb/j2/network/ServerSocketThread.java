package gb.j2.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class ServerSocketThread extends Thread {
    private final int PORT;
    private final int TIMEOUT;

    public ServerSocketThread(String name, int port, int timeout) {
        super(name);
        this.PORT = port;
        this.TIMEOUT = timeout;
        start();
    }

    @Override
    public void run() {
        System.out.println("Thread started");
       try (ServerSocket server = new ServerSocket(PORT)){
           System.out.println("Server started");
           server.setSoTimeout(TIMEOUT);
            while (!isInterrupted()){
                Socket socket;
                try {
                    socket = server.accept();
                }catch (SocketTimeoutException e){
                    e.printStackTrace();
                }
                System.out.println("Client connected");
            }
       }catch (IOException e) {
           e.printStackTrace();
       }finally {
           System.out.println("Server stopped");
       }
    }
}