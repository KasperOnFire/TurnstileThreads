package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientHandler extends Thread {

    private Socket socket;
    private final TurnstileCounter counter;

    public ClientHandler(Socket socket, TurnstileCounter counter) {
        this.socket = socket;
        this.counter = counter;
    }

    @Override
    public void run() {
        PrintWriter pw = null;
        try {
            Scanner scan = new Scanner(socket.getInputStream());
            pw = new PrintWriter(socket.getOutputStream(), true); //DONT FORGET AUTOFLUSH
            pw.println("Hello! what are you? Monitor or spectator?");
            String input = scan.nextLine();
            switch (input) {
                case "spectator":
                    new Turnstile(counter).start();
                    pw.println("Spectator passed through turnstile.");
                    break;
                case "monitor":
                    pw.println("Total spectators: " + counter.getCount());
                    break;
                default:
                    throw new AssertionError();
            }
        } catch (IOException ex) {
            Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            pw.close();
        }
    }

}
