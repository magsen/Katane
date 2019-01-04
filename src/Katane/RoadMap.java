/*********************************************************************************
*     File Name           :     RoadMap.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-03 17:59]
*     Description         :     The data structure to represent the road map (road set)
**********************************************************************************/

package Katane;

import java.util.HashMap;
import java.util.ArrayList;

/* This is a map of roads */
public class RoadMap extends Map {

	private HashMap<Coordinates, Road> roadSet;

	/* Constructor */
	public RoadMap () {
		roadSet = new HashMap<Coordinates, Road>();
	}

	/* Get the road at specified coordinates */
	public Road getRoad (Coordinates coor) {
		return roadSet.get(coor);
	}

	/* Test whether or not there is a building at the specified coordinates */
	public boolean isBuilding (Coordinates coor) {
		return roadSet.containsKey(coor);
	}

	/* Add a road to the road set */
	public void addRoadToMap (Coordinates coor, Road road) {
		roadSet.put(coor, road);
	}

	public ArrayList<Road> generateRoadList (Coordinates coor) {
		ArrayList<Road> roadList = new ArrayList<Road>();
		// TODO
		return roadList;
	}
}
