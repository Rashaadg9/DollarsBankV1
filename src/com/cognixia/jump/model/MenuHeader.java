package com.cognixia.jump.model;

import com.cognixia.jump.utility.ConsoleColor;

public class MenuHeader
{
	String headerTop = ConsoleColor.ANSI_GREEN + "\n<S-----------------------------------S>" + ConsoleColor.ANSI_RESET;
	String headerBottom = ConsoleColor.ANSI_GREEN + "<S-----------------------------------S>" + ConsoleColor.ANSI_RESET;
	
	public void welcome()
	{
		System.out.println(headerTop);
		System.out.println(ConsoleColor.ANSI_BLUE + ConsoleColor.ANSI_WHITE_BACKGROUND + "DOLLARSBANK Welcomes You!" + ConsoleColor.ANSI_RESET);
		System.out.println(headerBottom);
	}
	
	public void newAccount()
	{
		System.out.println(headerTop);
		System.out.println("Enter Details For New Account");
		System.out.println(headerBottom);
		
	}
	
	public void login()
	{
		System.out.println(headerTop);
		System.out.println("Enter Login Details");
		System.out.println(headerBottom);
	}
	
	public void welcomeLoggedIn(String username, double bal)
	{
		System.out.println(headerTop);
		System.out.println(ConsoleColor.ANSI_BLUE + ConsoleColor.ANSI_WHITE_BACKGROUND + "WELCOME"+ ConsoleColor.ANSI_RESET + " " + username);
		System.out.println("Account Balance: " + ConsoleColor.ANSI_GREEN + "$" + ConsoleColor.ANSI_RESET + bal);
		System.out.println(headerBottom);
	}
	
	public void transfer()
	{
		System.out.println(headerTop);
		System.out.println("Transfer portal");
		System.out.println(headerBottom);
	}
	
	public void displayInfo(User user)
	{
		System.out.println(ConsoleColor.ANSI_BLUE + ConsoleColor.ANSI_WHITE_BACKGROUND + "\n<S------------S> ACCOUNT INFORMATION <S------------S>" + ConsoleColor.ANSI_RESET);
		System.out.println("Username: " + user.getUsername());
		System.out.println("First Name: " + user.getFirstName());
		System.out.println("Last Name: " + user.getLastName());
		System.out.println("DOB: " + user.getDob());
		System.out.println("Contact: " + user.getContact());
		System.out.println("Current Balance: $" + user.getCash());
		System.out.println(ConsoleColor.ANSI_BLUE + ConsoleColor.ANSI_WHITE_BACKGROUND + "<S------------S> ACCOUNT INFORMATION <S------------S>\n" + ConsoleColor.ANSI_RESET);
	}
	
	public void displayRecent(Recent recent)
	{
		System.out.println(ConsoleColor.ANSI_BLUE + ConsoleColor.ANSI_WHITE_BACKGROUND + "\n<S------------S> 5 Recent Transactions <S------------S>" + ConsoleColor.ANSI_RESET);
		System.out.println(ConsoleColor.ANSI_WHITE + ConsoleColor.ANSI_BLACK_BACKGROUND + recent.getR1());
		System.out.println(recent.getR2());
		System.out.println(recent.getR3());
		System.out.println(recent.getR4());
		System.out.println(recent.getR5() + ConsoleColor.ANSI_RESET);
		System.out.println(ConsoleColor.ANSI_BLUE + ConsoleColor.ANSI_WHITE_BACKGROUND + "<S------------S> 5 Recent Transactions <S------------S>\n" + ConsoleColor.ANSI_RESET);
	}
}
