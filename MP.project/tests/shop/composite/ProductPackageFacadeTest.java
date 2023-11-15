package shop.composite;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

import shop.utilies.MockPrinterInterface;
import shop.utilies.PrinterInterface;

public class ProductPackageFacadeTest {

	private ProductPackage pack;
	private ProductPackageFacade face;

	@Before
	public void setUp() {
		pack = new ProductPackage("Pack");
		face = new ProductPackageFacade(pack);
	}

	@Test
	public void testAddAndAttach() {
		Item item = new Item("Item", 10, false);
		Service service = new Service("Sevice", 10, 5);
		face.addAndAttach(service);
		face.addAndAttach(item);

		assertThat(item.isAvailable()).isNotEqualTo(service.isAvailable()).isEqualTo(pack.isAvailable())
				.isEqualTo(face.isAvailable()).isFalse();

		item.changeAvailable(true);

		assertThat(item.isAvailable()).isEqualTo(service.isAvailable()).isEqualTo(pack.isAvailable())
				.isEqualTo(face.isAvailable()).isTrue();
	}

	@Test
	public void testRemoveAndDetach() {
		Item item = new Item("Item", 10, false);
		pack.add(item);
		item.attach(pack);

		assertThat(item.isAvailable()).isEqualTo(pack.isAvailable()).isEqualTo(face.isAvailable()).isFalse();

		face.removeAndDetach(item);

		assertThat(face.isAvailable()).isEqualTo(pack.isAvailable()).isNotEqualTo(item.isAvailable());
		assertThat(face.isAvailable()).isTrue();
	}

	@Test
	public void testGetProductLs() {
		PrinterInterface printer = new MockPrinterInterface();
		face.getProductLs(printer);

		assertThat(printer.toString()).isEqualTo("Package name: Pack\n");

	}

	@Test
	public void testGetProductPrice() {
		assertThat(face.getProductPrice()).isEqualTo(0);
	}

	@Test
	public void testFindByName() {
		Item item = new Item("Item", 10, false);
		pack.add(item);
		assertThat(face.findByName("Item")).contains(item);
	}

}
