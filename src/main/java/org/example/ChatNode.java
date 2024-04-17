package org.example;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ChatNode extends ReceiverAdapter {
    private JChannel channel;

    public ChatNode(String clusterName) throws Exception {
        channel = new JChannel(); // Use default config
        channel.setReceiver(this);
        channel.connect(clusterName);
        channel.getState(null, 10000); // Retrieve current state (messages) from the coordinator
    }

    public void viewAccepted(View view) {
        System.out.println("Cluster view: " + view);
    }

    public void receive(Message msg) {
        System.out.println("[" + msg.getSrc() + "] " + msg.getObject());
    }

    public void sendMessage(String message) throws Exception {
        Message msg = new Message(null, message);
        channel.send(msg);
    }

    public void start() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            System.out.print("> ");
            String line = reader.readLine();
            if (line == null || line.equalsIgnoreCase("exit")) {
                break;
            }
            sendMessage(line);
        }
        channel.close();
    }

    public static void main(String[] args) {
        try {
            if (args.length != 1) {
                System.out.println("Usage: ChatNode <cluster_name>");
                return;
            }
            String clusterName = args[0];
            ChatNode node = new ChatNode(clusterName);
            node.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
