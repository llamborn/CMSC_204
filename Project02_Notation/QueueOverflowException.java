// Programmer: Luke L
public class QueueOverflowException extends Exception
{
	public QueueOverflowException()
	{
		this("Queue Overflow Exception.");		
	}
	
	public QueueOverflowException(String message)
	{
		super(message);		
	}
}
