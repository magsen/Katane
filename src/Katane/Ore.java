/*********************************************************************************
*     File Name           :     Ore.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-05 02:32]
*     Description         :     Ore is a type of Ressource
**********************************************************************************/

package Katane;

/* the Ore class is a type of Ressource */
public class Ore extends Ressource {

	/* Constructor */
	public Ore() {
		super();
	}

	public Ore(int quantity) {
		super(quantity);
	}

	// TEST
	public void print() {
		System.out.println("I'm some Ore");
	}
}
