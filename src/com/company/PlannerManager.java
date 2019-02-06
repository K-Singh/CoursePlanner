package com.company;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The driver class of the application, it allows users to pick from a number of options so that they may add, remove,
 * find information about, and make backups for their Planner.
 */
public class PlannerManager {
	/**
	 * The main method runs a menu driven application which first creates an empty Planner object. The program prompts the
	 * user for a command to execute an operation. Once a command has been chosen, the program may ask the user for
	 * additional information if necessary, and performs the operation.
	 */
    public static void main(String[] args) {
	Planner p = new Planner();
	Planner backup = null;
	String operation = "";
	String options = "(A) Add Course\n" +
			"(G) Get Course\n" +
			"(R) Remove Course\n" +
			"(P) Print Courses in Planner\n" +
			"(F) Filter by Department Code\n" +
			"(L) Look For Course\n" +
			"(S) Size\n" +
			"(B) Backup\n" +
			"(PB) Print Courses in Backup\n" +
			"(RB) Revert to Backup\n" +
			"(Q) Quit";
	Scanner menu = new Scanner(System.in);
	while(!operation.equalsIgnoreCase("q")){
		System.out.print(options + "\n");
		System.out.print("Enter an operation: ");
		operation = menu.nextLine();
		int position;
		String name;
		String dep;
		int code;
		byte sec;
		String instructor;
		Course course;
		switch(operation.toLowerCase()) {
			case "q":
				System.out.print("Program terminated!");
				break;
			case "a":
				try {
					System.out.print("Enter course name: ");
					name = menu.nextLine();
					System.out.print("Enter department: ");
					dep = menu.nextLine();
					System.out.print("Enter course code: ");
					code = menu.nextInt();
					menu.nextLine();
					System.out.print("Enter course section: ");
					sec = menu.nextByte();
					menu.nextLine();
					System.out.print("Enter course instructor: ");
					instructor = menu.nextLine();
					System.out.print("Enter position: ");
					position = menu.nextInt();
					menu.nextLine();

					course = new Course(name, dep, code, sec, instructor);
					try {
						p.addCourse(course, position - 1);
						System.out.println(dep + " " + code + "." + sec + " added successfully! \n");
					} catch (IllegalArgumentException | FullPlannerException e) {
						System.out.println(e.getMessage());
					}

				}catch(InputMismatchException e){
					menu.nextLine();
					System.out.println("That input is not valid!");
				}
				break;
			case "p":
				p.printAllCourses();
				break;
			case "g":
				System.out.print("Enter the position for the course: ");
				position = menu.nextInt();
				menu.nextLine();
				try {
					Planner.printCourse(p, position - 1);
				}catch (IllegalArgumentException e){
					System.out.println(e.getMessage());
				}
				break;
			case "r":
				System.out.print("Enter the position for the course: ");
				position = menu.nextInt();
				menu.nextLine();
				try {
					course = p.getCourse(position-1);
					dep = course.getDepartment();
					code = course.getCode();
					sec = course.getSection();
					p.removeCourse(position-1);
					System.out.println(dep + " " + code+"."+sec + " removed successfully!");
				}catch(IllegalArgumentException e){
					System.out.println(e.getMessage());
				}
				break;
			case "f":
				System.out.print("Enter the department code: ");
				dep = menu.nextLine();
				Planner.filter(p, dep);
				break;
			case "l":
				System.out.print("Enter course name: ");
				name = menu.nextLine();
				System.out.print("Enter department: ");
				dep = menu.nextLine();
				System.out.print("Enter course code: ");
				code = menu.nextInt();
				menu.nextLine();
				System.out.print("Enter course section: ");
				sec = menu.nextByte();
				menu.nextLine();
				System.out.print("Enter course instructor: ");
				instructor = menu.nextLine();
				Course query = new Course(name,dep,code,sec,instructor);
				for(int i = 0; i < p.size(); i++){
					if(p.getCourse(i) != null){
						if(p.getCourse(i).equals((Course)query)){
							System.out.println(dep+" "+code+"."+sec+" exists at position "+(i+1));
							break;
						}
					}
					if(i == p.size()-1){
						System.out.println("No such course was found!");
					}
				}
				break;
			case "s":
				System.out.println("The planner size is " + p.size());
				break;
			case "b":
				try {
					backup = (Planner)p.clone();
					System.out.println("Created a backup of the current planner");
				}
				catch (IllegalArgumentException e){
					System.out.println(e.getMessage());
				}
				break;
			case "pb":
				if(backup != null){
					backup.printAllCourses();
				}else {
					System.out.println("No backup has been created yet!");
				}
				break;
			case "rb":
				if(backup != null){
					try {
						p = (Planner)backup.clone();
						System.out.println("Reverted back to backup");
					}
					catch (IllegalArgumentException e){
						System.out.println(e.getMessage());
					}
				}else {
					System.out.println("No backup has been created yet!");
				}
			default:
				System.out.println("That option does not exist!");
		}
	}
    }
}
