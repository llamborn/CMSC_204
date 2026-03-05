// Programmer: Luke L
import java.util.regex.Pattern;
import java.util.regex.Matcher;
public class CourseDBElement implements Comparable<CourseDBElement>
{
	// fields
	private String id;
	private int crn;
	private int credits;
	private String roomNum;
	private String instructor;
	
	// constructors
	public CourseDBElement()
	{
		
	}
	
	public CourseDBElement(String i, int c, int cred, String r, String inst)
	{
		id = i;
		crn = c;
		credits = cred;
		roomNum = r;
		instructor = inst;
	}
	
	// setter methods
	public void setId(String i)
	{
		id = i;
	}
	
	public void setCRN(int c)
	{
		credits = c;
	}
	
	public void setCredits(int cred)
	{
		crn = cred;
	}
	
	public void setRoomNum(String r)
	{
		roomNum = r;
	}
	
	public void setInstructor(String inst)
	{
		instructor = inst;
	}
	
	// getter methods
	public String getID()
	{
		return id;
	}
	
	public int getCRN()
	{
		return crn;
	}
	
	public int getCrn()
	{
		return crn;
	}
	
	public String getRoomNum()
	{
		return roomNum;
	}
	
	// other methods below
	
	// Returns a String of the course
	// @return a String of the course
	public String toString()
	{
		return ("\nCourse:" + id + " CRN:" + crn + " Credits:" + credits + " Instructor:" + instructor + " Room:" + roomNum);
	}
	
	// Compares two elements to see if identical
	// @param a CourseDBElement
	// @return an int, -1 0 or 1
	public int compareTo(CourseDBElement other)
	{
		return Integer.compare(getCRN(), other.getCRN());
	}
	
	// checks data validation of the id
	// @param String of the id
	// @return boolean
	public boolean idCheck(String holder) // checks for four letters then three digits
	{
		boolean test = true;
		if(holder.length()!=7) // checks for length of 7 characters
		{
			System.out.println("Error: Course Id must be four (4) letters, then three (3) digits, without spaces.");
			test = false;
		}
		else if(holder.length()==7) // if its 7 characters, then do this
		{
			// test first four are alphas
			Pattern patternA = Pattern.compile("^[a-zA-Z]*$"); // check for upper/lower alphas
			Matcher matcherA = patternA.matcher(holder.substring(0,4)); 
			boolean resultA = matcherA.matches();
			if(test==true&&resultA==false)
			{
				System.out.println("Error: Course Id must begin with four (4) characters.");
				test = false;
			}
			
			// test next three are numbers
			Pattern patternB = Pattern.compile("^[0-9]*$"); // check for numbers
			Matcher matcherB = patternB.matcher(holder.substring(4)); 
			boolean resultB = matcherB.matches();
			if(test==true&&resultB==false)
			{
				System.out.println("Error: Course Id must end with three numbers.");
				test = false;
			}
		}
		return test;
	}
	
	// checks data validation of the crn
	// @param int of the crn
	// @return boolean
	public static boolean crnCheck(int crn) // checks that CRN is 5 digits
	{
		//String holder = crn;
		String holder = Integer.toString(crn);
		boolean test = true;
		if(holder.length()!=5)
		{
			System.out.println("Error: CRN must be five (5) numbers.");
			test = false;
		}
		
		// test all are numbers
		Pattern patternB = Pattern.compile("^\\d+$"); // check for digits 0-9
		Matcher matcherB = patternB.matcher(holder); 
		boolean resultB = matcherB.matches();
		if(test==true&&resultB==false)
		{
			System.out.println("Error: CRN must be All numbers.");
			test = false;
		}
		
		return test;
	}
	
	// checks data validation of the credits
	// @param int of the credits
	// @return boolean
	public static boolean creditsCheck(int credits) // checks that hours are: 1 2 3 or 4
	{
		boolean test = true;
		String holder = Integer.toString(credits);
		if(holder.equals("1")||holder.equals("2")||holder.equals("3")||holder.equals("4"))
		{			
			test = true;
		}
		else
		{
			System.out.println("Error: Credits must be 1, 2, 3, or 4");
			test = false;
		}
		return test;
	}
	
	// checks data validation of the instructor
	// @param String of the instructor
	// @return boolean
	public static boolean instructorCheck(String instructor) // checks that room is at least 1 character
	{
		boolean test = true;
		
		if(instructor.length()<1 || spaceCheck(instructor)) // checks for length of at least 1 character, and also not all spaces
		{
			System.out.println("Error: Instructor must be at least one character.");
			test = false;
		}
		
		return test;
	}
	
	// checks data validation of the room
	// @param String of the room
	// @return boolean
	public static boolean roomCheck(String room) // checks that room is at least 1 character
	{
		boolean test = true;
		
		if(room.length()<1 || spaceCheck(room)) // checks for length of at least 1 character, and also not all spaces
		{
			System.out.println("Error: Room must be at least one character.");
			test = false;
		}
		
		return test;
	}
	
	// checks to see if String is nothing but spaces
	// @param String
	// @return boolean
	public static boolean spaceCheck(String holder)
	{
		boolean test = false;
		// test if all spaces
		Pattern patternB = Pattern.compile("^\s*$"); // check for only spaces
		Matcher matcherB = patternB.matcher(holder); 
		boolean resultB = matcherB.matches();
		if(resultB==true)
		{
			System.out.println("Error: Cannot be all spaces.");
			test = true;
		}
		return test;
	}
	
	// checks if element passes all the checks above
	// @return boolean
	public boolean isElementValid()
	{
		boolean isValid = true;
		
		if(!idCheck(id) || !crnCheck(crn) || !creditsCheck(credits) || !roomCheck(roomNum) || !instructorCheck(instructor))
			isValid = false;
		
		return isValid;
	}
	
}
