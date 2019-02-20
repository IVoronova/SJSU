package space;

import java.util.ArrayList;


public class Mission implements Comparable<Mission>
{
	private String			destination;
	private float			cost;
	
	
	public Mission(String destination, Float cost)
	{
		this.destination = destination;
		this.cost = cost;
	}
	
	
	public String getDestination()
	{
		return destination;
	}
	
	
	public float getCost()
	{
		return cost;
	}


	// Compare by cost, then by destination.
	public int compareTo(Mission that) 
	{ 		
		if(cost == that.getCost())
			return destination.compareTo(that.getDestination());
		
		return (int) Math.signum(cost - that.getCost());

	}
	
	
	// Let compareTo() do the work. This method should just be 1 line.
	public boolean equals(Object x)
	{
		return compareTo((Mission) x) == 0;
	}
	
	
	// As you saw in lecture, create an ArrayList<Object>. Add destination and
	// cost to the list. Return the list's hash code.
	public int hashCode()
	{
		ArrayList<Object> list = new ArrayList<>(); 
		list.add(cost);
		list.add(destination);
		return list.hashCode();
		
	}
	
	
	public String toString()
	{
		return "Mission to " + destination + " will cost " + cost;
	}
}
