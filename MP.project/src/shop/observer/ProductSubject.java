package shop.observer;

import java.util.ArrayList;
import java.util.Collection;

public abstract class ProductSubject {
	private Collection<ProductObserver> observers = new ArrayList<>();
	
	public void attach(ProductObserver toAttach) {
		observers.add(toAttach);
	}
	
	public void detach(ProductObserver toDetach) {
		observers.remove(toDetach);
	}
	
	public void notifyProductObserver() {
		observers.forEach(ProductObserver::update);
	}

}
