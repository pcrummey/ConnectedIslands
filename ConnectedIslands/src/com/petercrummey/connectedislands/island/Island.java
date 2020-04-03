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
public class Island
{
	private int id;
	private List<Island> connections = new ArrayList<>();
	
	public Island(int id)
	{
		this.id = id;
	}

	public final List<Island> getConnections()
	{
		return Collections.unmodifiableList(this.connections);
	}
	
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
		String islandDetail = "Island " + this.id + " has immediate connections:\n";
		for(Island connection : this.connections)
		{
			islandDetail = islandDetail + "\t" + connection.getId() + "\n";
		}
		return islandDetail;
	}

	public int getId()
	{
		return this.id;
	}

	public boolean isConnected(int id, List<Island> visited)
	{
		System.out.println("\tIs " + this.id + " connected to " + id + "?");
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
}
