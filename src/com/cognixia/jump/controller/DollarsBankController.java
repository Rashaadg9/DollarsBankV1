package com.cognixia.jump.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.cognixia.jump.dao.UserDao;
import com.cognixia.jump.model.MenuHeader;
import com.cognixia.jump.model.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DollarsBankController
{
	static int id = -1;
	static String username = "N/A";
	static boolean finish = false;
	
	static MenuHeader header = new MenuHeader();
	static UserDao userDao = new UserDao();
	static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws SQLException
	{
		
		while(finish != true)
		{
			welcome();
		}
		
		userDao.close();
		sc.close();
		
	}
	
	public static void welcome()
	{
		header.welcome();
		System.out.println("1. Create New Account");
		System.out.println("2. Login");
		System.out.println("3. Exit");
		System.out.print("Enter choice (1, 2, or 3) : ");
		int choice = sc.nextInt();
		sc.nextLine();
		
		switch(choice)
		{
			case 1:
				newAccount();
				break;
			case 2:
				login();
				break;
			case 3:
				finish = true;
				break;
			default:
				welcome();
				break;
			
		}
	}
	
	public static void newAccount()
	{
		User newUser = new User();
		String input;
		
		System.out.println("First Name:");
		input = sterilize( sc.nextLine() );
		while( (input.equals("") || input.equals(" ") || input == null) )
		{
			System.out.println("Field must not be blank");
			System.out.println("First Name:");
			input = sterilize( sc.nextLine() );
		}
		newUser.setFirstName(input);
		
		System.out.println("Last Name:");
		input = sterilize( sc.nextLine() );
		while( (input.equals("") || input.equals(" ") || input == null) )
		{
			System.out.println("Field must not be blank");
			System.out.println("Last Name:");
			input = sterilize( sc.nextLine() );
		}
		newUser.setLastName(input);
		
		System.out.println("Contact Number (in the format of ###-###-####) :");
		input = sterilize( sc.nextLine() );
		
		String numberRegex = "\\d{3}-\\d{3}-\\d{4}";
		// regex pattern
		Pattern pattern = Pattern.compile(numberRegex);
		// matcher created with pattern and string
		Matcher matcher = pattern.matcher(input);
		while( (matcher.matches() == false) )
		{
			System.out.println("Incorrect format");
			System.out.println("Contact Number (in the format of ###-###-####) :");
			input = sterilize( sc.nextLine() );
			matcher = pattern.matcher(input);
		}
		newUser.setContact(input);
		
		System.out.println("DOB (in format of MM/DD/YYYY ) :");
		input = sterilize( sc.nextLine() );
		String dateRegex = "\\d{2}\\/\\d{2}\\/\\d{4}";
		pattern = Pattern.compile(dateRegex);
		matcher = pattern.matcher(input);
		while( (matcher.matches() == false) )
		{
			System.out.println("Incorrect format");
			System.out.println("DOB (in format of MM/DD/YYYY ) :");
			input = sterilize( sc.nextLine() );
			matcher = pattern.matcher(input);
		}
		newUser.setDob(input);
		
		boolean available = false;
		System.out.println("Username:");
		input = sterilize( sc.nextLine() );
		if(userDao.checkUserName(input) > 0)
		{
			available = false;
		}
		else
		{
			available = true;
		}
		while( (input.equals("") || input.equals(" ") || input == null || available == false) )
		{
			System.out.println("Field must not be blank");
			if(available == false)
				System.out.println("Username is not available");
			System.out.println("Username:");
			input = sterilize( sc.nextLine() );
			if(userDao.checkUserName(input) > 0)
			{
				available = false;
			}
			else
			{
				available = true;
			}
		}
		newUser.setUsername(input);
		
		System.out.println("Password:");
		input = sterilize( sc.nextLine() );
		while( (input.equals("") || input.equals(" ") || input == null) )
		{
			System.out.println("Field must not be blank");
			System.out.println("Password:");
			input = sterilize( sc.nextLine() );
		}
		newUser.setPassword(input);
		
		if(userDao.createAcc(newUser) == true)
		{
			System.out.println("Successfully Created new account with username: " + newUser.getUsername());
		}
		else
		{
			System.out.println("Account creation failed");
		}
		
	}
	
	public static void login()
	{
		header.login();
		System.out.print("Username: ");
		String loginChoice1 = sc.nextLine();
		System.out.print("Password: ");
		String loginChoice2 = sc.nextLine();
		System.err.println();
		
		User user = userDao.login(loginChoice1, loginChoice2);
		id = user.getId();
		username = user.getUsername();
		
		System.out.println("Returned info");
		System.out.println("Username: " + username);
		System.out.println("ID: " + id);

	}
	
	public static String sterilize(String input)
	{
		String sterInput = input.trim();
		sterInput = sterInput.replaceAll(" ", "");
		
		return sterInput;	
	}
}
