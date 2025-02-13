import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class Client {
	public static void main(String args[]) throws ClassNotFoundException {
		Socket socket = null;
		ObjectInputStream objectInputStream = null;
		ObjectOutputStream objectOutputStream = null;
		try {
			socket = new Socket("localhost", 1234);
			ArrayList<String> lines = new ArrayList<>();
			lines.add("Descent of Man");
			lines.add("The Ascent of Man");
			lines.add("The Old Man and The Sea");
			lines.add("A Portrait of The Artist As a Young Man");
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			System.out.println("Lines sent to server " + lines);
			objectOutputStream.writeObject(lines);

			objectInputStream = new ObjectInputStream(socket.getInputStream());
			ArrayList<String> result = (ArrayList<String>) objectInputStream.readObject();
			System.out.println("lines received from server");
			for (String finalResult : result) {
				System.out.println(finalResult);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

				socket.close();
				objectInputStream.close();
				objectOutputStream.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
