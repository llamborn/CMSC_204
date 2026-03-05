//Programmer: Luke L

public class InvalidSequenceException extends Exception
{
	public InvalidSequenceException()
	{
		super("The password cannot contain more than two of the same character in a sequence.");
		
	}
}
