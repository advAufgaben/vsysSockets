package verbindungslos;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ArtikelClient {
	
	public static void main(String[] args) throws Exception {
		DatagramSocket	socket = new DatagramSocket();
		Artikel			artikel = new Artikel(1, "test", 20.0);
		/* Diesen Artikel an den Server 10.1.202.1:2000 senden */
		byte[] buffer = new byte[65536];
		buffer = artikel.serialize();
		InetAddress server = InetAddress.getByName("10.1.202.1");
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length, server, 2000);
		socket.send(packet);
		/* Vom Server einen neuen Artikel empfangen */
		DatagramPacket receivedPacket = new DatagramPacket(buffer, buffer.length);
		socket.receive(receivedPacket);
		/* Diesen Artikel ausgeben */
		artikel = Artikel.deserialize(receivedPacket.getData(), receivedPacket.getLength());
		artikel.ausgeben();
		
		socket.close();
	}
	
}
