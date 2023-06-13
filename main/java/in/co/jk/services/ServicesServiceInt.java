package in.co.jk.services;

import java.util.List;

import in.co.jk.dto.ServicesDTO;
import in.co.jk.exception.DuplicateRecordException;

public interface ServicesServiceInt {

	public long add(ServicesDTO dto) throws DuplicateRecordException;

	public void delete(ServicesDTO dto);	

	public ServicesDTO findBypk(long pk);

	

	public void update(ServicesDTO dto) throws DuplicateRecordException;

	public List<ServicesDTO> list();

	public List<ServicesDTO> list(int pageNo, int pageSize);

	public List<ServicesDTO> search(ServicesDTO dto);

	public List<ServicesDTO> search(ServicesDTO dto, int pageNo, int pageSize);

	public ServicesDTO findByServiceName(String service, String city);

}
