package org.example;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServerMain {
    public static void main(String[] args) {
        final int PORT = 12345; // Port number for the server to listen on
        final int BUFFER_SIZE = 1024;

        try (var socket = new DatagramSocket(PORT)) {
            System.out.println("Server listening on port " + PORT);

            byte[] buffer = new byte[BUFFER_SIZE];
            DatagramPacket incomingPacket = new DatagramPacket(buffer, buffer.length);

            while (true) {
                // Receive client's message
                socket.receive(incomingPacket);
                String message = new String(incomingPacket.getData(), 0, incomingPacket.getLength());
                System.out.println("Received from client: " + message);

                // Prepare response
                String response = "Server received: " + message;
                byte[] responseData = response.getBytes();

                // Send response back to client
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length,
                        incomingPacket.getAddress(), incomingPacket.getPort());
                socket.send(responsePacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
