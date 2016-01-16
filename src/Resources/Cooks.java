package Resources;

public class Cooks {
	
	private static Cooks instance = null;
	private Cook[] cooks;
	
	private Cooks() {
	}

	/**
	 * @param numberOfCooks
	 */
	public void initialize(int numberOfCooks) {
		// TODO Auto-generated method stub
		cooks = new Cook[numberOfCooks];
		for (int i = 0; i < cooks.length; i++) {
			cooks[i] = new Cook(i);
		}
	}

	/**
	 * @return
	 */
	public static Cooks getStaticInstance() {
		// TODO Auto-generated method stub
		synchronized(Cooks.class) {  
			if(instance == null)          
				instance = new Cooks();  
	    }
		return instance;
	}
	
	public int getNumberOfCooks() {
		return cooks.length;
	}
	
	public void set(int index, Cook cook) {
		cooks[index] = cook;
	}
}
