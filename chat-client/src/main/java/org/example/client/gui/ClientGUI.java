package org.example.client.gui;


import org.example.client.MessageWriter;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @author - Shirshak Upadhayay
 * @Date - 28/06/2024
 */
public class ClientGUI {

    private JFrame mainFrame;
    private JTextArea chatArea;
    private JScrollPane mainPlane;
    private JButton sendMessageBtn;


    private JTextField messageField;

    private BufferedWriter bufferedWriter;
    public ClientGUI(){
        mainFrame = new JFrame("Pigeon");
        mainFrame.setLayout(new BorderLayout());
        Font font = new Font("Arial", Font.PLAIN, 13);
        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setFont(font);
        chatArea.setWrapStyleWord(true);
        chatArea.setBackground(Color.black);
        chatArea.setForeground(Color.GREEN);

        mainPlane = new JScrollPane(chatArea);
        mainPlane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        messageField = new JTextField();
        sendMessageBtn= new JButton("Send");
        sendMessageBtn.setBackground(Color.cyan);

        sendMessageBtn.addActionListener(e -> sendMessage());
        messageField.addActionListener(e -> sendMessage());

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));  // Add padding of 10 pixels on all sides
        bottomPanel.add(messageField, BorderLayout.CENTER);
        bottomPanel.add(sendMessageBtn, BorderLayout.EAST);

        mainFrame.add(mainPlane, BorderLayout.CENTER);
        mainFrame.add(bottomPanel, BorderLayout.SOUTH);
        mainFrame.setSize(500, 500);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }

    public void setBufferedWriter(BufferedWriter bufferedWriter) {
        this.bufferedWriter = bufferedWriter;
    }

    private void sendMessage() {

        String message = messageField.getText().trim();
        if (!message.isEmpty()) {
            chatArea.append("You: " + message + "\n");
            messageField.setText("");
            messageField.requestFocus();
            try {
                MessageWriter messageWriter = new MessageWriter(bufferedWriter);
                messageWriter.writeMessageToSocket(message);
            }catch (IOException e){
                System.out.println(e.getMessage());
            }
        }
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }

    public JTextArea getChatArea() {
        return chatArea;
    }

    public JScrollPane getMainPlane() {
        return mainPlane;
    }

    public JButton getSendMessageBtn() {
        return sendMessageBtn;
    }

    public JTextField getMessageField() {
        return messageField;
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }

}
