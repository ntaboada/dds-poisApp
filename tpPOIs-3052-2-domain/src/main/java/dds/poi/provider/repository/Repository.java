package dds.poi.provider.repository;

import java.util.List;

public interface Repository<T> {

	public boolean create(T obj);
	
	public boolean delete(T obj);
	
	public boolean deleteAll();
	
	public boolean update(T obj);
	
	public T searchById(int id);
	
	public List<T> search(String valor);
	
	public List<T> findAll();
	
}
