import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		Socket socket = null;
		ObjectInputStream objectInputStream = null;
		ObjectOutputStream objectOutputStream = null;
		ServerSocket serverSocket = null;
		serverSocket = new ServerSocket(1234);
		while (true) {
			try {
				System.out.println("Waiting for connection...");
				socket = serverSocket.accept();

				while (true) {
					try {

					} catch (Exception e) {
						e.printStackTrace();
					}
					objectInputStream = new ObjectInputStream(socket.getInputStream());
					MasterControl masterControl = new MasterControl();
					ArrayList<String> lines = (ArrayList) objectInputStream.readObject();
					System.out.println("Message received from client! " + lines);
					ArrayList<String> serverResponse = (ArrayList<String>) masterControl.start(lines);
					objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
					objectOutputStream.writeObject(serverResponse);
					break;

				}
				socket.close();
				objectInputStream.close();
				objectOutputStream.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
