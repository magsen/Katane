/*********************************************************************************
*     File Name           :     BuildManagerWorker.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-04 02:00]
*     Description         :     The BuildManagerWorker handles the creation of the towns
*     					The BuildManagerWorker is often called BMW.
**********************************************************************************/

package Katane;

/* The BuildManagerWorker is called by the players to construct cities */
public class BuildManagerWorker {

	private Katane katane;

	/* Constructor */
	public BuildManagerWorker(Katane katane) {
		this.katane = katane;
	}

	public boolean buildRoad (Player player, World world, Coordinates coordinates) {
		RoadMap roadSet = world.getRoadSet();
		if (player.isEnoughRessourceRoad() == false) {
			return false;
		} else {
			if (roadSet.isRoad(coordinates) == true) {
				return false;
			} else {
				// what is near ? nothing because it's road…
				// I therefore add it to the roadSet

				Road road = new Road(player, coordinates);
				road.setAdjacentRoad(roadSet.generateAdjacentRoads(coordinates)); // Set the road nead
				// TODO still have to update the road adjacent to include thi newly created route
				roadSet.addRoadToMap(coordinates, road);
				roadSet.updateAdjacentRoads(coordinates, road);
				return true;
			}
		}
	}
}
