package shop.composite;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class ItemTest {

	@Test
	public void testChangeAvailable() {
		Item item = new Item("Item", 10, false);
		item.changeAvailable(false);
		assertThat(item.isAvailable()).isFalse();
		item.changeAvailable(true);
		assertThat(item.isAvailable()).isTrue();
	}

}
