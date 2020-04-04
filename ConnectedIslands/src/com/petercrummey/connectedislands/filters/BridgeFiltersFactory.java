/**
 * 
 */
package com.petercrummey.connectedislands.filters;

/**
 * @author Peter Crummey <peter@rose-reid.com>
 *
 */
public class BridgeFiltersFactory
{

	private BridgeFiltersFactory()  {}
	
	public static BridgeFilters create()
	{
		BridgeFilters filters = new BridgeFilters();
		filters.addFilter(new NoTollBridgeFilter());
		return filters;
	}
}
