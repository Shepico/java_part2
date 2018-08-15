package lesson6;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class SimpleClient {
    public static void main (String[] args) {
        try (Socket socket = new Socket("127.0.0.1",8189)) {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            /*out.write(123);
            int b = in.read();
            System.out.println("reciv - " + b);*/
            Scanner sc = new Scanner(System.in);
            while(true){
                String msg = sc.nextLine();
                out.writeUTF(msg);
                System.out.println(in.readUTF());
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
