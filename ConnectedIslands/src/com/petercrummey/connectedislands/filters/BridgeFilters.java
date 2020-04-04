package com.petercrummey.connectedislands.filters;

import java.util.ArrayList;
import java.util.List;

import com.petercrummey.connectedislands.connections.Bridge;

/**
 * @author Peter Crummey <peter@rose-reid.com>
 *
 */
public class BridgeFilters implements BridgeFilter
{
	private List<BridgeFilter> filters = new ArrayList<>();
	
	public BridgeFilters addFilter(BridgeFilter filter)
	{
		this.filters.add(filter);
		return this;
	}
	
	public boolean apply(Bridge bridge)
	{
		for(BridgeFilter filter : this.filters)
		{
			if(!filter.apply(bridge))
			{
				return false;
			}
		}
		return true;
	}
}
