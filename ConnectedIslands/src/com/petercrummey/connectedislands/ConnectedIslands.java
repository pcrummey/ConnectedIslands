/**
 * 
 */
package com.petercrummey.connectedislands;

import java.util.ArrayList;
import java.util.List;

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
		
		areTheyConnected(0, 1);
		areTheyConnected(0, 2);
		areTheyConnected(0, 4);
		
		areTheyConnected(4, 5);
		areTheyConnected(7, 8);
		areTheyConnected(2, 3);
	}
	
	private static void areTheyConnected(int islandOne, int islandTwo)
	{
		List<Island> visited = new ArrayList<Island>();
		System.out.println("Island " + islandOne + " connected to " + islandTwo + "?");
		System.out.println(islands.find(islandOne).isConnected(islandTwo, visited));
	}

}
