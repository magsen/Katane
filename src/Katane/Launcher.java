/*********************************************************************************
*     File Name           :     Launcher.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-05 06:12]
*     Description         :     The launcher (main class)
**********************************************************************************/

package Katane;

import java.util.ArrayList; //tmp
import java.util.HashMap; //tmp

/* The first class when the programm starts */
public class Launcher {

	/* the main */
	public static void main (String[] args){
		System.out.println("-- Launching the game --");
		@SuppressWarnings("unused")
		Katane katane = new Katane();
		test(katane);
	}

	public static void test (Katane katane) {
		ArrayList<World> listeW = katane.getWorld();
		Player P = katane.getPlayer(0);
		World W = listeW.get(0);
		TownMap townMap = W.getTownSet();
		TileMap tileMap = W.getTileSet();
		HashMap <Coordinates, Town> townSet = townMap.getTownSet();
		HashMap<Coordinates, Tile> tileSet = tileMap.getTileSet();

		P.isEnoughRessource(0, 0, 0, 0, 0);

		Coordinates coor;
		Tile tile;
		coor = new Coordinates(2, 2);
		tile = tileMap.getTile(coor);
		tile.print();

		/*
		(coor.roadToAdjacentTowns()).forEach((coordinates) -> coordinates.print());
		coor = new Coordinates(1, -3);
		(coor.townToAdjacentTowns()).forEach((coordinates) -> coordinates.print());
		coor = new Coordinates(0, 7);
		(coor.roadToAdjacentRoads()).forEach((coordinates) -> coordinates.print());
		*/
		BuildManagerWorker bmw = katane.getBMW();
		bmw.buildDolorean(P, W, coor);
		bmw.buildDolorean(P, W, coor);
		bmw.buildTimeTown(P, W, coor);
		bmw.buildTimeTown(P, W, coor);
		coor.setCoordinates(1, 1);
		bmw.buildDolorean(P, W, coor);
		bmw.buildTimeTown(P, W, coor);

		townSet.forEach( (c, t) -> {c.print();System.out.println(t.toString());});
	}
}
