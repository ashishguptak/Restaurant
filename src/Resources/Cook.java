package Resources;

import Timer.*;
import Customer.*;
import Resources.Machines.*;

public class Cook implements Runnable{
	
	private int cookId;		
	private Table tableServing;
	private DinerOrder order;
	private Thread th;
	public TimeUsed timeUsed = new TimeUsed();
	
	public Cook(int cookId) {
		this.cookId = cookId;
		th = new Thread(this, "Cook - "+this.cookId);
		th.start();
	}
	
	public int getId() {
		return this.cookId;
	}
	
	public void run() {
		while(Timer.getStaticInstance().getTime() <= 120 || Diners.getStaticInstance().getNumberOfCurrentDiners() > 0) {
				tableServing = Tables.getStaticInstance().getTableForCook();
				if(tableServing != null) {
					populateTimeUsed();
					tableServing.assignCook(this);
					tableServing.waitOnOrder();
					order = tableServing.getOrder();
					VendingMachine machine = VendingMachine.getInstance();
					while(!order.isComplete()) {
						ProcessOrder fooditem = machine.getMachineFor(order);
						fooditem.prepare(order, this);
					}
					populateTimeUsed(this.tableServing);
					tableServing.serveFood();
				}
		}
	}

	/**
	 * @param table
	 */
	private void populateTimeUsed(Table table) {
		this.timeUsed.updateTableTimeValues(table);
	}

	/**
	 * 
	 */
	private void populateTimeUsed() {
		this.timeUsed.initializeTimeUsedValues();
	}
	
	public TimeUsed getTimeUsed() {
		return timeUsed;
	}

	public void setTimeUsed(TimeUsed timeUsed) {
		this.timeUsed = timeUsed;
	}
}
