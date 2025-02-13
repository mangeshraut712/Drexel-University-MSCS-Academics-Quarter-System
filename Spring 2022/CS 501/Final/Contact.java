
public class Contact {

	private String lastName;
	private String firstName;
	private String email;
	private String phone;
	private int stars;

	public Contact(String lastName, String firstName, String email, String phone, int stars) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.email = email;
		this.phone = phone;
		this.stars = stars;

	}

	public Contact(String lastName, String firstName) {
		lastName = "";
		firstName = "";
	}

	public String getName() {
		return getName();
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setFullName(String lastName, String firstName) {
		this.setFullName(lastName, firstName);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getStars() {
		return stars;
	}

	public void setStars(int stars) {
		this.stars = stars;
	}

	@Override
	public String toString() {
		return ("Lastname : " + lastName + "\nFirstname : " + firstName + "\nEmail : " + email + "\nPhone : " + phone
				+ "\nStars : " + stars);
	}

}
