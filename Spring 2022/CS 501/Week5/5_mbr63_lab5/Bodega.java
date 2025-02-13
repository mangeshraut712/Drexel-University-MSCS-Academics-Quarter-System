import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Random;

public class Bodega implements inventory {
	Food food = new Food();
	private String location;
	private String brand_name;
	private double price;
	private int desired_stock_quantity;
	private int actual_stock_quantity;
	private LocalDate now = LocalDate.now();
	private LocalDate expiredate;
	private ArrayList<Food> items = new ArrayList<Food>();

	Random rand = new Random();

	public Bodega() {
		brand_name = "NA";
		price = 0.0;
		location = "LA";
		desired_stock_quantity = 0;
		actual_stock_quantity = 0;
	}

	public Bodega(String brand_name, double price, String location, int desired_stock_quantity,
			int actual_stock_quantity) {
		this.location = location;
		this.brand_name = brand_name;
		this.price = price;
		this.desired_stock_quantity = desired_stock_quantity;
		this.actual_stock_quantity = actual_stock_quantity;
	}

	public Bodega(String brand_name, double price, String location, int desired_stock_quantity,
			int actual_stock_quantity, LocalDate expiredate) {
		this.location = location;
		this.brand_name = brand_name;
		this.price = price;
		this.desired_stock_quantity = desired_stock_quantity;
		this.actual_stock_quantity = actual_stock_quantity;
		this.expiredate = expiredate;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

	public void setBrandname(String brand_name) {
		this.brand_name = brand_name;
	}

	public String getBrandname() {
		return brand_name;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setDSQ(int desired_stock_quantity) {
		this.desired_stock_quantity = desired_stock_quantity;
	}

	public int getDSQ() {
		return desired_stock_quantity;
	}

	public void setASQ(int actual_stock_quantity) {
		this.actual_stock_quantity = actual_stock_quantity;
	}

	public int getASQ() {
		return rand.nextInt(100);
	}

	@Override
	public boolean hasExpiredFood(LocalDate date) {
		Period obj = Period.between(now, expiredate);
		int duration = obj.getDays();
		if (duration < 7) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return ("Brandname : " + brand_name + "\nPrice : $" + price + "\nDesired_Stock_Quantity : "
				+ desired_stock_quantity + "\nActual_Stock_Quantity : " + actual_stock_quantity + "\n");
	}
}
