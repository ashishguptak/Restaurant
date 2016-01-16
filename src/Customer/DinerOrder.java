package Customer;

import MenuItems.*;

public class DinerOrder {
	
	public ItemCount itemCount = new ItemCount();
	public ItemReady itemReady = new ItemReady();

	/**
	 * @param numberOfBurgers
	 * @param numberOfFries
	 * @param numberOfCokes
	 * @param numberOfSundae
	 */
	public DinerOrder(int numberOfBurgers, int numberOfFries, int numberOfCokes, int numberOfSundae) {
		
		this.itemCount.initialize(numberOfBurgers,numberOfFries,numberOfCokes,numberOfSundae);
		this.itemReady.initialize(itemCount);

	}
	
	public ItemCount getItemCount() {
		return itemCount;
	}

	public void setItemCount(ItemCount itemCount) {
		this.itemCount = itemCount;
	}
	
	public ItemReady getItemReady() {
		return itemReady;
	}

	public void setItemReady(ItemReady itemReady) {
		this.itemReady = itemReady;
	}

	/**
	 * @return boolean
	 */
	public boolean isComplete() {
		if(this.getItemReady().isBurgersReady() && this.getItemReady().isFriesReady() && this.getItemReady().isCokeReady()
				&& this.getItemReady().isSundaeReady() )
			return true;
		else
			return false;
	}
}
