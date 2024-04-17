package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClientMain {
    public static void main(String[] args) {

        final String SERVER_IP = "localhost"; // Server's IP address
        final int SERVER_PORT = 12345; // Server's port

        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT)) {
            System.out.println("Connected to server: " + SERVER_IP + " on port " + SERVER_PORT);

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Send a message to the server
            String messageToSend = "Hello, server!";
            out.println(messageToSend);
            System.out.println("Sent to server: " + messageToSend);

            // Receive a response from the server
            String serverResponse = in.readLine();
            System.out.println("Received from server: " + serverResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
