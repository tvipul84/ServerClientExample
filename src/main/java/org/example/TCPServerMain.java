package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerMain {
    public static void main(String[] args) {
        final int PORT = 12345; // Port number for the server to listen on

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server listening on port " + PORT);

            while (true) {
                try (Socket clientSocket = serverSocket.accept()) {
                    System.out.println("Client connected: " + clientSocket);

                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                    String message = in.readLine(); // Read client's message
                    System.out.println("Received from client: " + message);

                    // Respond to the client
                    out.println("Server received: " + message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }    }
}