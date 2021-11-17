package modele;

import java.io.IOException;
import java.io.Writer;

/**  exo201: JDBC - DAO  ***/

public class User {
    ///////////ATTRIBUTS
	private long id;
	private String firstname; //prenom
	private String lastname;  // nom
	private String email;
	private String password;
	private String erreurs;
	///////////  GET SET
	public long getId() {
		return id;
	}
	public void setId(long i) {
		this.id = i;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String f) {
		this.firstname = f;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String l) {
		this.lastname = l;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String e) {
		this.email = e;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String p) {
		this.password = p;
	}

	public String getErreurs() {
		return erreurs;
	}
	public void setErreurs(String e) {
		this.erreurs = e;
	}
	////// METHOD  
	public void afficheUser() { // console
			System.out.println("personne");
			System.out.println( String.format(".%03d -> %16s - %16s - %16s - %16s ", getId(),getFirstname(), getLastname(),getEmail(),getPassword()) );
	}

	public void printUser(Writer out) { // HTML
		try {
			out.append("<ul>\n");
			out.append( String.format("<li class=\"%03d\">%03d - %16s - %16s- %16s - %16s</li>\n",this.getId(), this.getId(),this.getFirstname(),this.getLastname(),this.getEmail(),getPassword()));
			out.append("</ul>\n");  
		} catch (IOException e) {}
	}
	
	/////////////////////////////////////////////
	 /// methodes de validations de champs de saisies
/*	 
   
*/	
	
	///////////////////////////////////////////////////////////////////

}
