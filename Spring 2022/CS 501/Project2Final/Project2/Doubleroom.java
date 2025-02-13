import java.io.Serializable;

class Doubleroom extends Singleroom implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3927382334624203331L;
	String personname;
	String personcontact;
	String persongender;
	String personemail;

	Doubleroom() {
		this.name = "";
		this.personname = "";
	}

	Doubleroom(String name, String contact, String gender, String email, String personname, String personcontact,
			String persongender, String personemail) {
		this.name = name;
		this.contact = contact;
		this.gender = gender;
		this.email = email;
		this.personname = personname;
		this.personcontact = personcontact;
		this.persongender = persongender;
		this.personemail = personemail;
	}
}