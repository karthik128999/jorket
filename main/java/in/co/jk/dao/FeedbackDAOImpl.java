package in.co.jk.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.jk.dto.FeedbackDTO;

@Repository
public class FeedbackDAOImpl implements FeedbackDAOInt {

	private static Logger log = Logger.getLogger(FeedbackDAOImpl.class.getName());
	@Autowired
	private EntityManager entityManager;
	@Override
	public long add(FeedbackDTO dto) {
		log.info("FeedbackDAOImpl start method started");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long)session.save(dto);
		log.info("FeedbackDAOImpl Add method ends");
		return pk;
	}

	@Override
	public void delete(FeedbackDTO dto) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Query<FeedbackDTO> query = session.createSQLQuery("delete from j_service where id = "+dto.getId()+"");
		query.executeUpdate();
	//	entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
	}

	@Override
	public FeedbackDTO findBypk(long pk) {
		log.info("FeedbackDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		FeedbackDTO dto = (FeedbackDTO) session.get(FeedbackDTO.class, pk);
		log.info("FeedbackDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public FeedbackDTO findByServiceName(String className) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(FeedbackDTO.class);
		criteria.add(Restrictions.eq("serviceName", className));
		FeedbackDTO dto = (FeedbackDTO) criteria.uniqueResult();
		return dto;
	}

	@Override
	public void update(FeedbackDTO dto) {
		// TODO Auto-generated method stub
		log.info("FeedbackDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("FeedbackDAOImpl update method End");
	}

	@Override
	public List<FeedbackDTO> list() {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	@Override
	public List<FeedbackDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("FeedbackDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<FeedbackDTO> query = session.createQuery("from FeedbackDTO", FeedbackDTO.class);
		List<FeedbackDTO> list = query.getResultList();
		log.info("FeedbackDAOImpl List method End");
		return list;
	}

	@Override
	public List<FeedbackDTO> search(FeedbackDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List<FeedbackDTO> search(FeedbackDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("FeedbackDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(FeedbackDTO.class);
		if(dto.getUserId()!=null) {
			criteria.add(Restrictions.eq("userId", dto.getUserId()));
		}
		
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			
			
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult((int) pageNo);
				criteria.setMaxResults(pageSize);
			}
		}
		log.info("FeedbackDAOImpl Search method End");
		return criteria.list();
	}

}
