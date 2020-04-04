package com.petercrummey.connectedislands.filters;

import com.petercrummey.connectedislands.connections.Bridge;
import com.petercrummey.connectedislands.connections.Bridge.BridgeType;

/**
 * @author Peter Crummey <peter@rose-reid.com>
 *
 */
public class NoTollBridgeFilter implements BridgeFilter
{

	@Override
	public boolean apply(Bridge bridge)
	{
		return bridge.getType() != BridgeType.TOLL_BRIDGE;
	}

}
