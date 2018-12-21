package Katane;

public abstract class Town extends Building {

	public Town(Player player, Tile tile) {
		super(player, tile);
		this.tile = tile;
		//must verify that yu can build before add
		this.tile.addBuilding(this);
	}

}
