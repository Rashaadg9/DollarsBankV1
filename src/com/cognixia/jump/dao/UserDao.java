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
				String fName = rs.getString("first_name");
				String lName = rs.getString("last_name");
				String contact = rs.getString("contact");
				String dob = rs.getString("dob");
				String username = rs.getString("username");
				
				User user = new User(id, fName, lName, contact, dob, username, null, -0.0);
				
				allUsers.add(user);
			}
		}
		catch(SQLException e)
		{
			System.out.println("ERRR");
		}
		
		return allUsers;
	}
	
	public int checkUserName(String username)
	{
		int i = 0;
		//SELECT count(user_id) FROM users WHERE username = 'user1';
		try(PreparedStatement pstmt = conn.prepareStatement("SELECT count(user_id) FROM users WHERE username = ?"))
		{
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			i = rs.getInt("count(user_id)");
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		return i;
	}
	
	public boolean createAcc(User newUser)
	{
		
		try( PreparedStatement pstmt = conn.prepareStatement("INSERT INTO users(first_name, last_name, contact, dob, username, p_password, cash) VALUES(?, ?, ?, ?, ?, ?, ?)") )
		{
			pstmt.setString(1, newUser.getFirstName());
			pstmt.setString(2, newUser.getLastName());
			pstmt.setString(3, newUser.getContact());
			pstmt.setString(4, newUser.getDob());
			pstmt.setString(5, newUser.getUsername());
			pstmt.setString(6, newUser.getPassword());
			pstmt.setDouble(7, newUser.getCash());
			
			// if update occurred, count will be 1,
			// if didn't occur, count will be 0
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				return true;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
		return false;
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
				String fName = rs.getString("first_name");
				user.setId(id);
				user.setUsername(uName);
				user.setFirstName(fName);
				
				
			}
		}
		catch(SQLException e)
		{
			System.out.println("ERRR");
		}
		
		return user;
	}
	
	public double getBalance(int id)
	{
		double bal = -0.0;
		try(PreparedStatement pstmt = conn.prepareStatement("select cash from users WHERE user_id = ?"); )
		{
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			bal = rs.getDouble("cash");
		}
		catch(SQLException e)
		{
			System.out.println("ERRR");
		}
		return bal;
	}
	
	public boolean deposit(int id, double cash)
	{
		try( PreparedStatement pstmt = conn.prepareStatement("UPDATE users SET cash = ? WHERE user_id = ?") )
		{
			pstmt.setDouble(1, cash);
			pstmt.setInt(2, id);
			
			// if update occurred, count will be 1,
			// if didn't occur, count will be 0
			int count = pstmt.executeUpdate();
			
			if(count > 0) {
				return true;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		
		return false;
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
