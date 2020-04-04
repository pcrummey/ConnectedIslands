/**
 * 
 */
package com.petercrummey.connectedislands.connections;

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
	
	@Override
	public String toString()
	{
		return "Origin: " + this.origin + "| Destination: " + this.destination + "| Type: " + this.type;
	}
}
