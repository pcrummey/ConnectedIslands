/**
 * 
 */
package com.petercrummey.connectedislands.filters;

import com.petercrummey.connectedislands.connections.Bridge;

/**
 * @author Peter Crummey <peter@rose-reid.com>
 *
 */
public class NoFilter implements BridgeFilter
{

	@Override
	public boolean apply(Bridge bridge)
	{
		return true;
	}

}
