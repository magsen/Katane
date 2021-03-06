/*********************************************************************************
*     File Name           :     Launcher.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-11 01:21]
*     Description         :     The launcher (main class)
**********************************************************************************/

package Katane;

import java.util.ArrayList; //tmp
import java.util.HashMap; //tmp
import java.util.Scanner;

/* The first class when the programm starts */
public class Launcher {

	/* the main */
	public static void main (String[] args){
		System.out.println("-- Launching the game --");
		@SuppressWarnings("unused")
		Katane katane = new Katane();
		//test(katane);
		//cheatLongestRoad(katane);
		gameConsole(katane);
		//katane.startTurn(); // UNCOMMENT�this

		//katane.getMainGUI().getSupplyPanel().update();
		//test(katane);
	}

	public static void gameConsole (Katane katane) {
		ArrayList<World> listeW = katane.getWorld();
		Player P = katane.getPlayer(0), P2 = katane.getPlayer(1);
		World W = listeW.get(0);
		TownMap townMap = W.getTownSet();
		RoadMap roadMap = W.getRoadSet();
		TileMap tileMap = W.getTileSet();
		HashMap <Coordinates, Town> townSet = townMap.getTownSet();
		HashMap <Coordinates, Road> roadSet = roadMap.getRoadSet();
		HashMap<Coordinates, Tile> tileSet = tileMap.getTileSet();
		BuildManagerWorker bmw = katane.getBMW();
		Scanner scanner = new Scanner(System.in);
		int choice = 0, x = 0, y = 0;
		do {
			System.out.println("Que voulez-vous faire ?\n 1: Build Road\n 2: Build Town\n 3: Build TimeTown\n 5: algorithme de la route la plus longue\n 6: Affiche les ressources du joueur\n 7: Quitter l'interface console");
			try {
				choice = scanner.nextInt();
			} catch (Exception E) {
				System.out.println("Mauvaise entr�e");
			}
			if (choice == 1) {
				System.out.println("Choisissez une coordonn�e x pour installer votre route.\nSi vous tapez 42, vous aurez la liste de toutes les coordonn�es vous pourrez construire vos routes.");
				x = scanner.nextInt();
				if (x == 42) {
					bmw.possibleRoadsBuild(P, W);
				} else {
					System.out.println("Choisissez une coordonn�e y pour installer votre route.");
					y = scanner.nextInt();
					bmw.buildRoad(P, W, new Coordinates(x, y));
				}
			}
			if (choice == 2) {
				System.out.println("Choisissez une coordonn�e x pour installer votre dolorean.x.");
				x = scanner.nextInt();
				System.out.println("Choisissez une coordonn�e y pour installer votre route.");
				y = scanner.nextInt();
				bmw.buildDolorean(P, W, new Coordinates(x, y));
			}
			if (choice == 3) {
				System.out.println("Choisissez une coordonn�e x pour installer votre TimeTown.");
				x = scanner.nextInt();
				System.out.println("Choisissez une coordonn�e y pour installer votre route.");
				y = scanner.nextInt();
				bmw.buildTimeTown(P, W, new Coordinates(x, y));
			}
			if (choice == 4) {
				int brick, grain, ore, wood, wool;
				System.out.println("Choisissez votre quantite de Briques");
			}
			if (choice == 5) {
				System.out.println("1: Souhaitez avoir un exemple de l'application de la route la plus sur un grand �chantillon de route.\n 2: L'appliquer � une de mes route");
				x = scanner.nextInt();
				if (x == 1) {
					cheatLongestRoad(katane);
				}
				if (x == 2) {
					System.out.println("Choisissez une coordonn�e x pour d�marrer l'algorithme");
					x = scanner.nextInt();
					System.out.println("Choisissez une coordonn�e y pour d�marrer l'algorithme");
					y = scanner.nextInt();
					System.out.println(bmw.getLongestRoad(roadMap, P, new Coordinates(x, y)));
				}
			}
			if (choice == 6) {
				P.printRessources();
			}
		} while (x != 7);
	}
	
	public static void cheatLongestRoad(Katane katane) {
		ArrayList<World> listeW = katane.getWorld();
		Player P = katane.getPlayer(0), P2 = katane.getPlayer(1);
		World W = listeW.get(0);
		TownMap townMap = W.getTownSet();
		RoadMap roadMap = W.getRoadSet();
		TileMap tileMap = W.getTileSet();
		HashMap <Coordinates, Town> townSet = townMap.getTownSet();
		HashMap <Coordinates, Road> roadSet = roadMap.getRoadSet();
		HashMap<Coordinates, Tile> tileSet = tileMap.getTileSet();
		BuildManagerWorker bmw = katane.getBMW();
		Coordinates coor = new Coordinates(1, 2);
		
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(1, 2);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(1, 3);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(2, 1);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(2, -1);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(1, 0);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(1, 1);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(1, 4);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(1, 5);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(1, 6);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(1, 7);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(0, 8);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(0, 9);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(0, 7);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(0, 5);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(0, 6);
		bmw.buildRoad(P, W, coor, true);


		//11
		coor.setCoordinates(2, 4);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(2, 3);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(3, 1);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(3, -1);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(2, 0);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(3, -2); //branch
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(2, -1);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(1, 0);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(1, -1);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(0, 0);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(0, -1);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(0, 6); // loop
		bmw.buildRoad(P, W, coor, true);
		
		coor.setCoordinates(1, 2); // loop

		//14

		coor.setCoordinates(1, 3);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(2, 1);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(2, 2);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(3, -2);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(3, -4);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(2, -3);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(2, -2);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(1, -2);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(1, -4);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(0, -3);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(0, -2);
		bmw.buildRoad(P, W, coor, true);
		
		coor.setCoordinates(0, 1);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(0, 2);
		bmw.buildRoad(P, W, coor, true);
		coor.setCoordinates(0, 3);
		bmw.buildRoad(P, W, coor, true);
		
		System.out.println(bmw.getLongestRoad(roadMap, P, coor));
		
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
		int rQuantity[]= {0,-2,-3,-3,-2};
		tm.trade(P, P2 , rQuantity );
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
		//10
		
		
		

		coor.setCoordinates(1,3);


		//townSet.forEach( (c, t) -> {c.print();System.out.println(t.toString());});
		//(bmw.possibleRoadsBuild(P, W)).forEach( (c) -> c.print());
		
		// System.out.println(bmw.explorePath(roadMap, P, coor, new Coordinates(0, 3)));
		System.out.println(bmw.getLongestRoad(roadMap, P, coor));
		System.out.println("-- Init ended --");
		coor.setCoordinates(0, 6);
		System.out.println((coor.roadToAdjacentRoads()).get(2));
		
		RessourceProductionManager rpm = katane.getRPM();
		for (int i = 0; i < 00; i++) {
			rpm.attributesRessources();
			bmw.buildDolorean(P, W, coor);
			bmw.buildTimeTown(P, W, coor);
		}
	}
}
