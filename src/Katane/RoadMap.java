/*********************************************************************************
*     File Name           :     RoadMap.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-06 00:25]
*     Description         :     The data structure to represent the road map (road set)
**********************************************************************************/

package Katane;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;


/* This is a map of roads */
public class RoadMap extends ObjectMap {

	private HashMap<Coordinates, Road> roadSet;

	/* Constructor */
	public RoadMap () {
		roadSet = new HashMap<Coordinates, Road>();
	}

	/* Get the road at specified coordinates */
	public Road getRoad (Coordinates coor) {
		Coordinates c;
		for ( Map.Entry<Coordinates, Road> entry : roadSet.entrySet()) {
			c = entry.getKey();
			if (c.equals(coor)) {
				return entry.getValue();
			}
		}
		return null;
	}

	/* Test whether or not there is a road at the specified coordinates */
	public boolean isRoad (Coordinates coor) {
		Coordinates c;
		for ( Map.Entry<Coordinates, Road> entry : roadSet.entrySet()) {
			c = entry.getKey();
			if (c.equals(coor)) {
				return true;
			}
		}
		return false;
	}

	public boolean isOwner (Coordinates coor, Player player) {
		Road r = getRoad(coor);
		return r.isOwner(player);
	}

	/* Add a road to the road set */
	public void addRoadToMap (Coordinates coor, Road road) {
		Coordinates c = new Coordinates(coor);
		roadSet.put(c, road);
	}

	public ArrayList<Road> generateAdjacentRoads (Coordinates coor) {
		ArrayList<Road> roadList = new ArrayList<Road>();
		ArrayList<Coordinates> adjRoads = coor.roadToAdjacentRoads();

		// Add adjacents roads to the adjacent roads list if there exists
		for ( Coordinates c : adjRoads ) {
			if (isRoad(coor)) {
				roadList.add(getRoad(coor));
			}
		}
		return roadList;
	}

	public void updateAdjacentRoads (Coordinates coor, Road road) {
		for ( Road rd : generateAdjacentRoads(coor) ) {
			rd.addAdjacentRoads(road);
		}
	}
}
