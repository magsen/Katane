/*********************************************************************************
*     File Name           :     ObjectMap.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-04 04:08]
*     Description         :     The data structure to represent the map (tile set, road set and Town set)
**********************************************************************************/

package Katane;

import java.util.HashMap;

/* this is a data structure with HashMap */
abstract public class ObjectMap {

	private HashMap<Coordinates, Object> map;

	public ObjectMap () {
		map = new HashMap<Coordinates, Object>();
	}

	public boolean isBuilding (Coordinates coor) {
		return map.containsKey(coor);
	}

}

