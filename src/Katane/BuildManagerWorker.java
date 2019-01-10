/*********************************************************************************
*     File Name           :     BuildManagerWorker.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-10 00:14]
*     Description         :     The BuildManagerWorker handles the creation of the towns
*     					The BuildManagerWorker is often called BMW.
**********************************************************************************/

package Katane;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Iterator;
import java.util.Map;

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
				player.consumeRessourceRoad();
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
			if (townSet.isTown(coordinates) == true ) {
				System.out.println("Error while creating a Dolorean: There is already a town there");
				return false;
			} else {
				if (townSet.isEnemyTownNear(coordinates, player) == true) {
					System.out.println("Error while creating a Dolorean: There is a enemy town near");
					return false;
				} else {
					Town town = new Dolorean(player, coordinates);
					town.setAdjacentRoads(townSet.generateAdjacentRoads(coordinates, roadSet)); // Set the road near
					town.setAdjacentTiles(tileSet.generateAdjacentTilesFromTownCoordinate(coordinates));
					townSet.addTownToMap(coordinates, town); // Add the town to the map
					player.addTownToPlayerList(town); // Add link from player to city
					tileSet.addTownToTilesTownList(coordinates, town); //Add link from tiles to city
					player.consumeRessourceDolorean(); // Removes the ressources
					return true;
				}
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
				town.setAdjacentTiles(tileSet.generateAdjacentTilesFromTownCoordinate(coordinates));
				townSet.replaceTownToMap(coordinates, town);
				player.replaceTownToPlayerList(oldDolorean, town);
				tileSet.updateTownToTilesTownList(coordinates, oldDolorean, town);
				player.consumeRessourceTimeTown();
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

			// this part is identical
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
		int i = Road.getLengthPositionReference();
		Road.setLengthPositionReference(i+1);

		while (stRoads.isEmpty() == false) {
			coorRoad = stRoads.pop();
			r = roadSet.getRoad(coorRoad);
			r.setLengthPosition(i + 1);
			for ( Coordinates coorAdjRoads : coorRoad.townToAdjacentRoads() ) {
				if (roadSet.isRoad(coorAdjRoads)) {

					if (roadSet.isOwner(coorAdjRoads, player) && (roadSet.getRoad(coorAdjRoads)).getLengthPosition() < i + 1) {
						stRoads.push(coorAdjRoads);
					}
				} else {
					addWithoutRepetition(coordinateList, coorAdjRoads);
				}
			}
		}
		return coordinateList;
	}

	/* returns the length of the road NOT FINISHED */
	public int explorePath(RoadMap roadSet, Player player, Coordinates coor) {

		int maxLen = 0;
		int i = Road.getLengthPositionReference(), k;
		// only at the end Road.setLengthPositionReference(i+1);
		boolean oneWay = false, deadEnd = false;

		StepInformations step = new StepInformations(coor, 0); //step one side
		// ATTENTINO on peut push une route intersection
		Stack<StepInformations> stRoads = new Stack<StepInformations>();
		stRoads.push(step);

		while (stRoads.isEmpty() == false) {
			printStack(stRoads);
			step = stRoads.pop();
			k = step.length;
			if (maxLen < k) {
				maxLen = k;
			}
			Coordinates coorRoad = step.coordinate;
			Road r = roadSet.getRoad(coorRoad);
			System.out.println(r.toString());
			oneWay = false;

			Coordinates coorAdjRoads;
			coorAdjRoads = (coorRoad.roadToAdjacentRoads()).get(2); // First possibility

			/*
			// Start saving
			if (roadSet.isRoad(coorAdjRoads) && step.intersection =! false && roadSet.isOwner(coorAdjRoads, player) && (roadSet.getRoad(coorAdjRoads)).getLengthPosition() < i + 1) {

				coorAdjRoads = (coorRoad.townToAdjacentRoads()).get(3); // First possibility
				if (roadSet.isRoad(coorAdjRoads) && (roadSet.isOwner(coorAdjRoads, player) && (roadSet.getRoad(coorAdjRoads)).getLengthPosition() < i + 1)) {
					System.out.println("Intersection anticipée");

				}
			}
			*/

			// end saving

			// Explore the first side
			System.out.println("-- On est à " + coorAdjRoads.toString() + " --");
			if (roadSet.isRoad(coorAdjRoads)) {

				if (step.intersection =! false && roadSet.isOwner(coorAdjRoads, player) && (roadSet.getRoad(coorAdjRoads)).getLengthPosition() < i + 1) {
					System.out.println("Une route du coté 1 " + coorRoad.toString());
					Coordinates cTmp = (coorRoad.roadToAdjacentRoads()).get(3);
					if (roadSet.isRoad(cTmp) && roadSet.isOwner(cTmp, player) && (roadSet.getRoad(cTmp)).getLengthPosition() < i + 1) {
						//intersection
						System.out.println("Intersection !");
						step.intersection = true;
					}
					stRoads.push(step);
					step = new StepInformations(coorAdjRoads, k + 1);
					stRoads.push(step);

				} else {
					System.out.println("Pas de route au coté 1");
					step.intersection = false;
					oneWay = true;
					// Other side
					coorAdjRoads = (coorRoad.roadToAdjacentRoads()).get(3); // Second possibility
					if (roadSet.isRoad(coorAdjRoads)) {

						if (roadSet.isOwner(coorAdjRoads, player) && (roadSet.getRoad(coorAdjRoads)).getLengthPosition() < i + 1) {
							System.out.println("Une route du coté 2");
							stRoads.push(step);
							step = new StepInformations(coorAdjRoads, k + 1);
							stRoads.push(step);
						} else {
							System.out.println("Pas de route au coté 2");
							oneWay = true ^ deadEnd;
							// Dead-end Need a check
							k = k - 1;
						}
					}
				}
			}
		}
		return maxLen;
	}

	private int getOtherSide(Stack<StepInformations> st, Coordinates coor) {
		StepInformations s = st.pop();
		return 0;
	}

	private void printStack(Stack<StepInformations> st) {
		Stack<StepInformations> stSav = new Stack<StepInformations>();
		StepInformations tmp;
		System.out.println("-- Show stack --");
		while (st.isEmpty() == false) {
			tmp = st.pop();
			tmp.print();
			stSav.push(tmp);
		}
		while (stSav.isEmpty() == false) {
			tmp = stSav.pop();
			st.push(tmp);
		}
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
