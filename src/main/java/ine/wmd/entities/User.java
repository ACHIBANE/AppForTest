package ine.wmd.entities;

import java.io.Serializable;
import java.security.Principal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="USER")
public class User implements Principal, Serializable {
	
private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Long IdUser;
	
	@NotNull
	private String login;
	@NotNull
	private String pswd;
	
	public User(String login, String pswd) {
		this.login = login;
		this.pswd = pswd;
	}

	public User() {
	
	}
	public Long getIdUser() {
		return IdUser;
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