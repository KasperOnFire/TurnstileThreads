package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TurnstileServer {

    private static String ip = "localhost";
    private static int PORT = 1237;
    private static ServerSocket serverSocket;
    private static TurnstileCounter counter;

    public static void main(String[] args) throws IOException {
        if (args.length == 2) {
            ip = args[0];
            PORT = Integer.parseInt(args[1]);
        }

        counter = new TurnstileCounter();

        serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress(ip, PORT));

        System.out.println("Waiting for connection");

        while (true) {
            Socket socket = serverSocket.accept(); //BLOCKING
            System.out.println("Connected!");
            new ClientHandler(socket, counter).start();
        }
    }

}
