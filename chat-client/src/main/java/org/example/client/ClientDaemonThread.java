package org.example.client;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author - Shirshak Upadhayay
 * @Date - 28/06/2024
 */
public class ClientDaemonThread implements Runnable {

    private BufferedReader bufferedReader;
    private JTextArea chatArea;

    public ClientDaemonThread(BufferedReader reader, JTextArea jTextArea){
        this.bufferedReader = reader;
        this.chatArea = jTextArea;

    }
    @Override
    public void run() {
        String text = "";
        while(true){
            try {
                while ((text = bufferedReader.readLine()) != null) {
                    chatArea.append("Server: " + text + "\n");
                }
            }catch (IOException e){
                System.out.println(e.getMessage());
                break;
            }
        }
    }
}
