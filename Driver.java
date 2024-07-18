// Author: Hasnain Ali
// Class: CMSC 331, Prof Lupoli, MoWe 11:30 -12:45


import java.util.Scanner;
import java.util.ArrayList;

public class Driver {
	
	static Scanner sc = new Scanner(System.in);
	
	AttendanceLog newLog = new AttendanceLog();
	
	StudentRoster newRoster = new StudentRoster();
	
	AttendanceApp newApplication = new AttendanceApp(newLog, newRoster);
	
	ArrayList<Log> currentQuery;
	
	// A function responsible for displaying the main menu
	public void MainMenu() {
		boolean validInput = false; // A boolean used to determine if a valid choice was entered
	
		char userInput = '0'; // Initializes a user input variable
	
		// Displays the Main Menu
		while (validInput == false) {
			System.out.println("Please enter a single character on your keyboard to select a choice from the options below.");
			System.out.println("Please load attendance data and roster before doing anything else, there's no input validation for that.");
			System.out.println("0 - Exit Program");
			System.out.println("A - load_log()");
			System.out.println("B - print_collection()");
			System.out.println("C - print_count()");
			System.out.println("D - load_roster()");
			System.out.println("E - print_collection()");
			System.out.println("F - print_count()");
			System.out.println("G - list_students_not_in_class()");
			System.out.println("H - list_all_times_checking_in_and_out()"); 
			System.out.println("I - list_all_times_checked_in()"); 
			System.out.println("J - list_students_late_to_class()"); 
			System.out.println("K - get_first_student_to_enter()"); // Does not work completely (see in attendance app for more details)
			System.out.println("L - print_attendance_data_for_student()");
			System.out.println("M - is_present()"); 
			System.out.println("N - list_all_students_checked_in()");
			System.out.println("O - list_all_students_checked_in_before()"); 
			System.out.println("P - list_students_attendance_count()");
			System.out.println("R - print_query_list()");
			System.out.println("S - print_count()");
			userInput = sc.next().charAt(0);
		
			// Input validation
			if ((userInput == '0') || (userInput == 'A') || (userInput == 'B') || (userInput == 'C') || (userInput == 'D') || (userInput == 'E') || (userInput == 'F') || (userInput == 'G') || (userInput == 'H')
				|| (userInput == 'I') || (userInput == 'J') || (userInput == 'K') || (userInput == 'L') || (userInput == 'M') || (userInput == 'N') || (userInput == 'O') || (userInput == 'P') || (userInput == 'Q')
				|| (userInput == 'R') || (userInput == 'S')) {
				validInput = true;
				sc.reset();
			}	
			else {
				System.out.println("Please Enter a Valid Choice");
				validInput = false;
				System.out.println();
			}
		}
		
		// Calls the functions corresponding to the user's input
		if (userInput == '0') {
			System.out.println("Exited the program");
		}
		else if (userInput == 'A') {
			System.out.print("Enter the file name of the attendance data you wish to load: ");
			String filename = sc.next();
			newLog.load_log(filename);
			System.out.println(filename + " has been loaded");
			sc.reset();
			MainMenu();
		}
		else if (userInput == 'B') {
			newLog.print_collection();
			MainMenu();
		}
		else if (userInput == 'C') {
			int total = newLog.print_count();
			System.out.println("The number of logs read into the AttendanceLog collection is " + total);
			MainMenu();
		}
		else if (userInput == 'D') {
			System.out.print("Enter the file name of the roster data you wish to load: ");
			String filename = sc.next();
			newRoster.load_roster(filename);
			System.out.println(filename + " has been loaded");
			sc.reset();
			MainMenu();
		}
		else if (userInput == 'E') {
			newRoster.print_collection();
			MainMenu();
		}
		else if (userInput == 'F') {
			int total = newRoster.print_count();
			System.out.println("The number of students read into the StudentRoster collection is " + total);
			MainMenu();
		}
		else if (userInput == 'G') {
			ArrayList<Student> missingStudents = new ArrayList<Student>();
			missingStudents = newApplication.list_students_not_in_class();
			for (int i = 0; i < missingStudents.size(); i++) {
				System.out.println(missingStudents.get(i).getLastName() + " " + missingStudents.get(i).getFirstName());
			}
			MainMenu();
		}
		else if (userInput == 'H') {
			System.out.print("Please enter the first name of the student you are looking for: ");
			String firstName = sc.next();
			sc.reset();
			System.out.print("Please enter the last name of the student you are looking for: ");
			String lastName = sc.next();
			sc.reset();
			boolean found = false;
			for (int i = 0; i < newRoster.print_count(); i++) {
				String rosterLastName = newRoster.getStudents().get(i).getLastName();
				rosterLastName = rosterLastName.substring(0, rosterLastName.length()-1);
				String rosterFirstName = newRoster.getStudents().get(i).getFirstName();
				if ((rosterLastName.equals(lastName)) && (rosterFirstName.equals(firstName))) {
					found = true;
					Student currentStudent = newRoster.getStudents().get(i);
					newApplication.list_all_times_checking_in_and_out(currentStudent);
				}
			}
			if (found == false) {
				System.out.println("The person you are searching for is not in the roster");
			}
			MainMenu();
		}
		else if (userInput == 'I') {
			ArrayList<Log> allTimes = new ArrayList<Log>();
			allTimes = newApplication.list_all_times_checked_in();
			for (int i = 0; i < allTimes.size(); i++) {
				System.out.println(allTimes.get(i).getLastName() + " " + allTimes.get(i).getFirstName() + " ['" + allTimes.get(i).getClassSection().getKey() + 
						" " + allTimes.get(i).getClassSection().getValue() + "']");
			}
			currentQuery = allTimes;
			MainMenu();
		}
		else if (userInput == 'J') {
			System.out.print("Please enter a timestamp to determine what is considered late: ");
			String time = sc.next();
			sc.reset();
			System.out.print("Please enter the date you wish to use: ");
			String date = sc.next();
			sc.reset();
			ArrayList<Log> lateStudents = new ArrayList<Log>();
			lateStudents = newApplication.list_students_late_to_class(time, date);
			for (int i = 0; i < lateStudents.size(); i++) {
				System.out.println(lateStudents.get(i).getLastName() + " " + lateStudents.get(i).getFirstName().substring(0, lateStudents.get(i).getFirstName().length()-1));
			}
			currentQuery = lateStudents;
			MainMenu();
		}
		else if (userInput == 'K') {
			ArrayList<Log> firstStudents = new ArrayList<Log>();
			firstStudents = newApplication.get_first_student_to_enter();
			for (int i = 0; i < firstStudents.size(); i++) {
				System.out.println("***** First Student To Enter On " + firstStudents.get(i).getClassSection().getValue() + " *****");
				String lastName = firstStudents.get(i).getLastName();
				String firstName = firstStudents.get(i).getFirstName();
				firstName = firstName.substring(0, firstName.length()-1);
				System.out.println(lastName + " " + firstName);
			}
			currentQuery = firstStudents;
			MainMenu();
		}
		else if (userInput == 'L') {
			System.out.print("Please enter the first name of the student you are looking for: ");
			String firstName = sc.next();
			sc.reset();
			System.out.print("Please enter the last name of the student you are looking for: ");
			String lastName = sc.next();
			sc.reset();
			boolean found = false;
			for (int i = 0; i < newRoster.print_count(); i++) {
				String rosterLastName = newRoster.getStudents().get(i).getLastName();
				rosterLastName = rosterLastName.substring(0, rosterLastName.length()-1);
				String rosterFirstName = newRoster.getStudents().get(i).getFirstName();
				if ((rosterLastName.equals(lastName)) && (rosterFirstName.equals(firstName))) {
					found = true;
					Student currentStudent = newRoster.getStudents().get(i);
					newApplication.print_attendance_data_for_student(currentStudent);
				}
			}
			if (found == false) {
				System.out.println("The person you are searching for is not in the roster");
			}
			MainMenu();
		}
		else if (userInput == 'M') {
			System.out.print("Please enter the first name of the student you are looking for: ");
			String firstName = sc.next();
			sc.reset();
			System.out.print("Please enter the last name of the student you are looking for: ");
			String lastName = sc.next();
			sc.reset();
			System.out.print("Please enter the date you wish to use: ");
			String date = sc.next();
			sc.reset();
			boolean found = false;
			for (int i = 0; i < newRoster.print_count(); i++) {
				String rosterLastName = newRoster.getStudents().get(i).getLastName();
				rosterLastName = rosterLastName.substring(0, rosterLastName.length()-1);
				String rosterFirstName = newRoster.getStudents().get(i).getFirstName();
				if ((rosterLastName.equals(lastName)) && (rosterFirstName.equals(firstName))) {
					found = true;
					Student currentStudent = newRoster.getStudents().get(i);
					boolean present = newApplication.is_present(currentStudent, date);
					System.out.println(present);
				}
			}
			if (found == false) {
				System.out.println("The person you are searching for is not in the roster");
			}
			MainMenu();
		}
		else if (userInput == 'N') {
			System.out.print("Please enter the date you wish to use: ");
			String date = sc.next();
			sc.reset();
			ArrayList<Log> studentsPresent = new ArrayList<Log>();
			studentsPresent = newApplication.list_all_students_checked_in(date);
			for (int i = 0; i < studentsPresent.size(); i++) {
				String studentFirstName = studentsPresent.get(i).getFirstName();
				String studentLastName = studentsPresent.get(i).getLastName();
				System.out.println(studentLastName + " " + studentFirstName.substring(0, studentFirstName.length()-1));
			}
			currentQuery = studentsPresent;
			MainMenu();
		}
		else if (userInput == 'O') {
			System.out.print("Please enter a timestamp to check students who entered before this time: ");
			String time = sc.next();
			sc.reset();
			System.out.print("Please enter the date you wish to use: ");
			String date = sc.next();
			sc.reset();
			ArrayList<Log> earlyStudents = new ArrayList<Log>();
			earlyStudents = newApplication.list_all_students_checked_in_before(time, date);
			for (int i = 0; i < earlyStudents.size(); i++) {
				System.out.println(earlyStudents.get(i).getLastName() + " " + earlyStudents.get(i).getFirstName().substring(0, earlyStudents.get(i).getFirstName().length()-1));
			}
			newApplication.print_count(earlyStudents);
			currentQuery = earlyStudents;
			MainMenu();
		}
		else if (userInput == 'P') {
			System.out.print("Please enter a number to see which students have attended these number of classes: ");
			int classNumber = sc.nextInt();
			sc.reset();
			if ((classNumber < 1) && (classNumber > 2)) {
				System.out.println("A student only has one or two classes on record");
			}
			else {
				ArrayList<Log> studentsPresent = new ArrayList<Log>();
				studentsPresent = newApplication.list_students_attendance_count(classNumber);
				System.out.println("***** Those who attended " + classNumber + " class(es) *****");
				for (int i = 0; i < studentsPresent.size(); i++) {
					System.out.println(studentsPresent.get(i).getLastName() + " " + studentsPresent.get(i).getFirstName().substring(0, studentsPresent.get(i).getFirstName().length()-1));
				}
				newApplication.print_count(studentsPresent);
				currentQuery = studentsPresent;
			}
			MainMenu();
		}
		else if (userInput == 'R') {
			if (currentQuery != null) {
				newApplication.print_query_list(currentQuery);
			}
			MainMenu();
		}
		else {
			if (currentQuery != null) {
				newApplication.print_count(currentQuery);
			}
			MainMenu();
		}
	}
	
	public static void main(String[] args) {
		Driver currentSession = new Driver();
		currentSession.MainMenu();
	}
}
