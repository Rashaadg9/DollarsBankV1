package com.cognixia.jump.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.cognixia.jump.dao.UserDao;
import com.cognixia.jump.model.User;

public class DollarsBankController
{
	static int id = 0;
	static String username = "";
	
	public static void main(String[] args) throws SQLException
	{
		UserDao userDao = new UserDao();
		Scanner sc = new Scanner(System.in);
		
//		List<User> userList = userDao.getAllUsers();
//		
//		System.out.println(userList);
//		
//		userDao.close();
		
		System.out.println("Enter Login Cred");
		System.out.print("Username: ");
		String loginChoice1 = sc.nextLine();
		System.out.print("\nPassword: ");
		String loginChoice2 = sc.nextLine();
		System.err.println();
		
		User user = userDao.login(loginChoice1, loginChoice2);
		id = user.getId();
		username = user.getUsername();
		userDao.close();
		
		System.out.println("Returned info");
		System.out.println("Username: " + username);
		System.out.println("ID: " + id);
	}

}
