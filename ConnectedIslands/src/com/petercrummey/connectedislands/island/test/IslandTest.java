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

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testAddConnection()
	{
		Island island = new Island(0);
		island.addConnection(new Island(1));
		
		assertEquals(island.getConnections().size(), 1);
		assertEquals(island.getConnections().get(0).getId(), 1);
	}

	@Test
	void testGetId()
	{
		Island island = new Island(0);
		assertEquals(island.getId(), 0);
	}

	@Test
	void testIsConnected()
	{
		Island island = new Island(0);
		island.addConnection(new Island(1));
		
		List<Island> visited = new ArrayList<>();
		assertTrue(island.isConnected(0, visited));
		assertTrue(island.isConnected(1, visited));
		assertFalse(island.isConnected(2, visited));
	}

}
