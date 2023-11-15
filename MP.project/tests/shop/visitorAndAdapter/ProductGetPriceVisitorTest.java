package shop.visitorAndAdapter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import shop.composite.Item;
import shop.composite.ProductPackage;
import shop.composite.Service;
import shop.visitorAndAdapter.ProductGetPriceVisitor;

public class ProductGetPriceVisitorTest {
	
	private ProductGetPriceVisitor visitor;

	@Before
	public void setUp() {
		visitor = new ProductGetPriceVisitor();
	}

	@Test
	public void testVisitItem() {
		Item item = new Item("Item", 10, false);
		item.accept(visitor);
		
		assertThat(visitor.getPrice()).isEqualTo(10);
	}
	
	@Test
	public void testVistirService() {
		Service service = new Service("Service", 10, 5);
		service.accept(visitor);
		
		assertThat(visitor.getPrice()).isEqualTo(10);
	}
	
	@Test
	public void testVisitEmptyProductPackage() {
		ProductPackage pack = new ProductPackage("Pack");
		pack.accept(visitor);
		
		assertThat(visitor.getPrice()).isEqualTo(0);
	}

	@Test
	public void testVisitNotEmptyProductPackage() {
		ProductPackage pack = new ProductPackage("Pack");
		pack.add(new Item("Item", 10, false));
		pack.add(new Service("Service", 5, 5));
		pack.accept(visitor);
		
		assertThat(visitor.getPrice()).isEqualTo(15);
	}
	
	@Test
	public void testVisitNestedProductPackage() {
		ProductPackage pack = new ProductPackage("Pack");
		ProductPackage nested = new ProductPackage("Nested");
		pack.add(nested);
		nested.add(new Item("Item", 10, false));
		pack.add(new Service("Service", 5, 5));
		pack.accept(visitor);
		
		assertThat(visitor.getPrice()).isEqualTo(15);
	}
}
