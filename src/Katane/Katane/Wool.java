/*********************************************************************************
*     File Name           :     Wool.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-05 02:32]
*     Description         :     Wool is a type of Ressource
**********************************************************************************/

package Katane;

/* Class Wool is a type of Ressource */
public class Wool extends Ressource {

	/* Constructor */
	public Wool() {
		super();
	}

	public Wool(int quantity) {
		super(quantity);
	}

	// TEST
	public void print() {
		System.out.println("I'm some Wool");
	}
}
