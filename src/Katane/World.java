/*********************************************************************************
 *     File Name           :     World.java
 *     Created By          :     The LO43 Katane team
 *     Creation Date       :     [2018-09-14 13:32]
 *     Last Modified       :     [2019-01-02 03:44]
 *     Description         :     World is the class that gather the tiles, the buildings and the towns.
 *     					It although includes all the informations board/tileSet-specific.
 **********************************************************************************/

package Katane;

import java.util.HashMap;
import java.util.Arrays;

/* Class World
 * Attributes :
 * 	- tile, a list of tiles
 * 	- road, a list of roads
 * 	- town, a list of towns
 * 	- longestRoadValue, the length of the longest road
 */
public class World {

	private TileMap tileSet;
	private RoadMap roadSet;
	private TownMap townSet;
	private int longestRoadValue;
	private int longestRoadPlayer;

	/* Constructor */
	public World() {
		System.out.println("-- World --");
		this.tileSet = new TileMap();
		this.roadSet = new RoadMap();
		this.townSet = new TownMap();
		this.longestRoadValue = 0;
		this.setLongestRoadPlayer(-1);
	}

	/* Get the legth of the longest road */
	public int getLongestRoadValue() {
		return longestRoadValue;
	}

	/* Set the length of the longest road, if it's changed, return true - false otherwise */
	public boolean setLongestRoadValue(int longestRoadValue) {
		if(this.longestRoadValue < longestRoadValue) {
			this.longestRoadValue = longestRoadValue;
			return true; 
		}
		else
		{
			return false;
		}
	}

	/* Get the tile HashMap */
	public TileMap getTileSet() {
		return tileSet;
	}

	/* Get the tile HashMap */
	public RoadMap getRoadSet() {
		return roadSet;
	}

	/* Get the tile HashMap */
	public TownMap getTownSet() {
		return townSet;
	}

	public int getLongestRoadPlayer() {
		return longestRoadPlayer;
	}

	public void setLongestRoadPlayer(int longestRoadPlayer) {
		this.longestRoadPlayer = longestRoadPlayer;
	}
}
