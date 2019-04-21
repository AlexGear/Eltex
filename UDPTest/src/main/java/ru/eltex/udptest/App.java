package ru.eltex.udptest;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class App {
    private static final int PORT = 2007;

    public static void main(String[] args) {
        if(args.length == 0) {
            printUsageMsg();
            System.exit(1);
        }
        switch(args[0]) {
            case "server":
                runServer();
                break;

            case "client":
                runClient();
                break;

            default:
                printUsageMsg();
                System.exit(1);
        }
    }

    private static void printUsageMsg() {
        System.out.println("USAGE: ... server|client");
    }

    private static void runServer() {
        try {
            System.out.println("Waiting for incoming message...");
            String message = receiveMessage();
            System.out.println("Incoming message:");
            System.out.println(message);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void runClient() {
        try {
            System.out.println("Enter the message:");
            try (Scanner in = new Scanner(System.in)) {
                String outMessage = in.nextLine();
                sendMessage(outMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String receiveMessage() throws IOException {
        try (DatagramSocket socket = new DatagramSocket(App.PORT)) {
            byte[] buffer = new byte[65536];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);
            return new String(packet.getData());
        }
    }

    private static void sendMessage(String outMessage) throws IOException {
        try (DatagramSocket socket = new DatagramSocket()) {
            byte[] buffer = outMessage.getBytes();
            InetAddress address = InetAddress.getByName("127.0.0.1");
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, PORT);
            socket.send(packet);
        }
    }
}