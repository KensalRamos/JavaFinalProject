package application;

import application.User;



/*

 * File name: 		Application.java

 * Name: 			Kensal J. Ramos

 * Course:			COP 2800C at Valencia College

 * Instructor:		David Stendel

 * Description:		Members have read, and create privilages. 

 * 

 * Team Members:	Carlos Ortiz

 * 					Kenneth Soltis	

 * Date:			22 April 2019

 * 

 * 

 * 

 * 

 */



public class Adjunct extends User {



	private String username;

	private String password;

	

	public Adjunct() {

		setUsername(username);

		setPassword(password);

	}

	

	public boolean hasEdit() {

		return true;

	}

	

	public boolean hasAdminPerms() {

		return false;

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