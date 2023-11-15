package shop.visitorAndAdapter;

import java.util.Iterator;

import shop.composite.Item;
import shop.composite.Product;
import shop.composite.ProductPackage;
import shop.composite.Service;

public abstract class ProductVisitorAdapter implements ProductVisitor {

	@Override
	public void visitItem(Item item) {}

	@Override
	public void visitService(Service service) {}

	@Override
	public void visitProductPackage(ProductPackage productPackage) {
		Iterator<Product> iterator = productPackage.iterator();
		while(iterator.hasNext())
			iterator.next().accept(this);
	}

}
