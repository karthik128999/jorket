package in.co.jk.dao;

import java.util.List;


import in.co.jk.dto.RequestServiceDTO;


public interface RequestServiceDAOInt {
	public long add(RequestServiceDTO dto);

	public void delete(RequestServiceDTO dto);

	public RequestServiceDTO findBypk(long pk);

	public RequestServiceDTO findByServiceName(String login);
	
	public RequestServiceDTO findByServiceNameAndEmployeeName(Long service,Long login);

	public void update(RequestServiceDTO dto);

	public List<RequestServiceDTO> list();

	public List<RequestServiceDTO> list(int pageNo, int pageSize);

	public List<RequestServiceDTO> search(RequestServiceDTO dto);

	public List<RequestServiceDTO> search(RequestServiceDTO dto, int pageNo, int pageSize);
	
	public List<RequestServiceDTO> searchRequestServiceList(RequestServiceDTO dto);

	public List<RequestServiceDTO> searchRequestServiceList(RequestServiceDTO dto, int pageNo, int pageSize);

	List<RequestServiceDTO> findAllRequestAssignedByServiceId(Long serviceid,Long userid);

	RequestServiceDTO findByServiceId(Long id);

	RequestServiceDTO findByServiceNameAndEmployeeName(String service, Long login);

}
