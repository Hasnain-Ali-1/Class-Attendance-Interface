// Author: Hasnain Ali
// Class: CMSC 331, Prof Lupoli, MoWe 11:30 -12:45


public class Student {
	private String lastName;
	private String firstName;
	
	// Constructor
	public Student(String fileLastName, String fileFirstName) {
		lastName = fileLastName;
		firstName = fileFirstName;
	}

	// Getters
	public String getLastName() {
		return lastName;
	}

	public String getFirstName() {
		return firstName;
	}
	
}
