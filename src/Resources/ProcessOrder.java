package Resources;

import Customer.*;
import Timer.*;

public class ProcessOrder {
	public int timer;
	public boolean isOccupied;
	private int Id;
	
	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public ProcessOrder() {
		isOccupied = false;
    }
	
	synchronized public void occupy() {
		this.isOccupied = true;
	}
	
	synchronized public boolean isOccupied() {
		return isOccupied;
	}
	
	public void prepare(DinerOrder order, Cook cook) {
		int startTime = Timer.getStaticInstance().getTime();
		int cookingFinishTime = 0;
		switch(Id) {
		case 0:
			cookingFinishTime = startTime + order.getItemCount().getNumberOfBurgers() * timer;
			cook.getTimeUsed().setTimeBurgerMacihineWasUsed(startTime);
			order.getItemReady().setBurgersReady(true);;
			break;
		case 1:
			cookingFinishTime = startTime + order.getItemCount().getNumberOfFries() * timer;
			cook.getTimeUsed().setTimeFriesMachineWasUsed(startTime);
			order.getItemReady().setFriesReady(true);
			break;
		case 2:
			cookingFinishTime = startTime + order.getItemCount().getNumberOfCokes() * timer;
			cook.getTimeUsed().setTimeSodaMachineWasUsed(startTime);
			order.getItemReady().setCokeReady(true);
			break;
		case 3:
			cookingFinishTime = startTime + order.getItemCount().getNumberOfSundae() * timer;
			cook.getTimeUsed().setTimeSundaeMachineWasUsed(startTime);
			order.getItemReady().setSundaeReady(true);
			break;
		}

		while(Timer.getStaticInstance().getTime() < cookingFinishTime) {
			//cooking
			try {
				synchronized(Timer.getStaticInstance()) {
					Timer.getStaticInstance().wait();
				}
			} catch(InterruptedException ie) {}
		}
		this.isOccupied = false;
		
	}
}
