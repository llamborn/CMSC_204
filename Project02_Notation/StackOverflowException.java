// Programmer: Luke L
public class StackOverflowException extends Exception
{
	public StackOverflowException()
	{
		this("Stack Overflow Exception.");		
	}
	
	public StackOverflowException(String message)
	{
		super(message);		
	}
}
