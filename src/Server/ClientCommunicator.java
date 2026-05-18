package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

import Shared.mathProblem;
import Shared.mathSolution;


public class ClientCommunicator implements Runnable {
    LinkedBlockingQueue<mathProblem> Problems;
    LinkedBlockingQueue<mathSolution> Solutions;
    ServerSocket serverSocket;

    public ClientCommunicator(ServerSocket serverSocket, LinkedBlockingQueue<mathProblem> problems, LinkedBlockingQueue<mathSolution> solutions) {
        this.serverSocket = serverSocket;
        this.Problems = problems;
        this.Solutions = solutions;
    }

    @Override
    public void run() {

        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                System.out.println("[DIAGNOSTIC] A client has connected! Setting up streams...");
                PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                //these are the threads, and we pass in the writer and reader respectively
                System.out.println("[DIAGNOSTIC] Streams created. About to start ClientWriter...");
                new ClientWriter(pw).start();
                System.out.println("ClientWriter started successfully. About to start ClientReader...");
                new ClientReader(br).start();

            } catch (IOException e) {
                //idk yet
            }
        }
    }
}
