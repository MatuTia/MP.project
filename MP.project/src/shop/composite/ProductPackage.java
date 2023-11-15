package shop.composite;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Optional;

import shop.observer.ProductObserver;
import shop.visitorAndAdapter.ProductVisitor;

public class ProductPackage extends Product implements ProductObserver {
	private final Collection<Product> products = new ArrayList<Product>();
	private boolean available = true;

	public ProductPackage(String name) {
		super(name);
	}

	@Override
	public void accept(ProductVisitor visitor) {
		visitor.visitProductPackage(this);
	}

	@Override
	public boolean isAvailable() {
		return available;
	}

	Collection<Product> getProducts() {
		return products;
	}
	
	public Iterator<Product> iterator() {
		return products.iterator();
	}
	
	public void add(Product toAdd) {
		products.add(toAdd);
		isProductAvailable();
	}
	
	public void remove(Product toRemove) {
		products.remove(toRemove);
		isProductAvailable();
	}
	
	public Optional<Product> findByName(String name){
		return products.stream()
				.filter(product -> Objects.equals(name, product.getName()))
				.findFirst();
	}

	@Override
	public void update() {
		isProductAvailable();
	}

	private void isProductAvailable() {
		boolean productAvailable = products.stream()
										.map(product -> product.isAvailable())
										.filter(available -> !available)
										.findFirst()
										.orElse(true);
		if(productAvailable != available) {
			setAvailable(productAvailable);
			notifyProductObserver();
		}
	}

	private void setAvailable(boolean available) {
		this.available = available;
	}

}
