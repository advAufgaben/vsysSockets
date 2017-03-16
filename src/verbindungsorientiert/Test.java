package verbindungsorientiert;

import java.io.IOException;

public class Test {
	
	public static void main(String[] args) throws IOException {
		ZahlenClient client = new ZahlenClient();
		client.sendUntilZero();
	}
	
}
