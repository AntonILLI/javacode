package Tasks;

import java.awt.GridLayout;     //
import java.util.ArrayList;		//
import java.util.Scanner;		//
import javax.swing.BoxLayout;	// This part includes all imported libraries
import javax.swing.JLabel;		//
import javax.swing.JOptionPane; //
import javax.swing.JPanel;		//
import javax.swing.JTextField;	//

class Student { //main class Student that we will use to build instances of object-students
	
	String name;		//		
	int index = 0;		//
	int courseMarks1;	//
	int courseMarks2;	// These fields are used to build an instance of an object-student
	int courseMarks3;	//
	int average;		//
	char grd;			//
	
	//name constructor
	public Student (String stdName) {
		name = stdName;
	}
	//course marks constructor
	public Student (int cm1, int cm2, int cm3) {
		courseMarks1 = cm1;
		courseMarks2 = cm2;
		courseMarks3 = cm3;
	}
	//default output method
	public String toString(){
	    return name + " " + courseMarks1 + " " + courseMarks2
	    		+ " " + courseMarks3 + " " + average + " " + grd;
	  	}
	
}//Student class end bracket

class InputPanel {//class Input Panel is used to create a platform for user to input the information on course marks
	
	int Mark1;		//
	int Mark2;		// fields used to hold marks of each student
	int Mark3;		// per 3 courses and calculated average
	int averageMark;//
	
	public InputPanel() {
	JTextField inputMark1 = new JTextField("0", 5);
    JTextField inputMark2 = new JTextField("0", 5);
    JTextField inputMark3 = new JTextField("0", 5);

    JPanel myPanel = new JPanel();
    myPanel.setLayout(new GridLayout(3, 1));
    myPanel.add(new JLabel("Course 1:"));	myPanel.add(inputMark1, "0");
    myPanel.add(new JLabel("Course 2:"));   myPanel.add(inputMark2, "0");
    myPanel.add(new JLabel("Course 3:"));   myPanel.add(inputMark3, "0");
    
    int result = JOptionPane.showConfirmDialog(null, myPanel, "Enter course marks", JOptionPane.OK_CANCEL_OPTION);
    
    if (result == JOptionPane.OK_OPTION) {
       
       String text1 = inputMark1.getText();	Mark1 = Integer.parseInt(text1);
       String text2 = inputMark2.getText(); Mark2 = Integer.parseInt(text2);
       String text3 = inputMark3.getText(); Mark3 = Integer.parseInt(text3);
       
       int averageCalculated = (Mark1 + Mark2 + Mark3) / 3;
       averageMark = averageCalculated; 
    }

}//InputPanel method end bracket
}//InputPanel class  end bracket

class WorkingPanel {//this class is created in order to build a platform for user to interact with database
	
	int userInput;// it has only one field that will control the program execution in the main part
	
	public WorkingPanel() {
		
		JPanel workingPanel = new JPanel();
	
		workingPanel.setLayout(new BoxLayout(workingPanel, BoxLayout.PAGE_AXIS));
		workingPanel.add(new JLabel("==========================================================="                  ));
		workingPanel.add(new JLabel("Input [1]: Print the list of students"                  					   ));
		workingPanel.add(new JLabel("Input [2]: Sort and print the list alphabetically"      					   ));
		workingPanel.add(new JLabel("Input [3]: Sort and print the list in descending order based on the average"  ));
		workingPanel.add(new JLabel("Input [4]: Find the student by the key of average"	   					       ));
		workingPanel.add(new JLabel("Input [5]: Find the student who has the minimum average"					   ));
		workingPanel.add(new JLabel("Input [6]: Print the grade distribution"                					   ));
		workingPanel.add(new JLabel("Input [0]: Exit"                                        					   ));
		workingPanel.add(new JLabel("==========================================================="                  ));
		String input = JOptionPane.showInputDialog(null, workingPanel);
		
		if (input == null) {} else userInput = Integer.parseInt(input);
		
}//WorkingPanel method end bracket
}//WorkingPanel class  end bracket

class IdentifyGrade {// the class is made to hold a method to determine student grade based on average

	char gradeIdentified;
	
	public IdentifyGrade(int avrg) {
		char grade = 'N';
		     if (avrg >= 90) {grade = 'A';}
		else if (avrg >= 80) {grade = 'B';}
		else if (avrg >= 70) {grade = 'C';}
		else if (avrg >= 60) {grade = 'D';}
		else if (avrg <  60) {grade = 'F';}
		     gradeIdentified = grade;

}//IdentifyGrade method end bracket
}//IdentifyGrade class  end bracket

public class Task3_v2 {//main class starts
	//Binary search method is defined here and called later in the code
	public static int BinarySearch(ArrayList<Student> list, int key, int size, boolean goRight) {
	
	int low = 0;
	int high = list.size() - 1;
	int result = -1;
	
	while (low <= high) {
	int mid = low + (high - low) / 2;		
			if (key == list.get(mid).average) {
				result = mid;
				if (goRight == false) low = mid + 1;
				else high = mid - 1;
			}
			else if (key < list.get(mid).average) {
				high = mid - 1;
			}
			else low = mid + 1;
		}
		
	return result;
	}
	
	public static void main(String[] args) {//main method starts here

		//declare an array that will hold all students and their parameters
		ArrayList<Student> listOfStudents = new ArrayList<Student>();
				
				//start a loop that will ask user to input data for every student and confirm if more instances needed
				for(int i = 20; i != 0 ; i -= 1) {
					int answer = JOptionPane.showConfirmDialog(null, "Do you want to add a student?"
							+ " [ " + i + " ] input(s) left", "Student Add Window", JOptionPane.YES_NO_OPTION );
					
					if (answer == JOptionPane.YES_OPTION) {
					
					String input = JOptionPane.showInputDialog("Please, enter student name", "UnknownName");
					Student next = new Student(input);
					
					next.index++;
					
					InputPanel userInput = new InputPanel();
					next.courseMarks1 = userInput.Mark1;
					next.courseMarks2 = userInput.Mark2;
					next.courseMarks3 = userInput.Mark3;
					next.average = userInput.averageMark;
					
					IdentifyGrade studentGrade = new IdentifyGrade(next.average);
					next.grd = studentGrade.gradeIdentified;
					
					listOfStudents.add(next);
					} else break;
				}
				
				//declare a while loop that will make user panel pop up after every entry till 0 is typed
				boolean workIsDone = false;
				while (workIsDone == false) {
				WorkingPanel testRun = new WorkingPanel();
				
				//start a switch command that will compute and output an according request
				switch(testRun.userInput) {
				
			case 1: //////------====== Print the entire list ======------//////
						
						System.out.println("*****************************************************");
						System.out.println("                   You input: " + testRun.userInput   );
						System.out.println("*****************************************************");
						
						System.out.format("%-16s%-8s%-8s%-8s%-8s%-8s\n", "Student", "Mark1", "Mark2", "Mark3", "Average", "Grade");
						System.out.println("*****************************************************");
						for (int i = 0; i < listOfStudents.size(); i++) {
						System.out.format("%-16s%-8s%-8s%-8s%-8s%-8s\n",
								listOfStudents.get(i).name,
								listOfStudents.get(i).courseMarks1,
								listOfStudents.get(i).courseMarks2,
								listOfStudents.get(i).courseMarks3,
								listOfStudents.get(i).average,
								listOfStudents.get(i).grd);
						}
						break;
						
			case 2: //////------====== Sort the list alphabetically and print ======------//////
				
						// - version 2.0 - //
						// Introducing SelectionSorting //
						System.out.println("*****************************************************");
						System.out.println("                   You input: " + testRun.userInput   );
						System.out.println("*****************************************************");
					    
					    ArrayList<Student> listCaseTwo = (ArrayList<Student>) listOfStudents.clone();
					    
					    for (int i = 0; i < listCaseTwo.size(); i++) {
					    
					    String min = listCaseTwo.get(i).name;
			    	
			    		for (int j = i + 1; j < listCaseTwo.size(); j++) {
			    		if (min.compareTo(listCaseTwo.get(j).name) >= 0) {
			    			min = listCaseTwo.get(j).name;
			    			Student temp = listCaseTwo.get(i);
			    			listCaseTwo.set(i, listCaseTwo.get(j));
			    			listCaseTwo.set(j, temp);
			    			}
			    
			    			}
			    		
					    }
			    
			    
					    System.out.format("%-16s%-8s%-8s%-8s%-8s%-8s\n", "Student", "Mark1", "Mark2", "Mark3", "Average", "Grade");
					    System.out.println("*****************************************************");
					    for (int i = 0; i < listOfStudents.size(); i++) {
					    System.out.format("%-16s%-8s%-8s%-8s%-8s%-8s\n",
					    		listCaseTwo.get(i).name,
					    		listCaseTwo.get(i).courseMarks1,
					    		listCaseTwo.get(i).courseMarks2,
					    		listCaseTwo.get(i).courseMarks3,
					    		listCaseTwo.get(i).average,
					    		listCaseTwo.get(i).grd);
					    }
					    break;
					    
			case 3: //////------====== Sort by average in descending order and print ======------//////

				// - version 2.0 - //
				// Introducing InsertionSorting //				

						System.out.println("*****************************************************");
						System.out.println("                   You input: " + testRun.userInput   );
						System.out.println("*****************************************************");

						
						ArrayList<Student> listCaseThree = (ArrayList<Student>) listOfStudents.clone();
						ArrayList<Student> listSorted3 = new ArrayList<Student>();
						listSorted3.add(listCaseThree.get(0));
						
						
						for (int i = 1; i < listCaseThree.size(); i++) {
						boolean added = false;
							for (int j = 0; j < listSorted3.size(); j++) {
							
									if (listCaseThree.get(i).average >= listSorted3.get(j).average) {
									listSorted3.add(j, listCaseThree.get(i));
									added = true;
									break;
									}
							}
						if (added == false)listSorted3.add(listCaseThree.get(i));
						}
						
						System.out.format("%-16s%-8s%-8s%-8s%-8s%-8s\n", "Student", "Mark1", "Mark2", "Mark3", "Average", "Grade");
						System.out.println("*****************************************************");
						for (int i = 0; i < listSorted3.size(); i++) {
						System.out.format("%-16s%-8s%-8s%-8s%-8s%-8s\n",
								listSorted3.get(i).name,
								listSorted3.get(i).courseMarks1,
								listSorted3.get(i).courseMarks2,
								listSorted3.get(i).courseMarks3,
								listSorted3.get(i).average,
								listSorted3.get(i).grd);
						}
						break;
						
			case 4: //////------====== Find a student by key average and print ======------//////
						
				// - version 2.0 - //
				// Introducing BinarySearch //	
				
				System.out.println("*****************************************************");
				System.out.println("                   You input: " + testRun.userInput   );
				System.out.println("*****************************************************");

				System.out.println("Please, enter a desired average key:");
								
				ArrayList<Student> listCaseFour = (ArrayList<Student>) listOfStudents.clone();
				ArrayList<Student> listSorted4 = new ArrayList<Student>();
				listSorted4.add(listCaseFour.get(0));
				
				for (int i = 1; i < listCaseFour.size(); i++) {
				boolean added = false;
					for (int j = 0; j < listSorted4.size(); j++) {
					
							if (listCaseFour.get(i).average <= listSorted4.get(j).average) {
								listSorted4.add(j, listCaseFour.get(i));
							added = true;
							break;
							}
					}
				if (added == false)listSorted4.add(listCaseFour.get(i));
				}
				
				Scanner input = new Scanner(System.in);
				int key = input.nextInt();
				
				
				int start = BinarySearch(listSorted4, key, listSorted4.size(), true);
				int end = BinarySearch(listSorted4, key, listSorted4.size(), false);
			    
				if (start == -1 && end == -1) {
					System.out.println("No key average was found in the list");
					break;
				} else
				
			    System.out.format("%-16s%-8s%-8s%-8s%-8s%-8s\n", "Student", "Mark1", "Mark2", "Mark3", "Average", "Grade");
				System.out.println("*****************************************************");
				for (int i = start; i <= end; i++) {
			    System.out.format("%-16s%-8s%-8s%-8s%-8s%-8s\n",
			    		listSorted4.get(i).name,
			    		listSorted4.get(i).courseMarks1,
			    		listSorted4.get(i).courseMarks2,
			    		listSorted4.get(i).courseMarks3,
			    		listSorted4.get(i).average,
			    		listSorted4.get(i).grd);
			    }
			    break;
				    
			case 5://////------====== Find minimum average and print ======------//////

				// - version 2.0 - //
				// Introducing Linear(Sequential)Search //
				
				System.out.println("*****************************************************");
				System.out.println("                   You input: " + testRun.userInput   );
				System.out.println("*****************************************************");

				
				ArrayList<Student> listCaseFive = (ArrayList<Student>) listOfStudents.clone();
				
				int min = listCaseFive.get(0).average;
				
				for (int i = 0; i < listCaseFive.size(); i++) {
					if (listCaseFive.get(i).average <= min) {
					min = listCaseFive.get(i).average;	
					}
				}
				
				ArrayList<Integer> minIndex = new ArrayList<Integer>();
				
				for (int j = 0; j < listCaseFive.size(); j++) {
					if (listCaseFive.get(j).average == min) {
					minIndex.add(j);	
					}
				}
				//System.out.println("*****************************************************");
				for (int k = 0; k < minIndex.size(); k++) {
					int index = minIndex.get(k);
					System.out.format("Student with min average is: " + "%-15s" + "Average: " + "%d%n", listOfStudents.get(index).name, listOfStudents.get(index).average);
					}		    
					break;
				    
			case 6://////------====== Print grade distribution (statistics) ======------//////
				
				System.out.println("*****************************************************");
				System.out.println("                   You input: " + testRun.userInput   );
				System.out.println("*****************************************************");

										    int counterA = 0;
										    int counterB = 0;
										    int counterC = 0;
										    int counterD = 0;
										    int counterF = 0;
						for(int i=0; i < listOfStudents.size(); i++) {
					    	 if (listOfStudents.get(i).grd == 'A') {counterA++;}
					    else if (listOfStudents.get(i).grd == 'B') {counterB++;}
					    else if (listOfStudents.get(i).grd == 'C') {counterC++;}
					    else if (listOfStudents.get(i).grd == 'D') {counterD++;}
					    else if (listOfStudents.get(i).grd == 'F') {counterF++;}
					    }
						//System.out.println("*****************************************************");
						System.out.println("Grade distribution: A = " + counterA
					    								 +   "; B = " + counterB
														 +   "; C = " + counterC
			 											 +   "; D = " + counterD
														 +   "; F = " + counterF);
						break;
				    
			case 0://////------====== Exit the program ======------//////
					
				System.out.println("*****************************************************");
				System.out.println("                   You input: " + testRun.userInput   );
				System.out.println("*****************************************************");
						System.out.println("This is the end of the program…Thank you");
						workIsDone = true;
						break;					
				}//switch closing bracket
				}//while closing bracket bracket	
}//main method end bracket
}//main class end bracket