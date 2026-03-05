// Programmer: Luke L
import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class MorseCodeConverter 
{
	// fields
	static MorseCodeTree oak = new MorseCodeTree();
	static ArrayList<String> inOrd = new ArrayList<>(); // used for the LNRoutputTraversal in MorseCodeTree
	
	// the data in the tree in LNR order separated by a space
	// @return the data in the tree in LNR order separated by a space
	public static String printTree()
	{
		oak.LNRoutputTraversal(oak.getRoot(), inOrd); // send static ArrayList variable inOrd to the method LNRoutputTraversal in MorseCodeTree
		String result = "";
		for(int i=0; i<inOrd.size(); i++)
		{
			result += inOrd.get(i); // adds spaces after InOrder letters, except last letter
			if(i < inOrd.size()-1)
				result += " ";		
		}
		return result;
	}
	
	// Converts Morse code into English. Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’
	// @param code - the Morse code
	// @return the English translation
	public static String convertToEnglish​(String code)
	{
		String fixedcode = fixSlash(code);
		String result = "";
		String letter;
		String[] split = fixedcode.split("\\s"); // "\\s"   "/"   "(\\s|/)"
		
		for(int i=0; i<split.length; i++)
		{
			// counts dots and dashes, ignores letters
			int dotdashes = 0;
			for(int j=0; j<split[i].length(); j++)
			{
				if(split[i].charAt(j)=='.')
					dotdashes++;
				if(split[i].charAt(j)=='-')
					dotdashes++;
			}
			
			// only let Morse code words of 4 or fewer characters through
			if(dotdashes<=4)
			{
				if (split[i].equals("/")) // adds a space for a '/'
					result += " ";
				letter = oak.fetch(split[i]); // sends Morse code letter to be decoded
				result += letter; // collects letters in a new String to be returned
			}
			
			// if Morse code letter is greater than 4 characters, print error messages and prevent crash of GUI
			else if(dotdashes>4)
			{
				System.out.println("Morse code letters cannot be over 4 dots and dashes.");
				result = "Morse code letters cannot be over 4 dots and dashes.";
			}
		}		
		return result;
	}
	
	// Converts a file of Morse code into English Each letter is delimited by a space (‘ ‘). Each word is delimited by a ‘/’
	// @param input - name of the File that contains Morse Code
	// @return the English translation of the file
	public static String convertToEnglish​(File input) throws FileNotFoundException
	{
		Scanner inputFile = new Scanner(input); 
		while(inputFile.hasNext())
		{
			String line = inputFile.nextLine();
			return convertToEnglish​(line);			
		}	
		inputFile.close();
		return null;
	}
	
	// Data validation: prevents crashes if space not before and after '/' of inputted Morse code
	// Inserts a space before and after '/' if not there already
	// @param code - the Morse code String to be checked and fixed
	// @return String of fixed Morse code
	public static String fixSlash(String code)
	{
		String code2 = code;
		String s1 = "";
		String s2 = "";
		
		for(int i=0; i<code2.length(); i++) // fixes space before '/'
		{
			if(code2.charAt(i)=='/' && i!=0) // finds all '/', ignores '/' if first char
			{
				if(!(code2.charAt(i-1)==' ')) // puts a space before '/'
				{
					s1=code2.substring(0, i);
					s2=code2.substring(i, code2.length());
					code2=s1+" "+s2;
				}
			}
		}
		
		for(int i=0; i<code2.length(); i++) // fixes space after '/'
		{
			if(code2.charAt(i)=='/' && i!=code2.length()-1) // finds all '/', ignores '/' if first char
			{	
				if(code2.charAt(i+1)!=' ') // puts a space after '/'
				{
					s1=code2.substring(0, i+1);
					s2=code2.substring(i+1, code2.length());
					code2=s1+" "+s2;
				}	
			}
		}
		return code2;
	}
	
}
