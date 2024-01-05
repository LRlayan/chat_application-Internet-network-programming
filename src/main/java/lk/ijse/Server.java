package lk.ijse;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args)  {
        try {
            //Main server socket
            ServerSocket serverSocket = new ServerSocket(3000);
            //Local server socket
            Socket socket = serverSocket.accept();
            //Client Request Accepted!
            System.out.println("Accepted Request!");

            //server output message send client
            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            //Type server message input
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            System.out.println("Enter the response for client");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            String message = "me :";
            String reply = "";

            while (!message.equals("finish")){
                //Client Request message
                message = dataInputStream.readUTF();
                System.out.println("Client : " + message);
                //Server reply for client
                reply = bufferedReader.readLine();

                dataOutputStream.writeUTF(reply);
                dataOutputStream.flush();
            }

            dataInputStream.close();
            dataOutputStream.close();
            bufferedReader.close();
            socket.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
