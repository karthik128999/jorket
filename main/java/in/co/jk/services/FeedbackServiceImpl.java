package in.co.jk.services;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.jk.dao.FeedbackDAOInt;
import in.co.jk.dao.ServicesDAOInt;
import in.co.jk.dto.FeedbackDTO;
import in.co.jk.exception.DuplicateRecordException;


@Service
public class FeedbackServiceImpl implements FeedbackServiceInt {

	private static Logger log = Logger.getLogger(FeedbackServiceImpl.class.getName());
	@Autowired
	private FeedbackDAOInt dao;

	@Override
	@Transactional
	public long add(FeedbackDTO dto) throws DuplicateRecordException {
		log.info("FeedbackServiceImpl Add method start");
		
		long pk = dao.add(dto);
		log.info("FeedbackServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(FeedbackDTO dto) {
		// TODO Auto-generated method stub
		dao.delete(dto);
	}

	@Override
	public FeedbackDTO findBypk(long pk) {
		// TODO Auto-generated method stub
		log.info("FeedbackServiceImpl findBypk method start");
		FeedbackDTO dto = dao.findBypk(pk);
		log.info("FeedbackServiceImpl findBypk method end");
		return dto;
	}

	@Override
	public FeedbackDTO findByServiceName(String service) {
		// TODO Auto-generated method stub
		FeedbackDTO dto=dao.findByServiceName(service);
		return dto;
	}

	@Override
	@Transactional
	public void update(FeedbackDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
			dao.update(dto);
	}

	@Override
	public List<FeedbackDTO> list() {
		// TODO Auto-generated method stub
		log.info("FeedbackServiceImpl list method start");
		List<FeedbackDTO> list = dao.list();
		log.info("FeedbackServiceImpl list method end");
		return list;
	}

	@Override
	public List<FeedbackDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("FeedbackServiceImpl list method start");
		List<FeedbackDTO> list = dao.list(pageNo, pageSize);
		log.info("FeedbackServiceImpl list method end");
		return list;
	}

	@Override
	public List<FeedbackDTO> search(FeedbackDTO dto) {
		// TODO Auto-generated method stub
		log.info("FeedbackServiceImpl search method start");
		List<FeedbackDTO> list = dao.search(dto);
		log.info("FeedbackServiceImpl search method end");
		return list;
	}

	@Override
	public List<FeedbackDTO> search(FeedbackDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("FeedbackServiceImpl search method start");
		List<FeedbackDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("FeedbackServiceImpl search method end");
		return list;
	}

	@Override
	public FeedbackDTO findByClassName(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
