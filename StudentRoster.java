// Author: Hasnain Ali
// Class: CMSC 331, Prof Lupoli, MoWe 11:30 -12:45

import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class StudentRoster {
	private ArrayList<Student> Students;
	
	public StudentRoster() {
		Students = new ArrayList<Student>();
	}
	
	// Loads a user requested log file
		public void load_roster(String filename) {
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
				Student currentStudent = new Student(lastName,firstName);
				Students.add(currentStudent);
			}
			infile.close();
		}
		
		// Prints out the elements in the Students array list
		public void print_collection() {
			for (int i = 0; i < Students.size(); i++) {
				System.out.println(Students.get(i).getLastName() + " " + Students.get(i).getFirstName());
			}
		}
		
		// Returns the number of elements in the Students array list
		public int print_count() {
			return Students.size();
		}

		//Getter
		public ArrayList<Student> getStudents() {
			return Students;
		}
}
