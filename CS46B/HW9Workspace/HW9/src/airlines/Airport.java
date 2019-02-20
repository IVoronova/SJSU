package airlines;

import java.util.*;


public class Airport implements Comparable<Airport>
{
	private String					name;
	private int						x;
	private int						y;
	private Set<Airport>			connections;	// all airports with a direct route to/from this airport
	
	
	public Airport(String name, int x, int y)
	{
		this.name = name;
		this.x = x;
		this.y = y;
		connections = new TreeSet<>();
	}
	
	
	public String getName()
	{
		return name;
	}
	
	
	public int getX()
	{
		return x;
	}
	
	
	public int getY()
	{
		return y;
	}
	
	//
	// Returns ArrayList of all the Connections
	//
	public List<Airport> getConnections()
	{
		return new ArrayList<>(connections);
	}
	
	//
	// Adds that airport to the list of connections. This is a one-way route
	//
	public void connectTo(Airport that)
	{
		connections.add(that);
	}
	
	
	//
	// Removes "that" Airport of connections
	// Does nothing if this airport is not connected to that.
	//
	public void disconnectFrom(Airport that)
	{
		if(connections.contains(that))
			connections.remove(that);
		
	}
	
	
	
	public boolean equals(Object x)
	{
		return compareTo((Airport) x) == 0;
	}
	
	
	
	public int compareTo(Airport that)
	{
		return name.compareTo(that.getName());
	}
	
	//
	// Returns true if this airport has the "that" airport in connections
	//
	public boolean isConnectedTo(Airport that)
	{
		return connections.contains(that);
	}
	
	
	public String toString()
	{
		return "Airport " + name + " @(" + x + "," + y + ")";
	}
}
