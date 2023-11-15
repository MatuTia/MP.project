package shop.composite;

import java.util.Optional;

import shop.utilies.PrinterInterface;
import shop.visitorAndAdapter.ProductGetPriceVisitor;
import shop.visitorAndAdapter.ProductLsVisitor;

public class ProductPackageFacade {
	private ProductPackage pack;
	

	public ProductPackageFacade(ProductPackage pack) {
		this.pack = pack;
	}

	public void addAndAttach(Product product) {
		pack.add(product);
		product.attach(pack);
	}
	
	public void removeAndDetach(Product product) {
		pack.remove(product);
		product.detach(pack);
	}

	public PrinterInterface getProductLs(PrinterInterface printer) {
		ProductLsVisitor visitor = new ProductLsVisitor(printer);
		pack.accept(visitor);
		return printer;
	}
	
	public int getProductPrice() {
		ProductGetPriceVisitor visitor = new ProductGetPriceVisitor();
		pack.accept(visitor);
		return visitor.getPrice();
	}
	
	public Optional<Product> findByName(String name) {
		return pack.findByName(name);
	}
	
	public boolean isAvailable() {
		return pack.isAvailable();
	}
}
