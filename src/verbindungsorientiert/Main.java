package verbindungsorientiert;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

@SuppressWarnings("unused")
public class Main {
	
	private static final String SERVER_IP = "10.1.202.1";
	private static final int SERVER_PORT = 2000;
	
	public static void main(String[] args) throws Exception{
		//		sendArtikel();
		spam(50);
	}
	
	private static void sendArtikel() throws UnknownHostException, IOException, ClassNotFoundException {
		Socket socket = new Socket(SERVER_IP, SERVER_PORT);
		ObjectOutputStream outgoing = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream incoming = new ObjectInputStream(socket.getInputStream());
		
		outgoing.writeObject(new Artikel(1337, "Bastian was here", 1.0));
		Artikel artikel = (Artikel) incoming.readObject();
		
		socket.close();
		artikel.ausgeben();
	}
	
	public static void spam(int anzahl){
		for(int i = 0; i < anzahl; i++){
			Socket socket;
			try {
				socket = new Socket(SERVER_IP, SERVER_PORT);
				ObjectOutputStream outgoing = new ObjectOutputStream(socket.getOutputStream());
				outgoing.writeObject(new Artikel(1, "Hormel-Frühstücksfleisch Spam", 1.0));
				ObjectInputStream incoming = new ObjectInputStream(socket.getInputStream());
				incoming.readObject();
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
