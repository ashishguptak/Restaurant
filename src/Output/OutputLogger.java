package Output;

import Customer.DinerArrival;

public class OutputLogger {
	private static OutputLogger instance;
	private DinerArrival[] outputData;
	
	private OutputLogger() {
		
	}
	
	/**
	 * @param numberOfDiners
	 */
	public void initialize(int numberOfDiners) {
		outputData = new DinerArrival[numberOfDiners];
		for(int i=0; i<outputData.length; i++) {
			outputData[i] = new DinerArrival();
		}
	}
	
	/**
	 * @return
	 */
	public static OutputLogger getStaticInstance() {
		if(instance == null) {
			instance = new OutputLogger();
		}
		return instance;
	}

	public DinerArrival[] getOutputData() {
		return outputData;
	}
}