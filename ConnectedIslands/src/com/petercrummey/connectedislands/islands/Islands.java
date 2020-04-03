/**
 * 
 */
package com.petercrummey.connectedislands.islands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.petercrummey.connectedislands.island.Island;

/**
 * @author pcrummey
 *
 */
public class Islands
{
	List<Island> islands = new ArrayList<>();
	
	public final List<Island> getIslands()
	{
		return Collections.unmodifiableList(this.islands);
	}
	
	public Islands addIsland(Island island)
	{
		this.islands.add(island);
		return this;
	}
	
	public Island find(int id)
	{
		Island foundIsland = null;
		for(Island island : this.islands)
		{
			if(island.getId() == id)
			{
				foundIsland = island;
				break;
			}
		}
		if(foundIsland == null)
		{
			throw new IndexOutOfBoundsException("There is no Island " + id);
		}
		return foundIsland;
	}
	
	@Override
	public String toString()
	{
		String islands = "";
		
		for(Island island : this.islands)
		{
			islands = islands + island;
		}
		return islands;
	}
}
