package dao;


import java.util.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import modele.User;


/**  exo201: JDBC - DAO  ***/

public class UserDaoImpl implements UserDao {
	
	protected Connection conn;
	
	//////////////// CST /////////////
	public UserDaoImpl( String dbfile ) {
		// open a connection with propper jdbc
		this.conn = null;
		String jdbcUrl = "jdbc:sqlite:";
		try{ // dbfile = chemin de la resource
			Class.forName("org.sqlite.JDBC");
			this.conn = DriverManager.getConnection(jdbcUrl+ dbfile);
		}
		catch (SQLException | ClassNotFoundException  e){
			throw new Error("impossible d'ouvrir le fichier sqlite"+ dbfile, e);
		}
    } 
	
	////////  METHOD  ///////////////////////
	public void add(User user, String password) {
		// TODO : create a user
		String query = "INSERT INTO Users (firstname, lastname, email, password)"
                 + "VALUES(?, ?, ?, ?);";
		PreparedStatement ps =null;
		try {
			ps = this.conn.prepareStatement(query);
			ps.setString(1, user.getFirstname());
			ps.setString(2, user.getLastname());
			ps.setString(3, user.getEmail());
			ps.setString(4, password);
			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 		
	}
	
	public void update(User user, String password) {
		// TODO : update user information in DB
	    String query = "UPDATE Users "
                 + "SET firstname = ?, lastname = ?, email = ?, password = ? "
                 + "WHERE id = ?;";
	   PreparedStatement ps =null;
	   try {
			ps = conn.prepareStatement(query);
			ps.setString(1, user.getFirstname());
			ps.setString(2, user.getLastname());
			ps.setString(3, user.getEmail());
			ps.setString(4, password);
			ps.setInt(5, (int)user.getId());
			ps.executeUpdate();
			ps.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} 
	}
	
	public List<User> findAll(){
		// get all personnnes and assign each to a list
		String query = "SELECT * FROM Users";
		List<User> lpers = new ArrayList<>();
		User user;
		Statement stm =null;
		ResultSet rs =null;
		try {
			stm = conn.createStatement();
			rs =  stm.executeQuery(query);
			while( rs.next() ){	
				user= new User();
				user.setId(rs.getInt("id"));
				user.setFirstname( rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail( rs.getString("email"));
				user.setPassword( rs.getString("password"));
				lpers.add(user);	
			}	
			stm.close();			
		} catch (SQLException e ){
			throw new Error(" unable to excecute '"+ query+ "':",e);
		}
		return lpers;		
	}
	
	public User find(long id) {  // findById(long id)
		// TODO : get user data by its ID and map to User object 
		String query = "SELECT lastname,firstname,email FROM Users WHERE id = ?";
		User user=null;
		PreparedStatement ps = null;
		ResultSet rs =null;		
		try{
		    ps = conn.prepareStatement(query);
			int i = (int)id;
			ps.setInt(1,i);  				
			rs = ps.executeQuery();
			if( rs.next()){
				user= new User();
				user.setId(id);
				user.setFirstname(rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
			}
			ps.close();
	    } catch (SQLException e ){
			throw new Error(" unable to excecute '"+ query+ "':",e);
		}
		return user;			
	}
	
	public User findByEmail(String email) {
		// TODO : get user data by its ID and map to User object 
		String query = "SELECT id,lastname,firstname,email FROM Users WHERE email = ?"; 
		User user=null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		try{
			ps = conn.prepareStatement(query);
			ps.setString( 1, email );  				
			rs = ps.executeQuery();
			if(rs.next()){
				user= new User();
				user.setId(rs.getInt("id"));
				user.setFirstname( rs.getString("firstname"));
				user.setLastname(rs.getString("lastname"));
				user.setEmail(email);
			}
			ps.close();
	    } catch (SQLException e ){
			throw new Error(" unable to excecute '"+ query+ "':",e);
		}
		return user;
	}

	public long checkPassword(String email, String password) {
		// TODO : get user id that match, return -1 if none
		String query = "SELECT id FROM Users WHERE email = ? AND password=?";
		long ret=-1;
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(query);
			ps.setString( 1, email ); 
			ps.setString( 2, password ); 				
			ResultSet rs = ps.executeQuery();
			if(rs.next()) ret= rs.getInt("id"); // int -> long
			ps.close();
	    } catch (SQLException e ){
			throw new Error(" unable to excecute '"+ query+ "':",e);
		} 
		return ret;	
	}


	public void delete(long id) {
		// TODO : delete a user that match this ID
		String query = "DELETE FROM Users WHERE id = ?";
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(query);
			int i = (int)id;
			//ps.setLong(1,id); 
			ps.setInt( 1,i );  
			ps.executeUpdate();
			ps.close();
	    } catch (SQLException e ){
			throw new Error(" unable to excecute '"+ query+ "':",e);
		} 
	}
	public long exists(String email) {
		// TODO : check if user with that mail exists
		String query = "SELECT id FROM Users WHERE email = ?";
		long ret=-1;
		PreparedStatement ps = null;
		try{
			ps = conn.prepareStatement(query);
			ps.setString( 1, email );  
			ResultSet rs = ps.executeQuery();
			if(rs.next()) ret= rs.getInt("id"); // int -> long
			ps.close();
	    } catch (SQLException e ){
			throw new Error(" unable to excecute '"+ query+ "':",e);
        } 
		return ret;	
	}
	
}
