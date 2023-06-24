package user.mysql;

import java.util.Date;

public class User {
	private String userID;
	private String userName;
	private String password;
	private String fullName;
	private String email;
	private Date dateBirth;
	private String roleID;
	private String roleName;
	public User() {}
	public User(String userID, String userName, String password, String fullName, String email, 
			Date dateBirth, String roleID, String roleName) {
		this.userID = userID;
		this.userName = userName;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.dateBirth = dateBirth;
		this.roleID = roleID;
		this.roleName = roleName;
	}
	public void display() {
		System.out.println(this.toString());
	}
	@Override
	public String toString() {
		return "User [userID=" + userID + ", userName=" + userName + ", password=" + password + ", fullName=" + fullName
				+ ", email=" + email + ", dateBirth=" + dateBirth + ", roleID=" + roleID + ", roleName=" + roleName
				+ "]";
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getDateBirth() {
		return dateBirth;
	}
	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
}
