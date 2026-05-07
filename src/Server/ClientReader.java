package Server;

import Shared.mathProblem;

import java.io.*;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClientReader extends Thread {
	private BufferedReader br;
	
	public ClientReader(BufferedReader br) {
		this.br = br;
	}
	
	//This is the Master.Master reading from client
	public void run() {
		System.out.println("ClientReader is running");
		String input = "3 + 5";
		Pattern pattern = Pattern.compile("(\\d+)\\s*([+\\-*/])\\s*(\\d+)");
		Matcher matcher = pattern.matcher(input);
		int left = 0;
		String operator = "";
		int right = 0;
		if (matcher.find()) {
			left = Integer.parseInt(matcher.group(1));  // "3"
			operator = matcher.group(2);                  // "+"
			right = Integer.parseInt(matcher.group(3)); // "5"
		}
		int ID = 12;
		mathProblem problem = new mathProblem(ID, left, operator, right);
	}
}
