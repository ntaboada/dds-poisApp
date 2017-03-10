package dds.poi.provider.repository;

import java.util.List;

public interface Repository<T> {

	public void create(T obj);
	
	public void delete(T obj);
	
	public void deleteAll();
	
	public void update(T obj);
	
	public T searchById(Object id);
	
	public List<T> search(T entity);
	
	public List<T> findAll();
	
}
