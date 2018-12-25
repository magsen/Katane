package Katane;

public abstract class Building {
	protected Player player;
	protected Tile tile; //can be modified with coordinates
	public Building(Player player, Tile tile) {
		//modif with coord
		this.player = player;
	}
}
