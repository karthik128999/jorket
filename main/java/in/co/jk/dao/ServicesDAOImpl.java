package in.co.jk.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.jk.dto.ServicesDTO;
import in.co.jk.dto.UserDTO;

@Repository
public class ServicesDAOImpl implements ServicesDAOInt {

	private static Logger log = Logger.getLogger(ServicesDAOImpl.class.getName());
	@Autowired
	private EntityManager entityManager;
	@Override
	public long add(ServicesDTO dto) {
		log.info("ServicesDAOImpl start method started");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long)session.save(dto);
		log.info("ServicesDAOImpl Add method ends");
		return pk;
	}

	@Override
	public void delete(ServicesDTO dto) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Query<ServicesDTO> query = session.createSQLQuery("delete from j_service where id = "+dto.getId()+"");
		query.executeUpdate();
	//	entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
	}

	@Override
	public ServicesDTO findBypk(long pk) {
		log.info("ServicesDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		ServicesDTO dto = (ServicesDTO) session.get(ServicesDTO.class, pk);
		log.info("ServicesDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public ServicesDTO findByServiceName(String className, String city) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ServicesDTO.class);
		Criterion c1 = Restrictions.eq("serviceName", className);
	    Criterion c2 = Restrictions.eq("city", city);
	    Criterion c3 = Restrictions.and(c1, c2);
	    criteria.add(c3);
		ServicesDTO dto = (ServicesDTO) criteria.uniqueResult();
		return dto;
	}

	@Override
	public void update(ServicesDTO dto) {
		// TODO Auto-generated method stub
		log.info("ServicesDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("ServicesDAOImpl update method End");
	}

	@Override
	public List<ServicesDTO> list() {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	@Override
	public List<ServicesDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("ServicesDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<ServicesDTO> query = session.createQuery("from ServicesDTO", ServicesDTO.class);
		List<ServicesDTO> list = query.getResultList();
		log.info("ServicesDAOImpl List method End");
		return list;
	}

	@Override
	public List<ServicesDTO> search(ServicesDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List<ServicesDTO> search(ServicesDTO dto, int pageNo, int pageSize) {
		
		log.info("ServicesDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(ServicesDTO.class);
		
		if(dto.getCity()!=null && dto!=null) {
			criteria.add(Restrictions.eq("city", dto.getCity()));
		}
		
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getCity() != null && dto.getCity().length() > 0) {
				criteria.add(Restrictions.like("city", dto.getCity() + "%"));
			}
			
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult((int) pageNo);
				criteria.setMaxResults(pageSize);
			}
		}
		log.info("ServicesDAOImpl Search method End");
		return criteria.list();
	}

}
