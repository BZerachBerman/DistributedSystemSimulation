import java.util.Objects;
import java.net.*;
import java.io.*;
import java.util.Collections;
import java.util.ArrayList;

public class Master {
    AdditionCalculator additionCalculator;
    SubtractionCalculator subtractionCalculator;
    Client1 client1;
    Client2 client2;
    static final int  CLIENTS = 2;

    public static void main(String[] args) {
    	
    	//makes sure user enters valid portnumber
    	if (args.length != 1) {
			System.err.print("\"Usage: java EchoServer <port number>\"");
			System.exit(1);
		}
    	int portNumber = Integer.parseInt(args[0]);    	
    	
    	// sets up socket and starts the master
    	try ( ServerSocket masterSocket = new ServerSocket(portNumber); ) {
    		IO.print("Master Running...");
    		
    		// will keep accepting clients and set up sockets and threads for them
    		 while (true) {
    			 try {
    				 
    				 Socket clientSocket = masterSocket.accept();
    				 PrintWriter pw = new PrintWriter(clientSocket.getOutputStream(), true);
    				 BufferedReader br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    				 
    				 //these are the threads and we pass in the writer and reader respectivley 
    				 new ClientWriter(pw).start();
    				 new ClientReader(br).start();
    				 
    			 } catch(IOException e) {
    				 //idk yet
    			 }


    			 
    		 }
    			
    		
    	 } catch(IOException e) {
    		 //idk yet 
    	 }
    	
    	
    }
    
    public void start() {
        client1 = new Client1();
        client2 = new Client2();
        additionCalculator = new AdditionCalculator();
        subtractionCalculator = new SubtractionCalculator();
        int solution;

        for (int i = 1; i <= 10; i++) {
            //Instantiate two new problems.
            mathProblem problem1 = client1.getMathProblem();
            mathProblem problem2 = client2.getMathProblem();

            //Use basic algorithm to determine which calculator to use.
            if (Objects.equals(problem1.operation(), "addition")) {
                solution = additionCalculator.solve(problem1);
            } else solution = subtractionCalculator.solve(problem1);
            System.out.println(solution);

            //Do this a second time for the second problem.
            if (Objects.equals(problem2.operation(), "addition")) {
                solution = additionCalculator.solve(problem2);
            } else solution = subtractionCalculator.solve(problem2);
            System.out.println(solution);
        }
    }
}
