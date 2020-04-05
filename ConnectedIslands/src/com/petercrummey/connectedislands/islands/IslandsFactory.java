/**
 * 
 */
package com.petercrummey.connectedislands.islands;

import java.math.BigDecimal;

import com.petercrummey.connectedislands.connections.Bridge;
import com.petercrummey.connectedislands.connections.Bridge.BridgeType;
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
		
		//---------------------------------------------------------------------
		// 0 connected to 1, 3
		//---------------------------------------------------------------------
		islands.find(0)
	       .addConnection(new Bridge()
					  .setOrigin(islands.find(0))
					  .setDestination(islands.find(1))
					  .setType(BridgeType.FREE_BRIDGE))
	       .addConnection(new Bridge()
					  .setOrigin(islands.find(0))
					  .setDestination(islands.find(3))
					  .setType(BridgeType.FREE_BRIDGE));
		//---------------------------------------------------------------------
		// 1 connected to 0, 2, 3
		//---------------------------------------------------------------------
		islands.find(1)
	       .addConnection(new Bridge()
					  .setOrigin(islands.find(1))
					  .setDestination(islands.find(0))
					  .setType(BridgeType.FREE_BRIDGE))
	       .addConnection(new Bridge()
					  .setOrigin(islands.find(1))
					  .setDestination(islands.find(2))
					  .setType(BridgeType.FREE_BRIDGE))
	       .addConnection(new Bridge()
					  .setOrigin(islands.find(1))
					  .setDestination(islands.find(3))
					  .setType(BridgeType.FREE_BRIDGE));
		//---------------------------------------------------------------------
		// 2 connected to 1
		//---------------------------------------------------------------------
		islands.find(2)
	       .addConnection(new Bridge()
					  .setOrigin(islands.find(2))
					  .setDestination(islands.find(1))
					  .setType(BridgeType.FREE_BRIDGE));
		//---------------------------------------------------------------------
		// 3 connected to 0, 1
		//---------------------------------------------------------------------
		islands.find(3)
	       .addConnection(new Bridge()
					  .setOrigin(islands.find(3))
					  .setDestination(islands.find(0))
					  .setType(BridgeType.FREE_BRIDGE))
	       .addConnection(new Bridge()
					  .setOrigin(islands.find(3))
					  .setDestination(islands.find(1))
					  .setType(BridgeType.FREE_BRIDGE));
		//---------------------------------------------------------------------
		// 4 connected to 5
		//---------------------------------------------------------------------
		islands.find(4)
	       .addConnection(new Bridge()
					  .setOrigin(islands.find(4))
					  .setDestination(islands.find(5))
					  .setType(BridgeType.FREE_BRIDGE));
		//---------------------------------------------------------------------
		// 5 connected to 4
		//---------------------------------------------------------------------
		islands.find(5)
	       .addConnection(new Bridge()
					  .setOrigin(islands.find(5))
					  .setDestination(islands.find(4))
					  .setType(BridgeType.FREE_BRIDGE));
		//---------------------------------------------------------------------
		// 6 connected to 7, 8 (Toll Bridge)
		//---------------------------------------------------------------------
		islands.find(6)
	       .addConnection(new Bridge()
					  .setOrigin(islands.find(6))
					  .setDestination(islands.find(7))
					  .setType(BridgeType.FREE_BRIDGE))
	       .addConnection(new Bridge()
					  .setOrigin(islands.find(6))
					  .setDestination(islands.find(8))
					  .setType(BridgeType.TOLL_BRIDGE)
					  .setToll(new BigDecimal(2.50)));
		//---------------------------------------------------------------------
		// 7 connected to 6
		//---------------------------------------------------------------------
		islands.find(7)
	       .addConnection(new Bridge()
					  .setOrigin(islands.find(7))
					  .setDestination(islands.find(6))
					  .setType(BridgeType.FREE_BRIDGE));
		//---------------------------------------------------------------------
		// 8 connected to 6
		//---------------------------------------------------------------------
		islands.find(8)
	       .addConnection(new Bridge()
					  .setOrigin(islands.find(8))
					  .setDestination(islands.find(6))
					  .setType(BridgeType.FREE_BRIDGE));

		return islands;
	}
}
