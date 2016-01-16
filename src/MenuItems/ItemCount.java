package MenuItems;

public class ItemCount {
	private int numberOfBurgers;
	private int numberOfFries;
	private int numberOfCokes;
	private int numberOfSundae;
	/**
	 * @param numberOfBurgers
	 * @param numberOfFries
	 * @param numberOfCokes
	 * @param numberOfSundae
	 */
	public void initialize(int numberOfBurgers, int numberOfFries, int numberOfCokes, int numberOfSundae) {
		this.numberOfBurgers = numberOfBurgers;
		this.numberOfFries = numberOfFries;
		this.numberOfCokes = numberOfCokes;
		this.numberOfSundae = numberOfSundae;
	}
	public int getNumberOfBurgers() {
		return numberOfBurgers;
	}
	public void setNumberOfBurgers(int numberOfBurgers) {
		this.numberOfBurgers = numberOfBurgers;
	}
	public int getNumberOfFries() {
		return numberOfFries;
	}
	public void setNumberOfFries(int numberOfFries) {
		this.numberOfFries = numberOfFries;
	}
	public int getNumberOfCokes() {
		return numberOfCokes;
	}
	public void setNumberOfCokes(int numberOfCokes) {
		this.numberOfCokes = numberOfCokes;
	}
	public int getNumberOfSundae() {
		return numberOfSundae;
	}
	public void setNumberOfSundae(int numberOfSundae) {
		this.numberOfSundae = numberOfSundae;
	}
	
	
}
