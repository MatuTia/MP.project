package shop.composite;

import shop.visitorAndAdapter.ProductVisitor;

public class Item extends Product {
	private final int price;
	private boolean available;
	
	public Item(String name, int price, boolean available) {
		super(name);
		this.price = price;
		this.available = available;
	}
	
	@Override
	public void accept(ProductVisitor visitor) {
		visitor.visitItem(this);
	}

	public int getPrice() {
		return price;
	}

	@Override
	public boolean isAvailable() {
		return available;
	}
	
	public void changeAvailable(boolean available) {
		if(this.available != available) {
			setAvailable(available);
			notifyProductObserver();
		}
	}

	private void setAvailable(boolean available) {
		this.available = available;
	}

}
