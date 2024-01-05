package lk.ijse;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost" , 3000);

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
            System.out.println("Start Chat With Server : ");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

            String message = "";
            String reply = "";

            while (!message.equals("finish")){
              //  System.out.println("me : ");
                reply = bufferedReader.readLine();

                dataOutputStream.writeUTF(reply);
                dataOutputStream.flush();

                message = dataInputStream.readUTF();
                System.out.println("Server response : " + message);
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
