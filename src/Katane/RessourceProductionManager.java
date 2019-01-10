/*********************************************************************************
*     File Name           :     RessourceProductionManager.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2018-12-26 17:53]
*     Description         :     Handler of the ressource attribution to the players
**********************************************************************************/
package Katane;

import java.util.ArrayList;

/* Handler of the attribution of the ressources to the players */
public class RessourceProductionManager {

	private Katane katane;

	/* Constructor */
	public RessourceProductionManager(Katane katane) {
		this.katane = katane;
	}

	public void attributesRessources () {
		katane.rollDices();
		int i = katane.getTotalDice();
		ArrayList<Player> players = katane.getListPlayer();
		for(Player p : players) {
			for (Town t : p.getTownList()) {
				for (Tile tile : t.getAdjacentTiles()) {
					if (tile.getTileNumber() == i) {
						p.incrementRessource(tile.getRessource());
						if (t instanceof TimeTown) {
							p.incrementRessource(tile.getRessource());
						}
					}
				}
			}
		}
	}
}
