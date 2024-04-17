package org.example;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClientMain {
    public static void main(String[] args) {
        final String SERVER_IP = "localhost"; // Server's IP address
        final int SERVER_PORT = 12345; // Server's port

        try (var socket = new DatagramSocket()) {
            InetAddress serverAddress = InetAddress.getByName(SERVER_IP);

            // Send a message to the server
            String messageToSend = "Hello, server!";
            byte[] sendData = messageToSend.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
            socket.send(sendPacket);
            System.out.println("Sent to server: " + messageToSend);

            // Receive response from the server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);
            String serverResponse = new String(receivePacket.getData(), 0, receivePacket.getLength());
            System.out.println("Received from server: " + serverResponse);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
