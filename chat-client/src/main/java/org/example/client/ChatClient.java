package org.example.client;

import org.example.client.gui.ClientGUI;
import org.example.client.gui.StarterGUI;

import java.io.*;
import java.net.Socket;

/**
 * @author - Shirshak Upadhayay
 * @Date - 27/06/2024
 */
public class ChatClient {

    private int port;
    private String ip;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private OutputStreamWriter outputStreamWriter;
    private BufferedWriter bufferedWriter;

    private Socket socket;

    private ClientGUI clientGUI;

    private String clientName;
    private StarterGUI starterGUI;

    public ChatClient(String ip,int port,String clientName,StarterGUI starterGUI){
      this.ip = ip;
      this.port = port;
      this.clientName = clientName;
      this.starterGUI = starterGUI;
    }
    public void connect() {
        try {
            this.socket = new Socket(ip, port);
                    this.outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                    this.bufferedWriter = new BufferedWriter(outputStreamWriter);

                    this.inputStreamReader = new InputStreamReader(socket.getInputStream());
                    this.bufferedReader = new BufferedReader(inputStreamReader);
                    this.clientGUI = new ClientGUI();
                    starterGUI.setBufferedWriter(bufferedWriter);
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

}
