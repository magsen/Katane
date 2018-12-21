package Katane;

import java.util.ArrayList;


public class Katane {
	private ArrayList<World> world;
	private ArrayList<Player> player;
	private Dice dice;
	private RessourceProductionManager RPM;
	private TradeManager TM;
	private BuildManagerWorker BMW; 	
	
	public Katane() {
		System.out.println("-- Katane --");
		this.instanciate();
	}
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
	}
	public ArrayList<World> getWorld() {
		return world;
	}
	public void setWorld(ArrayList<World> world) {
		this.world = world;
	}
}
