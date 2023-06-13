package in.co.jk.dao;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.co.jk.dto.UserDTO;

@Repository
public class UserDAOImpl implements UserDAOInt {

	private static Logger log = Logger.getLogger(UserDAOImpl.class.getName());
	@Autowired
	private EntityManager entityManager;
	@Override
	public long add(UserDTO dto) {
		log.info("UserDAOImpl start method started");
		Session session = entityManager.unwrap(Session.class);
		long pk = (long)session.save(dto);
		log.info("UserDAOImpl Add method ends");
		return pk;
	}

	@Override
	public void delete(UserDTO dto) {
		// TODO Auto-generated method stub
		System.out.println("id>>>>>>"+dto.getId());
		Session session = entityManager.unwrap(Session.class);
		Query<UserDTO> query = session.createSQLQuery("delete from j_user where id = "+dto.getId()+"");
		query.executeUpdate();
	//	entityManager.remove(entityManager.contains(dto) ? dto : entityManager.merge(dto));
	}

	@Override
	public UserDTO findBypk(long pk) {
		log.info("UserDAOImpl FindByPk method Start");
		Session session = entityManager.unwrap(Session.class);
		UserDTO dto = (UserDTO) session.get(UserDTO.class, pk);
		log.info("UserDAOImpl FindByPk method End");
		return dto;
	}

	@Override
	public UserDTO findByLogin(String login) {
		// TODO Auto-generated method stub
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(UserDTO.class);
		criteria.add(Restrictions.eq("login", login));
		UserDTO dto = (UserDTO) criteria.uniqueResult();
		return dto;
	}

	@Override
	public void update(UserDTO dto) {
		// TODO Auto-generated method stub
		log.info("UserDAOImpl Update method Start");
		Session session = entityManager.unwrap(Session.class);
		session.merge(dto);
		log.info("UserDAOImpl update method End");
	}

	@Override
	public List<UserDTO> list() {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	@Override
	public List<UserDTO> list(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("UserDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<UserDTO> query = session.createQuery("from UserDTO", UserDTO.class);
		List<UserDTO> list = query.getResultList();
		log.info("UserDAOImpl List method End");
		return list;
	}

	@Override
	public List<UserDTO> search(UserDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List<UserDTO> search(UserDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("UserDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(UserDTO.class);
		criteria.add(Restrictions.eq("roleName", "PROFESSIONAL"));
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				criteria.add(Restrictions.like("firstName", dto.getFirstName() + "%"));
			}
			if (dto.getLastName() != null && dto.getLastName().length() > 0) {
				criteria.add(Restrictions.like("lastName", dto.getLastName() + "%"));
			}
			if (dto.getLogin() != null && dto.getLogin().length() > 0) {
				criteria.add(Restrictions.like("login", dto.getLogin() + "%"));
			}
			if (dto.getRoleName() != null && dto.getRoleName().length() > 0) {
				criteria.add(Restrictions.like("roleName", dto.getRoleName() + "%"));
			}
			
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult((int) pageNo);
				criteria.setMaxResults(pageSize);
			}
		}
		log.info("UserDAOImpl Search method End");
		
		return criteria.list();
	}

	@Override
	public UserDTO authentication(UserDTO dto) {
		// TODO Auto-generated method stub
		log.info("UserDAOImpl Authentication method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<UserDTO> query = session
				.createQuery("from UserDTO where login=:login and password=:password", UserDTO.class);
		query.setParameter("login",dto.getLogin());
		query.setParameter("password",dto.getPassword());
		dto = null;
		try {
			dto = query.getSingleResult();
		} catch (NoResultException nre) {
			
		}
		log.info("UserDAOImpl Authentication method End");
		return dto;
	}
	public List<UserDTO> getAllEmployees(UserDTO dto){
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(UserDTO.class);
		criteria.add(Restrictions.eq("roleId", dto.getRoleId()));
		return criteria.list();
	}

	@Override
	public List<String> skillList(Long userId) {
		List<String> skills = null;
		Session session = entityManager.unwrap(Session.class);
		String sql = "select skill_1,skill_2,skill_3,skill_4,skill_5 from j_user where id ="+userId+"";
		Query<String> createSQLQuery = session.createSQLQuery(sql);
		skills = createSQLQuery.getResultList();
		System.out.println("Skills: "+skills.size());
		return skills;
	}
	
	@Override
	public List<UserDTO> listCustomers() {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	@Override
	public List<UserDTO> listCustomers(int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("UserDAOImpl List method Start");
		Session session = entityManager.unwrap(Session.class);
		Query<UserDTO> query = session.createQuery("from UserDTO", UserDTO.class);
		List<UserDTO> list = query.getResultList();
		log.info("UserDAOImpl List method End");
		return list;
	}

	@Override
	public List<UserDTO> searchCustomers(UserDTO dto) {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	@Override
	public List<UserDTO> searchCustomers(UserDTO dto, int pageNo, int pageSize) {
		// TODO Auto-generated method stub
		log.info("UserDAOImpl Search method Start");
		Session session = entityManager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(UserDTO.class);
		criteria.add(Restrictions.eq("roleName", "CUSTOMER"));
		if (dto != null) {
			if (dto.getId() > 0) {
				criteria.add(Restrictions.eq("id", dto.getId()));
			}
			if (dto.getFirstName() != null && dto.getFirstName().length() > 0) {
				criteria.add(Restrictions.like("firstName", dto.getFirstName() + "%"));
			}
			if (dto.getLastName() != null && dto.getLastName().length() > 0) {
				criteria.add(Restrictions.like("lastName", dto.getLastName() + "%"));
			}
			if (dto.getLogin() != null && dto.getLogin().length() > 0) {
				criteria.add(Restrictions.like("login", dto.getLogin() + "%"));
			}
			if (dto.getRoleName() != null && dto.getRoleName().length() > 0) {
				criteria.add(Restrictions.like("roleName", dto.getRoleName() + "%"));
			}
			
			if (pageSize > 0) {
				pageNo = (pageNo - 1) * pageSize;
				criteria.setFirstResult((int) pageNo);
				criteria.setMaxResults(pageSize);
			}
		}
		log.info("UserDAOImpl Search method End");
		
		return criteria.list();
	}
}
