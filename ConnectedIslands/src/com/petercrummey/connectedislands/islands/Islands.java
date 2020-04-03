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
	private List<Island> islands = new ArrayList<>();
	private boolean isSorted = false;
	
	public final List<Island> getIslands()
	{
		return Collections.unmodifiableList(this.islands);
	}
	
	public Islands addIsland(Island island)
	{
		try
		{
			this.find(island.getId());
			//-----------------------------------------------------------------
			// Cannot have duplicate Islands. If we got this far, an Island
			// with the same id already exists. This is not valid.
			//-----------------------------------------------------------------
			throw new IllegalArgumentException("Cannot add duplicate island " + island.getId());
		} catch(IndexOutOfBoundsException iobe)
		{
			this.islands.add(island);
			this.isSorted = false;
		}
		return this;
	}
	
	/**
	 * Given an Island <i>id</i>, find that Island within the collection of Islands
	 * @param id Find an Island containing this <i>id</i>
	 * @return The Island object with the requested <i>id</i>
	 * @throws IndexOutOfBoundsException if given <i>id</i> is not an existing Island
	 */
	public Island find(Integer id)
	{
		Island foundIsland = null;
		this.sortIslands();

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
		this.sortIslands();
		
		for(Island island : this.islands)
		{
			islands = islands + island + "\n";
		}
		return islands;
	}
	
	private void sortIslands()
	{
		if(!this.isSorted)
		{
			Collections.sort(this.islands);
			this.isSorted = true;
		}
	}
}
