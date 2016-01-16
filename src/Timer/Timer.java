package Timer;

public class Timer {
	
	private static Timer timer = null;
	private int time;
	
	/**
	 * default constructor
	 */
	public Timer() {
		time = 0;
	}
	
	public int getTime() {
		return time;
	}

	/**
	 * @return
	 */
	public static Timer getStaticInstance() {
		// TODO Auto-generated method stub
		if(timer == null) {
			timer = new Timer();
		}
		return timer;
	}
	
	public void increment() {
		try {
			Thread.sleep(50);
		} catch(InterruptedException ie) {
			
		}
		time++;
	}
}
