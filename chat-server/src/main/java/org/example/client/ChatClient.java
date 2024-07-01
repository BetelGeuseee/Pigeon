package org.example.client;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

/**
 * @author - Shirshak Upadhayay
 * @Date - 27/06/2024
 */
public class ChatClient {

    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private OutputStreamWriter outputStreamWriter;
    private BufferedWriter bufferedWriter;

    private Socket socket;

    private ClientGUI clientGUI;
    public ChatClient(ClientGUI clientGUI){
        this.clientGUI = clientGUI;
    }

    private void connect() {
        try {
            this.socket = new Socket("localhost", 8989);
                    this.outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                    this.bufferedWriter = new BufferedWriter(outputStreamWriter);

                    this.inputStreamReader = new InputStreamReader(socket.getInputStream());
                    this.bufferedReader = new BufferedReader(inputStreamReader);

                    clientGUI.setBufferedWriter(bufferedWriter);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        startDaemonThread(); //daemon thread reads incoming messages from the server.
    }

    private void startDaemonThread() {
        ClientDaemonThread clientThread = new ClientDaemonThread(bufferedReader,clientGUI.getChatArea());
        Thread thread = new Thread(clientThread);
        thread.start();
    }

    private void sendMessageToChatServer(String message) throws IOException{
        bufferedWriter.write(message);
        bufferedWriter.newLine();
        bufferedWriter.flush();
    }

    public static void main(String[] args) {
        new ChatClient(new ClientGUI()).connect();
    }
}
