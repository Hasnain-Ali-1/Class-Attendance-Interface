// Author: Hasnain Ali
// Class: CMSC 331, Prof Lupoli, MoWe 11:30 -12:45

import java.util.ArrayList;

public class AttendanceApp {
	private AttendanceLog swipeData;
	private StudentRoster roster;
	
	public AttendanceApp(AttendanceLog newLog, StudentRoster newRoster) {
		swipeData = newLog;
		roster = newRoster;
		
	}
	
	// Returns an array list of students not in class
	// Will not change the currentQuery in main, the previous one will remain
	public ArrayList<Student> list_students_not_in_class() {
		System.out.println("***** Students Missing In Class *****");
		ArrayList<Student> noShows = new ArrayList<Student>();
		// Iterates over the roster
		for (int i = 0; i < roster.print_count(); i++) {
			boolean found = false;
			// Iterates over the SwipeData
			for (int j = 0; j < swipeData.print_count(); j++) {
				String swipeDataFirstName = swipeData.getLogs().get(j).getFirstName();
				swipeDataFirstName = swipeDataFirstName.substring(0, swipeDataFirstName.length()-1);
				String swipeDataLastName = swipeData.getLogs().get(j).getLastName();
				String rosterFirstName = roster.getStudents().get(i).getFirstName();
				String rosterLastName = roster.getStudents().get(i).getLastName();
				// if it found the student from the roster in the swipe data, boolean becomes true (resets for each iteration of i)
				if ((rosterFirstName.equals(swipeDataFirstName)) && (rosterLastName.equals(swipeDataLastName)))  {
					found = true;
				}
			}
			if (found == false) {
				noShows.add(roster.getStudents().get(i));
			}
		}
		// Prints out the noShow array list after the roster has been iterated through
		return noShows;
	}
	
	// Lists all swipe data for a particular student
	// Will not change the currentQuery in main, the previous one will remain
	public void list_all_times_checking_in_and_out(Student currStudent) {
		System.out.println("***** List All Swipe In And Out For A Student *****");
		// Iterates over the swipe data
		for (int i = 0; i < swipeData.print_count(); i++) {	
			String studentFirstName = currStudent.getFirstName();
			String studentLastName = currStudent.getLastName();
			String swipeDataFirstName = swipeData.getLogs().get(i).getFirstName();
			swipeDataFirstName = swipeDataFirstName.substring(0, swipeDataFirstName.length()-1);
			String swipeDataLastName = swipeData.getLogs().get(i).getLastName();
			// If it finds a match between the student passed in and the student on the swipe data
			if ((swipeDataFirstName.equals(studentFirstName)) && (swipeDataLastName.equals(studentLastName))) {
				// If there is a swipe out or a second class attended in addition to the swipe in, print both
				if (swipeData.getLogs().get(i).getClassSection2().getValue().equals("placeholder") == false) {
					System.out.println(swipeData.getLogs().get(i).getLastName() + " " + swipeData.getLogs().get(i).getFirstName() + " ['" + swipeData.getLogs().get(i).getClassSection().getKey() + 
					" " + swipeData.getLogs().get(i).getClassSection().getValue() + "']");
					System.out.println(swipeData.getLogs().get(i).getLastName() + " " + swipeData.getLogs().get(i).getFirstName() + " ['" + swipeData.getLogs().get(i).getClassSection2().getKey() + " " + 
					swipeData.getLogs().get(i).getClassSection2().getValue() + "']");						
				}
				// If there is not a second class or a swipe out in addition the to swipe in, print just the swipe in
				else {
					System.out.println(swipeData.getLogs().get(i).getLastName() + " " + swipeData.getLogs().get(i).getFirstName() + " ['" + swipeData.getLogs().get(i).getClassSection().getKey() + 
					" " + swipeData.getLogs().get(i).getClassSection().getValue() + "']");
				}
			}
		}
	}
	
	// Returns an array list with all student swipe info
	// Will change the currentQuery in main, the list being returned becomes currentQuery
	public ArrayList<Log> list_all_times_checked_in() {
		ArrayList<Log> allData = new ArrayList<Log>();
		System.out.println("***** Check In Times For All Students Who Attended *****");
		for (int i = 0; i < swipeData.print_count(); i++) {
			allData.add(swipeData.getLogs().get(i));
		}
		return allData;
	}
	
	// Returns an array list of Students who are late to class
	// Will change the currentQuery in main, the list being returned becomes currentQuery
	public ArrayList<Log> list_students_late_to_class(String time, String date) {
		System.out.println("***** Students Late To Class *****");
		ArrayList<Log> lateStudents = new ArrayList<Log>();
		int swipeDataHour2Int = 0;
		int swipeDataMinutes2Int = 0;
		int swipeDataSeconds2Int = 0;
		// The following converts the hours, minutes, and seconds for the time parameter into an int 
		String stringHour = new StringBuilder().append(time.charAt(0)).append(time.charAt(1)).toString();
		String stringMinutes = new StringBuilder().append(time.charAt(3)).append(time.charAt(4)).toString();
		String stringSeconds = new StringBuilder().append(time.charAt(6)).append(time.charAt(7)).toString();
		int hour = Integer.parseInt(stringHour);
		int minutes = Integer.parseInt(stringMinutes);
		int seconds = Integer.parseInt(stringSeconds);
		for (int i = 0; i < swipeData.print_count(); i++) {
			// Gets the time and date for each swipeData entry
			// Converts the hour, minutes, and seconds for the first class swipe in for each log into an int
			String swipeDataTime1 = swipeData.getLogs().get(i).getClassSection().getKey();
			String swipeDataTime2 = swipeData.getLogs().get(i).getClassSection2().getKey();
			String swipeDataHour = new StringBuilder().append(swipeDataTime1.charAt(0)).append(swipeDataTime1.charAt(1)).toString();
			String swipeDataMinutes = new StringBuilder().append(swipeDataTime1.charAt(3)).append(swipeDataTime1.charAt(4)).toString();
			String swipeDataSeconds = new StringBuilder().append(swipeDataTime1.charAt(6)).append(swipeDataTime1.charAt(7)).toString();
			// if the second swipe in time is not "placeholder", converts the hours, minute, and seconds into an int 
			if (swipeDataTime2.equals("placeholder") == false) {
				String swipeDataHour2 = new StringBuilder().append(swipeDataTime2.charAt(0)).append(swipeDataTime2.charAt(1)).toString();
				String swipeDataMinutes2 = new StringBuilder().append(swipeDataTime2.charAt(3)).append(swipeDataTime2.charAt(4)).toString();
				String swipeDataSeconds2 = new StringBuilder().append(swipeDataTime2.charAt(6)).append(swipeDataTime2.charAt(7)).toString();
				swipeDataHour2Int = Integer.parseInt(swipeDataHour2);
				swipeDataMinutes2Int = Integer.parseInt(swipeDataMinutes2);
				swipeDataSeconds2Int = Integer.parseInt(swipeDataSeconds2);
			}
			int swipeDataHourInt = Integer.parseInt(swipeDataHour);
			int swipeDataMinutesInt = Integer.parseInt(swipeDataMinutes);
			int swipeDataSecondsInt = Integer.parseInt(swipeDataSeconds);
			String studentFirstName = swipeData.getLogs().get(i).getFirstName();
			studentFirstName = studentFirstName.substring(0, studentFirstName.length()-1);
			String studentDate = swipeData.getLogs().get(i).getClassSection().getValue();
			String studentDate2 = swipeData.getLogs().get(i).getClassSection2().getValue();
			// Checks to see if any of the times for a student is earlier and if it occurs on the same date
			if ((hour < swipeDataHourInt) && (date.equals(studentDate))) {
				lateStudents.add(swipeData.getLogs().get(i));
			}
			else if ((minutes < swipeDataMinutesInt) && (date.equals(studentDate)) && (hour == swipeDataHourInt)) {
				lateStudents.add(swipeData.getLogs().get(i));
			}
			else if ((seconds < swipeDataSecondsInt) && (date.equals(studentDate)) && (hour == swipeDataHourInt) && (minutes == swipeDataMinutesInt)) {
				lateStudents.add(swipeData.getLogs().get(i));
			}
			// If there's data for a second class
			else if (swipeDataTime2.equals("placeholder") == false) {
				// Checks to see if any of the times for a student;s second class is earlier and if it occurs on the same date
				if ((hour < swipeDataHour2Int) && (date.equals(studentDate2))) {
					lateStudents.add(swipeData.getLogs().get(i));
				}
				else if ((minutes < swipeDataMinutes2Int) && (date.equals(studentDate2)) && (hour == swipeDataHour2Int)) {
					lateStudents.add(swipeData.getLogs().get(i));
				}
				else if ((seconds < swipeDataSeconds2Int) && (date.equals(studentDate2)) && (hour == swipeDataHour2Int) && (minutes == swipeDataMinutes2Int)) {
					lateStudents.add(swipeData.getLogs().get(i));
				}
			}
		}
		return lateStudents;
	}	
	
	// Returns an array list of the first student in each class
	// Will change the currentQuery in main, the list being returned becomes currentQuery
	// Does not work if a student has two different class dates, which is the case for the 1stAnd2ndClass.txt
	public ArrayList<Log> get_first_student_to_enter() {
		ArrayList<Log> firstStudents = new ArrayList<Log>();
		// Iterates over the swipe data
		for (int i =0; i < swipeData.print_count(); i++) {
			int index = 0;
			// Gets the hours, minutes, and seconds of the current swipData entry and converts them to integers
			String swipeDataTime = swipeData.getLogs().get(i).getClassSection().getKey();
			String swipeDataHour = new StringBuilder().append(swipeDataTime.charAt(0)).append(swipeDataTime.charAt(1)).toString();
			String swipeDataMinutes = new StringBuilder().append(swipeDataTime.charAt(3)).append(swipeDataTime.charAt(4)).toString();
			String swipeDataSeconds = new StringBuilder().append(swipeDataTime.charAt(6)).append(swipeDataTime.charAt(7)).toString();
			int swipeDataHourInt = Integer.parseInt(swipeDataHour);
			int swipeDataMinutesInt = Integer.parseInt(swipeDataMinutes);
			int swipeDataSecondsInt = Integer.parseInt(swipeDataSeconds);
			String swipeDataTime2 = swipeData.getLogs().get(i).getClassSection2().getKey();
			int swipeDataHourInt2 = 0;
			int swipeDataMinutesInt2 = 0;
			int swipeDataSecondsInt2 = 0;
			// If there's a second class
			if (swipeDataTime2.equals("placeholder") == false) {
				String swipeDataHour2 = new StringBuilder().append(swipeDataTime2.charAt(0)).append(swipeDataTime2.charAt(1)).toString();
				String swipeDataMinutes2 = new StringBuilder().append(swipeDataTime2.charAt(3)).append(swipeDataTime2.charAt(4)).toString();
				String swipeDataSeconds2 = new StringBuilder().append(swipeDataTime2.charAt(6)).append(swipeDataTime2.charAt(7)).toString();
				swipeDataHourInt2 = Integer.parseInt(swipeDataHour2);
				swipeDataMinutesInt2 = Integer.parseInt(swipeDataMinutes2);
				swipeDataSecondsInt2 = Integer.parseInt(swipeDataSeconds2);
			}
			// Forward declaration of the hours, minutes, and times of the student entries
			String currentEntryTime = "placeholder";
			String currentEntryHour = "placeholder";
			String currentEntryMinutes = "placeholder";
			String currentEntrySeconds = "placeholder";
			int currentEntryHourInt = 0;
			int currentEntryMinutesInt = 0;
			int currentEntrySecondsInt = 0;
			String currentEntryTime2 = "placeholder";
			String currentEntryHour2 = "placeholder";
			String currentEntryMinutes2 = "placeholder";
			String currentEntrySeconds2 = "placeholder";
			int currentEntryHourInt2 = 0;
			int currentEntryMinutesInt2 = 0;
			int currentEntrySecondsInt2 = 0;
			String swipeDataDate = "placeholder";
			String swipeDataDate2 = "placeholder";
			String dateInArray1 = "placeholder";
			String dateInArray2 = "placeholder";
			boolean found = false;
			// If the array list of first students to enter is empty
			if (firstStudents.size() == 0) {
				firstStudents.add(swipeData.getLogs().get(i));
			}
			else {
				// Iterates over the firstStudents array list
				for (int j = 0; j < firstStudents.size(); j++) {
					swipeDataDate = swipeData.getLogs().get(i).getClassSection().getValue();
					swipeDataDate2 = swipeData.getLogs().get(i).getClassSection2().getValue();
					dateInArray1 = firstStudents.get(j).getClassSection().getValue();
					dateInArray2 = firstStudents.get(j).getClassSection().getValue();
					// If it found an entry with a matching date in the firstStudents array list
					if ((swipeDataDate.equals(dateInArray1)) || ((swipeDataDate2.equals(dateInArray2)) && (swipeDataDate2.equals("placeholder") == false))) {
						found = true;
						index = j;
						currentEntryTime = firstStudents.get(j).getClassSection().getKey();
						currentEntryHour = new StringBuilder().append(currentEntryTime.charAt(0)).append(currentEntryTime.charAt(1)).toString();
						currentEntryMinutes = new StringBuilder().append(currentEntryTime.charAt(3)).append(currentEntryTime.charAt(4)).toString();
						currentEntrySeconds = new StringBuilder().append(currentEntryTime.charAt(6)).append(currentEntryTime.charAt(7)).toString();
						currentEntryHourInt = Integer.parseInt(currentEntryHour);
						currentEntryMinutesInt = Integer.parseInt(currentEntryMinutes);
						currentEntrySecondsInt = Integer.parseInt(currentEntrySeconds);
						currentEntryTime2 = firstStudents.get(j).getClassSection2().getKey();
						if (currentEntryTime2.equals("placeholder") == false) {
							currentEntryHour2 = new StringBuilder().append(currentEntryTime2.charAt(0)).append(currentEntryTime2.charAt(1)).toString();
							currentEntryMinutes2 = new StringBuilder().append(currentEntryTime2.charAt(3)).append(currentEntryTime2.charAt(4)).toString();
							currentEntrySeconds2 = new StringBuilder().append(currentEntryTime2.charAt(6)).append(currentEntryTime2.charAt(7)).toString();
							currentEntryHourInt2 = Integer.parseInt(currentEntryHour2);
							currentEntryMinutesInt2 = Integer.parseInt(currentEntryMinutes2);
							currentEntrySecondsInt2 = Integer.parseInt(currentEntrySeconds2);
						}
					}
				}
				// If it could not find the date in the firstStudents array list
				if (found == false) {
					firstStudents.add(swipeData.getLogs().get(i));
				}
				else {
					if (swipeDataDate.equals(dateInArray1)) {
						// Checks to see if any of the times for this swipeData entry is earlier than the entry in firstStudents with the same date
						if ((swipeDataHourInt < currentEntryHourInt)) {
							firstStudents.set(index, swipeData.getLogs().get(i));
						}
						else if ((swipeDataMinutesInt < currentEntryMinutesInt) && (swipeDataHourInt == currentEntryHourInt)) {
							firstStudents.set(index, swipeData.getLogs().get(i));
						}
						else if ((swipeDataSecondsInt < currentEntrySecondsInt) && (swipeDataHourInt == currentEntryHourInt) && (swipeDataMinutesInt == currentEntryMinutesInt)) {
							firstStudents.set(index, swipeData.getLogs().get(i));
						}
					}
					else {
						// If there's a second date and the first and second date don't share the same date, and the second date in the swipe data m,matches the second date of the entry
						// in firstStudents
						if ((dateInArray2.equals("placeholder") == false) && (dateInArray2.equals(dateInArray1) == false) && (swipeDataDate2.equals(dateInArray2))) {
							if ((swipeDataHourInt2 < currentEntryHourInt2)) {
								firstStudents.set(index, swipeData.getLogs().get(i));
							}
							else if ((swipeDataMinutesInt2 < currentEntryMinutesInt2) && (swipeDataHourInt2 == currentEntryHourInt2)) {
								firstStudents.set(index, swipeData.getLogs().get(i));
							}
							else if ((swipeDataSecondsInt2 < currentEntrySecondsInt2) && (swipeDataHourInt2 == currentEntryHourInt2) && (swipeDataMinutesInt2 == currentEntryMinutesInt2)) {
								firstStudents.set(index, swipeData.getLogs().get(i));
							}
						}
					}
				}
			}
		}
		return firstStudents;
	}
	
	// Prints the attendance data for a particular student
	// Will not change the currentQuery in main, the previous one will remain
	public void print_attendance_data_for_student(Student currStudent) {
		System.out.println("***** Looking up Student Attandance Data *****");
		boolean found = false;
		// Iterates through swipeData
		for (int i = 0; i < swipeData.print_count(); i++) {
			String studentFirstName = currStudent.getFirstName();
			String studentLastName = currStudent.getLastName();
			String swipeDataFirstName = swipeData.getLogs().get(i).getFirstName();
			swipeDataFirstName = swipeDataFirstName.substring(0, swipeDataFirstName.length()-1);
			String swipeDataLastName = swipeData.getLogs().get(i).getLastName();
			// If it finds a matching name in the swipeData with the name of the student passed in
			if ((swipeDataFirstName.equals(studentFirstName)) && (swipeDataLastName.equals(studentLastName))) {
				// If there is no second class or swipe out data
				if (swipeData.getLogs().get(i).getClassSection2().getKey().equals("placeholder")) {
					System.out.println(swipeData.getLogs().get(i).getLastName() + " " + swipeData.getLogs().get(i).getFirstName() + " ['" + swipeData.getLogs().get(i).getClassSection().getKey() + 
					" " + swipeData.getLogs().get(i).getClassSection().getValue() + "']");
				}
				else {
					System.out.println(swipeData.getLogs().get(i).getLastName() + " " + swipeData.getLogs().get(i).getFirstName() + " ['" + swipeData.getLogs().get(i).getClassSection().getKey() + 
					" " + swipeData.getLogs().get(i).getClassSection().getValue() + "' , '" + swipeData.getLogs().get(i).getClassSection2().getKey() + " " + 
					swipeData.getLogs().get(i).getClassSection2().getValue() + "']");					
				}
				found = true;
			} 
		}
		// If it could not find the student passed in
		if (found == false) {
			System.out.println("No student of this name in attendance log\n");
		}
	}
	
	// Returns a boolean depending on if a student has swiped in
	// Will not change the currentQuery in main, the previous one will remain
	public boolean is_present(Student currStudent, String date) {
		System.out.println("***** Looking to See if Student was Present *****");
		for (int i = 0; i < swipeData.print_count(); i++) {
			String studentFirstName = currStudent.getFirstName();
			String studentLastName = currStudent.getLastName();
			String swipeDataFirstName = swipeData.getLogs().get(i).getFirstName();
			swipeDataFirstName = swipeDataFirstName.substring(0, swipeDataFirstName.length()-1);
			String swipeDataLastName = swipeData.getLogs().get(i).getLastName();
			// If it finds a swipeData entry with a matching name and if one of the dates is also matching
			if ((swipeDataFirstName.equals(studentFirstName)) && (swipeDataLastName.equals(studentLastName))) { 
				if ((swipeData.getLogs().get(i).getClassSection().getValue().equals(date)) || (swipeData.getLogs().get(i).getClassSection2().getValue().equals(date))) {
					return true;
				}
			}
		}
		return false;
	}
	
	// Returns a list with all the students who checked in on a specific date
	// Will change the currentQuery in main, the list being returned becomes currentQuery
	public ArrayList<Log> list_all_students_checked_in(String date) {
		ArrayList<Log> studentsPresent = new ArrayList<Log>();
		System.out.println("***** Students Present On This Date *****");
		for (int i = 0; i < swipeData.print_count(); i++) { 
			// If it finds a matching date for either class section for the current swipeLog, it will get added to the array list
			if ((swipeData.getLogs().get(i).getClassSection().getValue().equals(date)) || (swipeData.getLogs().get(i).getClassSection2().getValue().equals(date))) {
				studentsPresent.add(swipeData.getLogs().get(i));
			}
		}
		return studentsPresent;
	}
	
	// Returns a list of students who checked in before a certain time on a specific date
	// Will change the currentQuery in main, the list being returned becomes currentQuery
	public ArrayList<Log> list_all_students_checked_in_before(String time, String date) {
		System.out.println("***** Those present on the given date and before the given time *****");
		ArrayList<Log> earlyStudents = new ArrayList<Log>();
		int swipeDataHour2Int = 0;
		int swipeDataMinutes2Int = 0;
		int swipeDataSeconds2Int = 0;
		// The following converts the hours, minutes, and seconds for the time parameter into an int 
		String stringHour = new StringBuilder().append(time.charAt(0)).append(time.charAt(1)).toString();
		String stringMinutes = new StringBuilder().append(time.charAt(3)).append(time.charAt(4)).toString();
		String stringSeconds = new StringBuilder().append(time.charAt(6)).append(time.charAt(7)).toString();
		int hour = Integer.parseInt(stringHour);
		int minutes = Integer.parseInt(stringMinutes);
		int seconds = Integer.parseInt(stringSeconds);
		for (int i = 0; i < swipeData.print_count(); i++) {
			// Gets the time and date for each swipeData entry
			// Converts the hour, minutes, and seconds for the first class swipe in for each log into an int
			String swipeDataTime1 = swipeData.getLogs().get(i).getClassSection().getKey();
			String swipeDataTime2 = swipeData.getLogs().get(i).getClassSection2().getKey();
			String swipeDataHour = new StringBuilder().append(swipeDataTime1.charAt(0)).append(swipeDataTime1.charAt(1)).toString();
			String swipeDataMinutes = new StringBuilder().append(swipeDataTime1.charAt(3)).append(swipeDataTime1.charAt(4)).toString();
			String swipeDataSeconds = new StringBuilder().append(swipeDataTime1.charAt(6)).append(swipeDataTime1.charAt(7)).toString();
			// if the second swipe in time is not "placeholder", converts the hours, minute, and seconds into an int 
			if (swipeDataTime2.equals("placeholder") == false) {
				String swipeDataHour2 = new StringBuilder().append(swipeDataTime2.charAt(0)).append(swipeDataTime2.charAt(1)).toString();
				String swipeDataMinutes2 = new StringBuilder().append(swipeDataTime2.charAt(3)).append(swipeDataTime2.charAt(4)).toString();
				String swipeDataSeconds2 = new StringBuilder().append(swipeDataTime2.charAt(6)).append(swipeDataTime2.charAt(7)).toString();
				swipeDataHour2Int = Integer.parseInt(swipeDataHour2);
				swipeDataMinutes2Int = Integer.parseInt(swipeDataMinutes2);
				swipeDataSeconds2Int = Integer.parseInt(swipeDataSeconds2);
			}
			int swipeDataHourInt = Integer.parseInt(swipeDataHour);
			int swipeDataMinutesInt = Integer.parseInt(swipeDataMinutes);
			int swipeDataSecondsInt = Integer.parseInt(swipeDataSeconds);
			String studentFirstName = swipeData.getLogs().get(i).getFirstName();
			studentFirstName = studentFirstName.substring(0, studentFirstName.length()-1);
			String studentDate = swipeData.getLogs().get(i).getClassSection().getValue();
			String studentDate2 = swipeData.getLogs().get(i).getClassSection2().getValue();
			// Checks to see if any of the times for a student is earlier and if it occurs on the same date
			if ((hour > swipeDataHourInt) && (date.equals(studentDate))) {
				earlyStudents.add(swipeData.getLogs().get(i));
			}
			else if ((minutes > swipeDataMinutesInt) && (date.equals(studentDate)) && (hour == swipeDataHourInt)) {
				earlyStudents.add(swipeData.getLogs().get(i));
			}
			else if ((seconds > swipeDataSecondsInt) && (date.equals(studentDate)) && (hour == swipeDataHourInt) && (minutes == swipeDataMinutesInt)) {
				earlyStudents.add(swipeData.getLogs().get(i));
			}
			// If there's data for a second class
			else if (swipeDataTime2.equals("placeholder") == false) {
				// Checks to see if any of the times for a student;s second class is earlier and if it occurs on the same date
				if ((hour > swipeDataHour2Int) && (date.equals(studentDate2))) {
					earlyStudents.add(swipeData.getLogs().get(i));
				}
				else if ((minutes > swipeDataMinutes2Int) && (date.equals(studentDate2)) && (hour == swipeDataHour2Int)) {
					earlyStudents.add(swipeData.getLogs().get(i));
				}
				else if ((seconds > swipeDataSeconds2Int) && (date.equals(studentDate2)) && (hour == swipeDataHour2Int) && (minutes == swipeDataMinutes2Int)) {
					earlyStudents.add(swipeData.getLogs().get(i));
				}
			}
		}
		return earlyStudents;
	}
	
	// Returns a list of students who attended a certain number of classes
	// Will change the currentQuery in main, the list being returned becomes currentQuery
	public ArrayList<Log> list_students_attendance_count(int classesAttended) {
		ArrayList<Log> presentStudents = new ArrayList<Log>();
		// Iterates over the swipe data
		for (int i = 0; i < swipeData.print_count(); i++) {
			// If we are looking for students who attended 1 class
			if (classesAttended == 1) {
				// if there is no data for a second class or there is swipe out data
				if ((swipeData.getLogs().get(i).getClassSection2().getValue().equals("placeholder")) || 
				(swipeData.getLogs().get(i).getClassSection2().getValue().equals(swipeData.getLogs().get(i).getClassSection().getValue())))  {
					presentStudents.add(swipeData.getLogs().get(i));
				}
			}
			// If we are looking for students who attended 2 classes
			else {
				// if there is data for a second class and both classes have different dates
				if ((swipeData.getLogs().get(i).getClassSection2().getValue().equals("placeholder") == false) && 
				(swipeData.getLogs().get(i).getClassSection2().getValue().equals(swipeData.getLogs().get(i).getClassSection().getValue()) == false)) {
					presentStudents.add(swipeData.getLogs().get(i));
				}
			}
		}
		return presentStudents;
	}
	
	// Prints out the currentQuery
	// Will not change the currentQuery in main, the previous one will remain
	// Will only work for an arrayList of logs
	// So if a function does not return an array of logs, the query is not changed
	// For example, if a query is already in place and from the main menu, print_attendance_data_for_student() is called, the query will remain the same as before
	public void print_query_list(ArrayList<Log> list) {
		System.out.println("***** All Students In The Query *****");
		for (int i = 0; i < list.size(); i++) {
			// If one of the member variables is still placeholder, don't output the second date/time
			if (list.get(i).getClassSection2().getKey().equals("placeholder")) {
				System.out.println(list.get(i).getLastName() + " " + list.get(i).getFirstName() + " ['" + list.get(i).getClassSection().getKey() + " " + list.get(i).getClassSection().getValue() + "']");
			}
			else {
				System.out.println(list.get(i).getLastName() + " " + list.get(i).getFirstName() + " ['" + list.get(i).getClassSection().getKey() + " " + list.get(i).getClassSection().getValue() + "' , '" 
			+ list.get(i).getClassSection2().getKey() + " " + list.get(i).getClassSection2().getValue() + "']");	
			}
		}
	}
	
	// Prints the number of elements in the currentQuery
	// Will not change the currentQuery in main, the previous one will remain
	// Will always display the number of elements in the arryList of logs passed in
	public void print_count(ArrayList<Log> list) {
		System.out.println("There were " + list.size() + " records for this query");
	}
}

