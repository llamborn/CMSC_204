// Programmer: Luke L
public class StackUnderflowException extends Exception
{
	public StackUnderflowException()
	{
		this("Stack Underflow Exception.");		
	}
	
	public StackUnderflowException(String message)
	{
		super(message);		
	}
}
