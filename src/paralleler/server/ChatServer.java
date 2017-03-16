package paralleler.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ChatServer {
	private static final int PORT = 2000;
	private ServerSocket server;
	private List<Socket> clients;
	
	public ChatServer() throws IOException{
		super();
		server = new ServerSocket(PORT);
		while(true){
			Socket socket = server.accept();
			new ChatThread(socket, clients).start();
		}
	}
	
	public static class ChatThread extends Thread{
		private Socket socket;
		private List<Socket> clients;
		
		public ChatThread(Socket socket, List<Socket> clients){
			super();
			this.socket = socket;
			this.clients = clients;
			synchronized(clients){
				this.clients.add(socket);
			}
		}
		
		@Override
		public void run(){
			try {
				while(true){
					ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
					String message = (String) input.readObject();
					String chatOutput = socket.getInetAddress().getHostAddress() + " says: " + message;
					System.out.println(chatOutput);
					this.sendToAll(chatOutput);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		private void sendToAll(String message){
			for(Socket client : clients){
				if(client != this.socket){
					try(ObjectOutputStream output = new ObjectOutputStream(client.getOutputStream())){
						output.writeObject(message);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
