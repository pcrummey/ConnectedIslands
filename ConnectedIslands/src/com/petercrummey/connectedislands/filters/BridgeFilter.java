/**
 * 
 */
package com.petercrummey.connectedislands.filters;

import com.petercrummey.connectedislands.connections.Bridge;

/**
 * @author Peter Crummey <peter@rose-reid.com>
 *
 */
public interface BridgeFilter
{
	/**
	 * Apply the filter and returned true if the filter applied to this Bridge
	 * @param bridge Bridge to which the filter will be applied
	 * @return true if the Bridge was selected by the filter
	 */
	public boolean apply(Bridge bridge);
}
