package org.example;

import org.example.server.ChatServer;
import org.example.server.ServerGUI;

public class App
{
    public static void main( String[] args )
    {
        ChatServer chatServer  = new ChatServer(new ServerGUI());
        chatServer.start();
    }
}
