/*********************************************************************************
*     File Name           :     Player.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-06 03:32]
*     Description         :     The Player class represents a player of the game.
**********************************************************************************/

package Katane;

import java.util.ArrayList;
import java.util.Iterator;

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
	private ArrayList<Town> townList;

	/* Constructor */
	public Player(Katane katane, int i) {
		this.katane = katane;
		this.setPlayerNumber(i);
		System.out.println("-- Player "+i+" --");
		this.townList = new ArrayList<Town>();
		this.ressource = new ArrayList<Ressource>();
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
<<<<<<< HEAD

	public boolean isEnoughRessourceRoad () {
		isEnoughRessource(0, 0, 1, 1, 0);
		return true;
	}

	public boolean isEnoughRessourceDolorean () {
		isEnoughRessource(0, 1, 1, 1, 1);
		return true;
	}

	public boolean isEnoughRessourceTimeTown () {
		isEnoughRessource(3, 2, 0, 0, 0);
		return true;
	}

	public boolean isEnoughRessource (int brick, int grain, int ore, int wood, int wool) {
		for ( Ressource r : ressource ) {
			if (r instanceof Brick) {
				if ( r.getQuantity() < brick) {
					return false;
				}
			} else {
				if (r instanceof Grain) {
					if ( r.getQuantity() < grain) {
						return false;
					}
				} else {
					if (r instanceof Ore) {
						if ( r.getQuantity() < ore) {
							return false;
						}
					} else {
						if (r instanceof Wood) {
							if ( r.getQuantity() < wood) {
								return false;
							}
						} else {
							if (r instanceof Wool) {
								if ( r.getQuantity() < wool) {
									return false;
								}
							} else {
							}
						}
					}
				}
			}
		}
		return true;
	}

	/*
	public boolean isEnoughRessource (Ressource... ressourceARGV) {
		for ( Ressource rARGV : ressourceARGV ) {
			for ( Ressource r : ressource ) {
				if (rARGV.getClass() == r.getClass()) {
					if (rARGV.getQuantity() > r.getQuantity()) {
						System.out.println("-- Pwah assé de reçoursse --");
						return false;
					}
				}
			}
		}
		return true;
	}
	*/

	public ArrayList<Town> getTownList () {
		return townList;
	}

	public void addTownToPlayerList (Town town) {
		townList.add(town);
	}

	public void replaceTownToPlayerList (Town tOld, Town tNew) {
		Town t = null;

		Iterator<Town> i = townList.iterator();

		while (i.hasNext() && t != tOld) {
			t = i.next();
		}

		if (t == tOld) {
			townList.remove(tOld);
			townList.add(tNew);
		}
=======
	
	
	public ArrayList<Ressource> getSpecificRessource( SamPredicate<Ressource> samPredicate) {
		ArrayList<Ressource> list = new ArrayList<>();

		 for (Ressource r : this.ressource) {
			 if (samPredicate.test(r)) {
				 list.add(r);
			 }
		 }

		 return list;
>>>>>>> 357871ea59466bc34309209a81f664baa46c6c96
	}
}
