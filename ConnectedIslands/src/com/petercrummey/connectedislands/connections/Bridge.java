/**
 * 
 */
package com.petercrummey.connectedislands.connections;

import java.math.BigDecimal;

import com.petercrummey.connectedislands.island.Island;

/**
 * @author Peter Crummey <peter@rose-reid.com>
 *
 */
public class Bridge
{
	public enum BridgeType
	{
		FREE_BRIDGE(0, "Free bridge"),
		TOLL_BRIDGE(1, "Toll bridge");
		
		private int type;
		private String typeDesc;
		
		private BridgeType(int type, String description)
		{
			this.type = type;
			this.typeDesc = description; 
		}
		
		@Override
		public String toString()
		{
			return "[" + this.type + "] " + this.typeDesc;
		}
	}

	private Island origin;
	private Island destination;
	private BridgeType type;
	private BigDecimal toll = BigDecimal.ZERO;

	public Island getOrigin()
	{
		return origin;
	}
	public Bridge setOrigin(Island origin)
	{
		this.origin = origin;
		return this;
	}
	public Island getDestination()
	{
		return destination;
	}
	public Bridge setDestination(Island destination)
	{
		this.destination = destination;
		return this;
	}
	public BridgeType getType()
	{
		return type;
	}
	public Bridge setType(BridgeType type)
	{
		this.type = type;
		return this;
	}
	
	public BigDecimal getToll()
	{
		return toll;
	}
	public Bridge setToll(BigDecimal toll)
	{
		this.toll = toll;
		return this;
	}
	
	public BigDecimal getBridgeCrossingCost()
	{
		BigDecimal totalCost = BigDecimal.ZERO;
		if(this.type == BridgeType.TOLL_BRIDGE)
		{
			totalCost = totalCost.add(this.toll);
		}
		return totalCost;
	}
	
	@Override
	public String toString()
	{
		return "Origin: " + this.origin + "| Destination: " + this.destination + "| Type: " + this.type;
	}
}
