package in.co.jk.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.jk.dto.AddToCartDTO;


@Repository
public class AddToCartDAOImpl implements AddToCartDAOInt {

	private static Logger log = Logger.getLogger(AddToCartDAOImpl.class.getName());
	@Autowired
	private EntityManager entityManager;
	@Override
	public long add(AddToCartDTO dto) {
		log.info("AddToCartDAOImpl start method started");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long)session.save(dto);
		log.info("AddToCartDAOImpl Add method ends");
		return pk;
	}

	@Override
	public void delete(AddToCartDTO dto) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Query<AddToCartDTO> query = session.createSQLQuery("delete from j_addtocart where id = "+dto.getId()+"");
		query.executeUpdate();
	//	entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
	}

	@Override
	public AddToCartDTO findBypk(long pk) {
		log.info("AddToCartDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		AddToCartDTO dto = (AddToCartDTO) session.get(AddToCartDTO.class, pk);
		log.info("AddToCartDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public AddToCartDTO findByServiceName(String className) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AddToCartDTO.class);
		criteria.add(Restrictions.eq("serviceName", className));
		AddToCartDTO dto = (AddToCartDTO) criteria.uniqueResult();
		return dto;
	}

	@Override
	public void update(AddToCartDTO dto) {
		// TODO Auto-generated method stub
		log.info("AddToCartDTOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("AddToCartDTOImpl update method End");
	}

	@Override
	public void accept(AddToCartDTO dto) {
		// TODO Auto-generated method stub
		log.info("AddToCartDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<AddToCartDTO> query = session.createQuery("UPDATE AddToCartDTO u SET u.status =:status where u.id =:bookingid ");
		query.setParameter("status", dto.getStatus());
		query.setParameter("bookingid", dto.getId());
		int i = query.executeUpdate();
		log.info("AddToCartDAOImpl update method End");
	}

	@Override
	public List<AddToCartDTO> list() {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	@Override
	public List<AddToCartDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("AddToCartDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<AddToCartDTO> query = session.createQuery("from AddToCartDTO", AddToCartDTO.class);
		List<AddToCartDTO> list = query.getResultList();
		log.info("AddToCartDAOImpl List method End");
		return list;
	}

	@Override
	public List<AddToCartDTO> search(AddToCartDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List<AddToCartDTO> search(AddToCartDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("AddToCartDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AddToCartDTO.class);
		criteria.addOrder(Order.desc("dateOfBooking"));
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
//			System.out.println("serv : "+dto.getServiceId());
			if (dto.getServiceName() != null && dto.getServiceName().length() > 0) {
				criteria.add(Restrictions.like("serviceName", dto.getServiceName()));
			}
			if (dto.getDateOfBooking() != null && dto.getDateOfBooking().length() > 0) {
				criteria.add(Restrictions.like("dateOfBooking", dto.getDateOfBooking() + "%"));
			}
			if (dto.getStatus() != null && dto.getStatus().length() > 0) {
				criteria.add(Restrictions.like("status", dto.getStatus() + "%"));
			}
			
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult((int) pageNo);
				criteria.setMaxResults(pageSize);
			}
		}
		log.info("AddToCartDAOImpl Search method End");
		return criteria.list();
	}
	
	@Override
	public List<AddToCartDTO> showBookingsToCustomer(AddToCartDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List<AddToCartDTO> showBookingsToCustomer(AddToCartDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("AddToCartDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		System.out.println("dto::"+dto.getCreatedBy());
		Criteria criteria = session.createCriteria(AddToCartDTO.class);
		criteria.add(Restrictions.like("createdBy", dto.getCreatedBy()));
		System.out.println("criteria:->"+criteria.list().size());
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			/*
			 * System.out.println("serv : "+dto.getServiceId()); if (dto.getServiceId() !=
			 * null ) { criteria.add(Restrictions.like("serviceId", dto.getServiceId())); }
			 */
			System.out.println("dob: "+dto.getDateOfBooking());
			if (dto.getDateOfBooking() != null && dto.getDateOfBooking().length() > 0) {
				criteria.add(Restrictions.like("dateOfBooking", dto.getDateOfBooking() + "%"));
			}
			if (dto.getStatus() != null && dto.getStatus().length() > 0) {
				criteria.add(Restrictions.like("status", dto.getStatus() + "%"));
			}
			
			
			
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult((int) pageNo);
				criteria.setMaxResults(pageSize);
			}
		}
		log.info("AddToCartDAOImpl Search method End");
		System.out.println("criteria2:->"+criteria.list().size());
		return criteria.list();
	}
	
	@Override
	public List<AddToCartDTO> searchBooking(AddToCartDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List<AddToCartDTO> searchBooking(AddToCartDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("AddToCartDAOImpl Search method Start");
		System.out.println("dob: "+dto.getDateOfBooking());
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AddToCartDTO.class);
		//convert dob to date
		//NativeQuery q = session
				//.createSQLQuery("select * from j_addtocart where str_to_date(date_of_booking,'%d/%m/%Y') < curdate()");
	//	List<AddToCartDTO> resultList = q.getResultList();
		String sqlWhere = "str_to_date(date_of_booking,'%d/%m/%Y') < curdate()";
		//criteria.add(Restrictions.lt("dateOfBooking", dto.getDateOfBooking() ));
		criteria.add(Restrictions.sqlRestriction(sqlWhere));
		System.out.println("c1: "+criteria.list().size());
		
			if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getUserId() != null && dto.getUserId() > 0) {
				criteria.add(Restrictions.eq("userId", dto.getUserId()));
			}
			if (dto.getProfessionalName() != null && dto.getProfessionalName().length() > 0) {
				criteria.add(Restrictions.like("professionalName", dto.getProfessionalName() + "%"));
			}
			
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult((int) pageNo);
				criteria.setMaxResults(pageSize);
			}
		}
		log.info("AddToCartDAOImpl Search method End");
		System.out.println("c2: "+criteria.list().size());
		return criteria.list();
	}
	
	@Override
	public List<AddToCartDTO> searchUpcomingBooking(AddToCartDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List<AddToCartDTO> searchUpcomingBooking(AddToCartDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("AddToCartDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AddToCartDTO.class);
		//criteria.add(Restrictions.gt("dateOfBooking",dto.getDateOfBooking()));
		String sqlWhere = "str_to_date(date_of_booking,'%d/%m/%Y') > curdate()";
		criteria.add(Restrictions.sqlRestriction(sqlWhere));
		System.out.println("DTO---- "+dto.getProfessionalName());
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getProfessionalName() != null && dto.getProfessionalName().length() > 0) {
				criteria.add(Restrictions.like("professionalName", dto.getProfessionalName() + "%"));
			}
			System.out.println(criteria);
			
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult((int) pageNo);
				criteria.setMaxResults(pageSize);
			}
		}
		log.info("AddToCartDAOImpl Search method End");
		return criteria.list();
	}
	
	@Override
	public List<AddToCartDTO> searchTodayBooking(AddToCartDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List<AddToCartDTO> searchTodayBooking(AddToCartDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("AddToCartDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(AddToCartDTO.class);
		//criteria.add(Restrictions.eq("dateOfBooking",dto.getDateOfBooking()));
		String sqlWhere = "str_to_date(date_of_booking,'%d/%m/%Y') = curdate()";
		criteria.add(Restrictions.sqlRestriction(sqlWhere));
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getProfessionalName() != null && dto.getProfessionalName().length() > 0) {
				criteria.add(Restrictions.like("professionalName", dto.getProfessionalName() + "%"));
			}
			
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult((int) pageNo);
				criteria.setMaxResults(pageSize);
			}
		}
		log.info("AddToCartDAOImpl Search method End");
		return criteria.list();
	}

	
}
