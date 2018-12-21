package Katane;

import java.util.ArrayList;

public class Tile {
	private ArrayList<Building> building;
	public Tile() {
		System.out.println("-- Tile --");
		this.building = new ArrayList<>();
	}

	public void addBuilding(Building building) {
		this.building.add(building);
	}
}
