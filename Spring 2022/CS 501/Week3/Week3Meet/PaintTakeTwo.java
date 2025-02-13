import java.util.Scanner;

class Wall {
	public double height;
	public double width;

	public Wall() {
		this.height = 0.0;
		this.width = 0.0;
	}

	public void setHeight(double height) {
		if (height < 0) {
			return;
		}
		this.height = height;

	}

	public void setWidth(double width) {
		if (width >= 0)
			this.width = width;
	}

	public double area() {
		return this.height * this.width;
	}

	public int getQuarts() {
		return (int) Math.ceil(this.area() / 100.0);

	}
}

public class PaintTakeTwo {

	public static void main(String args[]) {
		double width, height;
		Wall wallA = new Wall();
		Wall wallB = new Wall();

		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the wallA width:");
		width = scanner.nextDouble();
		System.out.println("Enter the wallA height:");
		height = scanner.nextDouble();
		wallA.setHeight(height);
		wallA.setWidth(width);
		System.out.println("Area:" + wallA.area());
		System.out.println("Quarts:" + wallA.getQuarts());

		System.out.println("Enter the wallB width:");
		width = scanner.nextDouble();
		System.out.println("Enter the wallB height:");
		height = scanner.nextDouble();
		wallB.setHeight(height);
		wallB.setWidth(width);
		System.out.println("Area:" + wallB.area());
		System.out.println("Quarts:" + wallB.getQuarts());
	}
}
