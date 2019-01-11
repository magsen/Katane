/*********************************************************************************
*     File Name           :     Building
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-06 00:10]
*     Description         :     The abstract class Building groups all the buildings
**********************************************************************************/

package Katane;

import java.util.ArrayList;

/* Abstract class for the buildings (Road, Dolorean and TimeTown) */
public abstract class Building {

	protected Player player;
	protected Coordinates coordinates;
	protected ArrayList<Road> adjacentRoads;

	/* Constructor with the owner (Player) and the coordinates */
	public Building(Player player, Coordinates coor) {
		this.player = player;
		this.coordinates = coor;
		adjacentRoads = new ArrayList<Road>();
		System.out.println("-- " + this.getClass().getSimpleName() + " has been built at " + coor.toString() + " --");
	}

	/* Get the adjacent roads in the list */
	public ArrayList<Road> getAdjacentRoads() {
		return adjacentRoads;
	}

	/* Set the adjacent roads in the list */
	public void setAdjacentRoads(ArrayList<Road> adjacentRoads) {
		this.adjacentRoads = adjacentRoads;
	}

	public void addAdjacentRoads(Road road) {
		adjacentRoads.add(road);
	}

	public boolean isOwner(Player player) {
		return (this.player == player);
	}
	
	public int getOwnerNumber () {
		return player.getPlayerNumber();
	}

	public Coordinates getCoordinates () {
		return coordinates;
	}
}
