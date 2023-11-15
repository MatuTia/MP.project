package shop.visitorAndAdapter;

import shop.composite.Item;
import shop.composite.ProductPackage;
import shop.composite.Service;
import shop.utilies.PrinterInterface;

public class ProductLsVisitor extends ProductVisitorAdapter {
	private PrinterInterface printer;
	
	public ProductLsVisitor(PrinterInterface printer) {
		this.printer = printer;
	}

	@Override
	public void visitItem(Item item) {
		printer.print("Item name: " + item.getName() + ", price: " + item.getPrice());
	}

	@Override
	public void visitService(Service service) {
		printer.print("Service name: " + service.getName() + ", price: " + service.getPrice() + ", subscription days: "
				+ service.getSubscriptionDay());
	}

	@Override
	public void visitProductPackage(ProductPackage productPackage) {
		printer.print("Package name: " + productPackage.getName());
		super.visitProductPackage(productPackage);
	}
}
