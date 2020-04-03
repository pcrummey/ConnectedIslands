/**
 * 
 */
package com.petercrummey.connectedislands.islands;

import com.petercrummey.connectedislands.island.Island;

/**
 * @author pcrummey
 *
 */
public class IslandsFactory
{

	public static Islands create()
	{
		Islands islands = new Islands();
		islands.addIsland(new Island(0));
		
		islands.addIsland(new Island(1));
		islands.addIsland(new Island(2));
		islands.addIsland(new Island(3));
		
		islands.addIsland(new Island(4));
		islands.addIsland(new Island(5));

		islands.addIsland(new Island(6));
		islands.addIsland(new Island(7));
		islands.addIsland(new Island(8));
		
		islands.find(0)
	       .addConnection(islands.find(1))
	       .addConnection(islands.find(3));
		islands.find(1)
	       .addConnection(islands.find(0))
	       .addConnection(islands.find(2))
	       .addConnection(islands.find(3));
		islands.find(2)
	       .addConnection(islands.find(1));
		islands.find(3)
	       .addConnection(islands.find(0))
	       .addConnection(islands.find(1));

		islands.find(4)
	       .addConnection(islands.find(5));
		islands.find(5)
	       .addConnection(islands.find(4));

		islands.find(6)
	       .addConnection(islands.find(7))
	       .addConnection(islands.find(8));
		islands.find(7)
	       .addConnection(islands.find(6));
		islands.find(8)
	       .addConnection(islands.find(6));

		return islands;
	}
}
