package phonebook2;

public class Address {
	private String street;

	private String city;
	private String state;
	private String zipcode;
	
	
	public Address () {
		
	}

	public void setCity (String city) {
		this.city=city;
	}

	public String getCity() {
		return this.city;
	}

	public void setState(String state) {
		this.state=state;
	}

	public String getState() {
		return this.state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}


 
	@Override
	public String toString() {
		return street + ", " + city + ", " + state + ", " + zipcode;
	}
}
