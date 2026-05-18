package Client;

import Shared.mathProblem;

import java.net.*;
import java.io.*;

public class Client {

    public static void main(String[] args) {

        //make sure user enters two arg(portnumber and localhost or ip)
        if (args.length != 2) {
            System.err.println("Usage: java EchoClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0]; // should be localhost
        int portNumber = Integer.parseInt(args[1]); // portnumber of master

        // sets up socket and the reader and writers for the client
        try (
                Socket socket = new Socket(hostName, portNumber);
                PrintWriter pw = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        ) {
            //code of client starts here (will accept input from user and then send to master)
            System.out.println(br.readLine());
            String userInput;
            System.out.println("Type your messages (type 'bye' to quit):");

            while ((userInput = stdIn.readLine()) != null) {
                pw.println(userInput); // Send to server

                if ("bye".equalsIgnoreCase(userInput)) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace(); // This is critical!    	}
        }
    }
}
