// Programmer: Luke L
import java.io.*;
import java.util.*;

public class CourseDBStructure implements CourseDBStructureInterface
{
	// fields
	int classesSize = 0;
	private LinkedList<CourseDBElement>[] lyst; // declare array of LinkedLists of Strings
	//ArrayList<String> courselist = new ArrayList<String>();
	
	// constructors
	// testing constructor
	public CourseDBStructure(String testing, int size)
	{
		classesSize = size;
		lyst = new LinkedList[size]; // instantiate the array with 5 indexes, change later to number of courses going into constructor
		for (int i=0; i<size; i++) // create array of LinkedLists
		{
			lyst[i] = new LinkedList<CourseDBElement>();
		}
	}
	
	public CourseDBStructure(int size)
	{
		classesSize = size;
		lyst = new LinkedList[size]; // instantiate the array with 5 indexes, change later to number of courses going into constructor
		for (int i=0; i<size; i++) // create array of LinkedLists
		{
			lyst[i] = new LinkedList<CourseDBElement>();
		}
	}
	
	// methods
	// Adds a CourseDBElement object to the CourseDBStructure using the hashcode
	// of the CourseDatabaseElemen object's crn value.
	// If the CourseDatabaseElement already exists, exit quietly 
	// @param element the CourseDBElement to be added to CourseDBStructure
	public void add(CourseDBElement element)
	{
		int place = makeHash(element.getCrn())%classesSize; // creates array index from hash number
		//int place = element.getCrn()%classesSize; // creates hash number
		if(lyst[place].size() == 0) // adds first student to the LinkedList bucket
		{
			lyst[place].add(element); // adds name to the LinkedList at that array index (from the hash number)
			//courselist.add(lyst[place].get(0).toString());
			//System.out.println("First student added: " + student.getName());
			//System.out.println(lyst[place].get(element));
		}
		else
		{
			boolean alreadyThere = false;
			boolean updated = false;
			for (int i=0; i<lyst[place].size(); i++)
			{
				if(lyst[place].get(i).compareTo(element) == 0 && !updated) // compares 2 elements for equality, to prevent duplicate entries
				{

					lyst[place].set(i, element);
					alreadyThere = true;
					updated = true;
					//System.out.println("hi");
					//System.out.println(lyst[place].toString());
				}
			}
			if(!alreadyThere)
			{
				lyst[place].add(element); // adds name to the LinkedList at that array index (from the hash number)
				//courselist.add(lyst[place].getLast().toString());
				//System.out.println("Another student added: " + student.getName());
			}
		}
	}
	
	// Find a courseDatabaseElement based on the key (crn) of the courseDatabaseElement 
	// If the CourseDatabaseElement is found return it If not, throw an IOException
	// @param crn crn (key) whose associated courseDatabaseElement is to be returned
	// @return a CourseDBElement whose crn is mapped to the key
	// @throws IOException if key is not found
	public CourseDBElement get(int crn) throws IOException
	{
		int place = makeHash(crn)%classesSize; // creates array index from hash number
		//int place = crn%classesSize; // creates hash number
		CourseDBElement holder = new CourseDBElement();
		for (int i=0; i<lyst[place].size(); i++)
		{
			if(lyst[place].get(i).getCrn() == crn) // compares 2 students for equality, to prevent duplicate entries
				holder = lyst[place].get(i);
		}
		
		if(holder == null) // if no Element is found, throw IOException
		{
			throw new IOException();
		}

		return holder;
	}
	
	// @return an array list of string representation of each course in the data structure separated by a new line. 
	// Refer to the following example:
	// Course:CMSC500 CRN:39999 Credits:4 Instructor:Nobody InParticular Room:SC100
	// Course:CMSC600 CRN:4000 Credits:4 Instructor:Somebody Room:SC200
	public ArrayList<String> showAll()
	{		
		ArrayList<String> courselist = new ArrayList<String>();

		
		for (int i=classesSize-1; i>=0; i--)
		{
			for (int j=0; j<lyst[i].size(); j++)
			{
				courselist.add(lyst[i].get(j).toString());
				
			}
		}
		
		//System.out.println(courselist);
		
		// for (int i=0; i<5; i++) starts at front of array
		// for (int i=4; i>=0; i--) starts at back of array
		//for (int j=lyst[i].size()-1; j>=0; j--) reverse gather the bucket
		//Collections.reverse(courselist); // use this if just adding to Arraylist
		return courselist;
	}
	
	// Returns the size of the ConcordanceDataStructure (number of indexes in the array)
	// @return an int of the table size
	public int getTableSize()
	{
		return lyst.length;
	}
	
	// returns hash code int from Crn input
	// @param an int crn
	// @return an int hash code
	public int makeHash(int crn) 
	{
		String holder = Integer.toString(crn); // going from int to String		
		return(holder.hashCode()); // generate hashcode from String
	}
}
