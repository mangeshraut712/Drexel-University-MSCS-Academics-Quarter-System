
public class TruckTester {
	public static void main(String args[]) {
		Truck t1 = new Truck();
		Truck t2 = new Truck();
		Pallet p1 = new Pallet();
		Pallet p2 = new Pallet();

		Truck t3 = new Truck("Mangesh", 200, 500, 1000, 100);
		Truck t4 = new Truck("Dayo", 300, 700, 2000, 75);

		t1.getDriver();
		t1.setDriver("Akshay");
		t1.addFuel(50);
		t1.getFuel();
		t1.getCapacity();
		t1.setCapacity(100);
		p1.getVolume();
		p2.getVolume();
		t1.getRange();
		t1.setRange(100);
		t1.getWeight();
		t1.load(p1);
		t1.load(p2);
		t1.unload(p1);
		t1.unload(p2);

		t2.getDriver();
		t2.setDriver("Gowtham");
		t2.addFuel(30);
		t2.getFuel();
		t2.getCapacity();
		t2.setCapacity(1);
		p1.getVolume();
		p2.getVolume();
		t2.getRange();
		t2.setRange(200);
		t2.getWeight();
		t2.load(p1);
		t2.load(p2);
		t2.unload(p1);
		t2.unload(p2);

		System.out.println(t1.getCapacity() + "\n" + t1.getDriver() + "\n" + t1.getFuel() + "\n" + t1.getRange() + "\n"
				+ t1.getWeight() + "\n" + t1.load(p1) + "\n" + t1.unload(p1) + "\n" + t1.load(p2) + "\n" + t1.unload(p2)
				+ "\n");

		System.out.println(t2.getCapacity() + "\n" + t2.getDriver() + "\n" + t2.getFuel() + "\n" + t2.getRange() + "\n"
				+ t2.getWeight() + "\n" + t1.load(p1) + "\n" + t1.unload(p1) + "\n" + t1.load(p2) + "\n" + t1.unload(p2)
				+ "\n");
		System.out.println(t3.getCapacity() + "\n" + t3.getDriver() + "\n" + t3.getFuel() + "\n" + t3.getRange() + "\n"
				+ t3.getWeight() + "\n");
		System.out.println(t4.getCapacity() + "\n" + t4.getDriver() + "\n" + t4.getFuel() + "\n" + t4.getRange() + "\n"
				+ t4.getWeight() + "\n");
	}
}
