/**
 * 
 */
package com.petercrummey.connectedislands.island;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.petercrummey.connectedislands.connections.Bridge;
import com.petercrummey.connectedislands.filters.BridgeFilters;
import com.petercrummey.connectedislands.trip.Trip;

/**
 * @author Peter Crummey <peter@rose-reid.com>
 *
 */
public class Island implements Comparable<Island>
{
	private Integer id;
	private List<Bridge> connections = new ArrayList<>();
	private static Trip trip = new Trip();
	
	public Island(Integer id)
	{
		this.id = id;
	}

	public final List<Bridge> getConnections()
	{
		return Collections.unmodifiableList(this.connections);
	}

	/**
	 * Add an Island connection to this Island
	 * @param bridge The bridge that exists between this island and another
	 * @return The current Island (i.e., <i>this</i>)
	 */
	public Island addConnection(Bridge bridge)
	{
		// An Island cannot have a connection to itself
		if(bridge.getDestination().getId() == this.id)
		{
			System.out.println("WARNING: island " + this.id + " cannot be connected to itself.");
		} else {
			this.connections.add(bridge);
		}
		return this;
	}
	
	@Override
	public String toString()
	{
		String islandDetail = "Island " + this.id + " has immediate connections: ";
		for(Bridge connection : this.connections)
		{
			islandDetail = islandDetail + connection.getDestination().getId() + " " + connection.getType() + " ";
		}
		return islandDetail;
	}

	public Integer getId()
	{
		return this.id;
	}

	public Trip getTrip()
	{
		return trip;
	}

	/**
	 * Determine if this Island is connected to an Island with the given <i>id</i>
	 * @param id The identifier of the Island to be searched for
	 * @param filters BridgeFilters to apply to each bridge 
	 * @return True of this Island is connected to an Island with the given <i>id</i>
	 */
	public boolean isConnected(Integer id, BridgeFilters filters)
	{
		trip.clear();
		return this.isConnected(id, filters, new ArrayList<>());
	}
	
	/**
	 * Determine if this Island is connected to an Island with the given <i>id</i>
	 * @param id The identifier of the Island to be searched for
	 * @param filters BridgeFilters to apply to each bridge 
	 * @param visited A list of Islands that have already been visited
	 * @return True of this Island is connected to an Island with the given <i>id</i>
	 */
	private boolean isConnected(Integer id, BridgeFilters filters, List<Island> visited)
	{
		System.out.println("\tIs " + this.id + " connected to " + id + "?  Visited: " + visited.toString());
		if(this.id == id)
		{
			System.out.println("\t\tI'm the one you're looking for!");
			return true;
		}
		if(visited.stream().allMatch(matchIsland -> matchIsland.getId() != this.getId()))  {  visited.add(this);  }
		for(Bridge bridge : this.connections)
		{
			if(!filters.apply(bridge))  {  System.out.println("\t\tBridge exists but was filtered out: " + bridge);  }
			if(!visited.contains(bridge.getDestination())  &&  filters.apply(bridge)  &&  bridge.getDestination().isConnected(id, filters, visited))
			{
				trip.addBridgeToRoute(bridge);
				trip.addCost(bridge.getBridgeCrossingCost());
				return true;
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
