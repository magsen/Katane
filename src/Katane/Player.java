/*********************************************************************************
*     File Name           :     Player.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2018-12-26 18:11]
*     Description         :     The Player class represents a player of the game.
**********************************************************************************/

package Katane;

import java.util.ArrayList;

/* The Player class represents a player of the game.
 * Attributes :
 * 	- katane, a reference to the Katane class
 * 	- playerNumber, the number associated to the player
 * 	- victoryPoint, the number of victory points owned by a player
 * 	- ressource, the list of ressources owned by a player
 */
public class Player {
	private Katane katane;
	private int playerNumber;
	private int victoryPoint;
	private ArrayList<Ressource> ressource;

	/* Constructor */
	public Player(Katane katane, int i) {
		this.katane = katane;
		this.setPlayerNumber(i);
		System.out.println("-- Player "+i+" --");
		this.ressource = new ArrayList<>();
		this.ressource.add(new Wood());
		this.ressource.add(new Wool());
		this.ressource.add(new Brick());
		this.ressource.add(new Ore());
		this.ressource.add(new Grain());
	}

	/* Get the number of a player */
	public int getPlayerNumber() {
		return playerNumber;
	}

	/* Set the number of a player */
	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	/* Get the number of victory points of a player */
	public int getVictoryPoint() {
		return victoryPoint;
	}

	/* Set the number of victory points of a player */
	public void setVictoryPoint(int victoryPoint) {
		this.victoryPoint = victoryPoint;
	}

	/* Get the ressource in the list */
	public ArrayList<Ressource> getRessource() {
		return ressource;
	}

	/* Set the ressource in the list */
	public void setRessource(ArrayList<Ressource> ressource) {
		this.ressource = ressource;
	}
	
	
	public ArrayList<Ressource> getSpecificRessource( SamPredicate<Ressource> samPredicate) {
		ArrayList<Ressource> list = new ArrayList<>();

		 for (Ressource r : this.ressource) {
			 if (samPredicate.test(r)) {
				 list.add(r);
			 }
		 }

		 return list;
	}
}
