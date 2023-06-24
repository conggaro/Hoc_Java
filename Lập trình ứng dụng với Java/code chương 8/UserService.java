package myapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class UserService {
	private String dbName;
	private String url;
	private String dbUsername;
	private String dbPassword;
	private Connection connection;
	public UserService() {
		this.dbName = "java";
		this.url = "jdbc:mysql://localhost:3306/" + this.dbName + 
				"?useUnicode=true&characterEncoding=utf-8";
		this.dbUsername = "root";
		this.dbPassword = "123456789";
		this.connection = null;
	}
	public ArrayList<User> getAllUsers() throws Exception{
		ArrayList<User> listUsers = new ArrayList<User>();
		User user = null;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		connection = DriverManager.getConnection(url, dbUsername, dbPassword);
		System.out.println("Ket noi den DB " + dbName + " thanh cong!");
		String query = "SELECT * FROM `user` inner join role on `user`.role = role.role_id";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		while(resultSet.next()) {
			user = new User();
			user.setUserID(resultSet.getString("user_id"));
			user.setUserName(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
			user.setFullName(resultSet.getString("fullname"));
			user.setDateBirth(simpleDateFormat.parse(resultSet.getString("date_of_birth")));
			user.setEmail(resultSet.getString("email"));
			user.setRoleID(resultSet.getString("role"));
			user.setRoleName(resultSet.getString("role_name"));
			listUsers.add(user);
		}
		statement.close();
		connection.close();
		return listUsers;
	}
	public void addUser(User newUser) throws Exception {
		connection = DriverManager.getConnection(url, dbUsername, dbPassword);
		String query = " insert into user(username, password, fullname, date_of_birth, email, role) "
				+ " values(?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, newUser.getUserName());
		preparedStatement.setString(2, newUser.getPassword());
		preparedStatement.setString(3, newUser.getFullName());
		preparedStatement.setTimestamp(4, new Timestamp(newUser.getDateBirth().getTime()));
		preparedStatement.setString(5, newUser.getEmail());
		preparedStatement.setInt(6, Integer.parseInt(newUser.getRoleID()));
		int rows = preparedStatement.executeUpdate();
		if(rows > 0) {
			System.out.println("Them thanh cong " + newUser.toString());
		}
		preparedStatement.close();
		connection.close();
	}
	
	public User getUserByID(String userID) throws Exception {
		User user = new User();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		connection = DriverManager.getConnection(url, dbUsername, dbPassword);
		String query = "SELECT * FROM `user` WHERE user.user_id = " + userID; 
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		while(resultSet.next()) {
			user = new User();
			user.setUserID(resultSet.getString("user_id"));
			user.setUserName(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
			user.setFullName(resultSet.getString("fullname"));
			user.setDateBirth(simpleDateFormat.parse(resultSet.getString("date_of_birth")));
			user.setEmail(resultSet.getString("email"));
			user.setRoleID(resultSet.getString("role"));
		}
		statement.close();
		connection.close();
		return user;
	}
	
	public void updateUser(User newUser) throws Exception {
		User oldUser = this.getUserByID(newUser.getUserID());
		if(!newUser.getPassword().trim().equals("")) {
			oldUser.setPassword(newUser.getPassword());
		}
		if(!newUser.getFullName().trim().equals("")) {
			oldUser.setFullName(newUser.getFullName());
		}
		if(!newUser.getEmail().trim().equals("")) {
			oldUser.setEmail(newUser.getEmail());
		}
		if(newUser.getDateBirth().getTime() > 0) {
			oldUser.setDateBirth(newUser.getDateBirth());
		}
		if(!newUser.getRoleID().trim().equals("")) {
			oldUser.setRoleID(newUser.getRoleID());
		}
		connection = DriverManager.getConnection(url, dbUsername, dbPassword);
		String query = "update user set password = ?, fullname = ?, email = ?, date_of_birth = ?, role = ? "
				+ " where user_id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setString(1, oldUser.getPassword());
		preparedStatement.setString(2, oldUser.getFullName());
		preparedStatement.setString(3, oldUser.getEmail());
		preparedStatement.setTimestamp(4, new Timestamp(oldUser.getDateBirth().getTime()));
		preparedStatement.setString(5, oldUser.getRoleID());
		preparedStatement.setString(6, oldUser.getUserID());
		int rows = preparedStatement.executeUpdate();
		if(rows > 0) {
			System.out.println("Update thanh cong " + oldUser.toString());
		}
		preparedStatement.close();
		connection.close();
	}
	
	public void deleteUser(User user) throws Exception {
		connection = DriverManager.getConnection(url, dbUsername, dbPassword);
		String query = "DELETE FROM user WHERE user.user_id = ? ";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
		preparedStatement.setInt(1, Integer.parseInt(user.getUserID()));
		int rows = preparedStatement.executeUpdate();
		if(rows > 0) {
			System.out.println("Xoa thanh cong User " + user.getUserID());
		}
		preparedStatement.close();
		connection.close();
	}
	
	
	
	
}
