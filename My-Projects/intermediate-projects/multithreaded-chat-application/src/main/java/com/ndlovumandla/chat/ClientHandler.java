package com.ndlovumandla.chat;

import java.io.*;
import java.net.*;

public class ClientHandler implements Runnable {
    private Socket socket;
    private User user;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            // Get user name
            output.writeUTF("Enter your name: ");
            String name = input.readUTF();
            user = new User(name, output);
            ChatServer.addUser(user);

            String message;
            while (true) {
                message = input.readUTF();
                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
                System.out.println(user.getName() + ": " + message);
                ChatServer.broadcast(message, user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                ChatServer.removeUser(user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
