/*
 * Programmer: Luke L
 * Class: CMSC204 
 * Description: Password checker, uses JFX interface
 * Due: 9/1/2023
 * Platform/compiler: Mac/Eclipse
 *
 * This is a utility program used to check the validity of a password. 
 * Password requirements are: length, upper/lower case, alpha and numeric characters, 
 * special characters, and characters in a sequence.
 */


import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class PasswordCheckerUtility 
{

	// constructor
	public PasswordCheckerUtility()
	{
		
	}
	/*
	 * Compare equality of two passwords
	 * @param password string to be checked for
	 * @param passwordConfirm string to be checked against password for
	 * @throws UnmatchedException - thrown if not same (case sensitive)
	 */
	public static void comparePasswords​(String password, String passwordConfirm) throws UnmatchedException
	{

		try
		{
		if (!password.equals(passwordConfirm))
		{
			throw new UnmatchedException("Passwords do not match");
		}
		}
		finally
		{
			
		}
		
	}
	
	/*
	 * Compare equality of two passwords
	 * @param password string to be checked for
	 * @param passwordConfirm string to be checked against password for
	 * @returns true if both same (case sensitive), false otherwise
	 */
	public static boolean comparePasswordsWithReturn​(String password, String passwordConfirm)
	{
		
		boolean result = true;
		
		if (password.equals(passwordConfirm))
			result = true;
		else if (!password.equals(passwordConfirm))
			result = false;
			
		return result;
	}

	/*
	 * This method will accept an ArrayList of passwords as the parameter and return an ArrayList with the status of any invalid passwords (weak passwords are not considered invalid). 
	 * @param passwords list of passwords
	 * @returns ArrayList of invalid passwords in the correct format
	 */
	public static ArrayList getInvalidPasswords​(ArrayList passwords)
	{
		int count = passwords.size();
		//System.out.println("Number of passwords in password Arraylist: " + count);
		
		ArrayList<String> badOnes = new ArrayList <String>();
		
		String holder = "";
		for(int i=0; i<count; i++)
		{
			boolean test = true;
			holder = (String) passwords.get(i);
			
			if (holder.length() < 6) // checks length
			{
				badOnes.add(holder + " The password must be at least 6 characters long.");	
				test = false;
			}
			
			String strA = holder;
			Pattern patternA = Pattern.compile(".*[A-Z].*");
			Matcher matcherA = patternA.matcher(strA); 
			boolean resultA = matcherA.matches();
			if (test == true && resultA == false) // checks for upper alpha
			{
				badOnes.add(holder + " The password must contain at least one uppercase alphabetic character.");				
				test = false;
			}
			
			String strB = holder;
			Pattern patternB = Pattern.compile(".*[a-z].*");
			Matcher matcherB = patternB.matcher(strB); 
			boolean resultB = matcherB.matches();
			if (test == true && resultB == false) // checks for lower alpha
			{
				badOnes.add(holder + " The password must contain at least one lowercase alphabetic character");				
				test = false;
			}
			
			String strC = holder;
			Pattern patternC = Pattern.compile(".*[0-9].*");
			Matcher matcherC = patternC.matcher(strC); 
			boolean resultC = matcherC.matches();
			if (test == true && resultC == false) // checks for digit
			{
				badOnes.add(holder + " The password must contain at least one digit.");				
				test = false;
			}
			
			String strD = holder;
			Pattern patternD = Pattern.compile("[a-zA-Z0-9]*");
			Matcher matcherD = patternD.matcher(strD); 
			boolean resultD = !matcherD.matches();
			if (test == true && resultD == false) // checks for special char
			{
				badOnes.add(holder + " The password must contain at least one special character");				
				test = false;
			}
			
			String strE = holder;
			Pattern patternE = Pattern.compile(".*([a-zA-Z0-9!@#$%^&*()-_=+{}`~:|<,>.?/])\\1\\1.*");
			Matcher matcherE = patternE.matcher(strE); 
			boolean resultE = !matcherE.matches();
			if (test == true && resultE == false) // checks for same char
			{
				badOnes.add(holder + " The password cannot contain more than two of the same character in sequence.");				
				test = false;
			}
		}
		return badOnes;
	}
	
	/*
	 * Checks the password length requirement - The password must be at least 6 characters long
	 * @param password string to be checked for length
	 * @returns true if meets minimum length requirement
	 * @throws LengthException - thrown if does not meet minimum length requirement
	 */
	public static boolean isValidLength(String password) throws LengthException
	{

		try 
		{
		if (password.length() < 6)
		{
			throw new LengthException("The password must be at least 6 characters long");
		}
		}
		finally
		{
			
		}
		
		boolean result = true;
		if (password.length() >= 6)
			result = true;
		else
			result = false;
		return result;
	}
	
	/*
	 * Checks if the password contains 6 to 9 characters
	 * @param password string to be checked for
	 * @returns true if password contains 6 to 9 characters, false otherwise
	 */
	public static boolean hasBetweenSixAndNineChars​(String password)
	{
		boolean result = false;
		if (password.length() >= 6 && password.length() <= 9)
			result = true;
		else
			result = false;
		return result;
	}

	/*
	 * Checks the password alpha character requirement - Password must contain an uppercase alpha character
	 * @param password string to be checked for alpha character requirement
	 * @returns true if meet alpha character requirement
	 * @throws NoUpperAlphaException - thrown if does not meet alpha character requirement
	 */
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException
	{
		String strA = password;
		Pattern patternA = Pattern.compile(".*[A-Z].*");
		Matcher matcherA = patternA.matcher(strA); 
		boolean resultA = matcherA.matches();
		try
		{
			if (resultA == false)
				throw new NoUpperAlphaException();
		}
		catch (NoUpperAlphaException e)
		{
			//System.out.println(e.getMessage());
		}
		//System.out.println("Is there a upper alpha character?");
		//System.out.println(resultA);
		return (resultA);
	}
	
	/*
	 * Checks the password lower character requirement - Password must contain an lowercase alpha character
	 * @param password string to be checked for alpha character requirement
	 * @returns true if meet alpha character requirement
	 * @throws NoLowerAlphaException - thrown if does not meet alpha character requirement
	 */
	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException
	{
		String strA = password;
		Pattern patternA = Pattern.compile(".*[a-z].*");
		Matcher matcherA = patternA.matcher(strA); 
		boolean resultA = matcherA.matches();
		try
		{
			if (resultA == false)
				throw new NoLowerAlphaException();
		}
		catch (NoLowerAlphaException e)
		{
			//System.out.println(e.getMessage());
		}
		//System.out.println("Is there a lower alpha character?");
		//System.out.println(resultA);
		return (resultA);
	}
	
	/* Checks the password Digit requirement - Password must contain a numeric character
	 * @param password string to be checked for Digit requirement
	 * @returns true if meet Digit requirement
	 * @throws NoDigitException - thrown if does not meet Digit requirement
	 */
	public static boolean hasDigit​(String password) throws NoDigitException
	{
		String strA = password;
		Pattern patternA = Pattern.compile(".*[0-9].*");
		Matcher matcherA = patternA.matcher(strA); 
		boolean resultA = matcherA.matches();
		try
		{
			if (resultA == false)
				throw new NoDigitException();
		}
		catch (NoDigitException e)
		{
			//System.out.println(e.getMessage());
		}
		//System.out.println("Is there a digit?");
		//System.out.println(resultA);
		return (resultA);
	}
	
	/*
	 * Checks the password Sequence requirement - Password should not contain more than 2 of the same character in sequence
	 * @param password string to be checked for Sequence requirement
	 * @returns false if does NOT meet Sequence requirement
	 * @throws InvalidSequenceException - thrown if meets Sequence requirement
	 */
	public static boolean NoSameCharInSequence(String password) throws InvalidSequenceException
	{
		String strA = password;
		Pattern patternA = Pattern.compile(".*([a-zA-Z0-9!@#$%^&*()-_=+{}`~:|<,>.?/])\\1\\1.*");
		Matcher matcherA = patternA.matcher(strA); 
		boolean resultA = !matcherA.matches();
		try
		{
			if (resultA == false)
				throw new InvalidSequenceException();
		}
		catch (InvalidSequenceException e)
		{
			//System.out.println(e.getMessage());
		}
		//System.out.println("Is a character Not repeated 3 times?");
		//System.out.println(resultA);
		return (resultA);
	}
	
	/*
	 * Checks the password SpecialCharacter requirement - Password must contain a Special Character
	 * @param password string to be checked for SpecialCharacter requirement
	 * @returns true if meets SpecialCharacter requirement
	 * @throws NoSpecialCharacterException - thrown if does not meet SpecialCharacter requirement
	 */
	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException
	{
		String strA = password;
		Pattern patternA = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcherA = patternA.matcher(strA); 
		boolean resultA = !matcherA.matches();
		try
		{
			if (resultA == false)
				throw new NoSpecialCharacterException();
		}
		catch (NoSpecialCharacterException e)
		{
			//System.out.println(e.getMessage());
		}
		//System.out.println("Is there a special character?");
		//System.out.println(resultA);
		return (resultA);
	}	
	
	/*
	 * Return true if valid password (follows all the following rules), returns false if an invalid password
	 * @param password string to be checked for validity
	 * @returns true if valid password (follows all rules from above), false if an invalid password
	 * @throws LengthException, NoSpecialCharacterException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, InvalidSequenceException
	 */
	public static boolean isValidPassword​(String password) throws LengthException, NoSpecialCharacterException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, InvalidSequenceException
	{
		
		boolean stillvalid = true;
		
		boolean len = isValidLength(password);
		if (len == true)
		{
			stillvalid = true;
		}
		else if (len == false)
		{
			stillvalid = false;
			throw new LengthException();
		}
		
		boolean ua = hasUpperAlpha(password);
		if (ua == true)
		{
			stillvalid = true;
		}
		else if (ua == false)
		{
			stillvalid = false;
			throw new NoUpperAlphaException();
		}
		
		boolean la = hasLowerAlpha(password);
		if (la == true)
		{
			stillvalid = true;
		}
		else if (la == false)
		{
			stillvalid = false;
			throw new NoLowerAlphaException();
		}
		
		boolean di = hasDigit​(password);
		if (di == true)
		{
			stillvalid = true;
		}
		else if (di == false)
		{
			stillvalid = false;
			throw new NoDigitException();
		}
		
		boolean sp = hasSpecialChar(password);
		if (sp == true)
		{
			stillvalid = true;
		}
		else if (sp == false)
		{
			stillvalid = false;
			throw new NoSpecialCharacterException();
		}
		
		boolean sc = NoSameCharInSequence(password);
		if (sc == true)
		{
			stillvalid = true;
		}
		else if (sc == false)
		{
			stillvalid = false;
			throw new InvalidSequenceException();
		}
		//System.out.println(isValidLength(password));
		//System.out.println(result);
		return stillvalid;

	}
	
	/*
	 * Checks if password is VALID and the length is NOT between 6-9 characters
	 * @param string to be checked if weak password
	 * @returns false if the password is valid and the length of password is NOT between 6 and 9 (inclusive)
	 * @throws WeakPasswordException - if length of password is between 6 and 9 (inclusive), ALTHOUGH the password may be VALID
	 */
	public static boolean isWeakPassword​(String password) throws WeakPasswordException
	{
		try 
		{
		if (hasBetweenSixAndNineChars​(password))
		{
			throw new WeakPasswordException();
		}
		}
		finally
		{
			
		}
		
		
		boolean result = false;
		if (hasBetweenSixAndNineChars​(password))
			result = true;
		else
			result = false;
		return result;

	}

}
