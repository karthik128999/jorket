package in.co.jk.services;

import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import in.co.jk.dao.UserDAOInt;
import in.co.jk.dto.UserDTO;
import in.co.jk.exception.DuplicateRecordException;

@Service
public class UserServiceImpl implements UserService {

	private static Logger log = Logger.getLogger(UserServiceImpl.class.getName());
	@Autowired
	private UserDAOInt dao;
	@Autowired
	JavaMailSender javaMailSender;

	@Override
	@Transactional
	public long add(UserDTO dto) throws DuplicateRecordException {
		log.info("UserServiceImpl Add method start");
		UserDTO existdto = dao.findByLogin(dto.getLogin());
		if (existdto != null)
			throw new DuplicateRecordException("Login id Already Exist");
		long pk = dao.add(dto);
		log.info("UserServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(UserDTO dto) {
		// TODO Auto-generated method stub
		dao.delete(dto);
	}

	@Override
	public UserDTO findBypk(long pk) {
		// TODO Auto-generated method stub
		log.info("UserServiceImpl findBypk method start");
		UserDTO dto = dao.findBypk(pk);
		log.info("UserServiceImpl findBypk method end");
		return dto;
	}

	@Override
	public UserDTO findByLogin(String login) {
		// TODO Auto-generated method stub
		UserDTO dto=dao.findByLogin(login);
		return dto;
	}

	@Override
	@Transactional
	public void update(UserDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
			dao.update(dto);
	}

	@Override
	public List<UserDTO> list() {
		// TODO Auto-generated method stub
		log.info("UserServiceImpl list method start");
		List<UserDTO> list = dao.list();
		log.info("UserServiceImpl list method end");
		return list;
	}

	@Override
	public List<UserDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("UserServiceImpl list method start");
		List<UserDTO> list = dao.list(pageNo, pageSize);
		log.info("UserServiceImpl list method end");
		return list;
	}

	@Override
	public List<UserDTO> search(UserDTO dto) {
		// TODO Auto-generated method stub
		log.info("UserServiceImpl search method start");
		List<UserDTO> list = dao.search(dto);
		log.info("UserServiceImpl search method end");
		return list;
	}

	@Override
	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("UserServiceImpl search method start");
		List<UserDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("UserServiceImpl search method end");
		return list;
	}

	@Override
	public UserDTO authentication(UserDTO dto) {
		// TODO Auto-generated method stub
		log.info("UserServiceImpl authentication method start");
		dto = dao.authentication(dto);
		log.info("UserServiceImpl authentication method end");
		return dto;
	}

	@Override
	@Transactional
	public boolean changePassword(Long id, String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		log.info("UserServiceImpl  changePassword method start"); 
		UserDTO dtoExist =  findBypk(id); 
		if (dtoExist != null && dtoExist.getPassword().equals(oldPassword)) {
				  dtoExist.setPassword(newPassword); 
				  dao.update(dtoExist);
				  log.info("UserServiceImpl  changePassword method end"); 
				  return true;
				  }else
				  {
				
		return false;}
	}

	@Override
	public boolean forgetPassword(String login) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<UserDTO> getAllEmployees(UserDTO dto) {
		// TODO Auto-generated method stub
		List<UserDTO> list = dao.getAllEmployees(dto);
		return list;
	}

	@Override
	public void sendMail(UserDTO dto) {
		UserDTO userDTO = findByLogin(dto.getLogin());
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setFrom("oldisgold10dob@gmail.com");
		sm.setTo(dto.getLogin());
		sm.setSubject("Forgot Password Mail");
		sm.setText("Your Password for the Login is: "+userDTO.getPassword());
		javaMailSender.send(sm);
	}

	@Override
	
	public List<String> skillList(Long userId) {
		List<String> list = dao.skillList(userId);
		return list;
	}
	
	
	@Override
	public List<UserDTO> listCustomers() {
		// TODO Auto-generated method stub
		log.info("UserServiceImpl listCustomers method start");
		List<UserDTO> list = dao.listCustomers();
		log.info("UserServiceImpl listCustomers method end");
		return list;
	}

	@Override
	public List<UserDTO> listCustomers(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("UserServiceImpl listCustomers method start");
		List<UserDTO> list = dao.listCustomers(pageNo, pageSize);
		log.info("UserServiceImpl listCustomers method end");
		return list;
	}

	@Override
	public List<UserDTO> searchCustomers(UserDTO dto) {
		// TODO Auto-generated method stub
		log.info("UserServiceImpl searchCustomers method start");
		List<UserDTO> list = dao.searchCustomers(dto);
		log.info("UserServiceImpl searchCustomers method end");
		return list;
	}

	@Override
	public List<UserDTO> searchCustomers(UserDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("UserServiceImpl searchCustomers method start");
		List<UserDTO> list = dao.searchCustomers(dto, pageNo, pageSize);
		log.info("UserServiceImpl searchCustomers method end");
		return list;
	}

	
}
