package Classes;

/**
 * @author Adrián Peñalver Fernández
 * @version 1.4.0
 * 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class DAO{

	private static final String SERVER = "127.0.0.1";
	private static final String PORT = "3306";
	private static final String DATABASE = "maze";
	private static final String URL = "jdbc:mysql://"+SERVER+":"+PORT+"/"+DATABASE;
	private static final String USER = "root";
	private static final String PASS = "1234";
	
	public User login(String username, String password) {

		try {

			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();

			String consulta = "SELECT * FROM user WHERE username = '" + username + "' AND password = '" + password + "';";
			ResultSet rs = stmt.executeQuery(consulta);
			
			User user = new User();

			while (rs.next()) {
				
				if(rs.getString("id")!=null) {
					
					user = new User(rs.getString("id"),rs.getString("username"),rs.getString("name"),rs.getString("nif"),rs.getString("email"),rs.getString("address"),rs.getString("birthdate"),rs.getString("role"));
					return user;
					
				}
				
			}

			rs.close();
			stmt.close();
			conn.close();
			
			return null;

		} catch (Exception e) {
			
			return null;
			
		}
		
	}
	
	public boolean checkUser(User user) {
		
		boolean check = false;
		
		try {

			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();

			String consulta = "SELECT * FROM user WHERE username = " + user.username + " OR nif = '" + user.nif + "' OR email = '"+user.email+"';";
			ResultSet rs = stmt.executeQuery(consulta);

			int i = 0;
			
			while (rs.next()) {

				i++;

			}
			
			if(i>0) {
				check = true;
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {

			check = false;

		}
		
		return check;
		
	}
	
	public boolean signup(User u, String password) {
			
		String pass = Utils.encryptMd5(password);
			
		try {

			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();

			String query = "INSERT INTO user (username, password, name, nif, email, address, birthdate, role) values ('"+u.username+"','"+pass+"','"+u.name+"','"+u.nif+"','"+u.email+"','"+u.addres+"','"+u.birthdate+"','user');";
				
			stmt.executeUpdate(query);
				
			stmt.close();
			conn.close();
			return true;

		} catch (Exception e) {
			
			return false;
				
		}
		
	}
	
	public boolean checkPassword(String username, String password) {

		boolean exit = false;
		
		try {

			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();

			String consulta = "SELECT username FROM user WHERE username = '" + username + "' AND password = '" + password + "';";
			ResultSet rs = stmt.executeQuery(consulta);

			while (rs.next()) {
					
				if(rs.getString("username").equals(username)) {
						
					exit = true;
						
				}
					
			}

			rs.close();
			stmt.close();
			conn.close();
			
			return exit;

		} catch (Exception e) {

			return exit;
				
		}
		
	}
	
	public boolean changeOneData(int id, String colum, String data) {
		
		try {

			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();

			String consulta = "UPDATE user SET "+colum+" = '"+data+"' WHERE id = "+id+";";
			stmt.executeUpdate(consulta);

			stmt.close();
			conn.close();
			
			return true;

		} catch (Exception e) {

			return false;
				
		}
		
	}
	
	public boolean changeOneData(int id, String colum, int data) {
		
		try {

			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();

			String consulta = "UPDATE user SET "+colum+" = "+data+" WHERE id = "+id+";";
			stmt.executeUpdate(consulta);

			stmt.close();
			conn.close();
			
			return true;

		} catch (Exception e) {

			return false;
				
		}
		
	}
	
	public boolean deleteUser(int id) {
		
		try {

			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();

			String consulta = "DELETE FROM user WHERE id = "+id+";";
			stmt.executeUpdate(consulta);

			stmt.close();
			conn.close();
			
			return true;

		} catch (Exception e) {

			return false;
				
		}
		
	}
	
	public boolean checkField(String colum, String data) {
		
		boolean check = false;
		
		try {

			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();

			String consulta = "SELECT * FROM user WHERE "+colum+" = '"+data+"';";
			ResultSet rs = stmt.executeQuery(consulta);

			int i = 0;
			
			while (rs.next()) {

				i++;

			}
			
			if(i>0) {
				check = true;
			}

			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {

			check = false;

		}
		
		return check;
		
	}
	
	public User[] listUsers() {
		
		ArrayList<User> users = new ArrayList<User>();
		
		try {

			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();

			String consulta = "SELECT * FROM user;";
			ResultSet rs = stmt.executeQuery(consulta);
			
			while (rs.next()) {

				users.add(new User(rs.getString("id"),rs.getString("username"),rs.getString("name"),rs.getString("nif"),rs.getString("email"),rs.getString("address"),rs.getString("birthdate"),rs.getString("role")));

			}
			
			rs.close();
			stmt.close();
			conn.close();

		} catch (Exception e) {

			System.out.println(e);
			return null;

		}
		
		return users.toArray(new User[0]);
		
	}
	
}