package in.co.jk.services;

import java.util.List;

import in.co.jk.dto.RequestServiceDTO;
import in.co.jk.dto.RequestServiceDTO;
import in.co.jk.exception.DuplicateRecordException;

public interface RequestServiceServiceInt {

	public long add(RequestServiceDTO dto) throws DuplicateRecordException;

	public void delete(RequestServiceDTO dto);	

	public RequestServiceDTO findBypk(long pk);

	public RequestServiceDTO findByClassName(String login);

	public void update(RequestServiceDTO dto) throws DuplicateRecordException;

	public List<RequestServiceDTO> list();

	public List<RequestServiceDTO> list(int pageNo, int pageSize);

	public List<RequestServiceDTO> search(RequestServiceDTO dto);

	public List<RequestServiceDTO> search(RequestServiceDTO dto, int pageNo, int pageSize);

	public List<RequestServiceDTO> searchRequestServiceList(RequestServiceDTO dto);

	public List<RequestServiceDTO> searchRequestServiceList(RequestServiceDTO dto, int pageNo, int pageSize);
	
	public RequestServiceDTO findByServiceName(String service);
	
	public List<RequestServiceDTO> findAllRequestAssignedByServiceId(Long serviceid,Long userid);

	public RequestServiceDTO findByServiceId(Long id);
}
