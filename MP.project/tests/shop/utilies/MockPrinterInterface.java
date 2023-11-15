package shop.utilies;

public class MockPrinterInterface implements PrinterInterface {
	private StringBuilder builder = new StringBuilder();

	@Override
	public void print(String string) {
		builder.append(string + "\n");
	}
	
	@Override
	public String toString() {
		return builder.toString();
	}

}
