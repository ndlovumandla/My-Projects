package com.ndlovumandla.chat;

import java.io.*;

public class User {
    private String name;
    private DataOutputStream output;

    public User(String name, DataOutputStream output) {
        this.name = name;
        this.output = output;
    }

    public String getName() {
        return name;
    }

    public void sendMessage(String message) {
        try {
            output.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
