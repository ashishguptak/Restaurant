package Resources.Machines;

import Customer.*;
import Timer.*;
import Resources.*;

public class VendingMachine {
	private final int MAX_TIME=120;
	private static VendingMachine instance = null;
	private BurgerMachine burgerMachine;
	private FriesMachine friesMachine;
	private CokeMachine cokeMachine;
	private SundaeMachine sundaeMachine;
	
	private VendingMachine() {
		burgerMachine = new BurgerMachine(5);
		friesMachine = new FriesMachine(3);
		cokeMachine  = new CokeMachine(2);
		sundaeMachine = new SundaeMachine(1);
	}
	
	public static VendingMachine getInstance() {
		if(instance == null) {
			instance = new VendingMachine();
		}
		return instance;
	}
	
	public ProcessOrder getMachineFor(DinerOrder order) {
		while(Timer.getStaticInstance().getTime() <=MAX_TIME || Diners.getStaticInstance().getNumberOfCurrentDiners() > 0) {
			if(!order.getItemReady().isBurgersReady()) {
				if(!burgerMachine.isOccupied()) {
					synchronized(burgerMachine) {
						if(!burgerMachine.isOccupied()) {		
							burgerMachine.occupy();
							//System.out.println("Time : "+Timer.getStaticInstance().getTime()+" Burger machine being alloted to : "+Thread.currentThread().getName());
							return burgerMachine;
						}
					}
				}
			}
			if(!order.getItemReady().isFriesReady()) {
				if(!friesMachine.isOccupied()) {
					synchronized(friesMachine) {
						if(!friesMachine.isOccupied()) {	
							friesMachine.occupy();
							//System.out.println("Time : "+Timer.getStaticInstance().getTime()+" Fries machine being alloted to : "+Thread.currentThread().getName());
							return friesMachine;
						}
					}
				}
			}
			if(!order.getItemReady().isCokeReady()) {
				if(!cokeMachine.isOccupied()) {
					synchronized(cokeMachine) {
						if(!cokeMachine.isOccupied()) {	
							cokeMachine.occupy();
							//System.out.println("Time : "+Timer.getStaticInstance().getTime()+" Coke machine being alloted to : "+Thread.currentThread().getName());
							return cokeMachine;
						}
					}
				}
			}
			if(!order.getItemReady().isSundaeReady()) {
				if(!sundaeMachine.isOccupied()) {
					synchronized(sundaeMachine) {
						if(!sundaeMachine.isOccupied()) {	
							sundaeMachine.occupy();
							//System.out.println("Time : "+Timer.getStaticInstance().getTime()+" Sundae machine being alloted to : "+Thread.currentThread().getName());
							return sundaeMachine;
						}
					}
				}
			}
		}	
		return null;
	}
}
