import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;

public class HotelTester {
	public static void main(String[] args) {
		try {
			File file = new File("backup");
			if (file.exists()) {
				FileInputStream fin = new FileInputStream(file);
				try (ObjectInputStream ois = new ObjectInputStream(fin)) {
					Hotel.hotel_ob = (holder) ois.readObject();
				}
			}
			Scanner scanner = new Scanner(System.in);
			int i, j;
			char c;
			System.out.println("*** Welcome to Hotel Management System ***");
			System.out.println("*** DREXEL UNIVERSITY CCI CS-501 PROJECT 2 ***");
			run: do {

				System.out.println(
						"\nEnter your choice :\n1.Display room details\n2.Display room availability \n3.Book\n4.Order food\n5.Checkout\n6.Exit\n");
				i = scanner.nextInt();
				switch (i) {
				case 1:
					System.out.println(
							"\nChoose room type :\n1.Luxury Double Room \n2.Deluxe Double Room \n3.Luxury Single Room \n4.Deluxe Single Room\n");
					j = scanner.nextInt();
					Hotel.features(j);
					break;
				case 2:
					System.out.println(
							"\nChoose room type :\n1.Luxury Double Room \n2.Deluxe Double Room \n3.Luxury Single Room\n4.Deluxe Single Room\n");
					j = scanner.nextInt();
					Hotel.availability(j);
					break;
				case 3:
					System.out.println(
							"\nChoose room type :\n1.Luxury Double Room \n2.Deluxe Double Room \n3.Luxury Single Room\n4.Deluxe Single Room\n");
					j = scanner.nextInt();
					Hotel.booking(j);
					break;
				case 4:
					System.out.print("Room Number -");
					j = scanner.nextInt();
					if (j > 60)
						System.out.println("Room doesn't exist");
					else if (j > 40)
						Hotel.foodOrder(j - 41, 4);
					else if (j > 30)
						Hotel.foodOrder(j - 31, 3);
					else if (j > 10)
						Hotel.foodOrder(j - 11, 2);
					else if (j > 0)
						Hotel.foodOrder(j - 1, 1);
					else
						System.out.println("Room doesn't exist");
					break;
				case 5:
					System.out.print("Room Number -");
					j = scanner.nextInt();
					if (j > 60)
						System.out.println("Room doesn't exist");
					else if (j > 40)
						Hotel.Checkout(j - 41, 4);
					else if (j > 30)
						Hotel.Checkout(j - 31, 3);
					else if (j > 10)
						Hotel.Checkout(j - 11, 2);
					else if (j > 0)
						Hotel.Checkout(j - 1, 1);
					else
						System.out.println("Room doesn't exist");
					break;
				case 6:
					break run;

				}

				System.out.println("\nContinue : (y/n)");
				c = scanner.next().charAt(0);
				if (!(c == 'y' || c == 'Y' || c == 'n' || c == 'N')) {
					System.out.println("Invalid Option");
					System.out.println("\nContinue : (y/n)");
					c = scanner.next().charAt(0);
				}

			} while (c == 'y' || c == 'Y');

			Thread t = new Thread(new Backup(Hotel.hotel_ob));
			t.start();
		} catch (Exception e) {
			System.out.println("Not a valid input");
		}
	}
}