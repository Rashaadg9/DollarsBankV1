package com.cognixia.jump.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cognixia.jump.connection.ConnectionManager;
import com.cognixia.jump.model.User;

public class UserDao
{
	private Connection conn = ConnectionManager.getConnection();
	
	public List<User> getAllUsers()
	{
		List<User> allUsers = new ArrayList<>();
		
		try(PreparedStatement pstmt = conn.prepareStatement("select * from users");
				ResultSet rs = pstmt.executeQuery();)
		{
			while(rs.next())
			{
				int id = rs.getInt("user_id");
				String username = rs.getString("username");
				String fName = rs.getString("first_name");
				String lName = rs.getString("last_name");
				String dob = rs.getString("dob");
				
				User user = new User(id, username, null, fName, lName, dob);
				
				allUsers.add(user);
			}
		}
		catch(SQLException e)
		{
			System.out.println("ERRR");
		}
		
		return allUsers;
	}
	
	public User login(String username, String password)
	{
		
		User user = new User();
		//login checks by username and password
		try(PreparedStatement pstmt = conn.prepareStatement("select * from users WHERE username = ? AND p_password = ?"); )
		{
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			
			
			while(rs.next())
			{
				//Only returns to user id and username
				int id = rs.getInt("user_id");
				String uName = rs.getString("username");
				user.setId(id);
				user.setUsername(uName);
				
				
			}
		}
		catch(SQLException e)
		{
			System.out.println("ERRR");
		}
		
		return user;
	}
	
	public void close()
	{
		try
		{
			conn.close();
			System.out.println("Connection was closed");
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
