package Server;

import Shared.mathProblem;
import Shared.mathSolution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * A Runnable class responsible for managing all communication to and from all server Slaves.
 * This includes connecting to the slaves, assigning them a SlaveReader and a SlaveWriter,
 * and scheduling.
 */
public class SlaveCommunicator implements Runnable {
    ConcurrentLinkedQueue<mathProblem> Problems; //For sharing math problems with Master
    ConcurrentLinkedQueue<mathSolution> Solutions; //Shared by all the SlaveReader Threads and Master
    ServerSocket serverSocket;
    SlaveReader[] readers; // For no reason.

    // For storing the individual inter-thread problem communication queues.
    List<ConcurrentLinkedQueue<mathProblem>> mathProblemQueues;
    int NUMBER_OF_SLAVES = 2;

    public SlaveCommunicator(ServerSocket serverSocket, ConcurrentLinkedQueue<mathProblem> Problems, ConcurrentLinkedQueue<mathSolution> Solutions) {
        this.Problems = Problems;
        this.Solutions = Solutions;
        readers = new SlaveReader[NUMBER_OF_SLAVES];
        mathProblemQueues = new ArrayList<>();
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        connectToSlaves(serverSocket);
        while (true) {
            if (!Problems.isEmpty()) {
                sendProblemToSlave(Problems.poll());
            }
        }
    }

    private void sendProblemToSlave(mathProblem problem) {
        String operator = problem.operator();
        if (operator.equals("+")) {
            mathProblemQueues.get(0).add(problem);
        }
    }

    private void connectToSlaves(ServerSocket serverSocket) {
        for (int i = 0; i < NUMBER_OF_SLAVES; i++) {
            // sets up socket and starts the master

            try {
                Socket slaveSocket = serverSocket.accept();
                PrintWriter pw = new PrintWriter(slaveSocket.getOutputStream(), true);
                BufferedReader br = new BufferedReader(new InputStreamReader(slaveSocket.getInputStream()));
                ConcurrentLinkedQueue<mathProblem> problems = new ConcurrentLinkedQueue<>();

                //these are the threads, and we pass in the writer and reader respectively
                SlaveWriter writer = new SlaveWriter(pw, problems);
                writer.start();
                mathProblemQueues.add(problems);

                SlaveReader reader = new SlaveReader(br, Solutions);
                reader.start();

            } catch (IOException e) {
                //idk yet
            }
        }
    }
}
