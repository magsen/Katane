/*********************************************************************************
*     File Name           :     TownMap.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-04 04:12]
*     Description         :     The data structure to represent the Town map (Town set)
**********************************************************************************/

package Katane;

import java.util.HashMap;
import java.util.Map;

/* This is a map of roads */
public class TownMap extends ObjectMap {

	private HashMap<Coordinates, Town> townSet;

	/* Constructor */
	public TownMap () {
		townSet = new HashMap<Coordinates, Town>();
	}

	/* This function add a town to the town map - Don't do any kind of checking. */
	public int addTownToMap (Coordinates coor, Town town) {
		townSet.put(coor, town);
		return 1; //TODO
	}

	/* Get the town at specified coordinates */
	public Town getTown (Coordinates coor) {
		Coordinates c;
		for ( Map.Entry<Coordinates, Town> entry : townSet.entrySet()) {
			c = entry.getKey();
			if (c.equals(coor)) {
				return entry.getValue();
			}
		}
		return null;
	}

	/* Test whether or not there is a town at the specified coordinates */
	public boolean isTown (Coordinates coor) {
		Coordinates c;
		for ( Map.Entry<Coordinates, Town> entry : townSet.entrySet()) {
			c = entry.getKey();
			if (c.equals(coor)) {
				return true;
			}
		}
		return false;
	}
}
