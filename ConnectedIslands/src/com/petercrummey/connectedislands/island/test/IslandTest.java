package com.petercrummey.connectedislands.island.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
		island.addConnection(new Island(1));
		Integer one = 1;
		assertEquals(island.getConnections().size(), 1);
		assertEquals(island.getConnections().get(0).getId(), one);
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
		assertFalse(island.isConnected(1, visited));
		
		Island islandOne = new Island(1);
		island.addConnection(islandOne);
		assertTrue(island.isConnected(0, visited));
		visited.clear();
		assertTrue(island.isConnected(1, visited));
		visited.clear();
		assertFalse(island.isConnected(2, visited));
		//---------------------------------------------------------------------
		// Test an island that is indirectly connected
		// 0 is connected to 1
		// 1 is connected to 2
		// 2 is connected to 3
		// ==> Test whether 0 is connected to 3
		Island islandTwo   = new Island(2);
		Island islandThree = new Island(3);
		
		islandOne.addConnection(islandTwo);
		islandTwo.addConnection(islandThree);
		visited.clear();
		assertTrue(island.isConnected(3, visited));
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
		
		islandThree.addConnection(islandFour);
		islandThree.addConnection(new Island(5));
		islandThree.addConnection(islandSix);
		
		islandSix.addConnection(new Island(7));
		visited.clear();
		assertTrue(island.isConnected(7, visited));
	}

}
