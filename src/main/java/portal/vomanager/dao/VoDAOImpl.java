package portal.vomanager.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import portal.vomanager.domain.Vo;

@Repository
public class VoDAOImpl implements VoDAO {

	private static final Logger log = Logger
			.getLogger(VoDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public List<Vo> getAllVo() {
		log.debug("getting all Vo instance");
		try {

			Session session = sessionFactory.getCurrentSession();

			// Create a Hibernate query (HQL)
			Query query = session.createQuery("FROM  Vo ORDER BY vo");

			// Retrieve all
			return query.list();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public Vo findById(Integer id) {
		log.debug("getting UserInfo instance with id: " + id);
		try {
			Session session = sessionFactory.getCurrentSession();
			Vo instance = (Vo) session.get(Vo.class, id);
			log.debug("get successful");
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	@SuppressWarnings("unchecked")
	public List<String> getAllDiscipline() {
		log.debug("getting all Vo instance");
		try {

			Session session = sessionFactory.getCurrentSession();

			// Create a Hibernate query (HQL)
			Query query = session
					.createSQLQuery("SELECT DISTINCT Discipline FROM  VO");

			// Retrieve all
			return query.list();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public void editVo(Vo vo) {
		log.debug("Editing existing userInfo");

		// Retrieve session from Hibernate
		Session session = sessionFactory.getCurrentSession();

		// Retrieve existing userInfo via id
		Vo existingVo = (Vo) session.get(Vo.class,
				vo.getIdVo());

		// Assign updated values to this userInfo
		existingVo.setAup(vo.getAup());
		existingVo.setDescription(vo.getDescription());
		existingVo.setDiscipline(vo.getDiscipline());
		existingVo.setEnrollementUrl(vo.getEnrollementUrl());
		existingVo.setHost(vo.getHost());
		existingVo.setInsertTime(vo.getInsertTime());
		existingVo.setMiddleware(vo.getMiddleware());
		existingVo.setVo(vo.getVo());
		existingVo.setVoManagerMail(vo.getVoManagerMail());
		existingVo.setConfigured(vo.getConfigured());
		

		// Save updates
		session.save(existingVo);
		
	}

	public void delete(int idVO) {
		log.debug("removing UserInfo instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			Vo instance = (Vo) session.get(Vo.class, idVO);
			session.delete(instance);
			log.debug("remove successful");
		} catch (RuntimeException re) {
			log.error("remove failed", re);
			throw re;
		}
		
	}

	@SuppressWarnings("unchecked")
	public List<Vo> getAllVoByName(String search) {
		log.debug("getting all Vo instance");
		try {

			Session session = sessionFactory.getCurrentSession();

			// Create a Hibernate query (HQL)
			Query query = session.createQuery("FROM  Vo WHERE vo LIKE '%"+search+"%' ORDER BY vo");

			// Retrieve all
			return query.list();
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public void addVo(Vo vo) {
		log.debug("persisting UserInfo instance");
		try {
			Session session = sessionFactory.getCurrentSession();
			session.save(vo);
			log.debug("persist successful");
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

}
