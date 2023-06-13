package in.co.jk.dao;

import java.util.List;

import in.co.jk.dto.UserDTO;

public interface UserDAOInt {
	public long add(UserDTO dto);

	public void delete(UserDTO dto);

	public UserDTO findBypk(long pk);

	public UserDTO findByLogin(String login);

	public void update(UserDTO dto);

	public List<UserDTO> list();

	public List<UserDTO> list(int pageNo, int pageSize);

	public List<UserDTO> search(UserDTO dto);

	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize);

	public UserDTO authentication(UserDTO dto);
	
	public List<UserDTO> getAllEmployees(UserDTO dto);
	
	public List<String> skillList(Long userId);
	
	public List<UserDTO> listCustomers();

	public List<UserDTO> listCustomers(int pageNo, int pageSize);

	public List<UserDTO> searchCustomers(UserDTO dto);

	public List<UserDTO> searchCustomers(UserDTO dto, int pageNo, int pageSize);

}
