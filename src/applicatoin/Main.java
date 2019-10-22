package application;

import java.lang.reflect.Member;
import java.sql.*;
import java.util.ArrayList;

import java.util.List;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Scene;

import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;

import application.Admin;

import application.Adjunct;

/**
 * @author Carlos
 *
 */
public class Main extends Application {

	@Override

	public void start(Stage primaryStage) {

		try {

			BorderPane root = (BorderPane) FXMLLoader.load(getClass().getResource("Scene1.fxml"));

			Scene scene = new Scene(root, 400, 400);

			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);

			primaryStage.show();

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

	static List<User> database = new ArrayList<>();

	/**
	 * @param args
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

		loadData(database);
		launch(args);
		flushData(database);
		saveData(database);

	}

	public static void createAdmin(List<User> database, String username, String password) {

		Admin admin = new Admin();

		admin.setUsername(username);

		admin.setPassword(password);

		System.out.println("Admin created with username: " + username + ", password: " + password);

		database.add(admin);

	}

	public static void createMember(List<User> database, String username, String password) {

		Adjunct member = new Adjunct();

		member.setUsername(username);

		member.setPassword(password);

		System.out.println("Member created with username: " + username + ", password: " + password);

		database.add(member);

	}

	// 999 = no object with such combination found

	public static int verify(List<User> database, String username, String password) {

		int flag = 999;

		for (int i = 0; i < database.size(); i++)

			if (database.get(i).getUsername().equals(username) && database.get(i).getPassword().equals(password))

				flag = i;

		return flag;

	}

	/**
	 * This method creates a jdbc connection object to the SQL server then pulls
	 * information from server to the application
	 * 
	 * @param database
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void loadData(List<User> database) throws ClassNotFoundException, SQLException {
		// local variables for the connection
		Connection conn = null;
		ResultSet rS = null;

		// 1.create the jdbc connection obj
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection("jdbc:sqlserver://localhost:3306;database = FORUM_PROJECT;", "Admin",
				"password");

		// 2.set up Statement
		Statement state = conn.createStatement();

		// 3. execute query
		rS = state.executeQuery("select * from USERS");

		// 4. Process result set
		// might have to create user objects then fill in the values accordingly
		for (int i = 0; rS.next(); i++) {

			if (rS.getString("ROLE") == "Administrator") {
				createAdmin(database, rS.getString("USERNAME"), rS.getString("PASSCODE"));

			} // end if
			else {
				createMember(database, rS.getString("USERNAME"), rS.getString("PASSCODE"));
			} // end else

		} // end for

		// 5. execute query for blog
		rS = state.executeQuery("select * from FORUM");
		for (int i = 0; rS.next(); i++) {
			Blog newBlog = new Blog();

			newBlog.setTitle(rS.getString("TITLE"));

			newBlog.setBody(rS.getString("POST"));

			Controller.Blog1.add(newBlog);

		}
	}

	/**
	 * This method establishes a jdbc connection obj to read from an array list and
	 * store the information on ms SQL server database
	 * 
	 * @param database
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void saveData(List<User> database) throws ClassNotFoundException, SQLException {

		// local variables for the connection
		Connection conn = null;
		PreparedStatement pS = null;
		ResultSet rS = null;

		// 1.create the jdbc connection obj
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection("jdbc:sqlserver://localhost:3306;database = FORUM_PROJECT;", "Admin",
				"password");

		// 3. save
		for (int i = 0; i < database.size(); i++) {
			// 2.here i create the prepared statement obj
			pS = conn.prepareStatement("insert into USERS (USERID,USERNAME, PASSCODE, ROLE) values (?,?,?,?)");
			pS.setInt(1, i);
			pS.setString(2, database.get(i).getUsername());
			System.out.println("Added: " + database.get(i).getUsername());
			pS.setString(3, database.get(i).getPassword());
			if (database.get(i).hasAdminPerms()) {
				pS.setString(4, "Administrator");
			} // if
			else if (database.get(i).hasEdit()) {
				pS.setString(4, "Adjunct");
			} else
				pS.setString(4, "guest");

			pS.executeUpdate();
		} // end for

		// 3. save
		for (int j = 0; j < Controller.Blog1.size(); j++) {
			pS = conn.prepareStatement("insert into FORUM (FORUMID,TITLE, POST) values (?,?,?)");
			pS.setInt(1, j);
			pS.setString(2, Controller.Blog1.get(j).getTitle());
			pS.setString(3, Controller.Blog1.get(j).getBody());
			int res = pS.executeUpdate();
			

			if (res == 0)
				System.out.println("Nothing was inserted to database");
			else
				System.out.println("data was saved to database");
		} // end for

	}

	/**
	 * This deletes all pre-existing data in the tables of the SQL database to
	 * prepare to save the data from the application
	 * 
	 * @param database
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public static void flushData(List<User> database) throws ClassNotFoundException, SQLException {

		// local variables for the connection
		Connection conn = null;
		PreparedStatement pS = null;
		ResultSet rS = null;

		// 1.create the jdbc connection obj
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		conn = DriverManager.getConnection("jdbc:sqlserver://localhost:3306;database = FORUM_PROJECT;", "Admin",
				"password");

		// 2.here i create the prepared statement obj

		pS = conn.prepareStatement("DELETE from USERS");

		// 4.execute query
		int res = pS.executeUpdate();

		// 5.create prepared statement obj for forum
		pS = conn.prepareStatement("DELETE from FORUM");
		// 6.execute query for FORUM
		res = pS.executeUpdate();
	}

	public static boolean checkUser(List<User> database, String username) {

		boolean flag = true;

		System.out.println("Passed in: " + username);

		for (int i = 0; i < database.size(); i++) {

			System.out.println("Database at index " + i + "has: " + database.get(i).getUsername());

			if (database.get(i).getUsername().equals(username)) {

				flag = false;

				break;

			}

		}

		return flag;

	}

}