package application;

import java.io.IOException;

import java.util.ArrayList;



import javafx.collections.FXCollections;

import javafx.collections.ObservableList;

import javafx.event.ActionEvent;

import javafx.fxml.FXMLLoader;

import javafx.scene.Node;

import javafx.scene.Parent;

import javafx.scene.Scene;

import javafx.scene.control.Button;

import javafx.scene.control.ComboBox;

import javafx.scene.control.Label;

import javafx.scene.control.ListView;

import javafx.scene.control.PasswordField;

import javafx.scene.control.TextArea;

import javafx.scene.control.TextField;

import javafx.stage.Stage;



/*

 * File name: 		Controller.java

 * Name: 			Kenneth Soltis

 * Course:			COP 2800C at Valencia College

 * Instructor:		David Stendel

 * Description:		This will be the controller class for our GUI and contains action listeners

 * 

 * Team Members:	Carlos Ortiz

 * 					Kensal J. Ramos	

 * Date:			22 April 2019

 * 

 * 

 * 

 * 

 */



public class Controller {

	public Button Delete;

	public Button logOut;

	public Label errorLine;

	public Button newUser ;

	public TextField userField;

	public PasswordField passwordField;

	public PasswordField confirmPassword;

	public TextField Title;

	public Button verifyUser;

	public Label error1;

	public Label error2;

	public Label error3;

	public Button delete;

	public TextField mainUserField;

	public PasswordField mainPasswordField;

	public TextArea Body;

	public ListView<String> List;

	public String temp;

	public static ArrayList<Blog> Blog1 = new ArrayList<Blog>();

	

	

	public void logOut(ActionEvent event) {

		try {

			 Parent mainScreen = FXMLLoader.load(getClass().getResource("Login.fxml"));

			 Scene mainScene = new Scene(mainScreen);

		        Stage main_stage= (Stage)((Node)event.getSource()).getScene().getWindow();

		        main_stage.setScene(mainScene);

		        main_stage.show();

		} catch (IOException e) {

			// TODO Auto-generated catch block

			e.printStackTrace();

		}

	}

	/**

	 * When you click the logout button it will take you back to the main screen and send your data to the database

	 */

	public void CancelNewUser(ActionEvent event) {

		try {

		Parent mainScreen = FXMLLoader.load(getClass().getResource("Login.fxml"));

        Scene mainScene = new Scene(mainScreen);

        Stage main_stage= (Stage)((Node)event.getSource()).getScene().getWindow();

        main_stage.setScene(mainScene);

        main_stage.show();

	}catch (IOException eox) {

        eox.printStackTrace();

    }

	}/**

	 * Upon click will take you back to the Login screen

	 */	

	public void deletePost(ActionEvent event) {

		try{		

		temp=List.getSelectionModel().getSelectedItem();

		List.getItems().remove(temp);

		Body.clear();

		Title.clear();

		}catch(NullPointerException e) {

			error3.setVisible(true);

			error3.setText("Select a post to delete it.");

		}

	}

	/**

	 * Will delete the selected post after clicking the button by removing the body and title from the list

	 */

	public void displayBlog(ActionEvent event) {

		try {

	temp=List.getSelectionModel().getSelectedItem();

		for(int i=0;i<Blog1.size();i++) {

			if(temp.equals(Blog1.get(i).getTitle())) {

				Title.setText(Blog1.get(i).getTitle());

				Body.setText(Blog1.get(i).getBody());

				

			}

		}

	}catch(NullPointerException e) {

		error3.setVisible(true);

		error3.setText("Select a post to delete it.");

	}

	}

	/**

	 * Will display the selected blog post and display it to your textArea and titleBox

	 */

	public void AddToBlog(ActionEvent event) {

		//if guest hide add to blog

		Blog newBlog=new Blog();

		newBlog.setTitle(Title.getText());

		newBlog.setBody(Body.getText());

		Blog1.add(newBlog);

		Body.clear();

		Title.clear();

		List.getItems().addAll(newBlog.getTitle());

	}/**

	 * Will add the whatever is in your textArea and textBox

	 */

	public void VerifyUser(ActionEvent event) {	

		if (Main.verify(Main.database, mainUserField.getText(), mainPasswordField.getText()) != 999) {

			System.out.println("User verfied! User: " + Main.database.get(Main.verify(Main.database, mainUserField.getText(), mainPasswordField.getText())).getUsername());

			mainUserField.clear();

			mainPasswordField.clear();

			Parent mainScreen;

			try {

				mainScreen = FXMLLoader.load(getClass().getResource("Scene1.fxml"));

				 Scene mainScene = new Scene(mainScreen);

			        Stage main_stage= (Stage)((Node)event.getSource()).getScene().getWindow();

			        main_stage.setScene(mainScene);

			        main_stage.show();

			} catch (IOException e) {

				// TODO Auto-generated catch block

				e.printStackTrace();

			}

		}

		else

			error1.setText("User Not Found");

			error1.setVisible(true);

	}

	/**

	 * Will verify upon clicking if the username and password if they exist and if they do you can login and go to the blog page

	 */

public void CreateNewUser(ActionEvent event) {

	try {

		if (passwordField.getText().equals(confirmPassword.getText())) {

			//System.out.println("User: " + userField.getText() + "Pass: " + passwordField.getText());

			if(Main.checkUser(Main.database, userField.getText())) {

				Main.createMember(Main.database, userField.getText(), passwordField.getText());

				Parent mainScreen = FXMLLoader.load(getClass().getResource("Login.fxml"));

			    Scene mainScene = new Scene(mainScreen);

			    Stage main_stage= (Stage)((Node)event.getSource()).getScene().getWindow();

			    main_stage.setScene(mainScene);

			    main_stage.show();

			}

			else

				error2.setText("Username Taken!!");

				error2.setVisible(true);

		}

		else

			error2.setText("Passwords don't match!");

			error2.setVisible(true);

	} catch (IOException eox) {

        eox.printStackTrace();

	}

}	

/**

 * Will add new user to the username and password database if the username isn't taken and the both passwords in the password field match

 */

	public void NewUser(ActionEvent event) {

		try {

            

            Parent newUser = FXMLLoader.load(getClass().getResource("NewLogin.fxml"));

            Scene newUserScene = new Scene(newUser);

            Stage app_stage= (Stage)((Node)event.getSource()).getScene().getWindow();

            app_stage.setScene(newUserScene);

            app_stage.show();

        } catch (IOException eox) {

            eox.printStackTrace();

        }

	}

	/**

	 * Will take you to the UserName creation page if you don't have one upon click 

	 */

}