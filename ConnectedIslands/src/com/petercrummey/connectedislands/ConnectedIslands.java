/**
 * 
 */
package com.petercrummey.connectedislands;

import com.petercrummey.connectedislands.filters.BridgeFilters;
import com.petercrummey.connectedislands.filters.BridgeFiltersFactory;
import com.petercrummey.connectedislands.filters.NoFilter;
import com.petercrummey.connectedislands.islands.Islands;
import com.petercrummey.connectedislands.islands.IslandsFactory;
import com.petercrummey.connectedislands.trip.Trip;

/**
 * @author pcrummey
 *
 */
public class ConnectedIslands {

	private static Islands islands;
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Trip trip = new Trip();
		islands = IslandsFactory.create();
		
		System.out.println(islands);
		
		System.out.println("========================================");
		
		areTheyConnected(0, 1, BridgeFiltersFactory.create(), trip);
		areTheyConnected(0, 2, BridgeFiltersFactory.create(), trip);
		areTheyConnected(0, 4, BridgeFiltersFactory.create(), trip);
		
		areTheyConnected(4, 5, BridgeFiltersFactory.create(), trip);
		areTheyConnected(7, 8, BridgeFiltersFactory.create(), trip);
		System.out.println("Same search - no filter");
		areTheyConnected(7, 8, new BridgeFilters().addFilter(new NoFilter()), trip);
		areTheyConnected(2, 3, BridgeFiltersFactory.create(), trip);
	}
	
	private static void areTheyConnected(int islandOne, int islandTwo, BridgeFilters filter, Trip trip)
	{
		System.out.println("Island " + islandOne + " connected to " + islandTwo + "?");
		System.out.println("**  " + islands.find(islandOne).isConnected(islandTwo, filter, trip) + "  **");
	}

}
