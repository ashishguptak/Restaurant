package Customer;

import Timer.*;


public class Diners {
	
	private static Diners instance = null;
	private Diner[] diners;
	private int numberOfCurrentDiners;
	
	/**
	 * @param numberOfDiners
	 */
	public Diners() {
		// TODO Auto-generated constructor stub
		this.numberOfCurrentDiners = 0;
	}

	/**
	 * @param numberOfDiners
	 */
	public void initialize(int numberOfDiners) {
		diners = new Diner[numberOfDiners];
	}

	/**
	 * @return
	 */
	public static Diners getStaticInstance() {
		if(instance == null) {
			instance = new Diners();
		}
		return instance;
	}
	
	public int getNumberOfCurrentDiners() {
		return this.numberOfCurrentDiners;
	}
	
	public int getNumberOfDiners() {
		return diners.length;
	}
	
	public Diner getDiner(int index) {
		if(index < diners.length)
			return diners[index];
		else
			return null;
	}
	
	public void addDiner(int index, Diner diner) {
		diners[index] = diner;
	}
	
	public void startDinersArrivedNow() {
		for(int i=0; i<diners.length; i++) {
			if(diners[i].getArrivalTime() == Timer.getStaticInstance().getTime()) {
				diners[i].dinerEnterRestaurant();
				System.out.println("Time : "+Timer.getStaticInstance().getTime()+"\t"+"Diner Number - " + i + " arrived.");
				this.numberOfCurrentDiners++;
			}
		}
	}
	
	public void leaveRestaurant() {
		this.numberOfCurrentDiners--;
	}
	
	public boolean isEarliest(int dinerId) {
		for(int i=0; i<diners.length; i++) {
			if(diners[i].isInRestaurant() && diners[i].getSeatingTime() == -1) {
				if(dinerId == i)
					return true;
				else
					return false;
			}
		}
		return false;
	}
}
