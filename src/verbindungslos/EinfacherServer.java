package verbindungslos;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class EinfacherServer {

	private static final int	MTU = 65536;
	
	private static final int	ServerPort = 2000;

	private static int			DEBUG = 1;

	private DatagramSocket	Anbieter;

	public EinfacherServer(int port) {
		try {
			Anbieter = new DatagramSocket(port);
		} catch (SocketException e) {
			Anbieter = null;
		}
	}

	public void starten() {
		byte[]			Puffer;
		DatagramPacket	Daten;
		String			Text;
		InetAddress		Adresse;
		int				Port;
		if (DEBUG > 0) System.out.println("Server gestartet");
		while (true) {
			Puffer = new byte[MTU];
			Daten = new DatagramPacket(Puffer, MTU);
			try {
				this.Anbieter.receive(Daten);
			} catch (IOException e) { }
			Adresse = Daten.getAddress();
			Port = Daten.getPort();
			Text = new String(Daten.getData(), 0, Daten.getLength());
			System.out.print(Adresse + ":" + Port + " schreibt: ");
			System.out.println("\"" + Text + "\"");
			Text = new String("\n   * \n  / \\ \n /   \\ \n/_____\\ \n  | |");
			Puffer = Text.getBytes();
			Daten = new DatagramPacket(Puffer, Puffer.length, Adresse, Port);
			try {
				this.Anbieter.send(Daten);
			} catch (IOException e) { }
		}		
	}

	public static void main(String[] args) {
		EinfacherServer	Dienst = new EinfacherServer(EinfacherServer.ServerPort);
		if (Dienst.Anbieter != null) {
			if (DEBUG > 0) System.out.println("Socket ge�ffnet an Port " +
					EinfacherServer.ServerPort);
			Dienst.starten();
		}
	}

}
