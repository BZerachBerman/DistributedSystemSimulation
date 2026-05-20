package Server;

import Shared.mathSolution;

import java.io.*;
import java.util.concurrent.LinkedBlockingQueue;

public class SlaveReader extends Thread {
    private BufferedReader br;
    private LinkedBlockingQueue<mathSolution> solutions;

    public SlaveReader(BufferedReader br, LinkedBlockingQueue<mathSolution> solutions) {
        this.br = br;
        this.solutions = solutions;
    }

    //This is the Master.Master reading from slave
    public void run() {
        System.out.println("SlaveReader is running");

        while (true) {
            try {
                String input = br.readLine();

                if (input == null) {
                    break;
                }

                System.out.println("Master received solution from slave: " + input);

                String[] parts = input.split(",");
                int problemID = Integer.parseInt(parts[0]);
                int solution = Integer.parseInt(parts[1]);

                solutions.add(new mathSolution(problemID, solution));

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
