package airlines;

import java.util.HashSet;
import java.util.List;

public class FlightNet extends HashSet<Airport>{

	public boolean nameIsAvailable(String name) {
		
		for(Airport a: this) {
			if(a.getName().equals(name))
				return false;
		}
		
		return true;
	}
	
	//
	// Connects 2 airports
	//
	public void connect(Airport a1, Airport a2) {
		a1.connectTo(a2);
		a2.connectTo(a1);
	}
	
	//
	// Disconnects 2 airports
	//
	public void disconnect(Airport a1, Airport a2) {
		a1.disconnectFrom(a2);
		a2.disconnectFrom(a1);
	}
	
	//
	// Removes an airport from the FlightNet as well as disconnects it from all the airports that are still
	// in the FlightNet
	//
	public void removeAndDisconnect(Airport removeMe) {
		
		remove(removeMe);
		List<Airport> connections = removeMe.getConnections();
		for(Airport a: connections) {
			if(contains(a)) //if the connection is in this FlightNet the airport is disconnected from removeMe
				disconnect(a, removeMe);
		}
	}
	
	//
	// Returns an airport whose coordinates are with in maximum distance of given x and y
	// return null if no airport is in the given parameters
	//
	public Airport getAirportNearXY(int x, int y, int maximumDistance) {
		
		for(Airport a: this) {
			if(Math.hypot(a.getX() - x, a.getY() - y) <= maximumDistance)
				return a;
			
		}
		return null;
	}
}
