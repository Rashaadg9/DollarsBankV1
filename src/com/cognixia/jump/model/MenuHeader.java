package com.cognixia.jump.model;

public class MenuHeader
{
	public void welcome()
	{
		System.out.println("DOLLARSBANK Welcomes You!");
	}
	
	public void newAccount()
	{
		System.out.println("Enter Details For New Account");
	}
	
	public void login()
	{
		System.out.println("Enter Login Details");
	}
	
	public void welcomeLoggedIn(String username)
	{
		System.out.println("WELCOME " + username);
	}
	
	public void displayInfo(User user)
	{
		System.out.println("\n<S------------S> ACCOUNT INFORMATION <S------------S>");
		System.out.println("Username: " + user.getUsername());
		System.out.println("First Name: " + user.getFirstName());
		System.out.println("Last Name: " + user.getLastName());
		System.out.println("DOB: " + user.getDob());
		System.out.println("Contact: " + user.getContact());
		System.out.println("Current Balance: $" + user.getCash());
		System.out.println("<S------------S> ACCOUNT INFORMATION <S------------S>\n");
	}
	
	public void displayRecent(Recent recent)
	{
		System.out.println("\n<S------------S> 5 Recent Transactions <S------------S>");
		System.out.println(recent.getR1());
		System.out.println(recent.getR2());
		System.out.println(recent.getR3());
		System.out.println(recent.getR4());
		System.out.println(recent.getR5());
		System.out.println("<S------------S> 5 Recent Transactions <S------------S>\n");
	}
}
