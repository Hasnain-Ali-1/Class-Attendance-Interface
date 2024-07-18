// Author: Hasnain Ali
// Class: CMSC 331, Prof Lupoli, MoWe 11:30 -12:45

import javafx.util.Pair;

public class Log {
	private String firstName;
	private String lastName;
	private Pair<String, String> classSection;
	private Pair<String, String> classSection2;
	
	// Constructor
	public Log(String fileLastName, String fileFirstName, String fileTime, String fileDate, String fileSecondTime, String fileSecondDate) {
		lastName = fileLastName;
		firstName = fileFirstName;
		classSection = new Pair<String, String>(fileTime, fileDate);
		classSection2 = new Pair<String, String>(fileSecondTime, fileSecondDate);
	}

	// Getters
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Pair<String, String> getClassSection() {
		return classSection;
	}
	
	public Pair<String, String> getClassSection2() {
		return classSection2;
	}

	// Setters
	public void setClassSection2(Pair<String, String> classSection2) {
		this.classSection2 = classSection2;
	}
}
