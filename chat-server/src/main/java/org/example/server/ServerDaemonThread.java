package org.example.server;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author - Shirshak Upadhayay
 * @Date - 28/06/2024
 */
public class ServerDaemonThread implements Runnable {
    private BufferedReader bufferedReader;
    private JTextArea chatArea;
    private String clientName;

    public ServerDaemonThread(BufferedReader reader, JTextArea chatArea,String clientName){
        this.bufferedReader = reader;
        this.chatArea = chatArea;
        this.clientName = clientName;
    }
    @Override
    public void run() {
        while(true){
            String text = "";
            try {
                while ((text = bufferedReader.readLine())!= null){
                   chatArea.append(clientName+": "+text+"\n");
                }
            }catch(IOException e){
                chatArea.append("Pigeon: "+clientName+" has been disconnected...");
                break;
            }
        }
    }
}
