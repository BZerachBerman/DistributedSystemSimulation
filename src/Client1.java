import java.util.Objects;
import java.net.*;
import java.io.*;
import java.util.Collections;
import java.util.ArrayList;

public class Client1 {
    mathProblem[] mathProblems;
    int nextProblem;
    
    public static void main(String args[]) {
    	
    	//make sure user enters two arg(portnumber and localhost or ip)
    	if(args.length != 2) {
			System.err.println("Usage: java EchoClient <host name> <port number>");
			System.exit(1);
		}
    	
    	String hostName = args[0]; // should be localhost
    	int portNumber = Integer.parseInt(args[1]); // portnumber of master
    	
    	// sets up socket and the reader and writers for the client 
    	try (
    			Socket socket = new Socket(hostName, portNumber);
				PrintWriter pw =  new PrintWriter(socket.getOutputStream(), true);
		        BufferedReader br =  new BufferedReader(new InputStreamReader(socket.getInputStream())); 
		        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    		) 
    	
    	{
    		//code of client starts here (will accept input from user and then send to master)
    		System.out.println(br.readLine());
    		
    		
    	} catch(IOException e) {
    		//idk yet
    	}
    	
    }
    
    Client1() {
        nextProblem = 0;
        mathProblems = new mathProblem[10];
        int randInt1;
        int randInt2;

        //Create 10 new math problems.
        for(int i = 0; i < 10; i++) {
            randInt1 = (int) (Math.random() * 10);
            randInt2 = (int) (Math.random() * 10);
            mathProblems[i] = new mathProblem(randInt1, randInt2, Math.random() < 0.5 ? "addition" : "subtraction");
        }
    }

    //Return the next math problem
    public mathProblem getMathProblem() {
        int currentProblem = nextProblem;
        nextProblem = currentProblem + 1 % mathProblems.length;
        return mathProblems[currentProblem];
    }
}
