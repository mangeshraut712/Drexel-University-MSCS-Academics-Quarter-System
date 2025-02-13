
public class Physical {

	private String street;
	private String city;
	private String region;
	private String country;
	private String postalCode;

	public Physical(String lastName, String firstName, String email, String phone, int stars, String street,
			String city, String region, String country, String postalCode) {

		this.street = street;
		this.city = city;
		this.region = region;
		this.country = country;
		this.postalCode = postalCode;
	}

	public Physical(String street, String city, String region, String country, String postalCode) {
		this.street = street;
		this.city = city;
		this.region = region;
		this.country = country;
		this.postalCode = postalCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getPostalcode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	@Override
	public String toString() {
		return ("Street : " + street + "\nCity : " + city + "\nRegion : " + region + "\nCountry : " + country
				+ "\nPostalCode : " + postalCode);

	}
}
