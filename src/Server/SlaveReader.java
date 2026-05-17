package Server;

import Shared.mathSolution;

import java.io.*;
import java.util.concurrent.LinkedBlockingQueue;

public class SlaveReader extends Thread {
    private BufferedReader br;
    LinkedBlockingQueue<mathSolution> solutions;

    public SlaveReader(BufferedReader br, LinkedBlockingQueue<mathSolution> solutions) {
        this.br = br;
        this.solutions = solutions;
    }

    //This is the Master.Master reading from slave
    public void run() {


    }

}
