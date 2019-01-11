/*********************************************************************************
*     File Name           :     TileMap.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-05 16:20]
*     Description         :     The data structure to represent the tile map (tile set)
**********************************************************************************/

package Katane;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;

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

	/* Get the tile at specified coordinates */
	public Tile getTile (Coordinates coor) {
		Coordinates c;
		for ( Map.Entry<Coordinates, Tile> entry : tileSet.entrySet()) {
			c = entry.getKey();
			if (c.equals(coor)) {
				return entry.getValue();
			}
		}
		return null; //Not found
	}


	
	public ArrayList<Tile> generateAdjacentTilesFromTownCoordinate (Coordinates coor) {
		ArrayList<Tile> tileList = new ArrayList<Tile>();
		for (Coordinates c : coor.townToAdjacentTiles()) {
			if (isTile(c)) {
				tileList.add(getTile(c));
			}
		}
		// IF THERE IS A tileList WITH A LENGTH < 2, IT's OUT OF THE MAP !
		return tileList;
	}
	public void addTownToTilesTownList (Coordinates coor, Town town) {
		ArrayList<Coordinates> adjTiles = coor.townToAdjacentTiles();
		Tile t = null;
		for (Coordinates c : adjTiles) {
			if (isTile(c)) {
				t = getTile(c);
				t.addToTownList(town);
			}
		}
	}

	public void updateTownToTilesTownList (Coordinates coor, Town tOld, Town tNew) {
		ArrayList<Coordinates> adjTiles = coor.townToAdjacentTiles();
		Tile t = null;
		for (Coordinates c : adjTiles) {
			if (isTile(c)) {
				t = getTile(c);
				t.updateTownToTownList(tOld, tNew);
			}
		}
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
	
	// TAG: 1 TILE
	public Tile[][] tileSetToArray2D () {
		Tile[][] tArray = new Tile[5][5];
		tileSet.forEach( (c, t) -> {
			System.out.println(c.toString());
				int x = c.getXCoordinate();
				int y = c.getYCoordinate();
				int x2;
				if (y % 2 == 0) {
					x2 = y+2;
				} else {
					x2 = y+3;
				}
				System.out.println( "->" + x2 + ", " + (x+2));
				//System.out.println( "->" + (x+2) + ", " + x);
				//tArray[x+2][y+3] = t;
			
		});
		return tArray;
	}
	
	public Tile[][] tileSetToArray2DHC () {
		Tile[][] tArray = new Tile[5][5];
		
		/*
		tArray[0][0] = getTile(new Coordinates(-2, -2));
		tArray[0][1] = getTile(new Coordinates(-2, -1));
		tArray[0][2] = getTile(new Coordinates(-2, 0));
		tArray[1][0] = getTile(new Coordinates(-1, -2));
		tArray[1][1] = getTile(new Coordinates(-1, -1));
		tArray[1][2] = getTile(new Coordinates(-1, 0));
		tArray[2][0] = getTile(new Coordinates(0, -2));
		tArray[2][1] = getTile(new Coordinates(0, -1));
		tArray[2][2] = getTile(new Coordinates(0, 0));
		*/
		


		tArray[0][0] = getTile(new Coordinates(-2, -3));
		tArray[0][1] = getTile(new Coordinates(-2, -2));
		tArray[0][2] = getTile(new Coordinates(-2, -1));
		tArray[0][3] = getTile(new Coordinates(-2, 0));
		tArray[0][4] = getTile(new Coordinates(-2, 1));
		tArray[1][0] = getTile(new Coordinates(-1, -3));
		tArray[1][1] = getTile(new Coordinates(-1, -2));
		tArray[1][2] = getTile(new Coordinates(-1, -1));
		tArray[1][3] = getTile(new Coordinates(-1, 0));
		tArray[1][4] = getTile(new Coordinates(-1, 1));
		tArray[2][0] = getTile(new Coordinates(0, -2));
		tArray[2][1] = getTile(new Coordinates(0, -1));
		tArray[2][2] = getTile(new Coordinates(0, 0));
		tArray[2][3] = getTile(new Coordinates(0, 1));
		tArray[2][4] = getTile(new Coordinates(0, 2));
		tArray[3][0] = getTile(new Coordinates(1, -2));
		tArray[3][1] = getTile(new Coordinates(1, -1));
		tArray[3][2] = getTile(new Coordinates(1, 0));
		tArray[3][3] = getTile(new Coordinates(1, 1));
		tArray[3][4] = getTile(new Coordinates(1, 2));
		tArray[4][0] = getTile(new Coordinates(2, -1));
		tArray[4][1] = getTile(new Coordinates(2, 0));
		tArray[4][2] = getTile(new Coordinates(2, 1));
		tArray[4][3] = getTile(new Coordinates(2, 2));
		tArray[4][4] = getTile(new Coordinates(2, 3));
		
		return tArray;
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
		coordinates = new Coordinates(0,0);
		
		// The desert Tile in the center
		tileSet.put(coordinates, new Tile(0));

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
		/*
		coordinates = new Coordinates(-2,-3);
		tileSet.put(coordinates, new Tile(true));
		coordinates = new Coordinates(2, -1);
		tileSet.put(coordinates, new Tile(true));
		coordinates = new Coordinates(-2, 1);
		tileSet.put(coordinates, new Tile(true));
		coordinates = new Coordinates(2, 3);
		tileSet.put(coordinates, new Tile(true));
		
		coordinates = new Coordinates(1, 3);
		tileSet.put(coordinates, new Tile(true));
		coordinates = new Coordinates(2, 3);
		tileSet.put(coordinates, new Tile(true));
		*/
		
		// real
		coordinates = new Coordinates(-2,-3);
		tileSet.put(coordinates, new Tile(true));
		coordinates = new Coordinates(-1,-3);
		tileSet.put(coordinates, new Tile(true));
		coordinates = new Coordinates(1,-2);
		tileSet.put(coordinates, new Tile(true));
		coordinates = new Coordinates(-2,1);
		tileSet.put(coordinates, new Tile(true));
		coordinates = new Coordinates(2, -1);
		tileSet.put(coordinates, new Tile(true));
		coordinates = new Coordinates(2, 3);
		tileSet.put(coordinates, new Tile(true));
	}
}
