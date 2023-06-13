package in.co.jk.services;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.jk.dao.RequestServiceDAOInt;
import in.co.jk.dao.ServicesDAOInt;
import in.co.jk.dto.RequestServiceDTO;
import in.co.jk.exception.DuplicateRecordException;


@Service
public class RequestServiceServiceImpl implements RequestServiceServiceInt {

	private static Logger log = Logger.getLogger(RequestServiceServiceImpl.class.getName());
	@Autowired
	private RequestServiceDAOInt dao;

	@Override
	@Transactional
	public long add(RequestServiceDTO dto) throws DuplicateRecordException {
		log.info("ServcieServiceImpl Add method start");
		RequestServiceDTO existdto = dao.findByServiceNameAndEmployeeName(dto.getServiceName(),dto.getUserId());
		if (existdto != null)
			throw new DuplicateRecordException("Service name Already Assigned");
		long pk = dao.add(dto);
		log.info("ServcieServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(RequestServiceDTO dto) {
		// TODO Auto-generated method stub
		dao.delete(dto);
	}

	@Override
	public RequestServiceDTO findBypk(long pk) {
		// TODO Auto-generated method stub
		log.info("ServcieServiceImpl findBypk method start");
		RequestServiceDTO dto = dao.findBypk(pk);
		log.info("ServcieServiceImpl findBypk method end");
		return dto;
	}

	@Override
	public RequestServiceDTO findByServiceName(String service) {
		// TODO Auto-generated method stub
		RequestServiceDTO dto=dao.findByServiceName(service);
		return dto;
	}
	
	@Override
	public RequestServiceDTO findByServiceId(Long id) {
		// TODO Auto-generated method stub
		RequestServiceDTO dto=dao.findByServiceId(id);
		return dto;
	}

	@Override
	@Transactional
	public void update(RequestServiceDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
			dao.update(dto);
	}

	@Override
	public List<RequestServiceDTO> list() {
		// TODO Auto-generated method stub
		log.info("ServcieServiceImpl list method start");
		List<RequestServiceDTO> list = dao.list();
		log.info("ServcieServiceImpl list method end");
		return list;
	}

	@Override
	public List<RequestServiceDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("ServcieServiceImpl list method start");
		List<RequestServiceDTO> list = dao.list(pageNo, pageSize);
		log.info("ServcieServiceImpl list method end");
		return list;
	}

	@Override
	public List<RequestServiceDTO> search(RequestServiceDTO dto) {
		// TODO Auto-generated method stub
		log.info("ServcieServiceImpl search method start");
		List<RequestServiceDTO> list = dao.search(dto);
		log.info("ServcieServiceImpl search method end");
		return list;
	}

	@Override
	public List<RequestServiceDTO> search(RequestServiceDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("ServcieServiceImpl search method start");
		List<RequestServiceDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("ServcieServiceImpl search method end");
		return list;
	}

	@Override
	public RequestServiceDTO findByClassName(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RequestServiceDTO> findAllRequestAssignedByServiceId(Long serviceid, Long userid) {
		// TODO Auto-generated method stub
		List<RequestServiceDTO> list = dao.findAllRequestAssignedByServiceId(serviceid, userid);
		return list;
	}

	@Override
	public List<RequestServiceDTO> searchRequestServiceList(RequestServiceDTO dto) {
		log.info("ServcieServiceImpl search method start");
		List<RequestServiceDTO> list = dao.searchRequestServiceList(dto);
		log.info("ServcieServiceImpl search method end");
		return list;
	}

	@Override
	public List<RequestServiceDTO> searchRequestServiceList(RequestServiceDTO dto, int pageNo, int pageSize) {
		log.info("ServcieServiceImpl search method start");
		List<RequestServiceDTO> list = dao.searchRequestServiceList(dto, pageNo, pageSize);
		log.info("ServcieServiceImpl search method end");
		return list;
	}

	
}
