package meinserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MeinServer extends Thread{
	Socket socket;
	public MeinServer(Socket s){
		socket = s;
	}
	
	@Override
	public void run(){
		this.verarbeiten(socket);
	}
	
	public void verarbeiten(Socket s){
		//Verarbeitung
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException {
		ServerSocket S = new ServerSocket(2000);
		while(true){
			Socket H = S.accept();
			Thread t = new MeinServer(H);
			t.start();
		}
	}
	
}
