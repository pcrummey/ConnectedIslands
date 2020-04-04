package com.petercrummey.connectedislands.island.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.petercrummey.connectedislands.connections.Bridge;
import com.petercrummey.connectedislands.connections.Bridge.BridgeType;
import com.petercrummey.connectedislands.filters.BridgeFilters;
import com.petercrummey.connectedislands.filters.NoFilter;
import com.petercrummey.connectedislands.filters.NoTollBridgeFilter;
import com.petercrummey.connectedislands.island.Island;

class IslandTest {

	private Island island;
	
	@BeforeEach
	void setUp() throws Exception
	{
		island = new Island(0);
	}

	@Test
	void testAddConnection()
	{
		island.addConnection(new Bridge()
					  .setOrigin(island)
					  .setDestination(new Island(1))
					  .setType(BridgeType.FREE_BRIDGE));
		Integer one = 1;
		assertEquals(island.getConnections().size(), 1);
		assertEquals(island.getConnections().get(0).getDestination().getId(), one);
	}

	@Test
	void testGetId()
	{
		assertEquals(island.getId(), new Integer(0));
	}

	@Test
	void testIsConnected()
	{
		List<Island> visited = new ArrayList<>();
		// Test island with no connections
		assertFalse(island.isConnected(1, new BridgeFilters().addFilter(new NoFilter()), visited));

		Island islandOne = new Island(1);
		island.addConnection(new Bridge()
				  .setOrigin(island)
				  .setDestination(islandOne)
				  .setType(BridgeType.FREE_BRIDGE));
		System.out.println("#1");
		assertTrue(island.isConnected(0, new BridgeFilters().addFilter(new NoFilter()), visited));
		visited.clear();
		System.out.println("#2");
		assertTrue(island.isConnected(1, new BridgeFilters().addFilter(new NoFilter()), visited));
		visited.clear();
		System.out.println("#3");
		assertFalse(island.isConnected(2, new BridgeFilters().addFilter(new NoFilter()), visited));
		//---------------------------------------------------------------------
		// Test an island that is indirectly connected
		// 0 is connected to 1
		// 1 is connected to 2
		// 2 is connected to 3
		// ==> Test whether 0 is connected to 3
		Island islandTwo   = new Island(2);
		Island islandThree = new Island(3);
		
		islandOne.addConnection(new Bridge()
				  .setOrigin(islandOne)
				  .setDestination(islandTwo)
				  .setType(BridgeType.FREE_BRIDGE));
		islandTwo.addConnection(new Bridge()
				  .setOrigin(islandTwo)
				  .setDestination(islandThree)
				  .setType(BridgeType.FREE_BRIDGE));
		visited.clear();
		System.out.println("#4");
		assertTrue(island.isConnected(3, new BridgeFilters().addFilter(new NoFilter()), visited));
		//---------------------------------------------------------------------
		// So far we've only tested linear connections. Need to test the case
		// where an island has multiple connections and must iterate through
		// those connections to find an indirect connection.
		// 0 is connected to 1
		// 1 is connected to 2
		// 2 is connected to 3
		// 3 is connected to 4, 5 & 6
		// 6 is connected to 7
		// ==> Test whether 0 is connected to 7
		Island islandFour = new Island(4);
		Island islandSix  = new Island(6);
		
		islandThree.addConnection(new Bridge()
				  .setOrigin(islandThree)
				  .setDestination(islandFour)
				  .setType(BridgeType.FREE_BRIDGE));
		islandThree.addConnection(new Bridge()
				  .setOrigin(islandThree)
				  .setDestination(new Island(5))
				  .setType(BridgeType.FREE_BRIDGE));
		islandThree.addConnection(new Bridge()
				  .setOrigin(islandThree)
				  .setDestination(islandSix)
				  .setType(BridgeType.FREE_BRIDGE));
		
		Island islandSeven = new Island(7);
		islandSix.addConnection(new Bridge()
				  .setOrigin(islandSix)
				  .setDestination(islandSeven)
				  .setType(BridgeType.FREE_BRIDGE));
		visited.clear();
		System.out.println("#5");
		assertTrue(island.isConnected(7, new BridgeFilters().addFilter(new NoFilter()), visited));
		//---------------------------------------------------------------------
		// Need to test IslandFilters now. Change search so that toll bridges
		// are not taken.
		// 0 is connected to 1
		// 1 is connected to 2
		// 2 is connected to 3
		// 3 is connected to 4, 5 & 6
		// 6 is connected to 7, 8 (TOLL_BRIDGE)
		// ==> Test whether 0 is connected to 7
		
		Island islandEight = new Island(8);
		islandSix.addConnection(new Bridge()
				  .setOrigin(islandSix)
				  .setDestination(islandEight)
				  .setType(BridgeType.TOLL_BRIDGE));
		visited.clear();
		System.out.println("#7");
		assertFalse(island.isConnected(8, new BridgeFilters().addFilter(new NoTollBridgeFilter()), visited));
		//---------------------------------------------------------------------
		// Need to test IslandFilters now. Change search so that toll bridges
		// are not taken.
		// 0 is connected to 1
		// 1 is connected to 2
		// 2 is connected to 3
		// 3 is connected to 4, 5 & 6
		// 6 is connected to 7, 8 (TOLL_BRIDGE)
		// 7 is connected to 8 (FREE_BRIDGE)
		// ==> Test whether 0 is connected to 7
		
		islandSeven.addConnection(new Bridge()
				  .setOrigin(islandSeven)
				  .setDestination(islandEight)
				  .setType(BridgeType.FREE_BRIDGE));
		visited.clear();
		System.out.println("#8");
		assertTrue(island.isConnected(8, new BridgeFilters().addFilter(new NoTollBridgeFilter()), visited));
	}

}
