package com.petercrummey.connectedislands.trip;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.petercrummey.connectedislands.connections.Bridge;

/**
 * @author Peter Crummey <peter@rose-reid.com>
 *
 */
public class Trip
{
	private BigDecimal totalCost = BigDecimal.ZERO;
	private List<Bridge> route = new ArrayList<>();

	public BigDecimal getTotalCost()
	{
		return totalCost;
	}

	public Trip addCost(BigDecimal cost)
	{
		this.totalCost = this.totalCost.add(cost);
		return this;
	}
	
	public Trip addBridgeToRoute(Bridge bridge)
	{
		this.route.add(bridge);
		return this;
	}
	
	public final List<Bridge> getRoute()
	{
		return Collections.unmodifiableList(this.route);
	}
	
	public Trip clear()
	{
		this.totalCost = BigDecimal.ZERO;
		this.route.clear();
		return this;
	}
	
	@Override
	public String toString()
	{
		return "Total cost: " + this.totalCost.setScale(2, RoundingMode.CEILING) + " | Bridges taken: " + route.toString();
	}
}
