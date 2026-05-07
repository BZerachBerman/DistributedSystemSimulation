package Server;

import Shared.mathProblem;
import Shared.mathSolution;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ConcurrentLinkedQueue;

/***
 * The Entry point for the Master/Server program. It spins up a SlaveCommunicator thread
 * and a ClientCommunicator thread
 * as well as creating some shared Data Structures for inter-thread communication.
 */
public class Master {
    public static void main(String[] args) {
        System.out.println("Server Started");
        int portNumber;

        if (args.length > 0) {
            portNumber = Integer.parseInt(args[0]);
        } else {
            portNumber = 8888; // Default port
            System.out.println("No ports provided. Using default port: " + portNumber);
        }
        new Master().start(portNumber);
    }

    public void start(int portNumber) {
        ClientCommunicator clientCommunicator;
        SlaveCommunicator slaveCommunicator;

        ConcurrentLinkedQueue<mathProblem> Problems = new ConcurrentLinkedQueue<>();
        ConcurrentLinkedQueue<mathSolution> Solutions = new ConcurrentLinkedQueue<>();

        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            System.out.print("Master.Master Running on port " + portNumber);
            slaveCommunicator = new SlaveCommunicator(serverSocket, Problems, Solutions);
            clientCommunicator = new ClientCommunicator(serverSocket, Problems, Solutions);

            Thread slaveThread = new Thread(slaveCommunicator);
            slaveThread.start();

            Thread clientThread = new Thread(clientCommunicator);
            clientThread.start();
        } catch (IOException e) {
            //idk yet
        }

    }
}