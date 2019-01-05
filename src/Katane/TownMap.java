/*********************************************************************************
*     File Name           :     TownMap.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-05 06:52]
*     Description         :     The data structure to represent the Town map (Town set)
**********************************************************************************/

package Katane;

import java.util.ArrayList;
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
	public void addTownToMap (Coordinates coor, Town town) {
		Coordinates c = new Coordinates(coor);
		townSet.put(c, town);
	}

	public void replaceTownToMap (Coordinates coor, Town town) {
		Coordinates c, cSav = null;
		for ( Map.Entry<Coordinates, Town> entry : townSet.entrySet()) {
			c = entry.getKey();
			if (c.equals(coor)) {
				cSav = entry.getKey();
			}
		}
		townSet.remove(cSav);
		c = new Coordinates(coor);
		townSet.put(c, town);
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
				System.out.println("isTown retourne true");
				return true;
			}
		}
		return false;
	}

	/* Test whether or not there is a Dolorean at the specified coordinates, if there is, check the owner. */
	public boolean isDoloreanOwner (Coordinates coor, Player player) {
		Coordinates c;
		Town t;
		for ( Map.Entry<Coordinates, Town> entry : townSet.entrySet()) {
			c = entry.getKey();
			if (c.equals(coor)) {
				t = entry.getValue();
				if (t instanceof Dolorean) {
					return t.isOwner(player);
				} else {
					return false;
				}
			}
		}
		return false;
	}

	public ArrayList<Road> generateAdjacentRoads (Coordinates coor, RoadMap roadSet) {
		ArrayList<Coordinates> adjRoads = coor.townToAdjacentRoads();
		ArrayList<Road> roadList = new ArrayList<Road>();

		for ( Coordinates c : adjRoads ) {
			if (roadSet.isRoad(coor)) {
				roadList.add(roadSet.getRoad(coor));
			}
		}
		return roadList;
	}

	// TEST
	public HashMap<Coordinates, Town> getTownSet () {
		return townSet;
	}
}
