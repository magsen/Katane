/*********************************************************************************
*     File Name           :     BuildManagerWorker.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-05 05:49]
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

	/* CARE ASSUME THAT THE RULES AUTHORIZE THIS PLACEMENT */
	public boolean buildRoad (Player player, World world, Coordinates coordinates) {
		RoadMap roadSet = world.getRoadSet();
		if (player.isEnoughRessourceRoad() == false) {
			System.out.println("Error while creating a Road: not enough ressources");
			return false;
		} else {
			if (roadSet.isRoad(coordinates) == true) {
				System.out.println("Error while creating a Road: There is already a road there");
				return false;
			} else {
				Road road = new Road(player, coordinates);
				road.setAdjacentRoads(roadSet.generateAdjacentRoads(coordinates)); // Set the road nead
				roadSet.addRoadToMap(coordinates, road);
				roadSet.updateAdjacentRoads(coordinates, road);
				return true;
			}
		}
	}

	public boolean buildDolorean (Player player, World world, Coordinates coordinates) {
		TownMap townSet = world.getTownSet();
		RoadMap roadSet = world.getRoadSet();
		if (player.isEnoughRessourceDolorean() == false) {
			System.out.println("Error while creating a Dolorean: not enough ressources");
			return false;
		} else {
			if (townSet.isTown(coordinates) == true) {
				System.out.println("Error while creating a Dolorean: There is already a town there");
				return false;
			} else {
				Town town = new Dolorean(player, coordinates);
				town.setAdjacentRoads(townSet.generateAdjacentRoads(coordinates, roadSet)); // Set the road nead
				townSet.addTownToMap(coordinates, town);
				player.addTownToPlayerList(town);
				return true;
			}
		}
	}

	public boolean buildTimeTown (Player player, World world, Coordinates coordinates) {
		TownMap townSet = world.getTownSet();
		RoadMap roadSet = world.getRoadSet();
		if (player.isEnoughRessourceTimeTown() == false) {
			System.out.println("Error while creating a TimeTown: not enough ressources");
			return false;
		} else {
			if (townSet.isDoloreanOwner(coordinates, player) == true) {
				Town oldDolorean = townSet.getTown(coordinates);
				Town town = new TimeTown(player, coordinates);
				town.setAdjacentRoads(townSet.generateAdjacentRoads(coordinates, roadSet)); // Set the road nead
				townSet.replaceTownToMap(coordinates, town);
				player.replaceTownToPlayerList(oldDolorean, town);
				return true;
			} else {
				System.out.println("There is already a TimeTown there or is owned by the another player");
			}
		}
		return false;
	}
}
