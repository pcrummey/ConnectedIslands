/**
 * 
 */
package com.petercrummey.connectedislands.island;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author pcrummey
 *
 */
public class Island implements Comparable<Island>
{
	private Integer id;
	private List<Island> connections = new ArrayList<>();
	
	public Island(Integer id)
	{
		this.id = id;
	}

	public final List<Island> getConnections()
	{
		return Collections.unmodifiableList(this.connections);
	}
	
	/**
	 * Add an Island connection to this Island
	 * @param island Instance of Island to be added as a connection
	 * @return The current Island (i.e., <i>this</i>)
	 */
	public Island addConnection(Island island)
	{
		// An Island cannot have a connection to itself
		if(island.getId() == this.id)
		{
			System.out.println("WARNING: island " + this.id + " cannot be connected to itself.");
		} else {
			this.connections.add(island);
		}
		return this;
	}
	
	@Override
	public String toString()
	{
		String islandDetail = "Island " + this.id + " has immediate connections: ";
		for(Island connection : this.connections)
		{
			islandDetail = islandDetail + connection.getId() + " ";
		}
		return islandDetail;
	}

	public Integer getId()
	{
		return this.id;
	}

	/**
	 * Determine if this Island is connected to an Island with the given <i>id</i>
	 * @param id The identifier of the Island to be searched for
	 * @param visited A list of Islands that have already been visited
	 * @return True of this Island is connected to an Island with the given <i>id</i>
	 */
	public boolean isConnected(Integer id, List<Island> visited)
	{
		System.out.println("\tIs " + this.id + " connected to " + id + "?  Visited: " + visited.toString());
		if(this.id == id)
		{
			System.out.println("\t\tI'm the one you're looking for!");
			return true;
		} else {
			if(!visited.contains(this))  {  visited.add(this);  }
			for(Island anIsland : this.connections)
			{
				if(!visited.contains(anIsland)  &&  anIsland.isConnected(id, visited))
				{
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public int compareTo(Island compareIsland)
	{
		return this.id.compareTo(compareIsland.getId());
	}
}
