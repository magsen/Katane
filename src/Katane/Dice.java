package Katane;

public class Dice {
	private int SIDES;
	private int result;
	public Dice(int sides) {
		System.out.println("-- Dice --");
		SIDES = sides;
		result = (int) (Math.random() * SIDES) + 1;
	}
	public int roll() {
		result = (int) (Math.random() * SIDES) + 1;
		return result;
	}
	public int getResult() {
		return result;
	}
}
