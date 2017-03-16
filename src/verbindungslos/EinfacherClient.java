package verbindungslos;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class EinfacherClient {


	private static final int	MTU = 65536;
	
	private static final int	ServerPort = 2000;

	private DatagramSocket		Verbindung;

	public EinfacherClient() {
		try {
			Verbindung = new DatagramSocket();
		} catch (SocketException e) {
			Verbindung = null;
		}
	}

	public void starten() {
		byte[]			Puffer;
		DatagramPacket	Paket;
		int				Port = EinfacherClient.ServerPort;
		InetAddress 	Adresse;
		String			Text;
		try {
			Adresse = InetAddress.getByName("10.1.202.1");
		} catch (UnknownHostException e) {
			Adresse = null;
		}
		Puffer = (new String("Hallo")).getBytes();
		if (Adresse != null) {
			Paket = new DatagramPacket(Puffer, Puffer.length,
					Adresse, Port);
			try {
				this.Verbindung.send(Paket);
				System.out.println("Daten gesendet an " + Adresse + ":" + Port);
			} catch (IOException e) {
				System.out.println("Fehler beim Senden an " + Adresse + ":" + Port);
			}
		}
		Puffer = new byte[MTU];
		Paket = new DatagramPacket(Puffer, Puffer.length);
		try {
			this.Verbindung.receive(Paket);
			Text = new String(Paket.getData(), 0, Paket.getLength());
			System.out.println("Empfangene Daten: " + Text);
		} catch (IOException e) {
			System.out.println("Fehler beim Empfangen");
		}
		this.Verbindung.close();
	}

	public static void main(String[] args) {
		EinfacherClient	Kunde = new EinfacherClient();
		if (Kunde.Verbindung != null) {
			System.out.println("Client wird gestartet an Port " + Kunde.Verbindung.getLocalPort());
			Kunde.starten();
		}
	}

}
