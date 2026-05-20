package Server;

import Shared.mathProblem;
import Shared.mathSolution;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * Slave that is optimized to doing subtraction quickly (2s) and addition slowly (10s).
 */
public class SubtractionSlave {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java Server.SubtractionSlave <host> <port>");
            return;
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
                Socket commSock = new Socket(hostName, portNumber);
                PrintWriter pw = new PrintWriter(commSock.getOutputStream(), true);
                BufferedReader br = new BufferedReader(new InputStreamReader(commSock.getInputStream()))
        ) {
            System.out.println("SubtractionSlave connected to Master");

            SubtractionSlave slave = new SubtractionSlave();

            while (true) {
                String input = br.readLine();

                if (input == null) {
                    break;
                }

                System.out.println("SubtractionSlave received problem: " + input);

                mathProblem problem = parseProblem(input);
                int answer = slave.solve(problem);
                mathSolution solution = new mathSolution(problem.problemID(), answer);

                String output = solution.problemID() + "," + solution.solution();
                System.out.println("SubtractionSlave sending solution: " + output);
                pw.println(output);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static mathProblem parseProblem(String input) {
        String[] parts = input.split(",");

        int problemID = Integer.parseInt(parts[0]);
        int left = Integer.parseInt(parts[1]);
        String operator = parts[2];
        int right = Integer.parseInt(parts[3]);

        return new mathProblem(problemID, left, operator, right);
    }

    public int solve(mathProblem problem) {
        if (problem.operator().equals("-")) {
            return subtract(problem.left(), problem.right());
        } else {
            return add(problem.left(), problem.right());
        }
    }

    private int subtract(int left, int right) {
        try {
            System.out.println("Sleeping for 2 seconds for optimal job type..., be back soon");
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return left - right;
    }

    private int add(int left, int right) {
        try {
            System.out.println("Sleeping for 10 seconds for non-optimal job type..., be back soon");
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return left + right;
    }
}