// Author: Hasnain Ali
// Class: CMSC 331, Prof Lupoli, MoWe 11:30 -12:45

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import javafx.util.Pair;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class AttendanceLog {
	private ArrayList<Log> Logs;
	
	// Constructor
	public AttendanceLog() {
		Logs = new ArrayList<Log>();
	}
	
	// Loads a user requested log file
	public void load_log(String filename) {
		Scanner infile = null;
		try {
			infile = new Scanner(new FileReader("D:/Eclipse/Final/src/dataSet/" + filename));
		}
		catch (FileNotFoundException e) {
			System.out.println(filename + " not found");
			e.printStackTrace();
			System.exit(0);
		}
		while (infile.hasNextLine()) {
			String line = infile.nextLine();
			StringTokenizer tokenizer = new StringTokenizer(line);
			String lastName = tokenizer.nextToken();
			String firstName = tokenizer.nextToken();
			String time = tokenizer.nextToken();
			String date = tokenizer.nextToken();
			Boolean foundDuplicate = false;
			if (Logs.size() == 0) {
				String secondTime = "placeholder";
				String secondDate = "placeholder";
				Log currentLog = new Log(lastName, firstName, time, date, secondTime, secondDate);
				Logs.add(currentLog);
			}
			else {
				// checks to see if the name is already in the array list, if it is, changes the placeholder values to the new time and date 
				for (int i = 0; i < Logs.size(); i++) {
					if ((Logs.get(i).getFirstName().equals(firstName)) && (Logs.get(i).getLastName().equals(lastName))) {	
						Pair<String, String> secondClass = new Pair<String, String>(time, date);
						Logs.get(i).setClassSection2(secondClass);
						foundDuplicate = true;
					}
				}
				if (foundDuplicate == false) {
					String secondTime = "placeholder";
					String secondDate = "placeholder";
					Log currentLog = new Log(lastName, firstName, time, date, secondTime, secondDate);
					Logs.add(currentLog);
				}
			}
		}
		infile.close();
	}
	
	// Prints out the elements in our logs array list
	public void print_collection() {
		for (int i = 0; i < Logs.size(); i++) {
			// If one of the member variables is still placeholder, don't output the second date/time
			if (Logs.get(i).getClassSection2().getKey().equals("placeholder")) {
				System.out.println(Logs.get(i).getLastName() + " " + Logs.get(i).getFirstName() + " ['" + Logs.get(i).getClassSection().getKey() + " " + Logs.get(i).getClassSection().getValue() + "']");
			}
			else {
				System.out.println(Logs.get(i).getLastName() + " " + Logs.get(i).getFirstName() + " ['" + Logs.get(i).getClassSection().getKey() + " " + Logs.get(i).getClassSection().getValue() + "' , '" 
			+ Logs.get(i).getClassSection2().getKey() + " " + Logs.get(i).getClassSection2().getValue() + "']");	
			}
		}
	}
	
	// Returns the number of logs in our logs array list
	public int print_count() {
		return Logs.size();
	}

	// Getter
	public ArrayList<Log> getLogs() {
		return Logs;
	}
	
	
	
	
	
}
