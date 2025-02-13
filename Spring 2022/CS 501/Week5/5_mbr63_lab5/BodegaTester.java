import java.time.LocalDate;

public class BodegaTester {
	public static void main(String args[]) {
		Bodega b1 = new Bodega();
		Bodega b2 = new Bodega();
		Bodega b3 = new Bodega("Nike", 100.0, "LA", 50, 25, LocalDate.parse("2022-05-15"));
		Bodega b4 = new Bodega("Addidas", 150.0, "PA", 40, 20, LocalDate.parse("2022-05-04"));

		Food f1 = new Food();

		b1.setBrandname("Fila");
		b1.getBrandname();
		b1.setPrice(50.0);
		b1.getPrice();
		b1.setDSQ(15);
		b1.getDSQ();
		b1.setASQ(10);
		b1.getASQ();
		b1.setLocation("LA");
		b1.getLocation();

		b2.setBrandname("BATA");
		b2.getBrandname();
		b2.setPrice(70.0);
		b2.getPrice();
		b2.setDSQ(25);
		b2.getDSQ();
		b2.setASQ(40);
		b2.getASQ();
		b2.setLocation("PA");
		b2.getLocation();

		System.out.println("Items in this List\n" + f1.getItems());
		System.out.println("\n" + b1.getBrandname() + "\n" + b1.getPrice() + "\n" + b1.getASQ() + "\n" + b1.getDSQ()
				+ "\n" + b1.getLocation() + "\n");
		System.out.println(b2.getBrandname() + "\n" + b2.getPrice() + "\n" + b2.getASQ() + "\n" + b2.getDSQ() + "\n"
				+ b2.getLocation() + "\n");
		System.out.println(b3.getBrandname() + "\n" + b3.getPrice() + "\n" + b3.getASQ() + "\n" + b3.getDSQ() + "\n"
				+ b3.getLocation() + "\n" + "Expired Status :" + b3.hasExpiredFood(LocalDate.parse("2022-05-01")) + "\n"
				+ f1.getfooditems() + "\n");
		System.out.println(b4.getBrandname() + "\n" + b4.getPrice() + "\n" + b4.getASQ() + "\n" + b4.getDSQ() + "\n"
				+ b4.getLocation() + "\n" + "Expired Status :" + b4.hasExpiredFood(LocalDate.parse("2022-03-03")) + "\n"
				+ f1.getnonfooditems() + "\n");
		System.out.println(b1.toString());
		System.out.println(b2.toString());
		System.out.println(b3.toString());
		System.out.println(b4.toString());

	}
}
