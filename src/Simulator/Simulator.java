package Simulator;

import Resources.*;
import Customer.*;
import Timer.*;
import Output.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Simulator {
	
	private final int MAX_TIME=120;
	private Diners diners;
	private Cooks cooks;
	private Tables tables;
	private Timer timer;
	private OutputLogger outputLogger;

	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Input file name argument is missing. Please run in the following format : " +
					"java Simulator <InputFile name>");
			System.exit(0);
		}
		Simulator restaurant = parseInput(args[0]);
		restaurant.runSimulation();
		restaurant.printOutput();
		restaurant.printOutputToFile("output.txt");
	}

	/**
	 * @param string
	 */
	private static Simulator parseInput(String inputFilename) {
		// TODO Auto-generated method stub
		Simulator restaurant = null;
		try{
			FileReader inputFile = new FileReader(inputFilename);
			BufferedReader inputReader = new BufferedReader(inputFile);
			restaurant = new Simulator(Integer.parseInt(inputReader.readLine().trim()),
					Integer.parseInt(inputReader.readLine().trim()), Integer.parseInt(inputReader.readLine().trim()));
			String line;
			int orderNumber = 0;
			while((line = inputReader.readLine()) != null){
				String orderLine[] = line.split("\\s+");
				int arrivalTimeStamp = Integer.parseInt(orderLine[0].trim());
				DinerOrder newDinerOrder = new DinerOrder(Integer.parseInt(orderLine[1].trim()), 
						Integer.parseInt(orderLine[2].trim()), Integer.parseInt(orderLine[3].trim()), Integer.parseInt(orderLine[4].trim()));
				restaurant.diners.addDiner(orderNumber, new Diner(arrivalTimeStamp,newDinerOrder,orderNumber));
				orderNumber++;
			}
			inputReader.close();
		}catch(Exception ex){
			System.out.println("Issue with opening/reading the input file.");
		}
		return restaurant;
	}

	private void runSimulation() {
		// TODO Auto-generated method stub
		while(timer.getTime() <= MAX_TIME || Diners.getStaticInstance().getNumberOfCurrentDiners() > 0) {
			diners.startDinersArrivedNow();
			timer.increment(); 
			synchronized(timer) {
				timer.notifyAll();
			}
		}
	}
	
	public void printOutputToFile(String outputFilename) {
		try {
			File file = new File("output.txt");
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter fw;

			fw = new FileWriter(file.getAbsoluteFile());

			BufferedWriter bw = new BufferedWriter(fw);
			String str;
			str = "Diner\t" + "Arrived" + "Seating\t" + "Table ID" + "Cook Num\t" + "Food\t" + "Leaving\t" + "BTime\t"
					+ "FTime\t" + "CTime\t" + "STime";
			// System.out.println(str);
			bw.write(str);
			bw.write("\n");
			DinerArrival[] entry = outputLogger.getOutputData();
			for (int i = 0; i < entry.length; i++) {
				str = i + "\t" + entry[i].toString();
				// System.out.println(str);
				bw.write(str);
				bw.write("\n");
			}
			bw.close();
		} catch (IOException e) {
			System.out.println("Problem in outputing code to file!");
			e.printStackTrace();
		} 
	}
	
	/**
	 * return null
	 */
	private void printOutput() {
		String str;
		str = "Diner\t" +"Arrived" + "Seating\t" +"Table ID" +"Cook Num\t" +"Food\t" + 
				"Leaving\t"+"BTime\t"+"FTime\t"+"CTime\t"+"STime";
		System.out.println(str);
		DinerArrival[] entry = outputLogger.getOutputData();
		for(int i=0; i<entry.length; i++) {
			str = 	i + "\t" + entry[i].toString();
			System.out.println(str);
		}
	}
	/**
	 * @param numberOfDiners
	 * @param numberOfCooks
	 * @param numberOfTables
	 */
	public Simulator(int numberOfDiners, int numberOfTables, int numberOfCooks) {
		// TODO Auto-generated constructor stub
		timer = Timer.getStaticInstance();
		tables = Tables.getStaticInstance();
		tables.initialize(numberOfTables);
		diners = Diners.getStaticInstance();
		diners.initialize(numberOfDiners);
		cooks = Cooks.getStaticInstance();
		cooks.initialize(numberOfCooks);
		outputLogger = OutputLogger.getStaticInstance();
		outputLogger.initialize(numberOfDiners);
	}
}
