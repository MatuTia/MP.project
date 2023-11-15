package shop.visitorAndAdapter;

import shop.composite.Item;
import shop.composite.ProductPackage;
import shop.composite.Service;

public interface ProductVisitor {

	void visitItem(Item item);

	void visitService(Service service);

	void visitProductPackage(ProductPackage productPackage);

}
