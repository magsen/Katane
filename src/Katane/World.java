package Katane;

import java.util.HashMap;

public class World {
	private HashMap<Object, Object> tile;
	private HashMap<Object, Object> road;
	private HashMap<Object, Object> town;
	private int LongestRoadValue;

	public World() {
		System.out.println("-- World --");
		this.tile = new HashMap<>();
		this.road = new HashMap<>();
		this.town = new HashMap<>();
		this.LongestRoadValue = 0;
	}

	public int getLongestRoadValue() {
		return LongestRoadValue;
	}

	public void setLongestRoadValue(int longestRoadValue) {
		if(longestRoadValue<0) {
			LongestRoadValue = 0;
		}
		else
		{
			LongestRoadValue = longestRoadValue;
		}
	}

}
