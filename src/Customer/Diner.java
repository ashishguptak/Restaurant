package Customer;

import Resources.*;
import Timer.*;
import Output.*;

public class Diner implements Runnable{
	
	private int dinerId;
	private int arrivalTime;		
	private int seatingTime;
	private int servedTime;
	private Table seatedTable;	
	private DinerOrder order;			 
	private Cook cook;
	private boolean inRestaurant;
	private Thread t;			
	
	/**
	 * @param arrivalTimeStamp
	 * @param newDinerOrder
	 * @param orderNumber
	 */
	public Diner(int arrivalTimeStamp, DinerOrder newDinerOrder, int orderNumber) {
		// TODO Auto-generated constructor stub
		this.dinerId = orderNumber;
		this.arrivalTime = arrivalTimeStamp;
		this.seatingTime = -1;
		this.order = newDinerOrder;
		this.inRestaurant = false;
		t = new Thread(this, "Diner Number - "+this.dinerId);
	}
	
	
	public int getDinerId() {
		return dinerId;
	}

	public int getArrivalTime() {
		return arrivalTime;
	}

	public int getSeatingTime() {
		return seatingTime;
	}

	public int getServedTime() {
		return servedTime;
	}

	public DinerOrder getOrder() {
		return order;
	}

	public boolean isInRestaurant() {
		return inRestaurant;
	}

	public void dinerEnterRestaurant() {
		inRestaurant = true;
		t.start();
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		OutputLogger output = OutputLogger.getStaticInstance();
		DinerArrival dinerEntry = output.getOutputData()[dinerId];
		seatedTable = Tables.getStaticInstance().getTableForDiner(this);
		seatingTime = Timer.getStaticInstance().getTime();
		System.out.println("Time : "+Timer.getStaticInstance().getTime()+"\t"+Thread.currentThread().getName() + " is seated on Table-" + seatedTable.tableId);
		seatedTable.setOrder(this.order);
		seatedTable.waitOnCookAssigned();
		cook = seatedTable.cook;
		while(cook==null){
			;
		}
		//System.out.println("Cook - " + cook.getId());
		dinerEntry.cookNumber = cook.getId();
		seatedTable.waitOnFoodServed();
		
		dinerEntry.arrivalTime = arrivalTime;
		dinerEntry.seatingTime = seatingTime;
		dinerEntry.tableNumber = seatedTable.tableId;
		
		this.servedTime = Timer.getStaticInstance().getTime();
		System.out.println("Time : "+Timer.getStaticInstance().getTime()+"\t"+Thread.currentThread().getName() + " Started Eating");
		while (Timer.getStaticInstance().getTime() < servedTime + 30) {
			// eating
			try {
				synchronized(Timer.getStaticInstance()) {
					Timer.getStaticInstance().wait();
				}
			} catch(InterruptedException ie) {}
		}

		dinerEntry.burgerMachineUsedTime = seatedTable.timeBurgerMacihineWasUsed;
		dinerEntry.friesMachineUsedTime = seatedTable.timeFriesMachineWasUsed;
		dinerEntry.sodaMachineUsedTime = seatedTable.timeSodaMachineWasUsed;
		dinerEntry.sundaeMachineUsedTime = seatedTable.timeSundaeMachineWasUsed;
		dinerEntry.foodServedTime = seatedTable.timeFoodBroughtToTable;
		
		Tables.getStaticInstance().releaseTable(seatedTable.tableId);
		dinerEntry.timeOfLeaving = Timer.getStaticInstance().getTime();
		leave();
	}
	
	public void leave() {
		inRestaurant = false;
		synchronized(Diners.getStaticInstance()) {
			Diners.getStaticInstance().leaveRestaurant();}
		synchronized(Tables.getStaticInstance()) {
			Tables.getStaticInstance().notifyAll(); }
	}
}
