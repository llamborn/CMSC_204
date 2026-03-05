// Programmer: Luke L
public class InvalidNotationFormatException extends Exception
{

	public InvalidNotationFormatException()
	{
		super("Invalid notation.");
	}
	public InvalidNotationFormatException(String message) 
	{
		super(message);
	}
	
}
