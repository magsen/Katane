/*********************************************************************************
*     File Name           :     Launcher.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-09 23:38]
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
		katane.startTurn(); // UNCOMMENT this
		test(katane);
	}

	public static void test (Katane katane) {
		ArrayList<World> listeW = katane.getWorld();
		Player P = katane.getPlayer(0), P2 = katane.getPlayer(1);
		World W = listeW.get(0);
		TownMap townMap = W.getTownSet();
		RoadMap roadMap = W.getRoadSet();
		TileMap tileMap = W.getTileSet();
		HashMap <Coordinates, Town> townSet = townMap.getTownSet();
		HashMap <Coordinates, Road> roadSet = roadMap.getRoadSet();
		HashMap<Coordinates, Tile> tileSet = tileMap.getTileSet();

		P.isEnoughRessource(0, 0, 0, 0, 0);

		Coordinates coor;
		Tile tile;
		coor = new Coordinates(2, 2);
		tile = tileMap.getTile(coor);
		tile.print();
		coor.setCoordinates(1, -4);

		/*
		(coor.roadToAdjacentTowns()).forEach((coordinates) -> coordinates.print());
		coor = new Coordinates(1, -3);
		(coor.townToAdjacentTowns()).forEach((coordinates) -> coordinates.print());
		coor = new Coordinates(0, 7);
		(coor.roadToAdjacentRoads()).forEach((coordinates) -> coordinates.print());
		*/
		/* example of trade */
		TradeManager tm = katane.getTM();
		int rQuantity[]= {0,2,-3,3,-2};
		tm.trade(P, P2 , rQuantity );
/*
		BuildManagerWorker bmw = katane.getBMW();
		bmw.buildDolorean(P, W, coor);
		bmw.buildDolorean(P, W, coor);
		bmw.buildTimeTown(P, W, coor);
		bmw.buildTimeTown(P, W, coor);
		coor.setCoordinates(1, 2);
		bmw.buildDolorean(P, W, coor);
		bmw.buildTimeTown(P, W, coor);
		coor.setCoordinates(1, 3);
		bmw.buildDolorean(P2, W, coor);
		//coor.setCoordinates(2, 4);
		bmw.buildRoad(P, W, coor);
		coor.setCoordinates(1, 3);
		bmw.buildRoad(P, W, coor);
		coor.setCoordinates(1, 4);
		bmw.buildRoad(P, W, coor);
		coor.setCoordinates(1, 5);
		bmw.buildRoad(P, W, coor);

		coor.setCoordinates(1, 2);

		townSet.forEach( (c, t) -> {c.print();System.out.println(t.toString());});
		(bmw.possibleRoadsBuild(P, W)).forEach( (c) -> c.print());
		//System.out.println(bmw.explorePath(roadMap, P, coor));
		System.out.println("-- Init ended --");
		coor.setCoordinates(0, 6);
		System.out.println((coor.roadToAdjacentRoads()).get(2));
		
		RessourceProductionManager rpm = katane.getRPM();
		for (int i = 0; i < 00; i++) {
			rpm.attributesRessources();
			bmw.buildDolorean(P, W, coor);
			bmw.buildTimeTown(P, W, coor);
		}
		*/
	}
}
