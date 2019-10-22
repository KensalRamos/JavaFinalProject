package application;

/*

 * File name: 		Application.java

 * Name: 			Kensal J. Ramos

 * Course:			COP 2800C at Valencia College

 * Instructor:		David Stendel

 * Description:		Admin class has all edit, delete, and create privileges.

 * 

 * Team Members:	Carlos Ortiz

 * 					Kenneth Soltis	

 * Date:			22 April 2019

 * 

 * 

 * 

 * 

 */



import application.User;



public class Admin extends User {



	// Fields

	private String username;

	private String password;

	

	public Admin() {

		setUsername(username);

		setPassword(password);

	}



	public boolean hasEdit() {

		return true;

	}

	

	public boolean hasAdminPerms() {

		return true;

	}

	

	/**

	 * @return the username

	 */

	public String getUsername() {

		return username;

	}



	/**

	 * @param username the username to set

	 */

	public void setUsername(String username) {

		this.username = username;

	}



	/**

	 * @return the password

	 */

	public String getPassword() {

		return password;

	}



	/**

	 * @param password the password to set

	 */

	public void setPassword(String password) {

		this.password = password;

	}



	

}