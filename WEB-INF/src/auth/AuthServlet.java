package auth;
import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import dao.UserDao;
import dao.UserDaoImpl;

/***  exo201:DAO avec JDBC - SerVLET/JSP ***/

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

	// TODO : only handle POST request for authentication
	// @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException {
			System.out.println("+++++++++++++   AuthServlet- doPost  ++++++++++++++++++++++");
			// TODO : get login / password from request parameters
			String login = null;  // admin@foo.com
			String password = null; // admin
			login = req.getParameter("femail");
			password = req.getParameter("fpassword"); 
			if ( login == null || password == null ) throw new ServletException("no email-login/password");
			//boolean succeed = "admin@foo.com".equals(login) && "admin".equals(password);
			UserDao userDao;
		    String resource=UserDao.SQLBDD+"BddUsers.db";
		    userDao = new UserDaoImpl(resource);
			if( userDao.checkPassword(login,password)!= -1){  // existe  en bdd
			// TODO : if auth is OK
			  // add something in session for next calls, session authentification
				HttpSession session = req.getSession();//initiation du moteur de session de jee.
				session.setAttribute("authentication","authenticated");//maj  variable de session
			  // then redirect to "welcome.jsp"
			  req.getRequestDispatcher("WEB-INF/jsp/welcome.jsp").forward(req,res);
			}
			else
			{		System.out.println("+++++++++++++   errorMessage- doPOst  ++++++++++++++++++++++");
			// TODO : if auth KO
			  // set an "errorMessage" in request attribute
			   req.setAttribute("errorMessage","refaire login/password");
			  // forward to auth.jsp with request dispatcher
			   req.getRequestDispatcher("WEB-INF/jsp/auth.jsp").forward(req,res);
			}
			
		}
	
	
	
	// TODO : allow to disconnect with a GET to /auth with any parameter "logout" value
	// @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException {
			System.out.println("+++++++++++++   AuthServlet- doGet  ++++++++++++++++++++++");
				// req.getRequestDispatcher("WEB-INF/jsp/auth.jsp").forward(req,res);
			  // TODO : check for "logout" parameter
			  //   if so : disconnect and show auth.jsp
				if (req.getParameter("logout") != null)
				{  System.out.println("+++++++++++++   LOGOUT- doGet  ++++++++++++++++++++++");
					HttpSession session = req.getSession();//initiation du moteur de session de jee.
					session.removeAttribute("authentication"); //suppr  variable de session
					//req.getRequestDispatcher("WEB-INF/src/auth.jsp").forward(req,res);
				}
				/*else
				{ //if not : Error 500
					throw new ServletException("no login/password");
					//req.getRequestDispatcher("WEB-INF/jsp/auth.jsp").forward(req,res);
				}*/
				req.getRequestDispatcher("WEB-INF/jsp/auth.jsp").forward(req,res);
			}

				
	//   
}

