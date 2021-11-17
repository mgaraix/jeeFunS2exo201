package user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import dao.UserDao;
import dao.UserDaoImpl;
import modele.User;

/***  exo201:DAO avec JDBC - SerVLET/JSP ***/

@WebServlet(urlPatterns="/register")
public class RegisterServlet extends HttpServlet {

	protected UserDao userDao;
	
	@Override
	public void init() throws ServletException {
		 System.out.println("#### INIT: " + getServletName()+ " OK ####");
		// TODO : initialize user DAO
		this.userDao = null;
		String resource=UserDao.SQLBDD+"BddUsers.db";
		userDao = new UserDaoImpl(resource);
		System.out.println("resource: "+resource);
		// par defaut dans tomcat/bin/....
	}
	
	// @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException {
		System.out.println("+++++++++++++   RegisterServlet- doGet  ++++++++++++++++++++++");
		req.getRequestDispatcher("WEB-INF/jsp/register.jsp").forward(req,res);
	}
	
	// TODO : only handle POST request for enregistrement
	// @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException {
		System.out.println("+++++++++++++   RegisterServlet- doPost  ++++++++++++++++++++++");
		 // validation du formulaire
		//validateForm(req);
		Enumeration en=req.getParameterNames();
		Map<String,String> map = new HashMap<>();
		boolean bool=true;
		String errForm="";
		while(en.hasMoreElements()&& bool){
			String npm= (String)en.nextElement();
			String vpm= (String)req.getParameter(npm);
			map.put(npm,vpm);
			System.out.println("Parameter Name is '"+npm+"' and Parameter Value is '"+vpm+"'");
			if (vpm == null || vpm.equals("")) {
				bool = false;
				errForm += npm + " est obligatoire!";
		    }
		System.out.println("Parameter Name is '"+npm+"' and Parameter Value is '"+vpm+"'");
		}
		if( errForm.equals("")){ // formulaire validé
			 User usr = new User();
             usr.setFirstname(map.get("ffirstname"));
             usr.setLastname(map.get("flastname"));
             usr.setEmail(map.get("femail"));
			 usr.setPassword(map.get("password"));
             if( userDao.exists(map.get("femail"))!= -1 ) { // existe  en bdd
			  userDao.update(usr,map.get("fpassword"));
			 }
			 else{  // existe pas en bdd
              userDao.add(usr,map.get("fpassword"));
			 }
			 req.getRequestDispatcher("WEB-INF/jsp/auth.jsp").forward(req,res); 
		 }
		 else{
			 req.setAttribute("errorsForm",errForm);
			 req.getRequestDispatcher("WEB-INF/jsp/register.jsp").forward(req,res); 
		 }
	}
	/*
	public String validateForm(HttpServletRequest req){
		Enumeration en=req.getParameterNames();
		Map<String,String> map = new HashMap<>();
		boolean bool=true;
		String errForm="";
		while(en.hasMoreElements()&& bool){
			String npm= (String)en.nextElement();
			String vpm= (String)req.getParameter(npm);
			map.put(npm,vpm);
			if (vpm == null || vpm.equals("")) {
				bool = false;
				errForm += npm + " est obligatoire!";
		    }
		System.out.println("Parameter Name is '"+npm+"' and Parameter Value is '"+vpm+"'");
		}
		return errForm;	
	}
	*/
				
	//   
}

