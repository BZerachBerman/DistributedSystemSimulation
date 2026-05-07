package Server;

import Shared.mathSolution;

import java.io.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class SlaveReader extends Thread {
    private BufferedReader br;
    ConcurrentLinkedQueue<mathSolution> solutions;

    public SlaveReader(BufferedReader br, ConcurrentLinkedQueue<mathSolution> solutions) {
        this.br = br;
        this.solutions = solutions;
    }

    //This is the Master.Master reading from slave
    public void run() {


    }

}
