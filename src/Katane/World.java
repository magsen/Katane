/*********************************************************************************
*     File Name           :     Wool.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2018-12-26 18:31]
*     Description         :     Wool is a type of Ressource
**********************************************************************************/

package Katane;

import java.util.HashMap;

/* Class World
 * Attributes :
 * 	- tile, a list of tiles
 * 	- road, a list of roads
 * 	- town, a list of towns
 * 	- longestRoadValue, the length of the longest road
 */
public class World {

	private HashMap<Object, Object> tile;
	private HashMap<Object, Object> road;
	private HashMap<Object, Object> town;
	private int longestRoadValue;

	/* Constructor */
	public World() {
		System.out.println("-- World --");
		this.tile = new HashMap<>();
		this.road = new HashMap<>();
		this.town = new HashMap<>();
		this.longestRoadValue = 0;
	}

	/* Get the legth of the longest road */
	public int getLongestRoadValue() {
		return longestRoadValue;
	}

	/* Set the legth of the longest road */
	public void setLongestRoadValue(int longestRoadValue) {
		if(longestRoadValue<0) {
			longestRoadValue = 0;
		}
		else
		{
			longestRoadValue = longestRoadValue;
		}
	}
}
