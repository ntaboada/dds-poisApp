package dds.poi.provider.repository;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import dds.poi.model.POI;
import dds.poi.provider.Provider;

public class POIRepository extends AbstractRepository<POI> implements
		Provider<List<POI>> {

	private static POIRepository _selfInstance;

	public static POIRepository getInstance() {
		if (_selfInstance == null) {
			_selfInstance = new POIRepository();
		}
		return _selfInstance;
	}

	@Override
	public void addQueryByExample(Criteria criteria, POI entity) {
		// TODO Auto-generated method stub
	}

	@Override
	public Class<POI> getEntityType() {
		return POI.class;
	}

	@Override
	public void addIdWhereClause(Criteria criteria, Object id) {
		criteria.add(Restrictions.eq("identificador", (long) id));
	}

	@Override
	public List<POI> searchPOIs(String valor) {
		return null;
//		Session session = this.openSession();
//		try {
//			return session.createCriteria(User.class)
//					.setFetchMode("candidatos", FetchMode.JOIN)
//					.add(Restrictions.eq("username", username))
//					.add(Restrictions.eq("password", password)).uniqueResult();
//		} catch (HibernateException e) {
//			throw new RuntimeException(e);
//		} finally {
//			session.close();
//		}
	}

}
