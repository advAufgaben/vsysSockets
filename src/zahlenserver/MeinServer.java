package zahlenserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MeinServer {
	
	private static final int PORT = 2000;
	
	private ServerSocket server;
	
	public MeinServer() throws IOException{
		server = new ServerSocket(PORT);
		System.out.println("Server wartet auf Verbindung");
		while(true){
			Socket verbindung = server.accept();
			System.out.println("Verbindung hergestellt");
			int input = verbindung.getInputStream().read();
			System.out.println("Input: " + input);
			int output = input;
			System.out.println("Output: " + output);
			verbindung.getOutputStream().write(output);
			verbindung.close();
		}
		//		server.close();
	}
	
}
