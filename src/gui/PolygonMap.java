package gui;

import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import javax.swing.*;
import Katane.*;

/**
 * The class graphically represents the entire playing field.
 * 该类以图形方式表示整个地图。
 * 
 * @author LIAO Haoyun
 * 
 */
@SuppressWarnings("serial")
public class PolygonMap extends JLayeredPane {
	/**
	 * The model of the Spielfelds.
	 */
	private World world;
	/**
	 * The X coordinate to which the Starthexagon is placed.
	 */
	private int x;

	/**
	 * The Y coordinate to which the Starthexagon is placed.
	 */
	private int y;
	/**
	 * The radius of the inner circle of a hexagon.
	 */
	private int radius;
	/**
	 * The height of a hexagon.
	 */
	private double hexagonLength;
	/**
	 * The width of a hexagon.
	 */
	private double hexagonWidth;
	/**
	 * Array that represents the entire game world.
	 * Represents a box on the game board. This class contains
  	 * Information about the adjacent nodes, their number, their resource,
  	 * as well as whether or not the rider is on it.
	 */
	private Tile[][] informationMap;
	/**
	 * The hexagon array, which represents a 7x7 hexagon hex.
	 */
	private Hexagon[][] hexagonMap = new Hexagon[7][7];
	/**
	 * Graphic for the port type Orientation southeast.
	 */
	private Image harborTypeSO;
	/**
	 * Graphic for port type Orientation southwest.
	 */
	private Image harborTypeSW;
	/**
	 * Graphic for the port type Orientation West.
	 */
	private Image harborTypeW;
	/**
	 * Graphic for the harbor type Orientation northwest.
	 */
	private Image harborTypeNW;
	/**
	 * Graphic for the port type Orientation northeast.
	 */
	private Image harborTypeNO;
	/**
	 * Graphic for the port type Orientation East.
	 */
	private Image harborTypeO;
	/**
	 * Array that contains the buttons for node selection.
	 */
	private Ellipse2D[] nodesActionAreas;
	/**
	 * Close a tile.
	 */
	private int heightTile;
	/**
	 * Width of a tile.
	 */
	private int widthTile;
	/**
	 * Width of a numbered chit.
	 * 编号chit的宽度。
	 */
	private int widthChit;
	/**
	 * Heel of a numbered chit.
	 */
	private int heightChit;
	/**
	 * Width of a harbor icon.
	 */
	private int widthIcon;
	/**
	 * Heigh of a harbor icon.
	 */
	private int heightIcon;
	/**
	 * Array that contains the buttons of the roads.
	 */
	private Polygon[] roadActionAreas;
	/**
	 * Array containing the individual hexagons in a 1-D-array.
	 */
	private Hexagon[] hexagonMap1D;
	/**
	 * Array that represents the entire game world in a 1-D-array.
	 */
	private Tile[] informationMap1D;
	/**
	 * Vector containing the indices of roads already built.
	 */
	private Vector<Integer> filledRects;
	/**
	 * Vector containing the owner IDs of the roads already built.
	 */
	private Vector<Integer> filledRectsOwner;
	/**
	 * Array that holds the silk threads.
	 */
	private int[] settlementNodes;
	/**
	 * If this value is true, you can see the sitelungen.
	 */
	private boolean showSettlementNodes;
	/**
	 * If this value is true, the stats can be displayed.
	 */
	private boolean showCityNodes;
	/**
	 * The ID of the player in turn.
	 */
	private int currentPlayerID;
	/**
	 * In this vector, the streets are kept.
	 */
	private Vector<Integer> streets;
	/**
	 * If this value is true, the penalty will be displayed.
	 */
	private boolean showStreets;
	/**
	 * Array that holds the city.
	 */
	private int[] cityNodes;
	/**
	 * Width of a non-vertical road.
	 */
	private double imageRoadWidth_NotVert;
	/**
	 * Heights of a non-vertical road.
	 */
	private double imageRoadHeight_NotVert;

	/**
	 * Width of a vertical road
	 */
	private double imageRoadWidth_Vert;
	/**
	 * Heights of a vertical road
	 */
	private double imageRoadHeight_Vert;
	/**
	 * Heel of a knot
	 */
	private double nodeHeight;

	/**
	 * Constructor of hexagon playfield.
	 * 
	 * @param world
	 *            The model data of the world.
	 * @param x
	 *            X coordinate of the point where the first hexagon is drawn.
	 * @param y
	 *            Y coordinate of the point where the first hexagon is drawn.
	 * @param radius
	 *            Half width of a hexagon.
	 */
	public PolygonMap(World world, final int x, final int y, int radius) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.radius = radius;
		showStreets = false;
		streets = new Vector<Integer>();
		filledRects = new Vector<Integer>();
		filledRectsOwner = new Vector<Integer>();
		settlementNodes = new int[0*world.getNodes().length];  //uncomment it !
		cityNodes = new int[0*world.getNodes().length];
		hexagonLength = 2 * radius;
		hexagonWidth = 2 * radius * Math.sin(Math.PI * (1.0 / 3.0));
		roadActionAreas = new Polygon[0*world.getRoads().length];
		nodesActionAreas = new Ellipse2D[0*world.getNodes().length];
		hexagonMap1D = new Hexagon[hexagonMap[0].length * hexagonMap.length];
		informationMap1D = new Tile[hexagonMap[0].length * hexagonMap.length];
		init();
	}

	/**
	 * Returns a road enigma
	 * 
	 * @param index
	 *            the Index
	 * @return road
	 */
	public Polygon getRoadActionAreas(int index) {
		return roadActionAreas[index];
	}

	/**
	 * Initializes the 1-D-array hiding the hexagon.
	 */
	public void initHexagonMap1D() {
		int k = 0;
		for (int i = 0; i < hexagonMap[0].length; i++) {
			for (int j = 0; j < hexagonMap[0].length; j++) {
				hexagonMap1D[k] = hexagonMap[j][i];
				k++;
			}
		}
	}

	/**
	 * Initializes the 1-D-array that holds the tiles from the world.
	 */
	public void initInformationMap1D() {
		int k = 0;
		for (int j = 0; j < informationMap[0].length; j++) {
			for (int i = 0; i < informationMap[0].length; i++) {
				informationMap1D[k] = informationMap[i][j];
				k++;
			}
		}
	}

	/**
	 * Returns the clicked node.
	 * 
	 * @param xCoord
	 *            the X coordinate of the mouse click
	 * @param yCoord
	 *            the y-coordinate of the mouse click
	 * @return the clicked node
	 */
	/*
	public Node getClickedNode(int xCoord, int yCoord) {// store status of node
		for (int i = 0; i < nodesActionAreas.length; i++) {
			if (nodesActionAreas[i].contains(xCoord - x, yCoord - y))
				return world.getNodes()[i];
		}
		return null;
	}
	*/

	/**
	 * Returns the clicked street.
	 * 
	 * @param xCoord
	 *            the X coordinate of the mouse click
	 * @param yCoord
	 *            the Y coordinate of the mouse click
	 * @return
	 */
	
	public Road getClickedRoad(int xCoord, int yCoord) {
		for (int i = 0; i < roadActionAreas.length; i++) {
			if (roadActionAreas[i].contains(xCoord - x, yCoord - y))
				return world.getRoads()[i];
		}
		return null;
	}
	

	/**
	 * Returns the index in the array 
	 * that contains the road polygons at the clicked location
	 * 
	 * @param xCoord
	 *            the X coordinate of the mouse click
	 * @param yCoord
	 *            the Y coordinate of the mouse click
	 * @return the index in the array
	 */
	public int getRoadActionAreasOfIndex(int xCoord, int yCoord) {
		for (int i = 0; i < roadActionAreas.length; i++) {
			if (roadActionAreas[i].contains(xCoord, yCoord)) {
				return i;
			}
		}
		return (Integer) null;
	}

	/**
	 * Returns the clicked node surface.
	 * 
	 * @param xCoord
	 *            the X coordinate of the mouse click
	 * @param yCoord
	 *            the Y coordinate of the mouse click
	 * @return the clicked node surface
	 */
	public Ellipse2D getActionArea(int xCoord, int yCoord) {
		for (int i = 0; i < nodesActionAreas.length; i++) {
			if (nodesActionAreas[i].contains(xCoord - x, yCoord - y)) {
				return nodesActionAreas[i];
			}
		}
		return null;
	}

	/**
	 * Returns the clicked hexagon.
	 * 
	 * @param xCoord
	 *            X coordinate of the mouse click
	 * @param yCoord
	 *            Y-coordinate of the mouse click
	 * @return the clicked hexagon.
	 */
	public Hexagon getTileActionAreas(int xCoord, int yCoord) {
		for (int i = 0; i < hexagonMap1D.length; i++) {
			if (hexagonMap1D[i].contains(xCoord - x, yCoord - y)) {
				return hexagonMap1D[i];
			}
		}
		return null;
	}

	/**
	 * Returns the index in the array that harbors the hexagons 
	 * at the clicked location
	 * 
	 * @param xCoord
	 *            X coordinate of the mouse click
	 * @param yCoord
	 *            Y-coordinate of the mouse click
	 * @return the index in the array
	 */
	public int getTileActionAreasOfIndex(int xCoord, int yCoord) {
		for (int i = 0; i < hexagonMap1D.length; i++) {
			if (hexagonMap1D[i].contains(xCoord - x, yCoord - y)) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Returns the overlay of a tile that was clicked.
	 * 
	 * @param xCoord
	 *            X-coordinate of the mouse click
	 * @param yCoord
	 *            Y-coordinate of the mouse click
	 * @return the clicked field
	 */
	public Rectangle getRobberActionAreas(int xCoord, int yCoord) {
		for (int i = 0; i < hexagonMap1D.length; i++) {
			if (hexagonMap1D[i].getTileActionArea().contains(xCoord - x,
					yCoord - y)) {
				return hexagonMap1D[i].getTileActionArea();
			}
		}
		return null;
	}

	/**
	 * Returns the clicked road polygon.
	 * 
	 * @param xCoord
	 *            X-coordinate of the mouse click
	 * @param yCoord
	 *            Y-coordinate of the mouse click
	 * @return the road polygon
	 */
	public Polygon getRoadActionAreas(int xCoord, int yCoord) {
		for (int j = 0; j < hexagonMap[0].length; j++) {
			for (int i = 0; i < hexagonMap[0].length; i++) {
				for (int k = 0; k < hexagonMap[i][j].getRoadRects().length; k++) {
					if (hexagonMap[i][j].getRoadRectOfIndex(k).contains(
							xCoord - x, yCoord - y)) {
						return hexagonMap[i][j].getRoadRectOfIndex(k);
					}
				}
			}
		}
		return null;
	}

	/**
	 * Initializes the PolygonMap and thus the visible world.
	 */
	public void init() {
		int width = (int) Toolkit.getDefaultToolkit().getScreenSize()
				.getWidth();
		int height = (int) Toolkit.getDefaultToolkit().getScreenSize()
				.getHeight();
		// Filling the hexagon array with the associated hexagons
		int xx =  (width / 10) * 3 + this.x;
		int xxx = (int)(hexagonWidth / 2.0);
		for (int i = 0; i < hexagonMap.length; i++) {
			for (int j = 0; j < hexagonMap[0].length; j++) {
				if (i % 2 == 0) {					
					int x = (j * (int)(hexagonWidth)) + xx;
					int y = (int) (this.y + height / 15 + i * 3.0 / 4.0
							* hexagonLength);
					hexagonMap[i][j] = new Hexagon(x, y, radius);
				} else {					
					int x = ((j * (int)(hexagonWidth) + xx) - xxx);
					int y = (int) (this.y + height / 15 + i * 3.0 / 4.0
							* hexagonLength);
					hexagonMap[i][j] = new Hexagon(x, y, radius);
				}
			}
		}
		imageRoadWidth_NotVert = hexagonMap[0][0].getImageRoadWidth_NotVert();
		imageRoadHeight_NotVert = hexagonMap[0][0].getImageRoadHeight_NotVert();

		imageRoadWidth_Vert = hexagonMap[0][0].getImageRoadWidth_Vert();
		imageRoadHeight_Vert = hexagonMap[0][0].getImageRoadHeight_Vert();

		// Dynamic scaling of graphic components 
		widthTile = (int) hexagonMap[0][0].getBounds().getWidth();
		heightTile = (int) hexagonMap[0][0].getBounds().getHeight();
		widthChit = (int) (hexagonMap[0][0].getBounds().getWidth() * 1.0 / 3.0);
		heightChit = widthChit;
		widthIcon = (int) (hexagonMap[0][0].getBounds().getWidth() * 1.0 / 2.0);
		heightIcon = (int) (hexagonMap[0][0].getBounds().getHeight() * 1.0 / 2.0);
		informationMap = (world.getTileSet()).tileSetToArray2DHC();
		//informationMap = world.getTileSet().getTiles();
		// TAG: 1 TILE
		
		
		 // Assignment of the buttons of the nodes, 
		 // to the corresponding nodes in the hexagonmap
		 // 将节点的按钮分配给hexagonmap中的相应节点
		 
		
		for (int i = 0; i < 0*informationMap.length; i++) {////////
			for (int j = 0; j < 0*informationMap[0].length; j++) {////////////
				if (informationMap[i][j] != null) {
					System.out.println(i + y);
					for (int k = 0; k < informationMap[i][j].getNodes().length; k++) {
						if (informationMap[i][j] != null && nodesActionAreas[world.getNodeInArray(informationMap[i][j].getNodeOfIndex(k))] == null) {
							nodesActionAreas[world.getNodeInArray(informationMap[i][j].getNodeOfIndex(k))] = hexagonMap[i][j].getNodeCircleOfIndex(k);
						}
					}
				}
			}
		}
		

		nodeHeight = 5; //nodesActionAreas[0].getHeight(); //HIHI
		//
		 // Initialization of the RoadActionAreas
		 //
		Road[] worldRoads = world.getRoads();
		for (int i = 0; i < 0*worldRoads.length; i++) {/////////////
			int startNodeIndex = world.getNodeIndex(world.getTownSet().getTown(worldRoads[i].getStart())); // On veut récupérer les 2 villes au début et aux extrémités de la route
			int endNodeIndex = world.getNodeIndex(world.getTownSet().getTown(worldRoads[i].getEnd()));
			double startX = nodesActionAreas[startNodeIndex].getCenterX();
			double startY = nodesActionAreas[startNodeIndex].getCenterY();
			double endX = nodesActionAreas[endNodeIndex].getCenterX();
			double endY = nodesActionAreas[endNodeIndex].getCenterY();
			double newX = 0;
			double newY = 0;

			if (startY > endY) {
				newY = startY - (startY - endY) / 2.0;
				//worldRoads[i].setDirection(Constants.ROAD_UP);
			}
			if (startY < endY) {
				newY = endY - (endY - startY) / 2.0;
				//worldRoads[i].setDirection(Constants.ROAD_DOWN);
			}
			if (endX - startX > 3.0) {
				newX = endX - (endX - startX) / 2.0;
			}
			if (endX - startX < 3.0) {
				newX = startX - (startX - endX) / 2.0;
				//worldRoads[i].setDirection(Constants.ROAD_VERT);
			}

			for (int j = 0; j < informationMap.length; j++) {
				for (int k = 0; k < informationMap[0].length; k++) {
					Polygon[] roadRects = hexagonMap[j][k].getRoadRects();
					for (int k2 = 0; k2 < roadRects.length; k2++) {
						if (roadRects[k2].contains(newX, newY)) {
							if (roadActionAreas[i] == null) {
								roadActionAreas[i] = roadRects[k2];
							}
						}
					}
				}
			}
		}
		initInformationMap1D();
		initHexagonMap1D();
		
	}
	/**
	 * Draws all relevant elements of the PolygonMap.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphic = (Graphics2D) g;
		graphic.setStroke(new BasicStroke(2f));
		graphic.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		// Draw the tiles with hides and raw materials 
		for (int i = 0; i < informationMap.length; i++) {
			for (int j = 0; j < informationMap[0].length; j++) {
				Image img = null;
				Image img2 = ImportImages.lumberBtn;
				Image img3 = null;
				
				// removes
				if (informationMap[i][j].isDesert()) {
					img = ImportImages.desert;
				} else {
					if (informationMap[i][j].isWater()) {
					img = null;
					} else {
						img2 = null;
						if (informationMap[i][j].getRessource() instanceof Brick) {
							img = ImportImages.brick;
						} else {
							if (informationMap[i][j].getRessource() instanceof Grain) {
								img = ImportImages.grain;
							} else {
								if (informationMap[i][j].getRessource() instanceof Ore) {
									img = ImportImages.ore;
								} else {
									if (informationMap[i][j].getRessource() instanceof Wood) {
										img = ImportImages.lumber;
									} else {
										img = ImportImages.wool;
									}
								}
							}
						}
					}
				}

				/*
				switch (informationMap[i][j].getRessource()) {
				case Constants.BRICK:
					img = ImportImages.brick;
					break;
				case Constants.LUMBER:
					img = ImportImages.lumber;
					break;
				case Constants.ORE:
					img = ImportImages.ore;
					break;
				case Constants.GRAIN:
					img = ImportImages.grain;
					break;
				case Constants.WOOL:
					img = ImportImages.wool;
					break;
				case Constants.DESERT:
					img = ImportImages.desert;
					break;
				case Constants.HARBOR:
					img2 = ImportImages.questionBtn;
					break;
				case Constants.LUMBERHARBOR:
					img2 = ImportImages.lumberBtn;
					break;
				case Constants.GRAINHARBOR:
					img2 = ImportImages.grainBtn;
					break;
				case Constants.OREHARBOR:
					img2 = ImportImages.oreBtn;
					break;
				case Constants.BRICKHARBOR:
					img2 = ImportImages.brickBtn;
					break;
				case Constants.WOOLHARBOR:
					img2 = ImportImages.woolBtn;
					break;

				default:
					break;
				}*/

				if (img != null) {
					g.drawImage(img, (int) hexagonMap[i][j].getBounds().getX(),
							(int) hexagonMap[i][j].getBounds().getY(),
							widthTile, heightTile, this);
				}
				if (img2 != null) {
					if (informationMap[i][j].isBrigand()) {
						g.drawImage(
							img2,
							(int) (hexagonMap[i][j].getBounds().getCenterX() - heightIcon / 3.2),
							(int) (hexagonMap[i][j].getBounds().getCenterY() - widthIcon / 3.2),
							(int) (widthIcon * 0.7), (int) (widthIcon * 0.7),
							this);
					}
				}
				// Draw the individual numbers on the tiles 
				switch (informationMap[i][j].getTileNumber()) {
				case 2:
					img3 = ImportImages.chit02;
					break;
				case 3:
					img3 = ImportImages.chit03;
					break;
				case 4:
					img3 = ImportImages.chit04;
					break;
				case 5:
					img3 = ImportImages.chit05;
					break;
				case 6:
					img3 = ImportImages.chit06;
					break;
				case 8:
					img3 = ImportImages.chit08;
					break;
				case 9:
					img3 = ImportImages.chit09;
					break;
				case 10:
					img3 = ImportImages.chit10;
					break;
				case 11:
					img3 = ImportImages.chit11;
					break;
				case 12:
					img3 = ImportImages.chit12;
					break;

				default:
					break;
				}
				if (img3 != null) {
					g.drawImage(img3, (int) hexagonMap[i][j].getBounds()
							.getCenterX() - widthChit / 2,
							(int) hexagonMap[i][j].getBounds().getCenterY()
									- heightChit / 2, widthChit, heightChit,
							this);
				}
				if (true) {//world.getRobberTile() == informationMap[i][j]
						/*
						&& informationMap[i][j].getRessource() != Constants.WATER
						&& informationMap[i][j].getRessource() != Constants.WOOLHARBOR
						&& informationMap[i][j].getRessource() != Constants.BRICKHARBOR
						&& informationMap[i][j].getRessource() != Constants.OREHARBOR
						&& informationMap[i][j].getRessource() != Constants.LUMBERHARBOR
						&& informationMap[i][j].getRessource() != Constants.GRAINHARBOR
						&& informationMap[i][j].getRessource() != Constants.HARBOR*/
					Image img1 = ImportImages.robber;
					g.drawImage(img1, (int) hexagonMap[i][j]
							.getTileActionArea().getBounds().getCenterX()
							- (widthTile / 3 + widthTile / 10),
							(int) hexagonMap[i][j].getTileActionArea()
									.getBounds().getCenterY()
									- heightTile / 3, widthTile / 2,
							widthTile / 2, this);
				}
			}
		}

		harborTypeSO = ImportImages.harbor;
		harborTypeSW = ImportImages.harbor1;
		harborTypeW = ImportImages.harbor2;
		harborTypeNW = ImportImages.harbor3;
		harborTypeNO = ImportImages.harbor4;
		harborTypeO = ImportImages.harbor5;
		// Drawing the different port types (alignments) 
		graphic.drawImage(harborTypeSO, (int) hexagonMap[0][1].getBounds()
				.getX(), (int) hexagonMap[0][1].getBounds().getY(), widthTile,
				heightTile, this);
		graphic.drawImage(harborTypeSW, (int) hexagonMap[0][3].getBounds()
				.getX(), (int) hexagonMap[0][3].getBounds().getY(), widthTile,
				heightTile, this);
		graphic.drawImage(harborTypeSW, (int) hexagonMap[1][5].getBounds()
				.getX(), (int) hexagonMap[1][5].getBounds().getY(), widthTile,
				heightTile, this);
		graphic.drawImage(harborTypeO, (int) hexagonMap[2][0].getBounds()
				.getX(), (int) hexagonMap[2][0].getBounds().getY(), widthTile,
				heightTile, this);
		graphic.drawImage(harborTypeW, (int) hexagonMap[3][6].getBounds()
				.getX(), (int) hexagonMap[3][6].getBounds().getY(), widthTile,
				heightTile, this);
		graphic.drawImage(harborTypeO, (int) hexagonMap[4][0].getBounds()
				.getX(), (int) hexagonMap[4][0].getBounds().getY(), widthTile,
				heightTile, this);
		graphic.drawImage(harborTypeNW, (int) hexagonMap[5][5].getBounds()
				.getX(), (int) hexagonMap[5][5].getBounds().getY(), widthTile,
				heightTile, this);
		graphic.drawImage(harborTypeNO, (int) hexagonMap[6][1].getBounds()
				.getX(), (int) hexagonMap[6][1].getBounds().getY(), widthTile,
				heightTile, this);
		graphic.drawImage(harborTypeNW, (int) hexagonMap[6][3].getBounds()
				.getX(), (int) hexagonMap[6][3].getBounds().getY(), widthTile,
				heightTile, this);
		graphic.setColor(new Color(139, 69, 19));
		Road[] worldRoads = world.getRoads();		
		 // Drawing the street
		/*
		for (int i = 0; i < 0*worldRoads.length; i++) {
			Image roadImage = null;
			int roadOwner = worldRoads[i].getOwnerNumber();
			double x_Start = 0;
			double y_Start = 0;
			double height = 0;
			double width = 0;
			if (roadOwner != Constants.NOBODY) {
				Color playerColor = world.getSettler(roadOwner).getColor();
				switch (worldRoads[i].getDirection()) {
				case Constants.ROAD_DOWN:
					x_Start = nodesActionAreas[worldRoads[i].getStart()
							.getIndex()].getCenterX();
					y_Start = nodesActionAreas[worldRoads[i].getStart()
							.getIndex()].getCenterY();
					height = imageRoadHeight_NotVert;
					width = imageRoadWidth_NotVert;
					if (playerColor.equals(Color.RED)) {
						roadImage = ImportImages.red_road_down;
					}
					if (playerColor.equals(Color.GREEN)) {
						roadImage = ImportImages.green_road_down;
					}
					if (playerColor.equals(Color.BLUE)) {
						roadImage = ImportImages.blue_road_down;
					}
					if (playerColor.equals(Color.YELLOW)) {
						roadImage = ImportImages.yellow_road_down;
					}

					break;

				case Constants.ROAD_UP:
					x_Start = nodesActionAreas[worldRoads[i].getStart()
							.getIndex()].getCenterX();
					y_Start = nodesActionAreas[worldRoads[i].getEnd()
							.getIndex()].getCenterY();
					height = imageRoadHeight_NotVert;
					width = imageRoadWidth_NotVert;
					if (playerColor.equals(Color.RED)) {
						roadImage = ImportImages.red_road_up;
					}
					if (playerColor.equals(Color.GREEN)) {
						roadImage = ImportImages.green_road_up;
					}
					if (playerColor.equals(Color.BLUE)) {
						roadImage = ImportImages.blue_road_up;
					}
					if (playerColor.equals(Color.YELLOW)) {
						roadImage = ImportImages.yellow_road_up;
					}

					break;

				case Constants.ROAD_VERT:
					height = imageRoadHeight_Vert + nodeHeight;
					width = imageRoadWidth_Vert / 2;
					x_Start = nodesActionAreas[worldRoads[i].getStart()
							.getIndex()].getX() + width / 2;
					y_Start = nodesActionAreas[worldRoads[i].getStart()
							.getIndex()].getY() + nodeHeight / 2;
					if (playerColor.equals(Color.RED)) {
						roadImage = ImportImages.red_road_vert;
					}
					if (playerColor.equals(Color.GREEN)) {
						roadImage = ImportImages.green_road_vert;
					}
					if (playerColor.equals(Color.BLUE)) {
						roadImage = ImportImages.blue_road_vert;
					}
					if (playerColor.equals(Color.YELLOW)) {
						roadImage = ImportImages.yellow_road_vert;
					}

					break;

				default:
					break;
				}
				if (roadImage != null) {

					g.drawImage(roadImage, (int) x_Start, (int) y_Start,
							(int) width, (int) height, this);
				}
			}
		}*/
		// Draw the road preview 
		if (isShowStreets()) {
			graphic.setComposite(AlphaComposite.getInstance(
					AlphaComposite.SRC_OVER, 0.4f));
			for (int i = 0; i < 0 * streets.size(); i++) { //////

				if (currentPlayerID == 0) {

					graphic.setColor(Color.BLUE);
					graphic.fill(roadActionAreas[streets.elementAt(i)]);
				} else if (currentPlayerID == 1) {
					graphic.setColor(Color.GREEN);
					graphic.fill(roadActionAreas[streets.elementAt(i)]);
				} else if (currentPlayerID == 2) {
					graphic.setColor(Color.RED);
					graphic.fill(roadActionAreas[streets.elementAt(i)]);
				} else if (currentPlayerID == 3) {
					graphic.setColor(Color.YELLOW);
					graphic.fill(roadActionAreas[streets.elementAt(i)]);
				} else {

				}

			}
		}
		// Draw the existing buildings 
		for (int i = 0; i < informationMap[0].length; i++) {
			for (int j = 0; j < informationMap.length; j++) {
				for (int k = 0; k < world.getNodes().length; k++) {
					Image city = null;
					Image settlement = null;

					if (world.getNodes()[k] instanceof TimeTown) { // Constants.CITY) {
						if (world.getNodes()[k].getOwnerNumber() == 0) {
							city = ImportImages.cityBlue;
						} else if (world.getNodes()[k].getOwnerNumber() == 1) {
							city = ImportImages.cityGreen;
						} else if (world.getNodes()[k].getOwnerNumber() == 2) {
							city = ImportImages.cityRed;
						} else if (world.getNodes()[k].getOwnerNumber() == 3) {
							city = ImportImages.cityYellow;
						}
						if (city != null) {
							g.drawImage(city,
									(int) getNodesActionAreaOfIndex(k)
											.getBounds().getCenterX()
											- (int) nodeHeight,
									(int) getNodesActionAreaOfIndex(k)
											.getBounds().getCenterY()
											- (int) nodeHeight,
									(int) nodeHeight * 2, (int) nodeHeight * 2,
									this);
						}
					}

					else if (world.getNodes()[k] instanceof Dolorean) { //  == Constants.SETTLEMENT) {
						if (world.getNodes()[k].getOwnerNumber() == 0) {
							settlement = ImportImages.settlementBlue;
						} else if (world.getNodes()[k].getOwnerNumber() == 1) {
							settlement = ImportImages.settlementGreen;
						} else if (world.getNodes()[k].getOwnerNumber() == 2) {
							settlement = ImportImages.settlementRed;
						} else if (world.getNodes()[k].getOwnerNumber() == 3) {
							settlement = ImportImages.settlementYellow;
						}
						if (settlement != null) {
							int height = (int) (nodeHeight * 2.0 / 3.0);
							/*g.drawImage(settlement,
									(int) getNodesActionAreaOfIndex(k)
											.getBounds().getCenterX()
											- (height),
									(int) getNodesActionAreaOfIndex(k)
											.getBounds().getCenterY()
											- (height), (height * 2),
									(height * 2), this); */
						}
					}
				}
			}
		}
		// Draw the settlement preview 
		if (isShowSettlementNodes()) {
			Image settlement = null;
			for (int i = 0; i < world.getNodes().length; i++) {
				if (currentPlayerID == 0) {
					settlement = ImportImages.settlementBlue;
				} else if (currentPlayerID == 1) {
					settlement = ImportImages.settlementGreen;
				} else if (currentPlayerID == 2) {
					settlement = ImportImages.settlementRed;
				} else if (currentPlayerID == 3) {
					settlement = ImportImages.settlementYellow;
				} else {

				}
				if (getSettlementNodes(i) != -1) {
					int height = (int) (nodeHeight * 2.0 / 3.0);
					graphic.setComposite(AlphaComposite.getInstance(
							AlphaComposite.SRC_OVER, 0.4f));
					g.drawImage(settlement,
							(int) nodesActionAreas[getSettlementNodes(i)]
									.getBounds().getCenterX() - (height),
							(int) nodesActionAreas[getSettlementNodes(i)]
									.getBounds().getCenterY() - (height),
							(height * 2), (height * 2), this);
					graphic.setComposite(AlphaComposite.getInstance(
							AlphaComposite.SRC_OVER, 0.4f));
				}
			}
		}
		// Draw the progress report
		if (isShowCityNodes()) {
			Image city = null;
			for (int i = 0; i < world.getNodes().length; i++) {
				if (currentPlayerID == 0) {
					city = ImportImages.cityBlue;
				} else if (currentPlayerID == 1) {
					city = ImportImages.cityGreen;
				} else if (currentPlayerID == 2) {
					city = ImportImages.cityRed;
				} else if (currentPlayerID == 3) {
					city = ImportImages.cityYellow;
				} else {

				}
				if (getCityNodes(i) != -1) {
					graphic.setComposite(AlphaComposite.getInstance(
							AlphaComposite.SRC_OVER, 0.8f));
					g.drawImage(city, (int) nodesActionAreas[getCityNodes(i)]
							.getBounds().getCenterX() - (int) nodeHeight,
							(int) nodesActionAreas[getCityNodes(i)].getBounds()
									.getCenterY() - (int) nodeHeight,
							(int) nodeHeight * 2, (int) nodeHeight * 2, this);
					graphic.setComposite(AlphaComposite.getInstance(
							AlphaComposite.SRC_OVER, 0.4f));
				}

			}
		}
	}
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getHeightTile() {
		return heightTile;
	}

	public void setHeightTile(int heightTile) {
		this.heightTile = heightTile;
	}

	public int getWidthTile() {
		return widthTile;
	}

	public void setWidthTile(int widthTile) {
		this.widthTile = widthTile;
	}

	public Ellipse2D[] getNodesActionAreas() {
		return nodesActionAreas;
	}

	public double getHexagonLength() {
		return hexagonLength;
	}

	public double getHexagonWidth() {
		return hexagonWidth;
	}

	public Ellipse2D getNodesActionAreaOfIndex(int index) {
		return nodesActionAreas[index];
	}

	@SuppressWarnings("unused")
	private int getFilledRectsOwner(int index) {
		return filledRectsOwner.elementAt(index);
	}

	public void addRoad(int roadIndex, int id) {
		filledRectsOwner.add(id);
		filledRects.add(roadIndex);
	}

	public Polygon getFilledRects(int index) {
		return roadActionAreas[filledRects.elementAt(index)];
	}

	public void setFilledRects(Vector<Integer> filledRects) {
		this.filledRects = filledRects;
	}

	public Tile getTile(int x, int y) {
		int index = getTileActionAreasOfIndex(x, y);
		if(index == -1)
			return null;
		return informationMap1D[getTileActionAreasOfIndex(x, y)];
	}

	public void setSettlementNodes(int index, int value) {
		settlementNodes[index] = value;
	}

	public int getSettlementNodes(int index) {
		return settlementNodes[index];
	}

	public boolean isShowSettlementNodes() {
		return showSettlementNodes;
	}

	public void setShowSettlementNodes(boolean showSettlementNodes) {
		this.showSettlementNodes = showSettlementNodes;
	}

	public int getCurrentPlayerID() {
		return currentPlayerID;
	}

	public void setCurrentPlayerID(int currentPlayerID) {
		this.currentPlayerID = currentPlayerID;
	}

	public void resetStreets() {
		streets = new Vector<Integer>();
	}

	public void addStreet(int index) {
		streets.add(index);
	}

	public int getStreet(int index) {
		return streets.elementAt(index);
	}

	public boolean isShowStreets() {
		return showStreets;
	}

	public void setShowStreets(boolean showStreets) {
		this.showStreets = showStreets;
	}

	public void setCityNodes(int index, int value) {
		cityNodes[index] = value;

	}

	public int getCityNodes(int index) {
		return cityNodes[index];
	}

	public boolean isShowCityNodes() {
		return showCityNodes;
	}

	public void setShowCityNodes(boolean showCityNodes) {
		this.showCityNodes = showCityNodes;
	}

	public Hexagon[] getHexagonMap1D() {
		return hexagonMap1D;
	}

}