package Server;

import Shared.mathProblem;

import java.io.*;
import java.util.concurrent.LinkedBlockingQueue;

public class SlaveWriter extends Thread {
    private PrintWriter pw;

    public SlaveWriter(PrintWriter pw, LinkedBlockingQueue<mathProblem> problems) {
        this.pw = pw;
    }

    // the point of the thread is to write to client
    public void run() {

        pw.println("Connected to Master.Master");

    }

}
