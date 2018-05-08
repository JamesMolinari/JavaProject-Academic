// -----------------------------------------------------
// Assignment 4
// Written by: James Molinari (27801866)
// ----------------------------------------------------

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.ArrayList;


public class EnrolmentResults {
	
public static CourseList processFilesForValidation(Scanner inFileStreamName){
		
	
		CourseList cl3 = new CourseList();
		String s1, courseID="", courseName="", preReqID="", coReqID="";
		double credit = 0;
		int x = 0;
		
		while(inFileStreamName.hasNextLine())
		{
			s1 = inFileStreamName.next();
			if (s1.contains("COMP")){
				courseID = s1;
				courseName = inFileStreamName.next();
				credit = inFileStreamName.nextDouble();
				s1 = inFileStreamName.next();
				s1 = inFileStreamName.nextLine();
				if (s1.isEmpty())
					preReqID = null;
				else
					preReqID = s1.trim();
				s1 = inFileStreamName.next();
				s1 = inFileStreamName.nextLine();
				if (s1.isEmpty())
					coReqID = null;
				else
					coReqID = s1.trim();
				
				Course c1 = new Course(courseID, courseName, credit, preReqID, coReqID);
				if (cl3.contains(courseID)){
					x--;
				}
				else
					cl3.append(c1);
				
			}
				x++;

	
		}
		inFileStreamName.close();
		return cl3;
}


public static void checkRequested(Scanner inFileStreamName, CourseList clx){
	
	
	ArrayList<String> finished = new ArrayList<String>();
	ArrayList<String> requested = new ArrayList<String>();
	
	
	String s1, s2, grb="", courseID="", courseName="", preReqID="", coReqID="";
	double credit = 0;
	
	inFileStreamName.nextLine();
	while(inFileStreamName.hasNextLine())
	{
		s2 = inFileStreamName.nextLine();
		if (s2.contains("Requested"))
			break;
		finished.add(s2);		

	}
	while(inFileStreamName.hasNextLine())
	{

		s2 = inFileStreamName.nextLine();
		requested.add(s2);		
	}		
	/*
    for(int i = 0; i < finished.size(); i++) {
        System.out.println(finished.get(i));
    }
	System.out.println("<-------------------------------------->");
    for(int i = 0; i < requested.size(); i++) {
        System.out.println(requested.get(i));
    }
    */
	
	System.out.println();
	String y;
	for (int i = 0; i<requested.size(); i++){
		String[] finish = new String[requested.size()];
		y = requested.get(i);
		String search1 = clx.find(y).getCourse().getPreReqID();
		String search2 = clx.find(y).getCourse().getCoReqID();
		search1 = search1.trim();
		search2 = search2.trim();
		
		if(!(search1.equals("")) && !(search2.equals(""))){
			boolean check1 = false;
			for (int x = 0; x<finished.size(); x++){
				finish[x] = finished.get(x);
				//System.out.println(".."+finish[x]+"..");
				if(finish[x].equals(search1) || finish[x].equals(search2)){
					if (check1==true){
						System.out.println("Student can enroll in " + y + " course as he/she has completed the pre-requesite " + search1 + " and is enrolling for co-requesite " + search2 + ".");
					}
					else if (x==finished.size()-1){
						System.out.println("Student can't enroll in " + y + " course as he/she doesn't have sufficient background needed.");
					}
					check1 = true;
				}
			if (check1==false && x==finished.size()-1){
				System.out.println("Student can't enroll in " + y + " course as he/she doesn't have sufficient background needed.");
			}
			}
			if (0==finished.size())
			{
				System.out.println("Student can't enroll in " + y + " course as he/she doesn't have sufficient background needed.");
			}
		}
		/*
		System.out.println(requested.size());
		boolean b6 = search1.equals("");
		boolean b7 = search2.equals("");
		System.out.println(b6);
		System.out.println(b7);
		boolean b4 = ((!(search1.equals(""))) && (search2.equals("")));
		boolean b5 = (((search1.equals(""))) && !(search2.equals("")));
		System.out.println(b4);
		System.out.println(b5);
		*/
		if( ((!(search1.equals(""))) && (search2.equals(""))) || 	(((search1.equals(""))) && !(search2.equals(""))) ){
			for (int x = 0; x<finished.size(); x++){
				finish[x] = finished.get(x);
				if(finish[x].equals(search1)){
					System.out.println("Student can enroll in " + y + " course as he/she has completed the pre-requesite " + search1 + ".");
				}
				if (finish[x].equals(search2)){
					System.out.println("Student can enroll in " + y + " course as he/she is enrolling for co-requesite " + search2 + ".");
				}
			}
			if (0==finished.size())
			{
				System.out.println("Student can't enroll in " + y + " course as he/she doesn't have sufficient background needed.");
			}
				
		}
		
		if ((((search1.equals(""))) && (search2.equals(""))))
		{
			System.out.println("Student can enroll in " + y + " course as he/she does not need any pre-requesites or co-requesties");
		}
		
	}//end of for
	
	if (requested.size()==0){
		System.out.println("No enrollment courses found.");
	}

		
	}
	


	

	public static void main(String[] args) {
		
		CourseList cl1 = new CourseList();
		
		Scanner sc = null;

		try
		{
			sc = new Scanner(new FileInputStream("Syllabus.txt"));
		}
		catch(FileNotFoundException e)
		{			
			System.out.println("\nPlease check if file exits! Program will terminate.");
			System.exit(0);
		}	

		cl1 = processFilesForValidation(sc);
		
		Scanner reader = new Scanner(System.in);
		System.out.println("What file would you like to process: ");
		String input = reader.nextLine();

		try
		{
			reader = new Scanner(new FileInputStream(input));
		}
		catch(FileNotFoundException e)
		{			
			System.out.println("\nPlease check if file exits! Program will terminate.");
			System.exit(0);
		}	
		
		checkRequested(reader, cl1);
		
		System.out.println();
		System.out.println("Type \"yes\" if you would like to search the syllabus for a course, if not type \"no\" and the system will exit.");
		reader.close();
		Scanner reader1 = new Scanner(System.in);
		input = reader1.nextLine();
		if (input.equals("yes")){
			System.out.println("What course would you like to search for:");
			input = reader1.nextLine();
			cl1.findIT(input);
			reader1.close();
		}else{
			reader.close();
			System.exit(0);
		}
		
		
	}

}
