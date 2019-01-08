/*********************************************************************************
*     File Name           :     BuildManagerWorker.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-06 03:50]
*     Description         :     The BuildManagerWorker handles the creation of the towns
*     					The BuildManagerWorker is often called BMW.
**********************************************************************************/

package Katane;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Iterator;

/* The BuildManagerWorker is called by the players to construct cities */
public class BuildManagerWorker {

	private Katane katane;

	/* Constructor */
	public BuildManagerWorker(Katane katane) {
		this.katane = katane;
	}

	/* CARE ASSUME THAT THE RULES AUTHORIZE THIS PLACEMENT */
	public boolean buildRoad (Player player, World world, Coordinates coor) {
		Coordinates coordinates = new Coordinates(coor);
		TownMap townSet = world.getTownSet();
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
				townSet.updateAdjacentRoads(coordinates, roadSet, road);
				return true;
			}
		}
	}

	public boolean buildDolorean (Player player, World world, Coordinates coor) {
		Coordinates coordinates = new Coordinates(coor);
		TownMap townSet = world.getTownSet();
		TileMap tileSet = world.getTileSet();
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
				tileSet.addTownToTilesTownList(coordinates, town);
				return true;
			}
		}
	}

	public boolean buildTimeTown (Player player, World world, Coordinates coor) {
		Coordinates coordinates = new Coordinates(coor);
		TownMap townSet = world.getTownSet();
		RoadMap roadSet = world.getRoadSet();
		TileMap tileSet = world.getTileSet();
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
				tileSet.updateTownToTilesTownList(coordinates, oldDolorean, town);
				return true;
			} else {
				System.out.println("There is already a TimeTown there or is owned by the another player");
			}
		}
		return false;
	}

	public ArrayList<Coordinates> possibleRoadsBuild (Player player, World world) {

		RoadMap roadSet = world.getRoadSet();
		Stack<Coordinates> stRoads = new Stack<Coordinates>();
		ArrayList<Coordinates> coordinateList = new ArrayList<Coordinates>();
		Coordinates coorPlayerTown;
		for (Town t : player.getTownList()) {
			System.out.println("1ère ville exlorée" + t.toString());
			coorPlayerTown = t.getCoordinates();

			for ( Coordinates coorAdjRoads : coorPlayerTown.townToAdjacentRoads() ) {
				if (roadSet.isRoad(coorAdjRoads)) {
					if (roadSet.isOwner(coorAdjRoads, player)) {
						stRoads.push(coorAdjRoads);
					}
				} else {
					System.out.println("Added " + coorAdjRoads.toString());
					addWithoutRepetition(coordinateList, coorAdjRoads);
				}
			}
		}

		Coordinates coorRoad;
		Road r;
		while (stRoads.isEmpty() == false) {
			coorRoad = stRoads.pop();
			r = roadSet.getRoad(coorRoad);
			r.setLengthPosition(1);
			for ( Coordinates coorAdjRoads : coorRoad.townToAdjacentRoads() ) {
				if (roadSet.isRoad(coorAdjRoads)) {

					if (roadSet.isOwner(coorAdjRoads, player) && (roadSet.getRoad(coorAdjRoads)).getLengthPosition() < 1) {
						stRoads.push(coorAdjRoads);
					}
				} else {
					addWithoutRepetition(coordinateList, coorAdjRoads);
				}
			}
		}
		return coordinateList;
	}

	private void addWithoutRepetition (ArrayList<Coordinates> coordinateList, Coordinates coordinate) {
		Iterator<Coordinates> i = coordinateList.iterator();
		Coordinates c;
		while (i.hasNext()) {
			c = i.next();
			if (c.equals(coordinate)) {
				return;
			}
		}
		coordinateList.add(coordinate);
	}
}
