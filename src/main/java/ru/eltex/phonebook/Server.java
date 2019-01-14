package ru.eltex.phonebook;

import java.io.Closeable;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable, Closeable {
    public final int port;

    private final ServerSocket serverSocket;
    private final PhoneBook phoneBook;
    private final Thread acceptanceThread;

    public Server(int port, PhoneBook phoneBook) throws IOException {
        this.port = port;
        this.phoneBook = phoneBook;

        serverSocket = new ServerSocket(port);
        acceptanceThread = new Thread(this);
        acceptanceThread.setDaemon(true);
        acceptanceThread.start();
    }

    @Override
    public void run() {
        while(true) {
            try {
                Socket clientSocket = serverSocket.accept();
                new Client(clientSocket, phoneBook);
            } catch (IOException e) {
                System.err.println("Failed to serve the client:");
                System.err.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }


    @Override
    public void close() throws IOException {
        acceptanceThread.interrupt();
        serverSocket.close();
    }
}
