package shop.composite;

import shop.observer.ProductSubject;
import shop.visitorAndAdapter.ProductVisitor;

public abstract class Product extends ProductSubject {

	private final String name;

	public Product(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public abstract void accept(ProductVisitor visitor);

	public abstract boolean isAvailable();
}