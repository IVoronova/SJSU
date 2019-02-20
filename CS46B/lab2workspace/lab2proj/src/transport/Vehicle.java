package transport;

public class Vehicle {

	private int nWheels;
	
	private double xPosition; 			// X coordinate position of vehicle
	private double yPosition;			// Y coordinate position of vehicle
	
	
	public Vehicle( int nWheels) {
		this.nWheels = nWheels;
		//System.out.println("Vehicle ctor");
	}
	
	public void setPosition (double xPosition, double yPosition) { //sets the position of the rover at given x-y coordinates
		
		this.xPosition = xPosition;
		this.yPosition = yPosition;
		
	}
	
	public double getXPosition() { //Return current x position of the vehicle
		return xPosition;
	}
	
	public double getYPosition() { //Return current y position of the vehicle
		return yPosition;
	}
	
	public void changePositionBy( double xDelta, double yDelta) { //Sets new position by added xDelta & yDelta to current x and y positions respectively
		
		xPosition += xDelta;
		yPosition += yDelta;
	}
	
	

}
