package verbindungsorientiert;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ZahlenClient {
	private static int PORT_EINFACH = 2001;
	private static int PORT_MEHRFACH = 2002;
	private static String SERVER_IP = "10.1.202.1";
	
	private Socket einfachesSocket;
	private Socket mehrfachSocket;
	
	public int send(int number) throws IOException{
		einfachesSocket = new Socket(SERVER_IP, PORT_EINFACH);
		einfachesSocket.getOutputStream().write(number);
		System.out.println("sent: " + number);
		int answer = einfachesSocket.getInputStream().read();
		einfachesSocket.close();
		System.out.println("received: " + answer);
		return answer;
	}
	
	public int send(String text) throws IOException{
		einfachesSocket = new Socket(SERVER_IP, PORT_EINFACH);
		einfachesSocket.getOutputStream().write(text.getBytes());
		System.out.println("sent: " + text);
		int answer = einfachesSocket.getInputStream().read();
		einfachesSocket.close();
		System.out.println("received: " + answer);
		return answer;
	}
	
	public void sendUntilZero() throws UnknownHostException, IOException{
		Scanner scanner = new Scanner(System.in);
		int value = scanner.nextInt();
		mehrfachSocket = new Socket(SERVER_IP, PORT_MEHRFACH);
		OutputStream out = mehrfachSocket.getOutputStream();
		InputStream in = mehrfachSocket.getInputStream();
		while(value != 0){
			out.write(value);
			int answer = in.read();
			System.out.println("received: " + answer);
			value = scanner.nextInt();
		}
		mehrfachSocket.close();
		scanner.close();
	}
	
	public void spam(){
		
	}
	
}
