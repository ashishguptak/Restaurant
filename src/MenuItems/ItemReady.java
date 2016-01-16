package MenuItems;


public class ItemReady {
	public boolean burgersReady = false;
	public boolean friesReady = false;
	public boolean cokeReady = false;
	public boolean sundaeReady = false;
	
	/**
	 * @param itemCount
	 */
	public void initialize(ItemCount itemCount) {
		if(itemCount.getNumberOfFries() > 0)
			friesReady = false;
		else
			friesReady = true;
		
		if(itemCount.getNumberOfCokes() > 0) 
			cokeReady = false;
		else
			cokeReady = true;
		
		if(itemCount.getNumberOfSundae() > 0) 
			sundaeReady = false;
		else
			sundaeReady = true;
	}

	public boolean isBurgersReady() {
		return burgersReady;
	}

	public void setBurgersReady(boolean burgersReady) {
		this.burgersReady = burgersReady;
	}

	public boolean isFriesReady() {
		return friesReady;
	}

	public void setFriesReady(boolean friesReady) {
		this.friesReady = friesReady;
	}

	public boolean isCokeReady() {
		return cokeReady;
	}

	public void setCokeReady(boolean cokeReady) {
		this.cokeReady = cokeReady;
	}

	public boolean isSundaeReady() {
		return sundaeReady;
	}

	public void setSundaeReady(boolean sundaeReady) {
		this.sundaeReady = sundaeReady;
	}
	
}
