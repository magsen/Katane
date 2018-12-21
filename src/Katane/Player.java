package Katane;

import java.util.ArrayList;

public class Player {
	private Katane katane;
	private int number;
	private int VictoryPoint;
	private ArrayList<Ressource> ressource;
	public Player(Katane katane, int i) {
		this.katane = katane;
		this.setNumber(i);
		System.out.println("-- Player "+i+" --");
		this.ressource = new ArrayList<>();
		this.ressource.add(new Wood());
		this.ressource.add(new Wool());
		this.ressource.add(new Brick());
		this.ressource.add(new Ore());
		this.ressource.add(new Grain());
		
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getVictoryPoint() {
		return VictoryPoint;
	}
	public void setVictoryPoint(int victoryPoint) {
		VictoryPoint = victoryPoint;
	}
	public ArrayList<Ressource> getRessource() {
		return ressource;
	}
	public void setRessource(ArrayList<Ressource> ressource) {
		this.ressource = ressource;
	}
	
}
