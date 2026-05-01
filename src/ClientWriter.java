import java.util.Objects;
import java.net.*;
import java.io.*;
import java.util.Collections;
import java.util.ArrayList;

public class ClientWriter extends Thread {
	private PrintWriter pw;
	
	public ClientWriter( PrintWriter pw) {
		this.pw = pw;
	}
	
    // the point of the thread is to write to client 
	public void run() {
		
			pw.println("Connected to Master");
		
	}

}
