package dds.poi.provider.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dds.poi.model.Banco;
import dds.poi.model.CGP;
import dds.poi.model.CategoriaConServicios;
import dds.poi.model.CategoriaPOI;
import dds.poi.model.LocalComercial;
import dds.poi.model.POI;
import dds.poi.model.ParadaColectivo;
import dds.poi.model.Rango;
import dds.poi.model.Review;
import dds.poi.model.Rubro;
import dds.poi.model.Servicio;
import dds.poi.model.ServicioBanco;
import dds.poi.model.ServicioCGP;
import dds.poi.model.search.user.User;
import dds.poi.model.search.user.UserProfile;

public abstract class AbstractRepository<T> implements Repository<T> {

	private static final SessionFactory sessionFactory = new Configuration()
			.configure()
			.addAnnotatedClass(UserProfile.class)
			.addAnnotatedClass(User.class)
			.addAnnotatedClass(Review.class)
			.addAnnotatedClass(Rango.class)
			.addAnnotatedClass(Rubro.class)
			.addAnnotatedClass(CategoriaPOI.class)
			.addAnnotatedClass(Servicio.class)
			.addAnnotatedClass(ServicioBanco.class)
			.addAnnotatedClass(ServicioCGP.class)
			.addAnnotatedClass(CategoriaConServicios.class)
			.addAnnotatedClass(Banco.class)
			.addAnnotatedClass(CGP.class)
			.addAnnotatedClass(LocalComercial.class)
			.addAnnotatedClass(ParadaColectivo.class)
			.addAnnotatedClass(POI.class)
			.buildSessionFactory();

	@Override
	public void create(T t) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(t);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	}

	@Override
	public void delete(T t) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.update(t);
			session.getTransaction().commit();
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(T obj) {
		// TODO Auto-generated method stub
	}

	@Override
	public T searchById(Object id) {
		Session session = sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(getEntityType());
			this.addIdWhereClause(criteria, id);
			return (T) criteria.uniqueResult();
		} catch (HibernateException e) {
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	}

	@Override
	public List<T> search(T entity) {
		Session session = sessionFactory.openSession();
		try {
			Criteria criteria = session.createCriteria(getEntityType());
			this.addQueryByExample(criteria, entity);
			return criteria.list();
		} catch (HibernateException e) {
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	}

	@Override
	public List<T> findAll() {
		Session session = sessionFactory.openSession();
		try {
			return session.createCriteria(getEntityType()).list();
		} finally {
			session.close();
		}
	}

	protected Session openSession() {
		return sessionFactory.openSession();
	}
	
	public abstract void addQueryByExample(Criteria criteria, T entity);

	public abstract Class<T> getEntityType();

	public abstract void addIdWhereClause(Criteria criteria, Object id);

}
