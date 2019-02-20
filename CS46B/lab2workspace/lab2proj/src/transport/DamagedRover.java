package transport;

public class DamagedRover extends MarsRover {
	private final static int MAX_TRAVEL_METERS_BEFORE_EMPTY_BATTERY = 10000;
	private final static int METERS_FROM_START_TO_CLIFF = 1000;
	private final static int N_SIMULATIONS = 5000;
	
	private double metersTraveled; 		// Total meters traveled
										// back and forth.
	private boolean fell; 				// If true, an expensive loss.
	

	
	/*
	 	Simulates travel under damage conditions. In each turn, travels forward or
	 	backward either 1, 2, 3, or 4 meters. Continues until there's no more power
	 	in the battery, or we fall off a cliff. Cliffs are at position = 1000 or
		position = -1000.
	*/
	public DamagedRover() {
		super();
	}
	
	
	private void move( double distance, boolean forward) {
		
		 if(forward) {
			 changePositionBy(distance, 0);
		 }
		 else {
			changePositionBy(-distance, 0);
		 }
	}
	
	public void simulateStormDamageTravel()
	{
		setPosition(0,0);
		while (metersTraveled < MAX_TRAVEL_METERS_BEFORE_EMPTY_BATTERY && !fell)
		{
			double distanceNextTurn = (int)(1 + 4*Math.random());
			boolean forwardNotBack = (Math.random() > 0.5);
			
			move(distanceNextTurn, forwardNotBack);
			
					metersTraveled += distanceNextTurn;
			// Check for falling off cliff. If Rover fell, set fell to true and
			// terminate (break out of) the loop.
			if (getXPosition() == 1000|| getXPosition() == -1000)
			{
				break;
				//TERMINATE THE LOOP
	 		}
	 	}
	 }
	public double getMetersTraveled() {
		return metersTraveled;
	}
	public boolean getFell() {
		return fell;
	}
	public static void main(String[] args) {
		DamagedRover r= new DamagedRover();
		for(int i=0;i<N_SIMULATIONS;i++) {
		r.simulateStormDamageTravel();
		if(r.getFell()) {
			System.out.println("fell");
			System.out.println(r.getMetersTraveled());
		}
		else {
			System.out.println("No more power");
			}
		}
	}
}

