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
	static String fName = "N/A";
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
				if( (id != -1) )
					welcomeLoggedIn();
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
		
		System.out.println("Initial Deposit Amount: (min $5.00 )");
		System.out.print("$");
		double amount = sc.nextDouble();
		sc.nextLine();
		while( (amount < 5.00))
		{
			System.out.println("Initial Deposit Amount: (min $5.00 )");
			System.out.print("$");
			amount = sc.nextDouble();
			sc.nextLine();
		}
		newUser.setCash(amount);
		
		if(userDao.createAcc(newUser) == true)
		{
			System.out.println("Successfully Created new account with username: " + newUser.getUsername());
			User user = userDao.login(newUser.getUsername(), newUser.getPassword());
			double bal = userDao.getBalance(user.getId());
			userDao.createRecent(user.getId(), bal);
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
		fName = user.getFirstName();
		
		System.out.println("Returned info");
		System.out.println("Username: " + username);
		System.out.println("ID: " + id);
		System.out.println("First Name: " + fName);

	}
	
	public static void welcomeLoggedIn()
	{
		boolean loggedIn = true;
		while(loggedIn == true)
		{
			header.welcomeLoggedIn(fName);
			System.out.println("1. Deposit Amount");
			System.out.println("2. Withdraw Amount");
			System.out.println("3. Funds Transfer");
			System.out.println("4. View 5 Recent Transactions");
			System.out.println("5. Display Your Information");
			System.out.println("6. Sign Out");
			System.out.print("\nEnter Choice (1, 2, 3, 4, 5, or 6) : ");
			int choice = sc.nextInt();
			sc.nextLine();
			
			switch(choice)
			{
				case 1:
					deposit();
					break;
				case 2:
					withdraw();
					break;
				case 3:
					transfer();
					break;
				case 4:
					getRecent();
					break;
				case 5:
					getInfo();
					break;
				case 6:
					id = -1;
					username = "N/A";
					fName = "N/A";
					loggedIn = false;
					break;
				default:
					System.out.println("Invalid option\n");
					break;
				
			}
		}
		
	}
	
	public static void deposit()
	{
		double current = userDao.getBalance(id);
		System.out.println("Current Balance: $" + current);
		
		System.out.println("Amount to Deposit: (min $5.00 )");
		System.out.print("$");
		double amount = sc.nextDouble();
		sc.nextLine();
		while( (amount < 5.00))
		{
			System.out.println("Amount to Deposit: (min $5.00 )");
			System.out.print("$");
			amount = sc.nextDouble();
			sc.nextLine();
		}
		
		double amount2 = amount + current;
		
		if(userDao.deposit(id, amount2) == true)
		{
			System.out.println("Successfully deposited cash");
			System.out.println("New balance: $" + userDao.getBalance(id));
			userDao.updateRecent(id, amount, "Deposit");
		}
		else
		{
			System.out.println("deposit failed");
		}
	}
	
	public static void withdraw()
	{
		double current = userDao.getBalance(id);
		System.out.println("Current Balance: $" + current);
		
		System.out.println("Amount to withdraw:");
		System.out.print("$");
		double amount = sc.nextDouble();
		sc.nextLine();
		while( (amount > current))
		{
			System.out.println("Can not withdraw more than available:");
			System.out.println("Amount to withdraw:");
			System.out.print("$");
			amount = sc.nextDouble();
			sc.nextLine();
		}
		
		double amount2 = current - amount;
		
		if(userDao.deposit(id, amount2) == true)
		{
			System.out.println("Successfully withdrew cash");
			System.out.println("New balance: $" + userDao.getBalance(id));
			userDao.updateRecent(id, amount, "Withdraw");
		}
		else
		{
			System.out.println("withdraw failed");
		}
	}
	
	public static void getInfo()
	{
		header.displayInfo(userDao.getUserById(id));
		
	}
	
	public static void getRecent()
	{
		header.displayRecent(userDao.getRecent(id));
	}
	
	public static void transfer()
	{
		header.transfer();
		System.out.println("Enter Username of account to transfer to");
		String user = sterilize(sc.nextLine());
		
		while( (userDao.checkUserName(user) > 0) == false)
		{
			System.out.println("User: " + user + " is INVALID!");
			System.out.println("Enter Username of account to transfer to");
			user = sterilize(sc.nextLine());
		}
		User me = userDao.getUserById(id);
		
		System.out.println("Enter Amount to Transfer");
		System.out.print("$");
		double cash = sc.nextDouble();
		sc.nextLine();
		
		while(cash > me.getCash())
		{
			System.out.println("Can NOT transfer more than avaliable");
			System.out.println("Enter Amount to Transfer");
			System.out.print("$");
			cash = sc.nextDouble();
			sc.nextLine();
		}
		
		User notMe = userDao.getUserByUsername(user);
		
		double amount = notMe.getCash() + cash;
		
		if(userDao.transfer(user, amount) == true)
		{
			System.out.println("Successfully Transfered cash to User: " + user);
			userDao.deposit(id, (me.getCash() - cash) );
			System.out.println("My balance: $" + userDao.getBalance(id));
			String typeTo = "Transfered to [" + notMe.getUsername() + "]";
			String typeFrom = "Recived Transfer from [" + username + "]";
			userDao.updateRecent(id, cash, typeTo);
			userDao.updateRecent(notMe.getId(), cash, typeFrom);
		}
		else
		{
			System.out.println("Transfer failed");
		}
		
		
	}
	
	public static String sterilize(String input)
	{
		String sterInput = input.trim();
		sterInput = sterInput.replaceAll(" ", "");
		
		return sterInput;	
	}
}
