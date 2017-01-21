package ine.wmd.entities;

import java.security.Principal;

public class User implements Principal {
	
	String login;
	String pswd;
	
	public User(){
	}
	
	public User(String login, String pswd) {
		this.login = login;
		this.pswd = pswd;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPswd() {
		return pswd;
	}

	public void setPswd(String pswd) {
		this.pswd = pswd;
	}

	@Override
	public String getName() {
		
		return null;
	}

}
