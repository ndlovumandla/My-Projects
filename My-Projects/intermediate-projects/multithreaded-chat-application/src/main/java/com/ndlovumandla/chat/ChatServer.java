package com.ndlovumandla.chat;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    private static final int PORT = 12345;
    private static Set<User> clientUsers = new HashSet<>();

    public static void main(String[] args) {
        System.out.println("Chat Server is running...");
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void broadcast(String message, User sender) {
        for (User user : clientUsers) {
            if (!user.equals(sender)) {
                user.sendMessage(message);
            }
        }
    }

    public static void addUser(User user) {
        clientUsers.add(user);
    }

    public static void removeUser(User user) {
        clientUsers.remove(user);
    }
}
