/*********************************************************************************
*     File Name           :     TileMap.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-04 04:14]
*     Description         :     The data structure to represent the tile map (tile set)
**********************************************************************************/

package Katane;

import java.util.HashMap;
import java.util.Map;

/* Class TileMap is basicly an HashMap that assign to Coordinates (the key), a 2-dimention vector like (x, y), to a Tile (Mapped value). */
public class TileMap extends ObjectMap {

	/* The HashMap of tiles */
	private HashMap<Coordinates, Tile> tileSet;

	/* Constructor */
	public TileMap () {
		tileSet = new HashMap<Coordinates, Tile>();
		createDefaultTileSet();
	}

	public HashMap<Coordinates, Tile> getTileSet () {
		return tileSet;
	}

	/* Get the road at specified coordinates */
	public Tile getTile (Coordinates coor) {
		Coordinates c;
		for ( Map.Entry<Coordinates, Tile> entry : tileSet.entrySet()) {
			c = entry.getKey();
			if (c.equals(coor)) {
				return entry.getValue();
			}
		}
		return null;
	}

	/* Test whether or not there is a road at the specified coordinates */
	public boolean isTile (Coordinates coor) {
		Coordinates c;
		for ( Map.Entry<Coordinates, Tile> entry : tileSet.entrySet()) {
			c = entry.getKey();
			if (c.equals(coor)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * Creates the Tile Set, it is hard-coded (as you can see).
	 *
	 * The default map is an hexagonal map, we start creating it layers after layers,
	 * There are 2 layers to be constructed since the inner center is a non-tile (it just has no coordinates assignements)
	 */
	public void createDefaultTileSet () {

		Coordinates coordinates, coordinatesSav; // the coordinates
		Tile tile;
		int i, j;

		coordinatesSav = new Coordinates();

		/* The exterior "for" is for each hexagonal layer construction
		 * The interiors "for" are for the "borders" of the hexagonal layer */
		for (i = 1; i<3; i++) {
			coordinatesSav.setCoordinates(-i, -i);
			coordinates = new Coordinates(coordinatesSav); /* Since HashMap has references as keys, we have to create a new object for each key. */
			tileSet.put(coordinates, new Tile());
			for (j = 0; j < i; j++) {
				coordinatesSav.incrementCoordinates(1, 0);
				coordinates = new Coordinates(coordinatesSav);
				tileSet.put(coordinates, new Tile());
			}
			for (j = 0; j < i; j++) {
				coordinatesSav.incrementCoordinates(1, 1);
				coordinates = new Coordinates(coordinatesSav);
				tileSet.put(coordinates, new Tile());
			}
			for (j = 0; j < i; j++) {
				coordinatesSav.incrementCoordinates(0, 1);
				coordinates = new Coordinates(coordinatesSav);
				tileSet.put(coordinates, new Tile());
			}
			for (j = 0; j < i; j++) {
				coordinatesSav.incrementCoordinates(-1, 0);
				coordinates = new Coordinates(coordinatesSav);
				tileSet.put(coordinates, new Tile());
			}
			for (j = 0; j < i; j++) {
				coordinatesSav.incrementCoordinates(-1, -1);
				coordinates = new Coordinates(coordinatesSav);
				tileSet.put(coordinates, new Tile());
			}
			for (j = 0; j < i-1; j++) {
				coordinatesSav.incrementCoordinates(0, -1);
				coordinates = new Coordinates(coordinatesSav);
				tileSet.put(coordinates, new Tile());
			}
		}
	}
}
