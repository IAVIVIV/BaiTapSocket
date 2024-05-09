package BT1;

import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args) {
		try {
			Socket socket = new Socket("localhost", 12345);
			BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String userInputLine;
			while ((userInputLine = userInput.readLine()) != null) {
				out.println(userInputLine);
				System.out.println("Server: " + in.readLine());
			}

			out.close();
			in.close();
			userInput.close();
			socket.close();
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for the connection to localhost.");
			System.exit(1);
		}
	}
}
