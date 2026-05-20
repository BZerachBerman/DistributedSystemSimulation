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
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A Runnable class responsible for managing all communication to and from all server Slaves.
 * This includes connecting to the slaves, assigning them a SlaveReader and a SlaveWriter, and scheduling.
 */
public class SlaveCommunicator implements Runnable {
    LinkedBlockingQueue<mathProblem> Problems; //For sharing math problems with Master
    LinkedBlockingQueue<mathSolution> Solutions; //Shared by all the SlaveReader Threads and Master
    ServerSocket serverSocket;
    SlaveReader[] readers; // For no reason.

    // For storing the individual inter-thread problem communication queues.
    List<LinkedBlockingQueue<mathProblem>> mathProblemQueueList;
    int NUMBER_OF_SLAVES = 2;

    //When first instantiated, SlaveCommunicator connects with slaves, spins up Reader and Writer threads,
    // and passes them slaves to communicate with.
    public SlaveCommunicator(ServerSocket serverSocket, LinkedBlockingQueue<mathProblem> Problems, LinkedBlockingQueue<mathSolution> Solutions) {
        this.Problems = Problems;
        this.Solutions = Solutions;
        readers = new SlaveReader[NUMBER_OF_SLAVES];
        mathProblemQueueList = new ArrayList<>();
        this.serverSocket = serverSocket;
        //NOTE: I chose to put this in the constructor in order to prevent Master
        //from connecting with clients until all the slaves are booted up.
        //This is not a final decision.
        connectToSlaves(serverSocket);

    }

    //The Run() method is just for scheduling; everything else(mathSolutions) pass straight through SlaveCommunicator.
    @Override
    public void run() {
        while (true) {
            try {
                // This will block (wait) automatically until an item is available
                mathProblem nextProblem = Problems.take();
                sendProblemToSlave(nextProblem);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt(); // Cleanly handle thread shutdown
            }
        }
    }

    private void sendProblemToSlave(mathProblem problem) {
        String operator = problem.operator();

        if (operator.equals("+")) {
            System.out.println("SlaveCommunicator assigning addition problem to AdditionSlave queue");
            mathProblemQueueList.get(0).add(problem);
        } else if (operator.equals("-")) {
            System.out.println("SlaveCommunicator assigning subtraction problem to SubtractionSlave queue");
            mathProblemQueueList.get(1).add(problem);
        } else {
            System.out.println("Unknown operator. Defaulting problem to first slave queue.");
            mathProblemQueueList.get(0).add(problem);
        }
    }


    private void connectToSlaves(ServerSocket serverSocket) {
        for (int i = 0; i < NUMBER_OF_SLAVES; i++) {
            // sets up socket and starts the master

            try {
                Socket slaveSocket = serverSocket.accept();
                PrintWriter pw = new PrintWriter(slaveSocket.getOutputStream(), true);
                BufferedReader br = new BufferedReader(new InputStreamReader(slaveSocket.getInputStream()));
                LinkedBlockingQueue<mathProblem> problems = new LinkedBlockingQueue<>();

                //these are the threads, and we pass in the writer and reader respectively
                SlaveWriter writer = new SlaveWriter(pw, problems);
                writer.start();
                mathProblemQueueList.add(problems);

                SlaveReader reader = new SlaveReader(br, Solutions);
                reader.start();

            } catch (IOException e) {
                //idk yet
            }
        }
    }
}
