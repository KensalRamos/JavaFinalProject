package application;



import application.User;



/*

 * File name: 		Application.java

 * Name: 			Kensal J. Ramos

 * Course:			COP 2800C at Valencia College

 * Instructor:		David Stendel

 * Description:		Guests are placeholder accounts for users that do not create a member or admin account

 * 

 * Team Members:	Carlos Ortiz

 * 					Kenneth Soltis	

 * Date:			22 April 2019

 * 

 * 

 * 

 * 

 */



public class Guest extends User {

	

	public boolean hasEdit() {

		return false;

	}

	

	public boolean hasAdminPerms() {

		return false;

	}

	

	

	

}