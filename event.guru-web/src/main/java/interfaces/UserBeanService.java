package interfaces;

import java.util.List;

import entities.User;

public interface UserBeanService {

	public List<User> GetAll();
	public void Delete(String Id);
	public void Create(User user);
	public User Login(String username,String passowrd);
	public User findById(String Id);
	public String TenantRegister(String email, String password, String confirm_password, String tenant_name);
	public String Register(String email, String password, String confirm_password);
}
