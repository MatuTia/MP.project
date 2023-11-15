package shop.visitorAndAdapter;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import shop.composite.Item;
import shop.composite.ProductPackage;
import shop.composite.Service;
import shop.utilies.MockPrinterInterface;
import shop.utilies.PrinterInterface;
import shop.visitorAndAdapter.ProductLsVisitor;

public class ProductLsVisitorTest {

	private PrinterInterface printer;
	private ProductLsVisitor visitor;

	@Before
	public void setUp() {
		printer = new MockPrinterInterface();
		visitor = new ProductLsVisitor(printer);
	}

	@Test
	public void testVisitItem() {
		Item item = new Item("Item", 10, false);
		item.accept(visitor);

		assertThat(printer.toString()).isEqualTo("Item name: Item, price: 10\n");
	}

	@Test
	public void testVisitService() {
		Service service = new Service("Service", 10, 5);
		service.accept(visitor);

		assertThat(printer.toString()).isEqualTo("Service name: Service, price: 10, subscription days: 5\n");
	}

	@Test
	public void testVisitEmptyProductPackage() {
		ProductPackage pack = new ProductPackage("Pack");
		pack.accept(visitor);

		assertThat(printer.toString()).isEqualTo("Package name: Pack\n");
	}

	@Test
	public void testVisitNotEmptyProductPackage() {
		ProductPackage pack = new ProductPackage("Pack");
		pack.add(new Item("Item", 10, false));
		pack.add(new Service("Service", 5, 5));
		pack.accept(visitor);

		assertThat(printer.toString()).isEqualTo("Package name: Pack\n" + "Item name: Item, price: 10\n"
				+ "Service name: Service, price: 5, subscription days: 5\n");
	}

	@Test
	public void testVisitNestedProductPackage() {
		ProductPackage pack = new ProductPackage("Pack");
		ProductPackage nested = new ProductPackage("Nested");
		pack.add(nested);
		nested.add(new Item("Item", 10, false));
		pack.add(new Service("Service", 5, 5));
		pack.accept(visitor);

		assertThat(printer.toString()).isEqualTo("Package name: Pack\n" + "Package name: Nested\n"
				+ "Item name: Item, price: 10\n" + "Service name: Service, price: 5, subscription days: 5\n");
	}
}
