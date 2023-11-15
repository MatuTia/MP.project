package shop.observer;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import shop.composite.Item;
import shop.composite.ProductPackage;

public class BuyerProductTest {

	@Test
	public void testUpdatebyItem() {
		Item item = new Item("Item", 10, true);
		BuyerProduct buyer = new BuyerProduct(item);
		assertThat(buyer.isProductAvailable()).isTrue();
		
		item.attach(buyer);
		item.changeAvailable(false);
		
		assertThat(buyer.isProductAvailable()).isEqualTo(item.isAvailable()).isFalse();
		
		boolean isAvailable = buyer.isProductAvailable();
		
		item.detach(buyer);
		item.changeAvailable(true);
		
		assertThat(isAvailable).isEqualTo(buyer.isProductAvailable());
		assertThat(isAvailable).isNotEqualTo(item.isAvailable());
	}
	
	@Test
	public void testUpdatebyAddProductPackage() {
		ProductPackage pack = new ProductPackage("Pack");
		BuyerProduct buyer = new BuyerProduct(pack);
		
		assertThat(buyer.isProductAvailable()).isEqualTo(pack.isAvailable()).isTrue();
		
		pack.attach(buyer);
		
		pack.add(new Item("Item", 10, true));
		
		assertThat(buyer.isProductAvailable()).isEqualTo(pack.isAvailable()).isTrue();
		
		boolean isProductAvailable = buyer.isProductAvailable();
		pack.detach(buyer);
		pack.add(new Item("Item", 10, false));
		
		assertThat(isProductAvailable).isEqualTo(buyer.isProductAvailable()).isTrue();
		assertThat(isProductAvailable).isNotEqualTo(pack.isAvailable());	
	}
	
	@Test
	public void testUpdatebyRemove() {
		ProductPackage pack = new ProductPackage("Pack");
		Item toRemove = new Item("Item", 10, false);
		Item toRemoveAfter = new Item("Item", 20, false);
		pack.add(toRemoveAfter);
		pack.add(toRemove);
		BuyerProduct buyer = new BuyerProduct(pack);
		
		assertThat(buyer.isProductAvailable()).isEqualTo(pack.isAvailable()).isFalse();
		
		pack.attach(buyer);
		pack.remove(toRemove);
		
		assertThat(buyer.isProductAvailable()).isEqualTo(pack.isAvailable()).isFalse();

		boolean isProductAvailable = buyer.isProductAvailable();
		pack.detach(buyer);
		
		pack.remove(toRemoveAfter);

		assertThat(isProductAvailable).isEqualTo(buyer.isProductAvailable()).isFalse();
		assertThat(isProductAvailable).isNotEqualTo(pack.isAvailable());		
	}
	
	@Test
	public void tesUpdatebyChangeItem() {
		ProductPackage pack = new ProductPackage("Pack");
		Item item = new Item("Item", 10, false);
		pack.add(item);
		item.attach(pack);
		BuyerProduct buyer = new BuyerProduct(pack);
		
		assertThat(buyer.isProductAvailable()).isEqualTo(pack.isAvailable()).isFalse();
		
		pack.attach(buyer);
		item.changeAvailable(true);
		
		assertThat(buyer.isProductAvailable()).isEqualTo(pack.isAvailable()).isTrue();
		
		boolean isProductAvailable = buyer.isProductAvailable();
		pack.detach(buyer);
		item.changeAvailable(false);
		
		assertThat(isProductAvailable).isEqualTo(buyer.isProductAvailable()).isTrue();
		assertThat(isProductAvailable).isNotEqualTo(pack.isAvailable());		
	}
	

}
