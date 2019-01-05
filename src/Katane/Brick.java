/*********************************************************************************
*     File Name           :     Brick.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-05 02:31]
*     Description         :     Ressource Brick
**********************************************************************************/

package Katane;

/* This is a Ressource (Brick) */
public class Brick extends Ressource {

	/* Constructor */
	public Brick() {
		super();
	}

	public Brick(int quantity) {
		super(quantity);
	}

	// TEST
	public void print() {
		System.out.println("I'm some Brick");
	}
}
