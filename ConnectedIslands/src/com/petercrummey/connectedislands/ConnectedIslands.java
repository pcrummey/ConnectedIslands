/**
 * 
 */
package com.petercrummey.connectedislands;

import java.util.ArrayList;
import java.util.List;

import com.petercrummey.connectedislands.filters.BridgeFilters;
import com.petercrummey.connectedislands.filters.BridgeFiltersFactory;
import com.petercrummey.connectedislands.filters.NoFilter;
import com.petercrummey.connectedislands.island.Island;
import com.petercrummey.connectedislands.islands.Islands;
import com.petercrummey.connectedislands.islands.IslandsFactory;

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
		islands = IslandsFactory.create();
		
		System.out.println(islands);
		
		System.out.println("========================================");
		
		areTheyConnected(0, 1, BridgeFiltersFactory.create());
		areTheyConnected(0, 2, BridgeFiltersFactory.create());
		areTheyConnected(0, 4, BridgeFiltersFactory.create());
		
		areTheyConnected(4, 5, BridgeFiltersFactory.create());
		areTheyConnected(7, 8, BridgeFiltersFactory.create());
		System.out.println("Same search - no filter");
		areTheyConnected(7, 8, new BridgeFilters().addFilter(new NoFilter()));
		areTheyConnected(2, 3, BridgeFiltersFactory.create());
	}
	
	private static void areTheyConnected(int islandOne, int islandTwo, BridgeFilters filter)
	{
		List<Island> visited = new ArrayList<Island>();
		System.out.println("Island " + islandOne + " connected to " + islandTwo + "?");
		System.out.println("**  " + islands.find(islandOne).isConnected(islandTwo, filter, visited) + "  **");
	}

}
