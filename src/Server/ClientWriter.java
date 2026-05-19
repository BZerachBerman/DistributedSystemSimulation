package Server;

import Shared.mathProblem;
import Shared.mathSolution;

import java.io.*;
import java.util.concurrent.LinkedBlockingQueue;

public class ClientWriter extends Thread {
    private PrintWriter pw;
    private LinkedBlockingQueue<mathSolution> solutions;

    public ClientWriter(PrintWriter pw, LinkedBlockingQueue<mathSolution> solutions) {
        this.pw = pw;
        this.solutions = solutions;
    }

    // the point of the thread is to write to client 
    public void run() {
        while (true) {
            try {
                int solution = solutions.take().solution();
                pw.println(solution);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
