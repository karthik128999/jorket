package in.co.jk.services;

import java.util.List;
import java.util.logging.Logger;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.co.jk.dao.AddToCartDAOInt;
import in.co.jk.dao.ServicesDAOInt;
import in.co.jk.dto.AddToCartDTO;
import in.co.jk.dto.UserDTO;
import in.co.jk.exception.DuplicateRecordException;


@Service
public class AddToCartServiceImpl implements AddToCartServiceInt {

	private static Logger log = Logger.getLogger(AddToCartServiceImpl.class.getName());
	@Autowired
	private AddToCartDAOInt dao;

	@Override
	@Transactional
	public long add(AddToCartDTO dto) throws DuplicateRecordException {
		log.info("AddToCartServiceImpl Add method start");
//		AddToCartDTO existdto = dao.findByServiceName(dto.getServiceName());
//		if (existdto != null)
//			throw new DuplicateRecordException("Service name Already Exist");
		long pk = dao.add(dto);
		log.info("AddToCartServiceImpl Add method end");
		return pk;
	}

	@Override
	@Transactional
	public void delete(AddToCartDTO dto) {
		// TODO Auto-generated method stub
		dao.delete(dto);
	}

	@Override
	public AddToCartDTO findBypk(long pk) {
		// TODO Auto-generated method stub
		log.info("AddToCartServiceImpl findBypk method start");
		AddToCartDTO dto = dao.findBypk(pk);
		log.info("AddToCartServiceImpl findBypk method end");
		return dto;
	}

	@Override
	public AddToCartDTO findByServiceName(String service) {
		// TODO Auto-generated method stub
		AddToCartDTO dto=dao.findByServiceName(service);
		return dto;
	}

	@Override
	@Transactional
	public void accept(AddToCartDTO dto) throws DuplicateRecordException {
		// TODO Auto-generated method stub
			dao.accept(dto);
	}

	@Override
	@Transactional
	public void update(AddToCartDTO dto) {
		// TODO Auto-generated method stub
			dao.update(dto);
	}
	@Override
	public List<AddToCartDTO> list() {
		// TODO Auto-generated method stub
		log.info("AddToCartServiceImpl list method start");
		List<AddToCartDTO> list = dao.list();
		log.info("AddToCartServiceImpl list method end");
		return list;
	}

	@Override
	public List<AddToCartDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("AddToCartServiceImpl list method start");
		List<AddToCartDTO> list = dao.list(pageNo, pageSize);
		log.info("AddToCartServiceImpl list method end");
		return list;
	}

	@Override
	public List<AddToCartDTO> search(AddToCartDTO dto) {
		// TODO Auto-generated method stub
		log.info("AddToCartServiceImpl search method start");
		List<AddToCartDTO> list = dao.search(dto);
		log.info("AddToCartServiceImpl search method end");
		return list;
	}

	@Override
	public List<AddToCartDTO> search(AddToCartDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("AddToCartServiceImpl search method start");
		List<AddToCartDTO> list = dao.search(dto, pageNo, pageSize);
		log.info("AddToCartServiceImpl search method end");
		return list;
	}

	@Override
	public AddToCartDTO findByClassName(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AddToCartDTO> searchBooking(AddToCartDTO dto) {
		// TODO Auto-generated method stub
		log.info("AddToCartServiceImpl search method start");
		List<AddToCartDTO> list = dao.searchBooking(dto);
		log.info("AddToCartServiceImpl search method end");
		return list;
	}

	@Override
	public List<AddToCartDTO> searchBooking(AddToCartDTO dto, int pageNo, int pageSize) {
		log.info("AddToCartServiceImpl search method start");
		List<AddToCartDTO> list = dao.searchBooking(dto, pageNo, pageSize);
		log.info("AddToCartServiceImpl search method end");
		return list;
	}
	
	@Override
	public List<AddToCartDTO> showBookingsToCustomer(AddToCartDTO dto) {
		// TODO Auto-generated method stub
		log.info("AddToCartServiceImpl search method start");
		List<AddToCartDTO> list = dao.showBookingsToCustomer(dto);
		log.info("AddToCartServiceImpl search method end");
		return list;
	}

	@Override
	public List<AddToCartDTO> showBookingsToCutomer(AddToCartDTO dto, int pageNo, int pageSize) {
		log.info("AddToCartServiceImpl search method start");
		List<AddToCartDTO> list = dao.showBookingsToCustomer(dto, pageNo, pageSize);
		log.info("AddToCartServiceImpl search method end");
		return list;
	}
	
	@Override
	public List<AddToCartDTO> searchUpcomingBooking(AddToCartDTO dto) {
		// TODO Auto-generated method stub
		log.info("AddToCartServiceImpl search method start");
		List<AddToCartDTO> list = dao.searchUpcomingBooking(dto);
		log.info("AddToCartServiceImpl search method end");
		return list;
	}

	@Override
	public List<AddToCartDTO> searchUpcomingBooking(AddToCartDTO dto, int pageNo, int pageSize) {
		log.info("AddToCartServiceImpl search method start");
		List<AddToCartDTO> list = dao.searchUpcomingBooking(dto, pageNo, pageSize);
		log.info("AddToCartServiceImpl search method end");
		return list;
	}
	
	@Override
	public List<AddToCartDTO> searchTodayBooking(AddToCartDTO dto) {
		// TODO Auto-generated method stub
		log.info("AddToCartServiceImpl search method start");
		List<AddToCartDTO> list = dao.searchTodayBooking(dto);
		log.info("AddToCartServiceImpl search method end");
		return list;
	}

	@Override
	public List<AddToCartDTO> searchTodayBooking(AddToCartDTO dto, int pageNo, int pageSize) {
		log.info("AddToCartServiceImpl search method start");
		List<AddToCartDTO> list = dao.searchTodayBooking(dto, pageNo, pageSize);
		log.info("AddToCartServiceImpl search method end");
		return list;
	}

	
}
