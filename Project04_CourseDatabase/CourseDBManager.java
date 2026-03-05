// Programmer: Luke Lamborn
import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class CourseDBManager implements CourseDBManagerInterface
{
	// fields
	private CourseDBStructure CourseDBStruc;
	
	// constructor
	public CourseDBManager()
	{
		CourseDBStruc = new CourseDBStructure(200);
	}
	
	// Adds a course (CourseDBElement) with the given information
	// to CourseDBStructure.
	// @param id course id 
	// @param crn course crn
	// @param credits number of credits
	// @param roomNum course room number
	// @param instructor name of the instructor
	public void add(String id, int crn, int credits, String roomNum, String instructor)
	{
				
		CourseDBElement s1 = new CourseDBElement(id, crn, credits, roomNum, instructor);
		// check for validity of elements here? before adding
		if(!s1.isElementValid())
			System.out.println("This course is invalid: " + s1.toString());
		else if(s1.isElementValid())
			CourseDBStruc.add(s1);

	}

	// finds  CourseDBElement based on the crn key
	// @param crn course crn (key)
	// @return a CourseDBElement object
	public CourseDBElement get(int crn)
	{		
		try
		{
			return CourseDBStruc.get(crn);
		}
		catch (IOException e) // if no Element at that key, then Exception thrown
		{
			System.out.println(e.getLocalizedMessage());
			return null;
		}
	}
	
	// Reads the information of courses from a test file and adds them
	// to the CourseDBStructure data structure
	// @param input input file 
	// @throws FileNotFoundException if file does not exists
	public void readFile(File input) throws FileNotFoundException 
	{
		//File input = new File("courses-F23.txt");
		Scanner inputFile = new Scanner(input); 
		//String line = inputFile.nextLine();

		while(inputFile.hasNext())
		{
			String line = inputFile.nextLine();
			if(!line.trim().isEmpty()) // if the line is not empty
			{
				String firstchar = line.substring(0,1);
				if(!firstchar.equals("#")) // if the first char is not "#"
				{
					int cut = line.lastIndexOf("#");
					
					if(cut != -1) // cuts off everything at end from "#" onwards
						line = line.substring(0, cut);
					
					String[] split = line.split("\\s");
					//System.out.print(split[0] + " "); // Id
					String id = split[0];
					//System.out.print(split[1] + " "); // Crn
					String crnString = split[1];
					// removes non-digits so it's safe to send to add method above
					crnString = crnString.replaceAll("[^0-9]", ""); 
					int crn = Integer.parseInt(crnString);
					//System.out.print(split[2] + " "); // credits
					String creditsString = split[2];
					// removes non-digits so it's safe to send to add method above
					creditsString = creditsString.replaceAll("[^0-9]", ""); 
					int credits = Integer.parseInt(creditsString);
					//System.out.print(split[3] + " "); // room
					String room = split[3];
					//System.out.print(split[4] + " "); // instructor
					String inst = split[4];
					if(split.length > 5)
					{
						//System.out.print(split[5] + " "); // instructor
						inst += " " + split[5];
					}
					if(split.length > 6)
					{
						//System.out.print(split[6] + " "); // instructor
						inst += " " + split[6];
					}
					//System.out.println();
					add(id, crn, credits, room, inst);
				}
			}
		}
		inputFile.close();
		
	}
		
	// an array list of string representation of each course in 
	// the data structure separated by a new line. 
	// Refer to the following example:
	// Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular Room:SC100
	// Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody Room:SC200
	public ArrayList<String> showAll()
	{
		/*
		ArrayList<String> courselist = new ArrayList<String>();
		courselist = CourseDBStruc.showAll();
		return courselist;
		*/
		return CourseDBStruc.showAll();
	}
}
