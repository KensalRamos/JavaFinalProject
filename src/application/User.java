package application;



/*

 * File name: 		User.java

 * Name: 			Kensal J. Ramos

 * Course:			COP 2800C at Valencia College

 * Instructor:		David Stendel

 * Description:		This will be our abstract parent class for our other objects.

 * 

 * Team Members:	Carlos Ortiz

 * 					Kenneth Soltis	

 * Date:			22 April 2019

 * 

 * 

 * 

 * 

 */



public abstract class User {



	abstract boolean hasEdit();

	abstract boolean hasAdminPerms();

	

	private String username;

	private String password;

	

	public User() {

		

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