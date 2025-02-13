import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

class Backup implements Runnable {
	holder hotel_ob;

	Backup(holder hotel_ob) {
		this.hotel_ob = hotel_ob;
	}

	@Override
	public void run() {
		try {
			FileOutputStream fout = new FileOutputStream("backup");
			try (ObjectOutputStream oos = new ObjectOutputStream(fout)) {
				oos.writeObject(hotel_ob);
			}
		} catch (Exception e) {
			System.out.println("Error in writing " + e);
		}

	}

}