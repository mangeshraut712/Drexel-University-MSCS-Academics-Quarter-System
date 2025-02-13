import java.io.Serializable;
import java.util.ArrayList;

class Singleroom implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4258030826886496149L;
	String name;
	String contact;
	String gender;
	String email;
	ArrayList<Food> food = new ArrayList<>();

	Singleroom() {
		this.name = "";
	}

	Singleroom(String name, String contact, String gender, String email) {
		this.name = name;
		this.contact = contact;
		this.gender = gender;
		this.email = email;
	}
}