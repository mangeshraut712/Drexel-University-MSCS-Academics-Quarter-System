import java.util.Scanner;

class Hotel {
	static holder hotel_ob = new holder();
	static Scanner scanner = new Scanner(System.in);

	static void personalDetails(int i, int rn) {
		String name, contact, gender, email;
		String personname = null, personcontact = null;
		String persongender = "", personemail = "";
		System.out.print("\nEnter customer name: ");
		name = scanner.next();
		System.out.print("Enter contact number: ");
		contact = scanner.next();
		System.out.print("Enter gender: ");
		gender = scanner.next();
		System.out.print("Enter Email: ");
		email = scanner.next();
		if (i < 3) {
			System.out.print("Enter second customer name: ");
			personname = scanner.next();
			System.out.print("Enter contact number: ");
			personcontact = scanner.next();
			System.out.print("Enter gender: ");
			persongender = scanner.next();
			System.out.print("Enter Email: ");
			personemail = scanner.next();
		}

		switch (i) {
		case 1:
			hotel_ob.luxury_doublerrom[rn] = new Doubleroom(name, contact, gender, email, personname, personcontact,
					persongender, personemail);
			break;
		case 2:
			hotel_ob.deluxe_doublerrom[rn] = new Doubleroom(name, contact, gender, email, personname, personcontact,
					persongender, personemail);
			break;
		case 3:
			hotel_ob.luxury_singleerrom[rn] = new Singleroom(name, contact, gender, email);
			break;
		case 4:
			hotel_ob.deluxe_singleerrom[rn] = new Singleroom(name, contact, gender, email);
			break;
		default:
			System.out.println("Wrong option");
			break;
		}
	}

	static void booking(int i) {
		int j, k;
		System.out.println("\nChoose room number from : ");
		switch (i) {
		case 1:
			for (j = 0; j < hotel_ob.luxury_doublerrom.length; j++) {
				if (hotel_ob.luxury_doublerrom[j] == null) {
					System.out.print(j + 1 + ",");
				}
			}
			System.out.print("\nEnter room number: ");
			try {
				k = scanner.nextInt();
				k--;
				if (hotel_ob.luxury_doublerrom[k] != null)
					throw new Exception();
				personalDetails(i, k);
			} catch (Exception e) {
				System.out.println("Invalid Option");
				return;
			}
			break;
		case 2:
			for (j = 0; j < hotel_ob.deluxe_doublerrom.length; j++) {
				if (hotel_ob.deluxe_doublerrom[j] == null) {
					System.out.print(j + 11 + ",");
				}
			}
			System.out.print("\nEnter room number: ");
			try {
				k = scanner.nextInt();
				k = k - 11;
				if (hotel_ob.deluxe_doublerrom[k] != null)
					throw new Exception();
				personalDetails(i, k);
			} catch (Exception e) {
				System.out.println("Invalid Option");
				return;
			}
			break;
		case 3:
			for (j = 0; j < hotel_ob.luxury_singleerrom.length; j++) {
				if (hotel_ob.luxury_singleerrom[j] == null) {
					System.out.print(j + 31 + ",");
				}
			}
			System.out.print("\nEnter room number: ");
			try {
				k = scanner.nextInt();
				k = k - 31;
				if (hotel_ob.luxury_singleerrom[k] != null)
					throw new Exception();
				personalDetails(i, k);
			} catch (Exception e) {
				System.out.println("Invalid Option");
				return;
			}
			break;
		case 4:
			for (j = 0; j < hotel_ob.deluxe_singleerrom.length; j++) {
				if (hotel_ob.deluxe_singleerrom[j] == null) {
					System.out.print(j + 41 + ",");
				}
			}
			System.out.print("\nEnter room number: ");
			try {
				k = scanner.nextInt();
				k = k - 41;
				if (hotel_ob.deluxe_singleerrom[k] != null)
					throw new Exception();
				personalDetails(i, k);
			} catch (Exception e) {
				System.out.println("Invalid Option");
				return;
			}
			break;
		default:
			System.out.println("Enter valid option");
			break;
		}
		System.out.println("Room Booked");
	}

	static void features(int i) {
		switch (i) {
		case 1:
			System.out.println("Number of double beds : 1\nAC : Yes\nFree breakfast : Yes\nCharge per day:4000 ");
			break;
		case 2:
			System.out.println("Number of double beds : 1\nAC : No\nFree breakfast : Yes\nCharge per day:3000  ");
			break;
		case 3:
			System.out.println("Number of single beds : 1\nAC : Yes\nFree breakfast : Yes\nCharge per day:2000  ");
			break;
		case 4:
			System.out.println("Number of single beds : 1\nAC : No\nFree breakfast : Yes\nCharge per day:1000 ");
			break;
		default:
			System.out.println("Enter valid option");
			break;
		}
	}

	static void availability(int i) {
		int j, count = 0;
		switch (i) {
		case 1:
			for (j = 0; j < 10; j++) {
				if (hotel_ob.luxury_doublerrom[j] == null)
					count++;
			}
			break;
		case 2:
			for (j = 0; j < hotel_ob.deluxe_doublerrom.length; j++) {
				if (hotel_ob.deluxe_doublerrom[j] == null)
					count++;
			}
			break;
		case 3:
			for (j = 0; j < hotel_ob.luxury_singleerrom.length; j++) {
				if (hotel_ob.luxury_singleerrom[j] == null)
					count++;
			}
			break;
		case 4:
			for (j = 0; j < hotel_ob.deluxe_singleerrom.length; j++) {
				if (hotel_ob.deluxe_singleerrom[j] == null)
					count++;
			}
			break;
		default:
			System.out.println("Enter valid option");
			break;
		}
		System.out.println("Number of rooms available : " + count);
	}

	static void billing(int i, int j) {
		double amount = 0;
		String list[] = { "Sandwich", "Pasta", "Noodles", "Salad" };
		System.out.println("\n*****************");
		System.out.println(" Billing Amount:-");
		System.out.println("*****************");

		switch (j) {
		case 1:
			amount += 4000;
			System.out.println("\nRoom Charge - " + 4000);
			System.out.println("\n===============");
			System.out.println("Food Charges:- ");
			System.out.println("===============");
			System.out.println("Item   Quantity    Price");
			System.out.println("-------------------------");
			for (Food obb : hotel_ob.luxury_doublerrom[i].food) {
				amount += obb.price;
				String format = "%-10s%-10s%-10s%n";
				System.out.printf(format, list[obb.itemno - 1], obb.quantity, obb.price);
			}

			break;
		case 2:
			amount += 3000;
			System.out.println("Room Charge - " + 3000);
			System.out.println("\nFood Charges:- ");
			System.out.println("===============");
			System.out.println("Item   Quantity    Price");
			System.out.println("-------------------------");
			for (Food obb : hotel_ob.deluxe_doublerrom[i].food) {
				amount += obb.price;
				String format = "%-10s%-10s%-10s%n";
				System.out.printf(format, list[obb.itemno - 1], obb.quantity, obb.price);
			}
			break;
		case 3:
			amount += 2000;
			System.out.println("Room Charge - " + 2000);
			System.out.println("\nFood Charges:- ");
			System.out.println("===============");
			System.out.println("Item   Quantity    Price");
			System.out.println("-------------------------");
			for (Food obb : hotel_ob.luxury_singleerrom[i].food) {
				amount += obb.price;
				String format = "%-10s%-10s%-10s%n";
				System.out.printf(format, list[obb.itemno - 1], obb.quantity, obb.price);
			}
			break;
		case 4:
			amount += 1000;
			System.out.println("Room Charge - " + 1000);
			System.out.println("\nFood Charges:- ");
			System.out.println("===============");
			System.out.println("Item   Quantity    Price");
			System.out.println("-------------------------");
			for (Food obb : hotel_ob.deluxe_singleerrom[i].food) {
				amount += obb.price;
				String format = "%-10s%-10s%-10s%n";
				System.out.printf(format, list[obb.itemno - 1], obb.quantity, obb.price);
			}
			break;
		default:
			System.out.println("Not valid");
		}
		System.out.println("\nTotal Amount- " + amount);
	}

	static void Checkout(int i, int j) {
		char w;
		switch (j) {
		case 1:
			if (hotel_ob.luxury_doublerrom[i] != null)
				System.out.println("Room used by " + hotel_ob.luxury_doublerrom[i].name);
			else {
				System.out.println("Empty Already");
				return;
			}
			System.out.println("Do you want to checkout ?(y/n)");
			w = scanner.next().charAt(0);
			if (w == 'y' || w == 'Y') {
				billing(i, j);
				hotel_ob.luxury_doublerrom[i] = null;
				System.out.println("Deallocated succesfully");
			}

			break;
		case 2:
			if (hotel_ob.deluxe_doublerrom[i] != null)
				System.out.println("Room used by " + hotel_ob.deluxe_doublerrom[i].name);
			else {
				System.out.println("Empty Already");
				return;
			}
			System.out.println(" Do you want to checkout ?(y/n)");
			w = scanner.next().charAt(0);
			if (w == 'y' || w == 'Y') {
				billing(i, j);
				hotel_ob.deluxe_doublerrom[i] = null;
				System.out.println("Deallocated succesfully");
			}

			break;
		case 3:
			if (hotel_ob.luxury_singleerrom[i] != null)
				System.out.println("Room used by " + hotel_ob.luxury_singleerrom[i].name);
			else {
				System.out.println("Empty Already");
				return;
			}
			System.out.println(" Do you want to checkout ? (y/n)");
			w = scanner.next().charAt(0);
			if (w == 'y' || w == 'Y') {
				billing(i, j);
				hotel_ob.luxury_singleerrom[i] = null;
				System.out.println("Deallocated succesfully");
			}

			break;
		case 4:
			if (hotel_ob.deluxe_singleerrom[i] != null)
				System.out.println("Room used by " + hotel_ob.deluxe_singleerrom[i].name);
			else {
				System.out.println("Empty Already");
				return;
			}
			System.out.println(" Do you want to checkout ? (y/n)");
			w = scanner.next().charAt(0);
			if (w == 'y' || w == 'Y') {
				billing(i, j);
				hotel_ob.deluxe_singleerrom[i] = null;
				System.out.println("Deallocated succesfully");
			}
			break;
		default:
			System.out.println("\nEnter valid option : ");
			break;
		}
	}

	static void foodOrder(int i, int j) {
		int k, l;
		char c;
		try {
			System.out.println(
					"\n==========\n   Menu:  \n==========\n\n1.Sandwich\t$10\n2.Pasta\t\t$15\n3.Noodles\t$20\n4.Salad\t\t$25\n");
			do {
				k = scanner.nextInt();
				System.out.print("Quantity- ");
				l = scanner.nextInt();

				switch (j) {
				case 1:
					hotel_ob.luxury_doublerrom[i].food.add(new Food(k, l));
					break;
				case 2:
					hotel_ob.deluxe_doublerrom[i].food.add(new Food(k, l));
					break;
				case 3:
					hotel_ob.luxury_singleerrom[i].food.add(new Food(k, l));
					break;
				case 4:
					hotel_ob.deluxe_singleerrom[i].food.add(new Food(k, l));
					break;
				}
				System.out.println("Do you want to order anything else ? (y/n)");
				c = scanner.next().charAt(0);
			} while (c == 'y' || c == 'Y');
		} catch (NullPointerException e) {
			System.out.println("\nRoom not booked");
		} catch (Exception e) {
			System.out.println("Cannot be done");
		}
	}
}