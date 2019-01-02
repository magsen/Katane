/*********************************************************************************
*     File Name           :     TownMap.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-02 01:53]
*     Description         :     The data structure to represent the Town map (Town set)
**********************************************************************************/

package Katane;

import java.util.HashMap;

/* This is a map of roads */
public class TownMap extends Map {

	private HashMap<Coordinates, Town> townSet;

	/* Constructor */
	public TownMap () {
		townSet = new HashMap<Coordinates, Town>();
	}

	/* Get the road at specified coordinates */
	public Town getTown (Coordinates coor) {
		return townSet.get(coor);
	}

	/* This function add a town to the town map - Don't do any kind of checking. */
	public int addTownToMap (Coordinates coor, Town town) {
		townSet.put(coor, town);
		return 1; //TODO
	}
}
