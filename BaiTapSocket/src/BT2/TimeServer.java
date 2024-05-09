package BT2;

import java.io.*;
import java.net.*;
import java.util.*;
import java.text.*;

public class TimeServer {
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(12345);
			System.out.println("Server is running...");
			while (true) {
				Socket clientSocket = serverSocket.accept();
				new ServerThread(clientSocket).start();
			}
		} catch (IOException e) {
			System.err.println("Could not listen on port 12345.");
			System.exit(1);
		}
	}
}

class ServerThread extends Thread {
	private Socket socket;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		try {
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				if (inputLine.equals("time")) {
					SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
					Date date = new Date();
					out.println("Server time: " + formatter.format(date));
				}
			}

			out.close();
			in.close();
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
