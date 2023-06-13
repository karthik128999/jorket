package in.co.jk.services;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.jk.dao.ServicesDAOInt;
import in.co.jk.dto.ServicesDTO;
import in.co.jk.exception.DuplicateRecordException;


@Service
public class ServicesServiceImpl implements ServicesServiceInt {

	private static Logger log = Logger.getLogger(ServicesServiceImpl.class.getName());
	@Autowired
	private ServicesDAOInt dao;

	@Override
	@Transactional
	public long add(ServicesDTO dto) throws DuplicateRecordException {
		log.info("ServcieServiceImpl Add method start");
		ServicesDTO existdto = dao.findByServiceName(dto.getServiceName(),dto.getCity());
		if (existdto != null)
			throw new DuplicateRecordException("Service name Already Exist for this city");
		long pk = dao.add(dto);
		log.info("ServcieServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(ServicesDTO dto) {
		// TODO Auto-generated method stub
		dao.delete(dto);
	}

	@Override
	public ServicesDTO findBypk(long pk) {
		// TODO Auto-generated method stub
		log.info("ServcieServiceImpl findBypk method start");
		ServicesDTO dto = dao.findBypk(pk);
		log.info("ServcieServiceImpl findBypk method end");
		return dto;
	}

	@Override
	public ServicesDTO findByServiceName(String service, String city) {
		// TODO Auto-generated method stub
		ServicesDTO dto=dao.findByServiceName(service,city);
		return dto;
	}

	@Override
	@Transactional
	public void update(ServicesDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
			dao.update(dto);
	}

	@Override
	public List<ServicesDTO> list() {
		// TODO Auto-generated method stub
		log.info("ServcieServiceImpl list method start");
		List<ServicesDTO> list = dao.list();
		log.info("ServcieServiceImpl list method end");
		return list;
	}

	@Override
	public List<ServicesDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("ServcieServiceImpl list method start");
		List<ServicesDTO> list = dao.list(pageNo, pageSize);
		log.info("ServcieServiceImpl list method end");
		return list;
	}

	@Override
	public List<ServicesDTO> search(ServicesDTO dto) {
		// TODO Auto-generated method stub
		log.info("ServcieServiceImpl search method start");
		List<ServicesDTO> list = dao.search(dto);
		log.info("ServcieServiceImpl search method end");
		return list;
	}

	@Override
	public List<ServicesDTO> search(ServicesDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("ServcieServiceImpl search method start");
		List<ServicesDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("ServcieServiceImpl search method end");
		return list;
	}

	

	
}
