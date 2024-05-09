package BT1;

import java.io.*;
import java.net.*;

public class Server {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(12345);
			Socket clientSocket = serverSocket.accept();
			PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			BufferedReader serverInput = new BufferedReader(new InputStreamReader(System.in));

			String inputLine, serverInputLine;
			while ((inputLine = in.readLine()) != null) {
				System.out.println("Client: " + inputLine);
				serverInputLine = serverInput.readLine();
				out.println(serverInputLine);
			}

			out.close();
			in.close();
			serverInput.close();
			clientSocket.close();
			serverSocket.close();
		} catch (IOException e) {
			System.err.println("Could not listen on port 12345.");
			System.exit(1);
		}
	}
}
