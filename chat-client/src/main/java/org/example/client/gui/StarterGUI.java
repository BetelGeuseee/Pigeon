package org.example.client.gui;

import org.example.client.ChatClient;
import org.example.client.MessageWriter;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @author - Shirshak Upadhayay
 * @Date - 29/06/2024
 */
public class StarterGUI extends JFrame {

    private JTextField ipField;
    private JTextField portField;
    private JTextField clientNameField;
    private BufferedWriter bufferedWriter;

    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    public StarterGUI(){
        setTitle("Connect to Pigeon");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding


        ipField = new JTextField(20);
        portField = new JTextField(20);
        clientNameField = new JTextField(20);


        JLabel label1 = new JLabel("Server's IP:");
        JLabel label2 = new JLabel("Port:");
        JLabel label3 = new JLabel("Client Name:");


        JButton connectButton = new JButton("Connect");
         connectButton.addActionListener(e -> connect());

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(label1, gbc);
        gbc.gridx = 1;
        panel.add(ipField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(label2, gbc);
        gbc.gridx = 1;
        panel.add(portField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(label3, gbc);
        gbc.gridx = 1;
        panel.add(clientNameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        panel.add(connectButton, gbc);

        add(panel);

        setVisible(true);
    }

    public void connect(){
        String ip = ipField.getText().trim();
        int port = Integer.parseInt(portField.getText().trim());
        String clientName = clientNameField.getText().trim();
        try {
            ChatClient client = new ChatClient(ip, port, clientName,this);
            client.connect();
            MessageWriter messageWriter = new MessageWriter(bufferedWriter);
            messageWriter.writeMessageToSocket(clientName);
            dispose();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
