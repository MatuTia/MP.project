package shop.composite;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;

public class ProductPackageTest {
	
	private ProductPackage pack;

	@Before
	public void setUp() {
		pack = new ProductPackage("Pack");
	}

	@Test
	public void testAdd() {
		Product toAdd = new Item("Item", 10, false);
		pack.add(toAdd);
		
		assertThat(pack.getProducts()).hasSize(1).contains(toAdd);
	}
	
	@Test
	public void testRemove() {
		Product notRemove = new Item("Item", 10, false);
		Product toRemove = new Service("Service", 10, 5);
		pack.getProducts().add(notRemove);
		pack.getProducts().add(toRemove);
		
		assertThat(pack.getProducts()).hasSize(2).contains(notRemove, toRemove);
		
		pack.remove(toRemove);
		
		assertThat(pack.getProducts()).hasSize(1).contains(notRemove);
	}
	
	@Test
	public void testFindByName() {
		Product notFind = new Item("Item", 10, false);
		Product toFind = new Service("Service", 10, 5);
		pack.getProducts().add(notFind);
		pack.getProducts().add(toFind);
		
		assertThat(pack.findByName("Product")).isEmpty();
		assertThat(pack.findByName("Service")).contains(toFind);
		
	}
	
	@Test 
	public void testUpdate() {
		Item item = new Item("Item", 10, true);
		pack.getProducts().add(item);
		assertThat(pack.isAvailable()).isTrue();
		
		item.attach(pack);
		item.changeAvailable(false);
		
		assertThat(pack.isAvailable()).isEqualTo(item.isAvailable()).isFalse();
		
		boolean isAvailable = pack.isAvailable();
		
		item.detach(pack);
		item.changeAvailable(true);
		
		assertThat(isAvailable).isEqualTo(pack.isAvailable());
		assertThat(isAvailable).isNotEqualTo(item.isAvailable());
	}

}
