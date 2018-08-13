package lesson6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main (String[] args) {
        try (ServerSocket server = new ServerSocket(8189)){
            Socket socket = server.accept();
            System.out.println("Client connected");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            /*int b = in.read();
            out.write(b);
            socket.close();*/
            while (true){
                String msg = in.readUTF();
                out.writeUTF("Echo:" + msg);
            }

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
