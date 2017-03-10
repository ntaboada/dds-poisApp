package dds.poi.adapter;

public interface Adapter<Receive, Return> {

	public Return adapt(Receive object);
	
}
