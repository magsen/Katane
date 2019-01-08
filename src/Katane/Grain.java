/*********************************************************************************
*     File Name           :     Grain.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-05 02:34]
*     Description         :     Grain is a type of Ressource.
**********************************************************************************/

package Katane;

/* Grain is a type of Ressource */
public class Grain extends Ressource {

	/* Constructor */
	public Grain() {
		super();
	}

	public Grain(int quantity) {
		super(quantity);
	}

	// TEST
	public void print() {
		System.out.println("I'm some Grain");
	}
}
