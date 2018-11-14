package phonebook2;

import Phonebook.Address;

public class Person implements Comparable<Person>{
private Address address = new Address();
	
	private String firstName;
	private String lastName;
	private String middleName;
	
	private String phoneNumber;
	
	public Person() {
		
	}
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getPhoneNumber() {
		
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = "(" + phoneNumber.substring(0, 3) + ")-" + phoneNumber.substring(3, 6) + "-" + phoneNumber.substring(6, 10);
	}
	
	public int compareTo(Person o) {
		return this.firstName.compareTo(o.firstName);
	}
	
	
	@Override
	public String toString() {
		String tempFullName="";
		if (getMiddleName().equals(null)){
			tempFullName = getFirstName()+ " " +getLastName();	
		}
		else{
			tempFullName = getFirstName()+ " " + getMiddleName()+ " " + getLastName();
		}
		return tempFullName + ", "+ address +", " + getPhoneNumber();
}
}
