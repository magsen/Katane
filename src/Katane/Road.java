package Katane;

import java.util.ArrayList;

public class Road extends Building{
	private ArrayList<Road> adjacentRoad;
	public Road(Player player ,Tile tile) {
		super(player, tile);
		this.tile = tile;
		//must verify that yu can build before add
		this.tile.addBuilding(this);
	}
	public ArrayList<Road> getAdjacentRoad() {
		return adjacentRoad;
	}
	public void setAdjacentRoad(ArrayList<Road> adjacentRoad) {
		this.adjacentRoad = adjacentRoad;
	}
}
