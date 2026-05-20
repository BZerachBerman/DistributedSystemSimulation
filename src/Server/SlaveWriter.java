package Server;

import Shared.mathProblem;

import java.io.PrintWriter;
import java.util.concurrent.LinkedBlockingQueue;

public class SlaveWriter extends Thread {
    private PrintWriter pw;
    private LinkedBlockingQueue<mathProblem> problems;

    public SlaveWriter(PrintWriter pw, LinkedBlockingQueue<mathProblem> problems) {
        this.pw = pw;
        this.problems = problems;
    }

    // Sends mathProblems from the queue to the slave over the socket
    public void run() {
        System.out.println("SlaveWriter is running");

        while (true) {
            try {
                mathProblem problem = problems.take();

                String message = problem.problemID()
                        + "," + problem.left()
                        + "," + problem.operator()
                        + "," + problem.right();

                System.out.println("Master sending problem to slave: " + message);
                pw.println(message);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
