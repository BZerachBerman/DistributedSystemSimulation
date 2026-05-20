package Server;

import Shared.mathProblem;
import Shared.mathSolution;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.LinkedBlockingQueue;

/***
 * The Entry point for the Master/Server program. It spins up a SlaveCommunicator thread
 * and a ClientCommunicator thread
 * as well as creating some shared Data Structures for inter-thread communication.
 */
public class Master {
    public static void main(String[] args) {
        System.out.println("Server Started");
        if (args.length != 2) {
            System.out.println("Usage: java Server.Master <clientPort> <slavePort>");
            return;
        }

        int clientPortNumber = Integer.parseInt(args[0]);
        int slavePortNumber = Integer.parseInt(args[1]);

        new Master().start(clientPortNumber, slavePortNumber);
    }

    public void start(int clientPortNumber, int slavePortNumber) {

        LinkedBlockingQueue<mathProblem> Problems = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<mathSolution> Solutions = new LinkedBlockingQueue<>();

        try {
            ServerSocket clientSocket = new ServerSocket(clientPortNumber);
            System.out.println("Master listening for clients on port " + clientPortNumber);

            ServerSocket slaveSocket = new ServerSocket(slavePortNumber);
            System.out.println("Master listening for slaves on port " + slavePortNumber);

            SlaveCommunicator slaveCommunicator = new SlaveCommunicator(slaveSocket, Problems, Solutions);
            ClientCommunicator clientCommunicator = new ClientCommunicator(clientSocket, Problems, Solutions);

            Thread slaveThread = new Thread(slaveCommunicator);
            System.out.println("Starting SlaveCommunicator");
            slaveThread.start();

            Thread clientThread = new Thread(clientCommunicator);
            System.out.println("Starting ClientCommunicator");
            clientThread.start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}