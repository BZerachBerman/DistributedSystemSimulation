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
        int portNumber;
        int masterPortNumber;
        if (args.length > 0) {
            portNumber = Integer.parseInt(args[0]);
            masterPortNumber = Integer.parseInt(args[1]);
        } else {
            System.out.println("No ports provided.");
            return;
        }
        new Master().start(portNumber, masterPortNumber);
    }

    public void start(int serverPortNumber, int masterPortNumber) {
        ClientCommunicator clientCommunicator;
        SlaveCommunicator slaveCommunicator;

        LinkedBlockingQueue<mathProblem> Problems = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<mathSolution> Solutions = new LinkedBlockingQueue<>();

        try {
            ServerSocket serverSocket = new ServerSocket(serverPortNumber);
            System.out.print("Server running on port " + serverPortNumber);

            ServerSocket masterSocket = new ServerSocket(masterPortNumber);
            System.out.println("Master running on port " + masterPortNumber);

            //slaveCommunicator = new SlaveCommunicator(masterSocket, Problems, Solutions);
            clientCommunicator = new ClientCommunicator(serverSocket, Problems, Solutions);

            //Thread slaveThread = new Thread(slaveCommunicator);
            //System.out.println("Starting SlaveCommunicator");
            //slaveThread.start();

            Thread clientThread = new Thread(clientCommunicator);
            System.out.println("Starting ClientCommunicator");
            clientThread.start();
        } catch (IOException e) {
            //idk yet
        }
    }
}