package Timer;

import Resources.*;

public class TimeUsed {
	private int timeBurgerMacihineWasUsed;
	private int timeFriesMachineWasUsed;
	private int timeSodaMachineWasUsed;
	private int timeSundaeMachineWasUsed;
	/**
	 * 
	 */
	public void initializeTimeUsedValues() {
		this.timeFriesMachineWasUsed = -1;
		this.timeSodaMachineWasUsed = -1;
		this.timeSundaeMachineWasUsed = -1;
	}
	public int getTimeBurgerMacihineWasUsed() {
		return timeBurgerMacihineWasUsed;
	}
	public void setTimeBurgerMacihineWasUsed(int timeBurgerMacihineWasUsed) {
		this.timeBurgerMacihineWasUsed = timeBurgerMacihineWasUsed;
	}
	public int getTimeFriesMachineWasUsed() {
		return timeFriesMachineWasUsed;
	}
	public void setTimeFriesMachineWasUsed(int timeFriesMachineWasUsed) {
		this.timeFriesMachineWasUsed = timeFriesMachineWasUsed;
	}
	public int getTimeSodaMachineWasUsed() {
		return timeSodaMachineWasUsed;
	}
	public void setTimeSodaMachineWasUsed(int timeSodaMachineWasUsed) {
		this.timeSodaMachineWasUsed = timeSodaMachineWasUsed;
	}
	public int getTimeSundaeMachineWasUsed() {
		return timeSundaeMachineWasUsed;
	}
	public void setTimeSundaeMachineWasUsed(int timeSundaeMachineWasUsed) {
		this.timeSundaeMachineWasUsed = timeSundaeMachineWasUsed;
	}
	/**
	 * @param table
	 */
	public void updateTableTimeValues(Table table) {
		table.timeBurgerMacihineWasUsed = this.timeBurgerMacihineWasUsed;
		table.timeFriesMachineWasUsed = this.timeFriesMachineWasUsed;
		table.timeSodaMachineWasUsed = this.timeSodaMachineWasUsed;
		table.timeSundaeMachineWasUsed = this.timeSundaeMachineWasUsed;
	}
	
}
