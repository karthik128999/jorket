package in.co.jk.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.jk.dto.RequestServiceDTO;


@Repository
public class RequestServiceDAOImpl implements RequestServiceDAOInt {

	private static Logger log = Logger.getLogger(RequestServiceDAOImpl.class.getName());
	@Autowired
	private EntityManager entityManager;
	@Override
	public long add(RequestServiceDTO dto) {
		log.info("RequestServiceDAOImpl start method started");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long)session.save(dto);
		log.info("RequestServiceDAOImpl Add method ends");
		return pk;
	}

	@Override
	public void delete(RequestServiceDTO dto) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Query<RequestServiceDTO> query = session.createSQLQuery("delete from j_request where id = "+dto.getId()+"");
		query.executeUpdate();
	//	entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
	}

	@Override
	public RequestServiceDTO findBypk(long pk) {
		log.info("RequestServiceDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		RequestServiceDTO dto = (RequestServiceDTO) session.get(RequestServiceDTO.class, pk);
		log.info("RequestServiceDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public RequestServiceDTO findByServiceName(String className) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(RequestServiceDTO.class);
		criteria.add(Restrictions.eq("serviceName", className));
		RequestServiceDTO dto = (RequestServiceDTO) criteria.uniqueResult();
		return dto;
	}
	
	@Override
	public RequestServiceDTO findByServiceId(Long id) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(RequestServiceDTO.class);
		criteria.add(Restrictions.eq("serviceId", id));
		RequestServiceDTO dto = (RequestServiceDTO) criteria.uniqueResult();
		return dto;
	}

	@Override
	public void update(RequestServiceDTO dto) {
		// TODO Auto-generated method stub
		log.info("RequestServiceDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("RequestServiceDAOImpl update method End");
	}

	@Override
	public List<RequestServiceDTO> list() {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	@Override
	public List<RequestServiceDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("RequestServiceDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<RequestServiceDTO> query = session.createQuery("from RequestServiceDTO", RequestServiceDTO.class);
		List<RequestServiceDTO> list = query.getResultList();
		log.info("RequestServiceDAOImpl List method End");
		return list;
	}

	@Override
	public List<RequestServiceDTO> search(RequestServiceDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List<RequestServiceDTO> search(RequestServiceDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("RequestServiceDAOImpl Search method Start");
		System.out.println("Useris: "+dto.getUserId());
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(RequestServiceDTO.class);
		System.out.println("Service: "+dto.getServiceName()+" USer: "+dto.getUserId() +" city: "+dto.getCity());
		//Restrictions.or(Restrictions.eq("userId", dto.getUserId()),(Restrictions.eq("serviceId", dto.getServiceId())));
		//Restrictions.and(Restrictions.like("serviceName", dto.getServiceName()),(Restrictions.like("city", dto.getCity())));
		Criterion c1 = Restrictions.eq("serviceName", dto.getServiceName());
	    Criterion c2 = Restrictions.eq("city", dto.getCity());
	    Criterion c3 = Restrictions.and(c1, c2);
	    criteria.add(c3);
	    System.out.println("criteria: "+criteria.list().size()+" ccc: "+criteria.list());
		
//	    if (dto != null) {
//			if (dto.getId() > 0) {
//				criteria.add(Restrictions.eq("id", dto.getId()));
//			}
//			if (dto.getServiceId() != null && dto.getServiceId() > 0) {
//				criteria.add(Restrictions.eq("serviceId", dto.getServiceId()));
//			}
			
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult((int) pageNo);
				criteria.setMaxResults(pageSize);
			}
	//	}
		log.info("RequestServiceDAOImpl Search method End");
		System.out.println("criteia list: "+criteria.list());
		return criteria.list();
	}

	@Override
	public RequestServiceDTO findByServiceNameAndEmployeeName(String service, Long login) {
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(RequestServiceDTO.class);
		Criterion c1 = Restrictions.eq("serviceName", service);
	    Criterion c2 = Restrictions.eq("userId", login);
	    Criterion c3 = Restrictions.and(c1, c2);
	    criteria.add(c3);
		//criteria.add(Restrictions.disjunction().add(Restrictions.or(Restrictions.eq("taxiName", taxiName)).add(Restrictions.eq("driverId", driverId))));
		RequestServiceDTO dto = (RequestServiceDTO) criteria.uniqueResult();
		return dto;
	}

	@Override
	public List<RequestServiceDTO> findAllRequestAssignedByServiceId(Long serviceid,Long userid) {
		Session session = entityManager.unwrap(Session.class);
		return session.createSQLQuery("SELECT j_request.id, j_user.first_name, j_user.last_name,j_service.service_name,j_request.image FROM j_user JOIN j_request ON j_user.id = j_request.user JOIN j_service ON j_service.id = j_request.service  where j_service.id='"+serviceid+"' and j_user.id='"+userid+"'").list();
	}

	@Override
	public RequestServiceDTO findByServiceNameAndEmployeeName(Long service, Long login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RequestServiceDTO> searchRequestServiceList(RequestServiceDTO dto) {
		return search(dto, 0, 0);
	}

	@Override
	public List<RequestServiceDTO> searchRequestServiceList(RequestServiceDTO dto, int pageNo, int pageSize) {
		System.out.println("Useris: "+dto.getUserId());
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(RequestServiceDTO.class);
		System.out.println("Service: "+dto.getServiceName()+" USer: "+dto.getUserId() +" city: "+dto.getCity());
		//Restrictions.or(Restrictions.eq("userId", dto.getUserId()),(Restrictions.eq("serviceId", dto.getServiceId())));
		
//	    if (dto != null) {
//			if (dto.getId() > 0) {
//				criteria.add(Restrictions.eq("id", dto.getId()));
//			}
//			if (dto.getServiceId() != null && dto.getServiceId() > 0) {
//				criteria.add(Restrictions.eq("serviceId", dto.getServiceId()));
//			}
			
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult((int) pageNo);
				criteria.setMaxResults(pageSize);
			}
	//	}
		log.info("RequestServiceDAOImpl Search method End");
		System.out.println("criteia list: "+criteria.list());
		return criteria.list();
	}

}
