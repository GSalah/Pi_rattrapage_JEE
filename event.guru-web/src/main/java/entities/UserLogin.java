package entities;

import java.io.Serializable;

public class UserLogin implements Serializable{

	private String username;
	private String password;
	private final String grant_type = "password";
	private static final long serialVersionUID = 1L;
	
	
	
	

	public UserLogin(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGrant_type() {
		return grant_type;
	}

	@Override
	public String toString() {
		return "UserLogin [username=" + username + ", password=" + password + ", grant_type=" + grant_type + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grant_type == null) ? 0 : grant_type.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserLogin other = (UserLogin) obj;
		if (grant_type == null) {
			if (other.grant_type != null)
				return false;
		} else if (!grant_type.equals(other.grant_type))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	
	
	
	
	
	

}
