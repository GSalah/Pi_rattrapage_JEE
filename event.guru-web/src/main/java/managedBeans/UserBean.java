package managedBeans;
import java.io.Serializable;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import entities.User;
import services.UserService;

@ManagedBean
@SessionScoped
public class UserBean implements Serializable {
	
	private String UserId;
	private String Email;
	private String Password;
	private String TenantId;
	private String TenantName;
	private String UserName;
	private String Status;
	private String Role;
	private String token;
	private Boolean IsAuthenticated;
	private String ConfirmPassword;
	private ArrayList<UserBean> PendingTenants;
	private UserService service = new UserService();
	
	/**@PostConstruct
	public void init() {
	  Role = "President";
	  Email = "tenant@tenant.com";
	  UserName = "tenant@tenant.com";
	  IsAuthenticated = true;
	  PendingTenants = new ArrayList<UserBean>();
		
	}*/
	
	public String TenantLogin() {
		if (service.Login(this.getEmail(),this.getPassword()) != null){
			User u = service.Login(Email,Password);
			this.token = u.getToken();
			this.UserId = u.getUserId();
			this.Email = u.getEmail(); 	
			this.UserName = u.getUserName();
			if (u.getTenantName().equals(this.TenantName)) {
				this.TenantId = u.getTenantId();
				this.Status = u.getStatus();
				if(u.getStatus().equals("pending")) {
					FacesMessage msg = new FacesMessage("Error","Your account is pending validation.");
					FacesContext.getCurrentInstance().addMessage("form:error", msg);
					return null;
				}					
			}
			else {
				FacesMessage msg = new FacesMessage("Error","Invalid Tenant Name.");
				FacesContext.getCurrentInstance().addMessage("form:error", msg);
				return null;
			}
			this.Role = u.getRole();
			this.IsAuthenticated = true;
			return "/front-pages/index?faces-redirect=true";
		}
		FacesMessage msg = new FacesMessage("Error","Invalid login attempt.");
		FacesContext.getCurrentInstance().addMessage("form:error", msg);
		return null;
	}
	
	public String TenantRegister() {
		if (!this.Password.equals(this.ConfirmPassword)) {
			FacesMessage msg = new FacesMessage("Error","Confirm password is not correct.");
			FacesContext.getCurrentInstance().addMessage("form:error", msg);
			return null;
		}
		if (service.TenantRegister(this.Email, this.Password, this.Password, this.TenantName).equals("ok")) {
			return "/back-pages/registered?faces-redirect=true";
		}
		else {
			FacesMessage msg = new FacesMessage("Error","This Tenant is already existing.");
			FacesContext.getCurrentInstance().addMessage("form:error", msg);
			return null;
		}

	}
	
	public String Login() {
		if (service.Login(this.getEmail(),this.getPassword()) != null){
			User u = service.Login(Email,Password);
			this.token = u.getToken();
			this.UserId = u.getUserId();
			this.Email = u.getEmail(); 	
			this.UserName = u.getUserName();
			if (u.getTenantId() != null) {
				FacesMessage msg = new FacesMessage("Error","Invalid user credentials.");
				FacesContext.getCurrentInstance().addMessage("form:error", msg);
				return null;				
			}
			this.Role = u.getRole();
			this.IsAuthenticated = true;
			return "/front-pages/index?faces-redirect=true";
		}
		FacesMessage msg = new FacesMessage("Error","Invalid login attempt.");
		FacesContext.getCurrentInstance().addMessage("form:error", msg);
		return null;
	}
	
	public String Register() {
		if (!this.Password.equals(this.ConfirmPassword)) {
			FacesMessage msg = new FacesMessage("Error","Confirm password is not correct.");
			FacesContext.getCurrentInstance().addMessage("form:error", msg);
			return null;
		}
		if (service.TenantRegister(this.Email, this.Password, this.Password, this.TenantName).equals("ok")) {
			return "/front-pages/login?faces-redirect=true";
		}
		else {
			FacesMessage msg = new FacesMessage("Error","This mail address is already existing.");
			FacesContext.getCurrentInstance().addMessage("form:error", msg);
			return null;
			
		}

	}
	
	public String Logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return"/front-pages/login?faces-redirect=true";
	}

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

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}		

	public String getConfirmPassword() {
		return ConfirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		ConfirmPassword = confirmPassword;
	}

	public String getTenantId() {
		return TenantId;
	}

	public void setTenantId(String tenantId) {
		TenantId = tenantId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	public Boolean getIsAuthenticated() {
		return IsAuthenticated;
	}

	public void setIsAuthenticated(Boolean isAuthenticated) {
		IsAuthenticated = isAuthenticated;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public ArrayList<UserBean> getPendingTenants() {
		return PendingTenants;
	}

	public void setPendingTenants(ArrayList<UserBean> pendingTenants) {
		PendingTenants = pendingTenants;
	}

	public String getTenantName() {
		return TenantName;
	}

	public void setTenantName(String tenantName) {
		TenantName = tenantName;
	}

	public UserService getService() {
		return service;
	}

	public void setService(UserService service) {
		this.service = service;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	
	
	
	
	
	
	
	
	

}
