package com.petercrummey.connectedislands.trip.test;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.petercrummey.connectedislands.connections.Bridge;
import com.petercrummey.connectedislands.connections.Bridge.BridgeType;
import com.petercrummey.connectedislands.island.Island;
import com.petercrummey.connectedislands.trip.Trip;

class TripTest
{
	private Trip trip;
	
	@BeforeEach
	void setUp() throws Exception
	{
		this.trip = new Trip();
	}

	@Test
	void testCost()
	{
		assertEquals(BigDecimal.ZERO, this.trip.getTotalCost());
		//---------------------------------------------------------------------
		BigDecimal costOne = new BigDecimal(2.51);
		this.trip.addCost(costOne);
		assertEquals(costOne, this.trip.getTotalCost());
		//---------------------------------------------------------------------
		BigDecimal costTwo = new BigDecimal(3.99);
		this.trip.addCost(costTwo);
		assertEquals(costOne.add(costTwo), this.trip.getTotalCost());
		//---------------------------------------------------------------------
		BigDecimal costThree = new BigDecimal(2.51);
		this.trip.addCost(costThree);
		assertEquals(costOne.add(costTwo.add(costThree)), this.trip.getTotalCost());
	}

	@Test
	void testRoutes()
	{
		Island islandZero = new Island(0);
		Island islandOne = new Island(1);
		//---------------------------------------------------------------------
		this.trip.clear();
		//---------------------------------------------------------------------
		this.trip.addBridgeToRoute(new Bridge()
				  .setOrigin(islandZero)
				  .setDestination(islandOne)
				  .setType(BridgeType.FREE_BRIDGE));
		assertEquals(1, this.trip.getRoute().size());
		//---------------------------------------------------------------------
		Island islandTwo = new Island(2);
		BigDecimal threeFiftyOne = new BigDecimal(3.51);
		this.trip.addBridgeToRoute(new Bridge()
				  .setOrigin(islandZero)
				  .setDestination(islandTwo)
				  .setType(BridgeType.TOLL_BRIDGE)
				  .setToll(threeFiftyOne));
		assertEquals(2, this.trip.getRoute().size());
		//---------------------------------------------------------------------
		assertEquals(islandZero, this.trip.getRoute().get(0).getOrigin());
		assertEquals(islandOne, this.trip.getRoute().get(0).getDestination());
		assertEquals(BridgeType.FREE_BRIDGE, this.trip.getRoute().get(0).getType());
		//---------------------------------------------------------------------
		assertEquals(islandZero, this.trip.getRoute().get(1).getOrigin());
		assertEquals(islandTwo, this.trip.getRoute().get(1).getDestination());
		assertEquals(BridgeType.TOLL_BRIDGE, this.trip.getRoute().get(1).getType());
		assertEquals(threeFiftyOne, this.trip.getRoute().get(1).getToll());
	}
}
