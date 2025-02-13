import java.io.Serializable;

class Food implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5424023478959085222L;
	int itemno;
	int quantity;
	float price;

	Food(int itemno, int quantity) {
		this.itemno = itemno;
		this.quantity = quantity;
		switch (itemno) {
		case 1:
			price = quantity * 10;
			break;
		case 2:
			price = quantity * 15;
			break;
		case 3:
			price = quantity * 20;
			break;
		case 4:
			price = quantity * 25;
			break;
		}
	}
}