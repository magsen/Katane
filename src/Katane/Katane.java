/*********************************************************************************
*     File Name           :     Katane.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-10 00:32]
*     Description         :     Katane is the main class of the Model part of the Model/Control/View architechture
**********************************************************************************/

package Katane;

import java.util.ArrayList;


/* The class Katane
 *      Attributes :
 *      	- List of World (a list to store the multiples boards of this game)
 *      	- List of Player
 *      	- The Dice
 *      	- The RessourceProductionManager (RPM), which handles the attribution of the ressources to players
 *      	- The TradeManager (TM), which handles the trades of ressources between players
 *      	- The BuildManagerWorker (BMW), which handles the building of Towns by the players
 */
public class Katane {

	private ArrayList<World> world;
	private ArrayList<Player> player;
	private Dice dice;
	private RessourceProductionManager RPM;
	private TradeManager TM;
	private BuildManagerWorker BMW;

	/* Constructor */
	public Katane() {
		System.out.println("-- Katane --");
		this.instanciate();
	}

	/* Instanciation of classes */
	private void instanciate() {
		this.dice = new Dice(6);
		this.RPM = new RessourceProductionManager(this);
		this.TM = new TradeManager(this);
		this.BMW = new BuildManagerWorker(this);
		this.world = new ArrayList<>();
		this.world.add(new World());
		this.player = new ArrayList<>();
		this.player.add(new Player(this,1));
		this.player.add(new Player(this,2));
		this.player.add(new Player(this,3));
		this.player.add(new Player(this,4));
		/* Instanciation of GUI */ 
		
		
		
		/* example of trade */
		int rQuantity[]= {0,0,0,0,0};
		TM.trade(player.get(0), player.get(1) , rQuantity );
		
		/* example of lambda generic to get ressource */
		for(Ressource ressource : this.player.get(0).getSpecificRessource((ressource)-> ressource instanceof Wood)){
			((Wood) ressource).hit();
		}
	}

	/* Get a World in the list */
	public ArrayList<World> getWorld() {
		return world;
	}

	/* Set a World in the list */
	public void setWorld(ArrayList<World> world) {
		this.world = world;
	}
<<<<<<< HEAD







	public ArrayList<Player> getListPlayer () {
		return player;
	}

	public Player getPlayer (int playerNumber) {
		return player.get(playerNumber);
	}

	public BuildManagerWorker getBMW () {
		return BMW;
=======
	public Player getPlayerN(int index) {
		for(Player player : this.player) {
			if(player.getPlayerNumber()==index) {
				return player;
			}
		}
		return null;
>>>>>>> 357871ea59466bc34309209a81f664baa46c6c96
	}

	public Player getPlayerN(int index) {
		for(Player player : this.player) {
			if(player.getPlayerNumber()==index) {
				return player;
			}
		}
		return null;
	}
}

