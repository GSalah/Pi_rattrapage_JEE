package entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User implements Serializable {
	
	@JsonProperty("Id")
	private String UserId;
	
	@JsonProperty("Email")
	private String Email;
	
	@JsonProperty("UserName")
	private String UserName;
	
	@JsonProperty("TenantId")
	private String TenantId;
	
	@JsonProperty("TenantName")
	private String TenantName;

	@JsonProperty("Status")
	private String Status;
	
	@JsonProperty("Role")
	private String Role;
	
	private String Token;
	
	private static final long serialVersionUID = 1L;

	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getTenantId() {
		return TenantId;
	}
	public void setTenantId(String tenantId) {
		TenantId = tenantId;
	}
	public String getTenantName() {
		return TenantName;
	}
	public void setTenantName(String tenantName) {
		TenantName = tenantName;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String userName) {
		UserName = userName;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	
	
	
	public String getToken() {
		return Token;
	}
	public void setToken(String token) {
		Token = token;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Email == null) ? 0 : Email.hashCode());
		result = prime * result + ((Role == null) ? 0 : Role.hashCode());
		result = prime * result + ((Status == null) ? 0 : Status.hashCode());
		result = prime * result + ((TenantId == null) ? 0 : TenantId.hashCode());
		result = prime * result + ((TenantName == null) ? 0 : TenantName.hashCode());
		result = prime * result + ((UserId == null) ? 0 : UserId.hashCode());
		result = prime * result + ((UserName == null) ? 0 : UserName.hashCode());
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
		User other = (User) obj;
		if (Email == null) {
			if (other.Email != null)
				return false;
		} else if (!Email.equals(other.Email))
			return false;
		if (Role == null) {
			if (other.Role != null)
				return false;
		} else if (!Role.equals(other.Role))
			return false;
		if (Status == null) {
			if (other.Status != null)
				return false;
		} else if (!Status.equals(other.Status))
			return false;
		if (TenantId == null) {
			if (other.TenantId != null)
				return false;
		} else if (!TenantId.equals(other.TenantId))
			return false;
		if (TenantName == null) {
			if (other.TenantName != null)
				return false;
		} else if (!TenantName.equals(other.TenantName))
			return false;
		if (UserId == null) {
			if (other.UserId != null)
				return false;
		} else if (!UserId.equals(other.UserId))
			return false;
		if (UserName == null) {
			if (other.UserName != null)
				return false;
		} else if (!UserName.equals(other.UserName))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [UserId=" + UserId + ", Email=" + Email + ", UserName=" + UserName + ", TenantId=" + TenantId
				+ ", TenantName=" + TenantName + ", Status=" + Status + ", Role=" + Role + "Token" + Token+"]";
	}
	
	
	
	

}
