package shop.observer;

import shop.composite.Product;

public class BuyerProduct implements ProductObserver {
	private Product product;
	private boolean productAvailable;

	public BuyerProduct(Product product) {
		this.product = product;
		this.productAvailable = product.isAvailable();
	}

	@Override
	public void update() {
		productAvailable = product.isAvailable();
	}

	public boolean isProductAvailable() {
		return productAvailable;
	}

}
