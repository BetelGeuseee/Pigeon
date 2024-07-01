package org.example.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * @author - Shirshak Upadhayay
 * @Date - 27/06/2024
 */
public class ChatServer {
    private final int PORT = 8989;
    private ServerSocket serverSocket;
    private InputStreamReader inputStreamReader;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private OutputStreamWriter outputStreamWriter;

    private ServerGUI serverGUI;
    private String clientName;
    public ChatServer(ServerGUI serverGUI) {
        this.serverGUI = serverGUI;
    }

    public void start(){
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Listening on: " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();

                outputStreamWriter = new OutputStreamWriter(clientSocket.getOutputStream());
                bufferedWriter = new BufferedWriter(outputStreamWriter);
                inputStreamReader = new InputStreamReader(clientSocket.getInputStream());
                bufferedReader = new BufferedReader(inputStreamReader);
                if (clientSocket.isConnected()) {
                    this.clientName = getClientsName();
                    serverGUI.setBufferedWriter(bufferedWriter);
                    startDaemonThread(clientName);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private String getClientsName() throws IOException {
        return bufferedReader.readLine();
    }

    private void startDaemonThread(String clientName) {
        ServerDaemonThread serverThread = new ServerDaemonThread(bufferedReader, serverGUI.getChatArea(),clientName);
        Thread thread = new Thread(serverThread);
        thread.start();
    }

}
