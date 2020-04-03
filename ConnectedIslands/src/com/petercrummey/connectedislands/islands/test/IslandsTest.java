package com.petercrummey.connectedislands.islands.test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.petercrummey.connectedislands.island.Island;
import com.petercrummey.connectedislands.islands.Islands;

class IslandsTest
{
	Islands islands;
	
	@BeforeEach
	void setUp() throws Exception
	{
		islands = new Islands();
		islands.addIsland(new Island(0));
	}

	@Test
	void testAddIsland()
	{
		assertEquals(islands.getIslands().size(), 1);
		assertEquals(islands.getIslands().get(0).getId(), 0);
		//---------------------------------------------------------------------
		// Test duplicate Island
		//---------------------------------------------------------------------
		Exception ex = assertThrows(IllegalArgumentException.class, () ->
		{
			islands.addIsland(new Island(0));
		});
		assertEquals("Cannot add duplicate island 0", ex.getMessage());
	}

	@Test
	void testFind()
	{
		assertNotNull(islands.find(0));
		//---------------------------------------------------------------------
		// Test an out of bounds index
		//---------------------------------------------------------------------
		Exception ex = assertThrows(IndexOutOfBoundsException.class, () ->
		{
			islands.find(1);
		});
		assertEquals("There is no Island 1", ex.getMessage());
	}

}
