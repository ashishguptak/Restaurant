package Resources;

import Timer.*;
import Customer.*;

public class Table {
	
	public int tableId;		
	public boolean isOccupied;
	public boolean cookAssigned;
	public boolean foodServed;
	private DinerOrder order;
	public Cook cook;
	public Diner diner;
	public int timeBurgerMacihineWasUsed;
	public int timeFriesMachineWasUsed;
	public int timeSodaMachineWasUsed;
	public int timeSundaeMachineWasUsed;
	public int timeFoodBroughtToTable;
	
	/**
	 * @param tableId
	 */
	public Table(int tableId) {
		// TODO Auto-generated constructor stub
		this.tableId = tableId;
		this.isOccupied = false;
		this.cookAssigned = false;
		this.foodServed = false;
		this.order = null;
	}
	
	public void release() {
		this.isOccupied = false;
		this.cookAssigned = false;
		this.foodServed = false;
		this.order = null;
	}
	
	public DinerOrder getOrder() {
		return order;
	}

	public synchronized void setOrder(DinerOrder order) {
		this.order = order;
		notifyAll();
	}
	
	public synchronized void waitOnCookAssigned() {
		try {
			while(!cookAssigned)
				wait();
		} catch(InterruptedException ie) {}
	}
	
	public synchronized void assignCook(Cook cook) {
		this.cookAssigned = true;
		this.cook = cook;
		notifyAll();
	}
	
	public synchronized void waitOnOrder() {
		try {
			while(order == null)
				wait();
		} catch(InterruptedException ie) {}
	}
	
	public synchronized void waitOnFoodServed() {
		try {
			while(!foodServed)
				wait();
		} catch(InterruptedException ie) {}
	}
	
	public synchronized void serveFood() {
		foodServed = true;
		timeFoodBroughtToTable = Timer.getStaticInstance().getTime();
		notifyAll();
	}
}
