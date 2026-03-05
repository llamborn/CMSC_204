// Programmer: Luke L

public class Notation 
{
	// fields
	
	// constructor
	public Notation()
	{
		
	}
	
	// methods below
	
	// evaluates a postfix expression from a String to a double
	// @param postfixExpr - the postfix expression in String format
	// @return the evaluation of the postfix expression as a double
	// @throws InvalidNotationFormatException - if the postfix expression format is invalid
	public static double evaluatePostfixExpression​(String postfixExpr) throws InvalidNotationFormatException
	{
		MyStack<Double> valueStack = new MyStack<>(100);
		
		if (isPostfixValid(postfixExpr) == false)
			throw new InvalidNotationFormatException();
		
		double answer = 0.0;
		int characterCount = postfixExpr.length();
		int index = 0;
		char nextCharacter = ' ';

		try
		{
		while (index < characterCount)
		{
			nextCharacter = postfixExpr.charAt(index);
			switch (nextCharacter)
			{
			case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': case '0':
				double value = (double)nextCharacter-48;			
				valueStack.push(value);
				//System.out.println("this is the number added: " + value);
				break;
			
			case '+': case '-': case '*': case '/':
				double operandOne = valueStack.pop();
				double operandTwo = valueStack.pop();
				double result = 0.0;
				
				if(nextCharacter == '+')
					result = operandTwo + operandOne;
				if(nextCharacter == '-')
					result = operandTwo - operandOne;
				if(nextCharacter == '*')
					result = operandTwo * operandOne;
				if(nextCharacter == '/')
					result = operandTwo / operandOne;
				
				valueStack.push(result);
				//System.out.println("result so far: " + result);
			default: 
				break; // ignore unexpected characters
			
			}
			index++;
		}
		answer = (valueStack.top());
		}
		catch (StackOverflowException e)
		{
			System.out.println(e.getLocalizedMessage());
		}
		catch (StackUnderflowException e)
		{
			System.out.println(e.getLocalizedMessage());
		}

		return answer;
	}
	
	// Convert the Postfix expression to the Infix expression
	// @param postfix - the postfix expression in string format
	// @return the infix expression in string format
	// @throws InvalidNotationFormatException - if the postfix expression format is invalid
	public static String convertPostfixToInfix​(String postfix) throws InvalidNotationFormatException
	{
		MyStack<String> parenStack = new MyStack<>(100);
		
		if (isPostfixValid(postfix) == false)
			throw new InvalidNotationFormatException();
		
		int characterCount = postfix.length();
		int index = 0;
		char nextCharacter = ' ';

		try
		{
		while (index < characterCount)
		{
			nextCharacter = postfix.charAt(index);
			switch (nextCharacter)
			{
			// put operands into stack
			case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': case '0':
				parenStack.push(String.valueOf(nextCharacter));
				break;
			
			case '+': case '-': case '*': case '/': // convert operator to paren String, put back into stack
				String operator = String.valueOf(nextCharacter);
				String val1 = parenStack.pop();
				String val2 = parenStack.pop();
				String paren = "(" + val2 + operator + val1 + ")";
				parenStack.push(paren);
				break;
			
			default:
				break; // ignore unexpected characters				
			}		
			index++;
		}
		}
		catch (StackOverflowException e)
		{
			System.out.println(e.getLocalizedMessage());
		}
		catch (StackUnderflowException e)
		{
			System.out.println(e.getLocalizedMessage());
		}

		return parenStack.toString(); // stub
	}
	
	// Convert an infix expression into a postfix expression
	// @param infix - the infix expression in string format
	// @return the postfix expression in string format
	// @throws InvalidNotationFormatException - if the infix expression format is invalid
	public static String convertInfixToPostfix​(String infix) throws InvalidNotationFormatException
	{
		MyStack<Character> operatorStack = new MyStack<>(100);
		MyQueue<Character> operatorQueue = new MyQueue<>(100);
		
		if (isInfixValid(infix) == false)
			throw new InvalidNotationFormatException();
		
		String postfix = "";
		int characterCount = infix.length();
		int index = 0;
		char nextCharacter = ' ';
		char topOperator = ' ';

		try
		{		
		while (index < characterCount)
		{
			nextCharacter = infix.charAt(index);
			switch (nextCharacter)
			{
			case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': case '0':
				postfix += nextCharacter;
				break;
				
			case '^':
				operatorStack.push(nextCharacter);
				break;
				
			case '+': case '-': case '*': case '/':
				
				int nextcharp = 1;
				int stackp = 2;
				if (!operatorStack.isEmpty())
				{
					
					if (nextCharacter == '(')
						nextcharp = 0;
					if ((nextCharacter == '+') || (nextCharacter == '-'))
						nextcharp = 1;
					//if (nextCharacter == '^') // don't need?
					//	nextcharp = 2;
					else if ((nextCharacter == '*') || (nextCharacter == '/'))
						nextcharp = 2;
					
					if (operatorStack.top() == '(')
						stackp = 0;
					if ((operatorStack.top() == '+') || (operatorStack.top() == '-'))
						stackp = 1;
					//if (operatorStack.peek() == '^') // don't need?
					//	stackp = 3;
					else if ((operatorStack.top() == '*') || (operatorStack.top() == '/'))
						stackp = 2;
				}
				
				while ((!operatorStack.isEmpty() && (nextcharp <= stackp) && (operatorStack.top() != '('))) // only does this loop if stack is Not empty
				{					

					postfix += operatorStack.top(); // adds operator from stack onto postfix
					operatorStack.pop(); // removes operator from stack
					//System.out.println("hi +-*/");
				}
				
				operatorStack.push(nextCharacter);
				//System.out.println("stack so far: operatorStack");
				break;
				
			case '(':
				operatorStack.push(nextCharacter);
				//System.out.println("hi (");
				break;
				
			case ')': 
				topOperator = operatorStack.pop();
				while (topOperator != '(')
				{
					postfix += topOperator;
					topOperator = operatorStack.pop();
				}
				break;
				
			default: 
				break; // ignore unexpected characters				
			}
			//System.out.println("Here is the stack: " + operatorStack.stringToArray());
			//System.out.println("Postfix so far: " + postfix);
			index++;
			
		}
		while (!operatorStack.isEmpty())
		{
			topOperator = operatorStack.pop();
			postfix += topOperator;
			//System.out.println("last one");
		}

		}
		catch (StackOverflowException e)
		{
			System.out.println(e.getLocalizedMessage());
		}
		catch (StackUnderflowException e)
		{
			System.out.println(e.getLocalizedMessage());
		}
		//System.out.println("is the stack empty?" + operatorStack.isEmpty());
		//System.out.println(postfix);
		
		// send postfix to Queue, then get the String from that to return
		try
		{
			for (int i=0; i<postfix.length(); i++)
			{
				operatorQueue.enqueue(postfix.charAt(i));
			}
		}
		catch (QueueOverflowException e)
		{
			System.out.println(e.getLocalizedMessage());
		}
		
		//return postfix;
		return operatorQueue.toString();
	}

	// Check the validity of an infix String, to see if parenthesis are balanced
	// @param infix - the infix expression in String format
	// @return boolean if the String is valid or not
	public static boolean isInfixValid(String infix)
	{
		boolean isValid = true;
		int leftparenth = 0;
		int rightparenth = 0;
		
		for (int i = 0; i<infix.length(); i++)
		{
			if (infix.charAt(i) == '(')
				leftparenth++;
			else if (infix.charAt(i) == ')')
				rightparenth++;			
		}
		if (leftparenth == rightparenth)
			isValid = true;
		else
			isValid = false;
		
		return isValid;
	}
	
	// Check the validity of an postfix String, to see if it consists of operators and operands
	// @param infix - the infix expression in String format
	// @return boolean if the String is valid or not
	public static boolean isPostfixValid(String postfix)
	{
		boolean isValid = true;
		int totaldigits = 0;
		int totalops = 0;
		
		for (int i = 0; i<postfix.length(); i++)
		{
			if (postfix.charAt(i)=='0' || postfix.charAt(i)=='1' || postfix.charAt(i)=='2' || postfix.charAt(i)=='3' || postfix.charAt(i)=='4' || postfix.charAt(i)=='5' || postfix.charAt(i)=='6' || postfix.charAt(i)=='7' || postfix.charAt(i)=='8' || postfix.charAt(i)=='9')
				totaldigits++;
		}
		for (int i = 0; i<postfix.length(); i++)
		{
			if (postfix.charAt(i)=='+' || postfix.charAt(i)=='-' || postfix.charAt(i)=='*' || postfix.charAt(i)=='/')
				totalops++;
		}

		if (totaldigits == (totalops+1))
			isValid = true;
		else
			isValid = false;
		
		return isValid;
	}
	
}
