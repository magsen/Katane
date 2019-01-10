/*********************************************************************************
*     File Name           :     BuildManagerWorker.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-11 00:51]
*     Description         :     The BuildManagerWorker handles the creation of the towns
*     					The BuildManagerWorker is often called BMW.
**********************************************************************************/

package Katane;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Iterator;
import java.util.Map;

import java.util.Scanner;

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
				// player.consumeRessourceRoad(); UNCOMMENT !
				if (world.setLongestRoadValue(getLongestRoad (roadSet, player, coordinates))) {
					changePlayerLongestRoad(world, player);
				}
				return true;
			}
		}
	}

	private void changePlayerLongestRoad (World w, Player newP) {

		if (w.getLongestRoadPlayer() != -1) {
			Player oldP = katane.getPlayerN(w.getLongestRoadPlayer());
			oldP.setVictoryPoint(oldP.getVictoryPoint() - 2); // Hard-coded longest road victory points
		}
		w.setLongestRoadPlayer(katane.getPlayerNumber(newP));
		newP.setVictoryPoint(newP.getVictoryPoint() + 2);
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
					player.setVictoryPoint(player.getVictoryPoint() + 1);
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
				player.setVictoryPoint(player.getVictoryPoint() + 1);
				tileSet.updateTownToTilesTownList(coordinates, oldDolorean, town);
				player.consumeRessourceTimeTown();
				return true;
			} else {
				System.out.println("There is already a TimeTown there or is owned by the another player");
			}
		}
		return false;
	}

	public boolean buildRoad (World world, Coordinates coor) {
		return buildRoad (katane.getCurrentPlayer(), world, coor);
	}
	public boolean buildDolorean (World world, Coordinates coor) {
		return buildDolorean (katane.getCurrentPlayer(), world, coor);
	}
	public boolean buildTimeTown (World world, Coordinates coor) {
		return buildTimeTown (katane.getCurrentPlayer(), world, coor);
	}

	/* Returns a list of ArrayList of Coordinates containing all the possibles road builds */
	public ArrayList<Coordinates> possibleRoadsBuild (Player player, World world) {

		RoadMap roadSet = world.getRoadSet();
		Stack<Coordinates> stRoads = new Stack<Coordinates>();
		ArrayList<Coordinates> coordinateList = new ArrayList<Coordinates>();
		Coordinates coorPlayerTown;
		for (Town t : player.getTownList()) {
			System.out.println("1er ville exploree" + t.toString());
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

	/* returns the longest path length of the road.  Doesn't count the first coordinates in the length - Explore to the opposite side of prevCoor*/
	public int explorePathOneDirection(RoadMap roadSet, Player player, Coordinates coor, Coordinates prevCoor) {

		int maxLen = 0; // This is the length of the current longest path.
		int i = Road.getLengthPositionReference(), k;
		Coordinates coorAdjRoads;

		StepInformations step = new StepInformations(coor, i + 1);
		Stack<StepInformations> stRoads = new Stack<StepInformations>();
		stRoads.push(step);

		while (stRoads.isEmpty() == false) {
			step = stRoads.pop();
			k = step.length;

			if (maxLen < k) { // update longest length
				maxLen = k;
			}
			Coordinates coorRoad = step.coordinate;
			Road r = roadSet.getRoad(coorRoad);

			r.setLengthPosition(k+1); // Mark the road

			coorAdjRoads = (coorRoad.roadToAdjacentRoadsOneDirection(prevCoor)).get(0); // First possibility

			// Explore the left side
			if (step.everythingExplored == false) {
				if (step.wasThere == false && isOwnerRoadAndNeverUsed(roadSet, player, coorAdjRoads, i)) {
					// There is a road left side
					step.wasThere = true;
					stRoads.push(step); // Keep the path
					step = new StepInformations(coorAdjRoads, k + 1); // Next road to explore
					stRoads.push(step);
					prevCoor = coorRoad;

				} else {
					// Explore the rigth side
					step.everythingExplored = true;
					coorAdjRoads = (coorRoad.roadToAdjacentRoadsOneDirection(prevCoor)).get(1); // Second possibility
					if (roadSet.isRoad(coorAdjRoads)) {

						if (isOwnerRoadAndNeverUsed(roadSet, player, coorAdjRoads, i)) {
							stRoads.push(step); // keep the path
							step = new StepInformations(coorAdjRoads, k + 1); // add a right road to explore
							stRoads.push(step);
							prevCoor = coorRoad;
						} else {
							r.setLengthPosition(i); // Unmark the road
							prevCoor = getCoordinatesStackDepth2(stRoads, coorRoad); // Update the previous road (open the stack for that)
							k = k - 1;
						}
					} else {
						prevCoor = getCoordinatesStackDepth2(stRoads, coorRoad); // Update the previous road (open the stack for that)
						r.setLengthPosition(i); // Unmark the road
						k = k - 1;
					}
				}
			} else {
				r.setLengthPosition(i); // Unmark
				prevCoor = getCoordinatesStackDepth2(stRoads, coorRoad); // update previous road
				k = k - 1;
			}
		}
		return maxLen - i - 1;
	}

	/* This returns the longest road (in roadSet) of a player (player) passing by a given road coordinate (coor).
	 * First of all, we explore the side one on a left-right exploration (see binary tree recursive exploration).
	 *
	 * If we find an intersection or a dead-end, we computes the maximum length of the other side path. And compares the sum with maxLen - The ex-longestLength */
	public int getLongestRoad (RoadMap roadSet, Player player, Coordinates coor) {

		int maxLen = 0, lenSide = 0, pathLen = 0;
		Road.setLengthPositionReference(Road.getLengthPositionReference() + 2); // Update to avoid being broken by possibleRoad()
		int ref = Road.getLengthPositionReference(), k; // ref is an offset
		Coordinates coorAdjRoad, coorRoad;

		// Get a near road coordinate - doesn't matter which one chose, just to have the same direction
		Coordinates prevCoor = (coor.roadToAdjacentRoads()).get(0), prevCoorOppositeSide = (coor.roadToAdjacentRoads()).get(2);

		// Initialize and add the initial road with an index of (ref + 1)
		StepInformations step = new StepInformations(coor, ref + 1);

		// Initialize the stack of the steps informations (road coordinates, current path length, coordinates belong to path (was already there in this path), is there an intersection, and force step removing (avoid infinity loops)
		Stack<StepInformations> stRoads = new Stack<StepInformations>();
		stRoads.push(step);

		while (stRoads.isEmpty() == false) {
			printStack(stRoads);
			step = stRoads.pop();
			k = step.length;
			lenSide = k - ref; // the real length without the offset "ref"

			coorRoad = step.coordinate;
			Road r = roadSet.getRoad(coorRoad);
			r.setLengthPosition(k+1); // Mark the road, to avoid other process(e.g. explorePathOneDirection) and this one to use it.

			coorAdjRoad = (coorRoad.roadToAdjacentRoadsOneDirection(prevCoor)).get(0); // First possibility - Avoid reverting the direction of exploration - and get the first next road possibility.

			if (step.everythingExplored == false) {
				// First time there - or second if there is an intersection
				if (step.wasThere == false && isOwnerRoadAndNeverUsed(roadSet, player, coorAdjRoad, ref)) {
					// First time going to this path - And there is a player's road

					pathLen = explorePathOneDirection(roadSet, player, coor, prevCoorOppositeSide);
					if (maxLen < lenSide + pathLen) {
						maxLen = lenSide + pathLen;
					}

					step.wasThere = true;
					stRoads.push(step); // saves the old path
					step = new StepInformations(coorAdjRoad, k + 1);
					stRoads.push(step); // Add the left Road to exploration
					prevCoor = coorRoad; // Update previous road (avoid revert travel)

				} else {
					// There were no road on the other side - or this now the right path
					step.everythingExplored = true;

					coorAdjRoad = (coorRoad.roadToAdjacentRoadsOneDirection(prevCoor)).get(1); // Second possibility

					if (roadSet.isRoad(coorAdjRoad)) {

						if (isOwnerRoadAndNeverUsed(roadSet, player, coorAdjRoad, ref)) {
							// There is a rigth-road to explore
							stRoads.push(step); // saves the old path
							step = new StepInformations(coorAdjRoad, k + 1);
							stRoads.push(step); // Add the rigth road to exploration
							prevCoor = coorRoad; // Update the previous road
						} else {
							// The right path is a dead end, invokes the other side longest road exploration
							pathLen = explorePathOneDirection(roadSet, player, coor, prevCoorOppositeSide); // Explore the other side
							if (maxLen < lenSide + pathLen) {
								maxLen = lenSide + pathLen;
							}
							r.setLengthPosition(ref); // Unmark the road
							prevCoor = getCoordinatesStackDepth2(stRoads, coorRoad); // Update the previous road (open the stack for that)
							k = k - 1;
						}
					} else {
						pathLen = explorePathOneDirection(roadSet, player, coor, prevCoorOppositeSide); // Explore the other side
						if (maxLen < lenSide + pathLen) {
							maxLen = lenSide + pathLen;
						}
						prevCoor = getCoordinatesStackDepth2(stRoads, coorRoad); // Update the previous road (open the stack for that)
						k = k - 1;
						r.setLengthPosition(ref); // road cleaning
					}
				}
			} else {
				r.setLengthPosition(ref); //clean road
				prevCoor = getCoordinatesStackDepth2(stRoads, coorRoad);
				k = k - 1;
			}
		}
		return maxLen;
	}

	/* Retrieves StepInformation 2 depth below the top of the stack */
	private Coordinates getCoordinatesStackDepth2 (Stack<StepInformations> st, Coordinates defaultCoordinates) {
		StepInformations stepPrev, stepPrev2;
		if (st.isEmpty() == false) {
			stepPrev = st.pop();
			if (st.isEmpty() == false) {
				stepPrev2 = st.pop();
				st.push(stepPrev2);

			} else {
				st.push(stepPrev);
				System.out.println("Error not enough stack");
				return defaultCoordinates;
			}
			st.push(stepPrev);
			return stepPrev2.coordinate;
		} else {
			System.out.println("Error not enough stack");
			return defaultCoordinates;
		}
	}

	/* Check if a road exist, belongs to the player, and is unmarked */
	private boolean isOwnerRoadAndNeverUsed(RoadMap roadSet, Player p, Coordinates c, int currentIndex) {
		return roadSet.isRoad(c) && roadSet.isOwner(c, p) && ((roadSet.getRoad(c)).getLengthPosition() <= currentIndex);
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

	/* Add a coordinati in a ArrayList of Coordinates without sames coordinates */
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

