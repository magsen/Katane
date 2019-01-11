/*********************************************************************************
*     File Name           :     Katane.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Description         :     Katane is the main class of the Model part of the Model/Control/View architechture
**********************************************************************************/

package Katane;

import java.awt.Color;
import java.util.ArrayList;

import gui.MainGUI;
import gui.TradeChoosePanel;


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
	private Dice dice1;
	private Dice dice2;
	private RessourceProductionManager RPM;
	private TradeManager TM;
	private BuildManagerWorker BMW;
	private boolean victory;
	private Player currentPlayer;
	private int currentPlayerIndex;
	public MainGUI mainGUI;
	
	/* Constructor */
	public Katane() {
		System.out.println("-- Katane --");
		this.instanciate();
	}

	/* Instanciation of classes */
	private void instanciate() {
		this.dice1 = new Dice(6);
		this.dice2 = new Dice(6);
		this.victory = false;
		this.RPM = new RessourceProductionManager(this);
		this.TM = new TradeManager(this);
		this.BMW = new BuildManagerWorker(this);
		this.world = new ArrayList<>();
		this.world.add(new World());
		this.player = new ArrayList<>();
		this.player.add(new Player(this,1,Color.YELLOW));
		this.player.add(new Player(this,2,Color.BLUE));
		this.player.add(new Player(this,3,Color.RED));
		this.player.add(new Player(this,4,Color.GREEN));
		this.currentPlayerIndex = 4;
		this.currentPlayer = this.getPlayerN(currentPlayerIndex);
		/* Instanciation of GUI */ 
		
		this.mainGUI = new MainGUI(this);
	}
		
		
		/* example of trade 
		int rQuantity[]= {0,0,0,0,0};
		TM.trade(player.get(0), player.get(1) , rQuantity );
		
		 example of lambda generic to get ressource 
		for(Ressource ressource : this.player.get(0).getSpecificRessource((ressource)-> ressource instanceof Wood)){
			((Wood) ressource).hit();
		}
		*/
	

	
	/*
	 * GameRoutine Class
	 * StartTheCurrent Turn
	 * This method is call automaticaly at the end of a turn
	 * 
	 * Link to RessourceProductionManager
	 */
	public void startTurn() {
		this.currentPlayerIndex += 1;
		if(this.currentPlayerIndex > 4) {this.currentPlayerIndex=1;}
		this.currentPlayer = this.getPlayerN(currentPlayerIndex);
		RPM.attributesRessources();
	}
	/*
	 * GameRoutine Class
	 * EndTheCurrent Turn
	 * This method end the turn and auto-call a new turn if there is no victory
	 * 
	 * 
	 */
	public void endTurn() {
		/* Finish the game */
		System.out.println("-- End of a Turn -- Player " +  this.currentPlayerIndex);
		isThereVictory();
		if(victory) {
			victory();/* launche victory event */
		}else {
			startTurn();/* exit and start new turn (avoid recursive call) */
		}
	}





	public void isThereVictory() {
		for(Player p : player) {
			if(p.getVictoryPoint()>=10) {
				victory = true;
			}
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

	public ArrayList<Player> getListPlayer () {
		return player;
	}

	public Player getPlayer (int playerNumber) {
		return player.get(playerNumber);
	}

	public Integer getCurrentPlayerIndex () {
		return currentPlayerIndex;
	}

	public int getPlayerNumber(Player player) {
		for (Player p : this.player) {
			if (p == player) {
				return p.getPlayerNumber();
			}
		}
		return -1;
	}

	public Player getCurrentPlayer () {
		return currentPlayer;
	}
	public BuildManagerWorker getBMW () {
		return BMW;
	}
	public TradeManager getTM () {
		return TM;
	}

	public RessourceProductionManager getRPM () {
		return RPM;
	}

	public Player getPlayerN(int index) {
		for(Player player : this.player) {
			if(player.getPlayerNumber()==index) {
				return player;
			}
		}
		return null;
	}
	public void victory() {
		/* victory event*/
	}

	public void rollDices() {
		dice1.roll();
		dice2.roll();
	}
	public int getTotalDice() {
		return dice1.getResult() + dice2.getResult();
	}

	public MainGUI getMainGUI() {
		return this.mainGUI;
	}

}
