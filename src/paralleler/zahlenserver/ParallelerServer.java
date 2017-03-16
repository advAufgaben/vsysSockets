package paralleler.zahlenserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ParallelerServer {
	
	private static final int PORT = 2000;
	
	private ServerSocket server;
	
	public ParallelerServer() throws IOException{
		server = new ServerSocket(PORT);
		System.out.println("Server wartet auf Verbindung");
		while(true){
			final Socket verbindung = server.accept();
			new Thread(() ->{
				try {
					System.out.println("Verbindung hergestellt");
					int input;
					input = verbindung.getInputStream().read();
					System.out.println("Input: " + input);
					int output = input + 5;
					System.out.println("Output: " + output);
					verbindung.getOutputStream().write(output);
					verbindung.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
	
}
