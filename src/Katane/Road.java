/*********************************************************************************
*     File Name           :     Road.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-06 04:50]
*     Description         :     Building type Road
**********************************************************************************/

package Katane;

import java.util.ArrayList;

/* Class Road which is a Building */
public class Road extends Building{

	private int lengthPosition; //temporary variable
	private static int lengthPositionReference;

	/* Constructor - Owned by a Player and is on a Tile (tile list) */
	public Road(Player player, Coordinates coor) {
		super(player, coor);
		lengthPosition = 0;
		lengthPositionReference = 0;
		//must verify that you can build before add
		//this.tile.addBuilding(this);
	}

	public int getLengthPosition() {
		return lengthPosition;
	}

	public void setLengthPosition(int lengthPosition) {
		this.lengthPosition = lengthPosition;
	}

	public void incrementLengthPosition(int lengthPosition) {
		this.lengthPosition += lengthPosition;
	}

	public static int getLengthPositionReference () {
		return lengthPositionReference;
	}

	public static void setLengthPositionReference (int lengthPositionReference) {
		Road.lengthPositionReference = lengthPositionReference;
	}

	public static void incrementLengthPositionReference (int lengthPositionReference) {
		Road.lengthPositionReference += lengthPositionReference;
	}

	/* Get the adjacent roads in the list */
	public ArrayList<Road> getAdjacentRoads() {
		return adjacentRoads;
	}

	/* Set the adjacent roads in the list */
	public void setAdjacentRoads(ArrayList<Road> adjacentRoads) {
		this.adjacentRoads = adjacentRoads;
	}

	public void addAdjacentRoad(Road road) {
		adjacentRoads.add(road);
	}
	
	//gui::
	public Coordinates getStart() {
		return coordinates.roadToAdjacentTowns().get(0);
	}
	public Coordinates getEnd () {
		return coordinates.roadToAdjacentTowns().get(1);
	}
}
