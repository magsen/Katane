package gui;

import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * Class representing a hexagon.
  Used to detect the tiles on click.
 * @author YU Shijia
 * 
 */
@SuppressWarnings("serial")
public class Hexagon extends Polygon {

	double radius; //半径

	double sin = (Math.sin(Math.PI / 6));
	
	double cos = (Math.cos(Math.PI / 6));
	
	double multSin; //正弦倍半径
	
	double multCos;
	
	double sin30;
	
	double cosin30;

	/*
	 * ActionArea of the first node
	 */
	private Ellipse2D.Double nodeCircleOne;
	
	private Ellipse2D.Double nodeCircleTwo;
	
	private Ellipse2D.Double nodeCircleThree;
	
	private Ellipse2D.Double nodeCircleFour;
	
	private Ellipse2D.Double nodeCircleFive;
	
	private Ellipse2D.Double nodeCircleSix;
	/*
	 * Size the node ActionArea
	 */
	double circleSize;
	/**
	 * Array with the ActionAreas of the nodes
	 */
	private Ellipse2D[] nodeCircles;

	/**
	 * X-coordinate of the first node
	 */
	double pointCircleOne_X;
	
	double pointCircleOne_Y;
	
	double pointCircleTwo_X;
	
	double pointCircleTwo_Y;

	double pointCircleThree_X;
	
	double pointCircleThree_Y;
	
	double pointCircleFour_X;
	
	double pointCircleFour_Y;
	
	double pointCircleFive_X;
	
	double pointCircleFive_Y;
	
	double pointCircleSix_X;
	
	double pointCircleSix_Y;

	/*
	 * ActionArea the first road
	 */
	private Polygon rectOne;
	
	private Polygon rectTwo;
	
	private Polygon rectThree;
	
	private Polygon rectFour;
	
	private Polygon rectFive;
	
	private Polygon rectSix;
	
	/*
	 * Array with the action areas of the streets
	 */
	private Polygon[] roadRects;

	/**
	 * X-coordinate of the first point of the calculation of the Road ActionAreas
	 */
	private double pointOne_X;
	
	private double pointOne_Y;
	
	private double pointTwo_X;
	
	private double pointTwo_Y;
	
	private double pointThree_X;
	
	private double pointThree_Y;
	
	private double pointFour_X;
	
	private double pointFour_Y;
	
	private double pointFive_X;
	
	private double pointFive_Y;
	
	private double pointSix_X;
	
	private double pointSix_Y;

	/**
	 * Radius of the circle
	 */
	private double circleRadius;

	@SuppressWarnings("unused")
	private Image village;
	
	private int x;
	
	private int y;
	/*
	 * ActionArea of the hexagon
	 */
	private Rectangle tileActionArea;

	/*
	 * Width of a non-vertical road
	 */
	private double imageRoadWidth_NotVert;
	
	private double imageRoadHeight_NotVert;
	/*
	 * Width of a vertical road
	 */
	private double imageRoadWidth_Vert;
	
	private double imageRoadHeight_Vert;

	//Constructor of the hexagon
	public Hexagon(int x, int y, int radius) {
		this.x = x;
		this.y = y;
		this.radius = radius;
		multSin = sin * radius;
		multCos = cos * radius;
		sin30 = 1.0 / 2.0;
		cosin30 = 1.0 / 2.0 * Math.sqrt(3);
		circleSize = radius / 3.0;
		circleRadius = circleSize / 2.0;
		tileActionArea = new Rectangle(radius, radius);
		initHexagon();

		initActionAreaNodes();

		initActionAreaRoads();
		initTileActionAreas();

	}

	public void initTileActionAreas() {
		tileActionArea.setBounds((int) super.getBounds().getCenterX()
				- getRadius() / 2, (int) super.getBounds().getCenterY()
				- getRadius() / 2, getRadius(), getRadius());

	}

	public void initHexagon() {

		pointOne_X = (x);
		pointOne_Y = (y - radius);
		pointTwo_X = (x + multCos);
		pointTwo_Y = (y - multSin);
		pointThree_X = (x + multCos);
		pointThree_Y = (y + multSin);
		pointFour_X = (x);
		pointFour_Y = (y + radius);
		pointFive_X = (x - multCos);
		pointFive_Y = (y + multSin);
		pointSix_X = (x - multCos);
		pointSix_Y = (y - multSin);

		imageRoadWidth_NotVert = (pointOne_X - pointSix_X);
		imageRoadHeight_NotVert = (pointSix_Y - pointOne_Y);

		addPoint((int) pointOne_X, (int) pointOne_Y);
		addPoint((int) pointTwo_X, (int) pointTwo_Y);
		addPoint((int) pointThree_X, (int) pointThree_Y);
		addPoint((int) pointFour_X, (int) pointFour_Y);
		addPoint((int) pointFive_X, (int) pointFive_Y);
		addPoint((int) pointSix_X, (int) pointSix_Y);
	}

	public void initActionAreaNodes() {
		village = ImportImages.testvillage;

		pointCircleOne_X = (pointOne_X - circleRadius);
		pointCircleOne_Y = (pointOne_Y - circleRadius);
		pointCircleTwo_X = (pointTwo_X - circleRadius);
		pointCircleTwo_Y = (pointTwo_Y - circleRadius);
		pointCircleThree_X = (pointThree_X - circleRadius);
		pointCircleThree_Y = (pointThree_Y - circleRadius);
		pointCircleFour_X = (pointFour_X - circleRadius);
		pointCircleFour_Y = (pointFour_Y - circleRadius);
		pointCircleFive_X = (pointFive_X - circleRadius);
		pointCircleFive_Y = (pointFive_Y - circleRadius);
		pointCircleSix_X = (pointSix_X - circleRadius);
		pointCircleSix_Y = (pointSix_Y - circleRadius);

		nodeCircles = new Ellipse2D[6];

		nodeCircleOne = new Ellipse2D.Double(pointCircleOne_X,
				pointCircleOne_Y, circleSize, circleSize);
		nodeCircleTwo = new Ellipse2D.Double(pointCircleTwo_X,
				pointCircleTwo_Y, circleSize, circleSize);
		nodeCircleThree = new Ellipse2D.Double(pointCircleThree_X,
				pointCircleThree_Y, circleSize, circleSize);
		nodeCircleFour = new Ellipse2D.Double(pointCircleFour_X,
				pointCircleFour_Y, circleSize, circleSize);
		nodeCircleFive = new Ellipse2D.Double(pointCircleFive_X,
				pointCircleFive_Y, circleSize, circleSize);
		nodeCircleSix = new Ellipse2D.Double(pointCircleSix_X,
				pointCircleSix_Y, circleSize, circleSize);

		nodeCircles[0] = nodeCircleOne;
		nodeCircles[1] = nodeCircleTwo;
		nodeCircles[2] = nodeCircleThree;
		nodeCircles[3] = nodeCircleFour;
		nodeCircles[4] = nodeCircleFive;
		nodeCircles[5] = nodeCircleSix;
	}

	
	public void initActionAreaRoads() {
		roadRects = new Polygon[6];

		double pointX_Touching_One = pointOne_X + cosin30 * circleRadius;
		double pointY_Touching_One = pointOne_Y + sin30 * circleRadius;

		double pointX_PolyOne_One = pointX_Touching_One + sin30 * circleRadius;
		double pointY_PolyOne_One = pointY_Touching_One - cosin30
				* circleRadius;

		double pointX_PolyOne_Two = pointX_PolyOne_One + cosin30
				* (radius - 2 * circleRadius);
		double pointY_PolyOne_Two = pointY_PolyOne_One + sin30
				* (radius - 2 * circleRadius);

		double pointX_PolyOne_Three = pointX_PolyOne_Two - sin30 * 2.0
				* circleRadius;
		double pointY_PolyOne_Three = pointY_PolyOne_Two + cosin30 * 2.0
				* circleRadius;

		double pointX_PolyOne_Four = pointX_PolyOne_One - sin30 * 2.0
				* circleRadius;
		double pointY_PolyOne_Four = pointY_PolyOne_One + cosin30 * 2.0
				* circleRadius;

		rectOne = new Polygon(new int[] { (int) (pointX_PolyOne_One),
				(int) (pointX_PolyOne_Two), (int) (pointX_PolyOne_Three),
				(int) (pointX_PolyOne_Four) }, new int[] {
				(int) (pointY_PolyOne_One), (int) (pointY_PolyOne_Two),
				(int) (pointY_PolyOne_Three), (int) (pointY_PolyOne_Four) }, 4);

		rectTwo = new Polygon(new int[] { (int) (pointTwo_X + circleRadius),
				(int) (pointTwo_X - circleRadius),
				(int) (pointThree_X - circleRadius),
				(int) (pointThree_X + circleRadius) }, new int[] {
				(int) (pointTwo_Y + circleRadius),
				(int) (pointTwo_Y + circleRadius),
				(int) (pointThree_Y - circleRadius),
				(int) (pointThree_Y - circleRadius) }, 4);

		double pointX_Touching_Three = pointThree_X - cosin30 * circleRadius;
		double pointY_Touching_Three = pointThree_Y + sin30 * circleRadius;

		double pointX_PolyThree_One = pointX_Touching_Three - sin30
				* circleRadius;
		double pointY_PolyThree_One = pointY_Touching_Three - cosin30
				* circleRadius;

		double pointX_PolyThree_Two = pointX_PolyThree_One - cosin30
				* (radius - 2 * circleRadius);
		double pointY_PolyThree_Two = pointY_PolyThree_One + sin30
				* (radius - 2 * circleRadius);

		double pointX_PolyThree_Three = pointX_PolyThree_Two + sin30 * 2.0
				* circleRadius;
		double pointY_PolyThree_Three = pointY_PolyThree_Two + cosin30 * 2.0
				* circleRadius;

		double pointX_PolyThree_Four = pointX_PolyThree_One + sin30 * 2.0
				* circleRadius;
		double pointY_PolyThree_Four = pointY_PolyThree_One + cosin30 * 2.0
				* circleRadius;

		rectThree = new Polygon(new int[] { (int) (pointX_PolyThree_One),
				(int) (pointX_PolyThree_Two), (int) (pointX_PolyThree_Three),
				(int) (pointX_PolyThree_Four) },
				new int[] { (int) (pointY_PolyThree_One),
						(int) (pointY_PolyThree_Two),
						(int) (pointY_PolyThree_Three),
						(int) (pointY_PolyThree_Four) }, 4);

		double pointX_Touching_Four = pointFive_X + cosin30 * circleRadius;
		double pointY_Touching_Four = pointFive_Y + sin30 * circleRadius;

		double pointX_PolyFour_One = pointX_Touching_Four + sin30
				* circleRadius;
		double pointY_PolyFour_One = pointY_Touching_Four - cosin30
				* circleRadius;

		double pointX_PolyFour_Two = pointX_PolyFour_One + cosin30
				* (radius - 2 * circleRadius);
		double pointY_PolyFour_Two = pointY_PolyFour_One + sin30
				* (radius - 2 * circleRadius);

		double pointX_PolyFour_Three = pointX_PolyFour_Two - sin30 * 2.0
				* circleRadius;
		double pointY_PolyFour_Three = pointY_PolyFour_Two + cosin30 * 2.0
				* circleRadius;

		double pointX_PolyFour_Four = pointX_PolyFour_One - sin30 * 2.0
				* circleRadius;
		double pointY_PolyFour_Four = pointY_PolyFour_One + cosin30 * 2.0
				* circleRadius;

		rectFour = new Polygon(new int[] { (int) (pointX_PolyFour_One),
				(int) (pointX_PolyFour_Two), (int) (pointX_PolyFour_Three),
				(int) (pointX_PolyFour_Four) }, new int[] {
				(int) (pointY_PolyFour_One), (int) (pointY_PolyFour_Two),
				(int) (pointY_PolyFour_Three), (int) (pointY_PolyFour_Four) },
				4);

		rectFive = new Polygon(new int[] { (int) (pointFive_X + circleRadius),
				(int) (pointFive_X - circleRadius),
				(int) (pointSix_X - circleRadius),
				(int) (pointSix_X + circleRadius) }, new int[] {
				(int) (pointFive_Y - circleRadius),
				(int) (pointFive_Y - circleRadius),
				(int) (pointSix_Y + circleRadius),
				(int) (pointSix_Y + circleRadius) }, 4);

		double pointX_Touching_Six = pointOne_X - cosin30 * circleRadius;
		double pointY_Touching_Six = pointOne_Y + sin30 * circleRadius;

		double pointX_PolySix_One = pointX_Touching_Six - sin30 * circleRadius;
		double pointY_PolySix_One = pointY_Touching_Six - cosin30
				* circleRadius;

		double pointX_PolySix_Two = pointX_PolySix_One - cosin30
				* (radius - 2 * circleRadius);
		double pointY_PolySix_Two = pointY_PolySix_One + sin30
				* (radius - 2 * circleRadius);

		double pointX_PolySix_Three = pointX_PolySix_Two + sin30 * 2.0
				* circleRadius;
		double pointY_PolySix_Three = pointY_PolySix_Two + cosin30 * 2.0
				* circleRadius;

		double pointX_PolySix_Four = pointX_PolySix_One + sin30 * 2.0
				* circleRadius;
		double pointY_PolySix_Four = pointY_PolySix_One + cosin30 * 2.0
				* circleRadius;

		rectSix = new Polygon(new int[] { (int) (pointX_PolySix_One),
				(int) (pointX_PolySix_Two), (int) (pointX_PolySix_Three),
				(int) (pointX_PolySix_Four) }, new int[] {
				(int) (pointY_PolySix_One), (int) (pointY_PolySix_Two),
				(int) (pointY_PolySix_Three), (int) (pointY_PolySix_Four) }, 4);

		roadRects[0] = rectOne;
		roadRects[1] = rectTwo;
		roadRects[2] = rectThree;
		roadRects[3] = rectFour;
		roadRects[4] = rectFive;
		roadRects[5] = rectSix;

		imageRoadWidth_Vert = rectTwo.getBounds().getWidth();
		imageRoadHeight_Vert = rectTwo.getBounds().getHeight();
	}

	public Ellipse2D[] getNodeCircles() {
		return nodeCircles;
	}

	public Ellipse2D getNodeCircleOfIndex(int index) {
		return nodeCircles[index];
	}

	public Polygon[] getRoadRects() {
		return roadRects;
	}

	public Polygon getRoadRectOfIndex(int index) {
		return roadRects[index];
	}

	public int getRadius() {
		return (int) radius;
	}

	public Rectangle getTileActionArea() {
		return tileActionArea;
	}

	public double getImageRoadWidth_NotVert() {
		return imageRoadWidth_NotVert;
	}

	public double getImageRoadHeight_NotVert() {
		return imageRoadHeight_NotVert;
	}

	public double getImageRoadHeight_Vert() {
		return imageRoadHeight_Vert;
	}

	public double getImageRoadWidth_Vert() {
		return imageRoadWidth_Vert;
	}

}
