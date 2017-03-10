package dds.poi.provider.repository;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import dds.poi.model.search.user.User;

public class UserRepository extends AbstractRepository<User>{

    private static UserRepository _selfInstance;

    public static UserRepository getInstance() {
        if(_selfInstance == null) {
            _selfInstance = new UserRepository();
        }
        return _selfInstance;
    }

	public boolean correctCredentials(String username, String password) {
		Session session = this.openSession();
		try {
			return (User) session.createCriteria(User.class)
				.add(Restrictions.eq("userName", username))
				.add(Restrictions.eq("password", password))
				.uniqueResult() != null;
		} catch (HibernateException e) {
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	}

	public Long getUserIdByUserNameAndPassword(String username, String password) {
		Session session = this.openSession();
		try {
			return ((User) session.createCriteria(User.class)
				.add(Restrictions.eq("userName", username))
				.add(Restrictions.eq("password", password))
				.uniqueResult()).getIdUsuario();
		} catch (HibernateException e) {
			throw new RuntimeException(e);
		} finally {
			session.close();
		}
	}

	@Override
	public void addQueryByExample(Criteria criteria, User entity) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Class<User> getEntityType() {
		return User.class;
	}


	@Override
	public void addIdWhereClause(Criteria criteria, Object id) {
		criteria.add(Restrictions.eq("idUsuario", (long)id));
	}
}
