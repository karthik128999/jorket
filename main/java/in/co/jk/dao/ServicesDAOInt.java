package in.co.jk.dao;

import java.util.List;

import in.co.jk.dto.ServicesDTO;


public interface ServicesDAOInt {
	public long add(ServicesDTO dto);

	public void delete(ServicesDTO dto);

	public ServicesDTO findBypk(long pk);

	public ServicesDTO findByServiceName(String login, String city);

	public void update(ServicesDTO dto);

	public List<ServicesDTO> list();

	public List<ServicesDTO> list(int pageNo, int pageSize);

	public List<ServicesDTO> search(ServicesDTO dto);

	public List<ServicesDTO> search(ServicesDTO dto, int pageNo, int pageSize);

	

}
