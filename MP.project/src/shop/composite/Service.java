package shop.composite;

import shop.visitorAndAdapter.ProductVisitor;

public class Service extends Product {

	private final int price;
	private final int subscriptionDay;

	public Service(String name, int price, int subscriptionDay) {
		super(name);
		this.price = price;
		this.subscriptionDay = subscriptionDay;
	}

	@Override
	public void accept(ProductVisitor visitor) {
		visitor.visitService(this);
	}

	@Override
	public boolean isAvailable() {
		return true;
	}

	public int getPrice() {
		return price;
	}

	public int getSubscriptionDay() {
		return subscriptionDay;
	}

}
