package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentLinkedQueue;

import Shared.mathProblem;
import Shared.mathSolution;


public class ClientCommunicator implements Runnable {
    ConcurrentLinkedQueue<mathProblem> Problems;
    ConcurrentLinkedQueue<mathSolution> Solutions;
    ServerSocket serverSocket;

    public ClientCommunicator(ServerSocket serverSocket, ConcurrentLinkedQueue<mathProblem> problems, ConcurrentLinkedQueue<mathSolution> solutions) {
        this.serverSocket = serverSocket;
        this.Problems = problems;
        this.Solutions = solutions;
    }

    @Override
    public void run() {

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                //these are the threads, and we pass in the writer and reader respectively
                new ClientWriter(pw).start();
                new ClientReader(br).start();

            } catch (IOException e) {
                //idk yet
            }
        }
    }

    public mathProblem getNextProblem() {
        return Problems.poll();
    }

    public void send(mathSolution solution) {

    }
}
