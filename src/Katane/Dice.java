/*********************************************************************************
*     File Name           :     Dice.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2018-12-26 15:32]
*     Description         :     The dice class to provides rolls
**********************************************************************************/

package Katane;

/* Class Dice with a custom number of sides - It provides random */
public class Dice {

	private int SIDES;
	private int result;

	/* Constructor with its sides */
	public Dice(int sides) {
		System.out.println("-- Dice --");
		SIDES = sides;
		result = (int) (Math.random() * SIDES) + 1;
	}

	/* Roll the dice to refresh the random number */
	public int roll() {
		result = (int) (Math.random() * SIDES) + 1;
		return result;
	}

	/* Retrieves the dice randomed number */
	public int getResult() {
		return result;
	}
}
