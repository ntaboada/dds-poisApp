package dds.poi.observer;


public interface Observable {
	void addObserver(Observer obs);

	void removeObserver(Observer obs);

	void notifyAllObservers();
}
