package org.example.client;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * @author - Shirshak Upadhayay
 * @Date - 28/06/2024
 */
//this class help to write message in underlying socket.
public class MessageWriter {
    private BufferedWriter bufferedWriter;

    public MessageWriter(BufferedWriter bufferedWriter){
        this.bufferedWriter = bufferedWriter;
    }
    public void writeMessageToSocket(String message) throws IOException{
            bufferedWriter.write(message);
            bufferedWriter.newLine();
            bufferedWriter.flush();
    }
}
