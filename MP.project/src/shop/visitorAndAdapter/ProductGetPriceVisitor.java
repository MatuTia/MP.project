package shop.visitorAndAdapter;

import shop.composite.Item;
import shop.composite.Service;

public class ProductGetPriceVisitor extends ProductVisitorAdapter {
	private int price;

	public int getPrice() {
		return price;
	}
	
	@Override
	public void visitItem(Item item) {
		price += item.getPrice();
	}
	
	@Override
	public void visitService(Service service) {
		price += service.getPrice();
	}
	
	

}
