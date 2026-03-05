// Programmer: Luke L
public class QueueUnderflowException extends Exception
{
	public QueueUnderflowException()
	{
		this("Queue Underflow Exception.");		
	}
	
	public QueueUnderflowException(String message)
	{
		super(message);		
	}
}
