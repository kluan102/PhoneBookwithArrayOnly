package phonebook2;


import java.util.Scanner;

import Phonebook.Menu;
import Phonebook.Person;

public class Application {
	private static Person[] person= new Person[0];
	
	public static void main(String[] args) {
		int mainChoice=0;
		
		Scanner input = new Scanner(System.in);
		do {
			mainChoice= Menu.menuChoice();
			System.out.println("-------------------------------------------------------------------------------------------------");
			if (mainChoice==1) {
				System.out.println("Enter this format: First Middle Last Name, address, city, state, zipcode, ten digits telephone number");
				addNewPerson(input.nextLine());
			}
			if (mainChoice==2) {
				System.out.println("Enter this format: (314)-345-6789");
				deletePerson(input.nextLine());
				System.out.println("-------------------------------------------------------------------------------------------------");
			}
			else if(mainChoice==3) {
				System.out.println("-------------------------------------------------------------------------------------------------");
				searchParameter(Menu.searchChoice());
			}
			else if (mainChoice==4) {
				System.out.print("Please enter a telephone to continue to update (e.g. (314)-123-4567): ");
				int personNumber = matchTelephone(input.next());
				if (personNumber != 2000) {
					updateParameter(Menu.updateChoice(), personNumber);
					//from update menu
				}
				else {
					System.out.println("Sorry, number is not in contact list");
				}
				////(input.next());
				//int updateOption=Menu.updateChoice(); --need to update with a given telephone #
				
			}
			if (mainChoice==5) {
				System.out.println("-------------------------------------------------------------------------------------------------");
				
				if (person.length>0) {
					//Collections.sort(person);
					if (person.length>1) {
						person = getSort();
					}
					for (int i=0; i< person.length; i++) {
						System.out.println(person[i].toString());
					}
				}
				else {
					System.out.println("Your contact is emptied");
				}
				
			}
			
		} while (mainChoice != 6); 
	}	
	
	public static void addNewPerson (String inputEntry) {
		Person[] tempHolder = new Person[person.length +1];
		//extending the array
		for (int i=0; i< person.length; i++ ) {
			tempHolder [i] = person[i];
		}
		
		int newSpot = tempHolder.length-1; 
		person=tempHolder;
				
				
		//John Doe, 114 Market St, St Louis, MO, 63403, 636643569
		int commaCounter =0;
		int commaLocation =0;
		person[newSpot] = new Person();
		//Extracting the names portion
		String name="";
		for (int i=0; i<inputEntry.length(); i++) {
			if (inputEntry.charAt(i) == ',' && commaCounter ==0) {
				
					name = inputEntry.substring(0, i);
					int space=0;
					int spaceLocation =0;
					int spaceLocation2 = 0;
					//Split the first and last names (maybe middle)
					for (int j=0; j<name.length(); j++) {
						if (name.charAt(j)==' ' && space==0) {
							person[newSpot].setFirstName(name.substring(0, j).trim());
							person[newSpot].setMiddleName(null);
							person[newSpot].setLastName(name.substring(j+1, name.length()).trim());
							spaceLocation = j;
							space++;
							
							
						}
						else if (name.charAt(j)==' ' && space ==1 ) {
							person[newSpot].setMiddleName(name.substring(spaceLocation+1, j).trim());
							person[newSpot].setLastName(name.substring(j+1, name.length()).trim());
							space++;
							spaceLocation2 =j;
						}
						else if (name.charAt(j)==' ' && space ==2) {
							person[newSpot].setMiddleName(person[newSpot].getMiddleName()+ name.substring(spaceLocation2,j));
							person[newSpot].setLastName(name.substring(j+1, name.length()).trim());
							//System.out.println(person[newSpot].getFirstName() +" " + person[newSpot].getMiddleName() + " " +person[newSpot].getLastName());
						}
						
					}
				
				commaCounter ++;
				commaLocation = i;
			}
			else if (inputEntry.charAt(i) == ',' && commaCounter ==1 ) {
				person[newSpot].getAddress().setStreet(inputEntry.substring(commaLocation+1, i).trim());
				commaCounter ++;
				commaLocation =i;
			}
			else if (inputEntry.charAt(i) == ',' && commaCounter ==2 ) {
				person[newSpot].getAddress().setCity(inputEntry.substring(commaLocation+1, i).trim());
				commaCounter ++;
				commaLocation =i;
			}
			else if (inputEntry.charAt(i) == ',' && commaCounter ==3 ) {
				person[newSpot].getAddress().setState(inputEntry.substring(commaLocation+1, i).trim());
				commaCounter ++;
				commaLocation =i;
			}
			else if (inputEntry.charAt(i) == ',' && commaCounter ==4 ) {
				person[newSpot].getAddress().setZipcode(inputEntry.substring(commaLocation+1, i).trim());
				commaCounter ++;
				commaLocation =i;
				//System.out.println(person[newSpot].getAddress());
			}
			if (inputEntry.charAt(i) == ',' && commaCounter ==5 ) {
				person[newSpot].setPhoneNumber(inputEntry.substring(i+1).trim());
				commaCounter ++;
				commaLocation =i;
				//System.out.println(person[newSpot].getPhoneNumber());
			}	
			
		}		
		
	}
	
	public static void deletePerson(String inputNumber) {
		Person[] tempHolder = new Person[person.length -1];
		int count = 0;
		for (int i=0; i< person.length; i++) {
			if (! (person[i].getPhoneNumber().equals(inputNumber)  )) {
				tempHolder[count] = person[i];
				count++;
			}
			
		}
		person=tempHolder;
		System.out.println("You have successfully deleted this number from your contact");
	}
	
	public static void searchParameter (int parameter) {
		System.out.println("-------------------------------------------------------------------------------------------------");
		Scanner input = new Scanner(System.in);
		if (parameter ==1) {
			System.out.print("Enter first name ");
			searchFirstName(input.nextLine());
		}
		else if (parameter==2) {
			System.out.print("Enter last name ");
			searchLastName(input.next());
		}
		else if (parameter==3) {
			System.out.print("Enter full name (e.g. John Mee Doe): ");
			//String test = input.nextLine();
			searchFullName(input.nextLine());
		}
		else if (parameter==4) {
			System.out.print("Enter phonenumber (e.g. (314)-123-4567): ");
			searchPhone(input.next());
		}
		else if (parameter==5) {
			System.out.print("Enter city (e.g. St.Louis); ");
			searchCity(input.next());
		}
		else if (parameter==6) {
			System.out.print("Enter state (e.g. MO): ");
			searchState(input.next());
		}
		else if (parameter==7) {
			System.out.print("Enter zipcode (e.g. 63456): ");
			searchZipcode(input.next());
		}
		
		
	}
	public static void searchFirstName(String firstName) {
		for (int i=0; i<person.length; i++) {
			if (person[i].getFirstName().toLowerCase().equals(firstName.toLowerCase())) {
				System.out.println(person[i].toString());
			}
		}
	}
	
	public static void searchLastName(String lastName) {
		for (int i=0; i<person.length; i++) {
			if (person[i].getLastName().toLowerCase().equals(lastName.toLowerCase())) {
				System.out.println(person[i].toString());
			}
		}
	}
	
	public static void searchFullName(String fullName) {
		for (int i=0; i<person.length; i++) {
			String tempFullName ="";
			
			if(person[i].getMiddleName().equals(null))
			{
				tempFullName = person[i].getFirstName().toLowerCase() + " " + person[i].getLastName().toLowerCase();
				
			}
			else{
				tempFullName = person[i].getFirstName().toLowerCase() + " " + person[i].getMiddleName()+ " " + person[i].getLastName().toLowerCase();
			}
			
			fullName = fullName.toLowerCase();
			//System.out.println(fullName + " " + tempFullName+ " "+ tempFullName.equals(fullName)); */
			if ( tempFullName.equals(fullName)) {
				System.out.println(person[i].toString()); 
			}
		}
	}
	
	public static void searchPhone(String phoneNumber) {
		for (int i=0; i<person.length; i++) {
			if (person[i].getPhoneNumber().equals(phoneNumber)) {
				System.out.println(person[i].toString());
			}
		}
	}
	
	public static void searchCity(String city) {
		for (int i=0; i<person.length; i++) {
			String tempCity =person[i].getAddress().getCity().toLowerCase();
			//System.out.println(tempCity.trim() + " " + city + " " + tempCity.trim().equals(city.toLowerCase()));
			if (tempCity.trim().equals(city.toLowerCase())) {
				System.out.println(person[i].toString());
			}
		}
	}
	
	public static void searchState(String state) {
		for (int i=0; i<person.length; i++) {
			String tempState =person[i].getAddress().getState().toLowerCase();
			if (tempState.trim().equals(state.toLowerCase())) {
				System.out.println(person[i].toString());
			}
		}
	}
	
	public static void searchZipcode(String zipcode) {
		for (int i=0; i<person.length; i++) {
			String tempZipcode =person[i].getAddress().getZipcode().toLowerCase();
			if (tempZipcode.trim().equals(zipcode)) {
				System.out.println(person[i].toString());
			}
		}
	}
	
	public static int matchTelephone(String phoneNumber) {
		int match = 2000;
		for (int i=0; i<person.length; i++) {
			if (person[i].getPhoneNumber().equals(phoneNumber)) {
				match= i;
			}
		}
		return match;
	}
	public static void updateParameter(int parameter, int personNumber) {
		Scanner input = new Scanner(System.in);
		if (parameter==1) {
			System.out.println("Current first name: " + person[personNumber].getFirstName());
			System.out.print("Please enter new first name: ");
			String tempFirst = input.next().toLowerCase();
			tempFirst = (tempFirst.substring(0,1).toUpperCase() + tempFirst.substring(1)).trim();
			person[personNumber].setFirstName(tempFirst);
			System.out.println("-------------------------------------------------------------------------------------------------");
			System.out.println("Updated contact is :" + person[personNumber].toString());
			
		}
		if (parameter==2) {
			System.out.println("Current last name: " + person[personNumber].getLastName());
			System.out.print("Please enter new last name: ");
			String tempLast = input.next().toLowerCase();
			tempLast = (tempLast.substring(0,1).toUpperCase() + tempLast.substring(1)).trim();
			person[personNumber].setLastName(tempLast);
			System.out.println("-------------------------------------------------------------------------------------------------");
			System.out.println("Updated contact is :" + person[personNumber].toString());
			
		}
		if (parameter==3) {
			System.out.println("Current telephone number: " + person[personNumber].getPhoneNumber());
			System.out.print("Please enter new telephone number (e.g. 1234567890): ");
			String tempPhoneNumber = input.next();
			person[personNumber].setPhoneNumber(tempPhoneNumber);
			System.out.println("-------------------------------------------------------------------------------------------------");
			System.out.println("Updated contact is :" + person[personNumber].toString());
		}
		if (parameter==4) {
			System.out.println("Current address: " + person[personNumber].getAddress());
			System.out.print("Please enter new address like the above the format above: ");
			String tempAddress = input.nextLine();
			//trimmer and split:   123 walker st, st.louis, mo, 63126
			String[] commaSplitter = tempAddress.split(",");
			person[personNumber].getAddress().setStreet(commaSplitter[0].trim());
			person[personNumber].getAddress().setCity(commaSplitter[1].trim());
			person[personNumber].getAddress().setState(commaSplitter[2].trim());
			person[personNumber].getAddress().setZipcode(commaSplitter[3].trim());
			System.out.println("-------------------------------------------------------------------------------------------------");
			System.out.println("Updated contact is :" + person[personNumber]);
			
		}
	}
	
	public static Person[] getSort() {
		Person[] tempHolder = new Person[person.length];
		tempHolder = person;
		for(int i=0; i<person.length; i++) {
			for (int j=i, k=j+1 ; j< person.length-1; j++, k++) {
				
				if (tempHolder[j].getFirstName().compareTo(tempHolder[k].getFirstName())>0){	
					Person switchPlace = new Person();
					switchPlace = tempHolder[j];
					tempHolder[j] = tempHolder[k];
					tempHolder[k] = switchPlace;
				}			
			}	
		}
		return tempHolder;
	}

	public static Person[] getPerson() {
		return person;
	}

	public static void setPerson(Person[] person) {
		Application.person = person;
	}
}

//John Doe, 114 Market St, St.Louis, MO, 63403, 6366435693
//Mary Doe, 2445 Fyler Ave, St.Louis, MO, 63139, 3146014399
//Lou Nguyen, 3504 Arsenal St, St.Louis, MO, 63118, 3147724246
//Pary Kate Lee Olsen, 6758 Gogo Ave, Saint Peter, MO, 75638, 8764560987 
//(636)-643-5693
