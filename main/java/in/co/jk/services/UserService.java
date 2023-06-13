package in.co.jk.services;

import java.util.List;
import in.co.jk.dto.UserDTO;
import in.co.jk.exception.DuplicateRecordException;

public interface UserService {
	public long add(UserDTO dto) throws DuplicateRecordException;

	public void delete(UserDTO dto);

	public List<String> skillList(Long userId);

	public UserDTO findBypk(long pk);

	public UserDTO findByLogin(String login);

	public void update(UserDTO dto) throws DuplicateRecordException;

	public List<UserDTO> list();

	public List<UserDTO> list(int pageNo, int pageSize);

	public List<UserDTO> search(UserDTO dto);

	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize);

	public UserDTO authentication(UserDTO dto);

	public boolean changePassword(Long id, String oldPassword, String newPassword);

	public boolean forgetPassword(String login);

	public List<UserDTO> getAllEmployees(UserDTO dto);

	public void sendMail(UserDTO dto);
	
	public List<UserDTO> listCustomers();

	public List<UserDTO> listCustomers(int pageNo, int pageSize);

	public List<UserDTO> searchCustomers(UserDTO dto);

	public List<UserDTO> searchCustomers(UserDTO dto, int pageNo, int pageSize);

}
