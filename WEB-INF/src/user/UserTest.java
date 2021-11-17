package user;
import java.sql.*;
import java.util.List;

import dao.UserDao;
import dao.UserDaoImpl;
import modele.User;

/***  ex0201:DAO avec JDBC - User ***/ 
public class UserTest {
	
	public static void main(String[] args) {
		String resource = UserDao.SQLBDD +"BddUsers.db";
		System.out.println("UserTest :resource =" + resource);
		UserDao pDao = new UserDaoImpl(resource);
		// par defaut dans tomcat/bin/....
		
		
		List<User> lpers = pDao.findAll();
		if ( lpers.isEmpty() ) {
			System.out.println("Aucun utilisateur en base");
		}
		else
		System.out.println("--- ***** findAll ****** -----");
		for(User per : lpers) {
			System.out.println( 
				String.format(
					"%3d | %16s | %16s | %16s| %16s",
					per.getId(),
					per.getFirstname(),
					per.getLastname(),
					per.getEmail(),
					per.getPassword()
				)
			);
		}
		for(User per : lpers) {
			per.afficheUser(); 
		}
	}	
}