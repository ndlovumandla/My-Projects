package com.ndlovumandla.chat;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatClient {
    private static DataOutputStream output;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            output = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            new Thread(() -> {
                try {
                    DataInputStream input = new DataInputStream(socket.getInputStream());
                    String message;
                    while (true) {
                        message = input.readUTF();
                        System.out.println(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

            String name = getName(scanner);
            while (true) {
                String message = scanner.nextLine();
                if (message.equalsIgnoreCase("exit")) {
                    output.writeUTF("exit");
                    break;
                }
                output.writeUTF(name + ": " + message);
            }

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getName(Scanner scanner) throws IOException {
        System.out.print("Enter your name: ");
        return scanner.nextLine();
    }
}
