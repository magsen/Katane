/*********************************************************************************
*     File Name           :     Ressource.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2018-12-26 17:10]
*     Description         :     Abstract class of the types of ressources
**********************************************************************************/

package Katane;

/* Abstract class of the types of ressources */
public abstract class Ressource {

	protected int quantity;

	/* Get the quantity of a Ressource */
	public int getQuantity() {
		return quantity;
	}

	/* Set the quantity of a Ressource */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/* Constructor */
	public Ressource() {
		this.quantity = 0;
	}
}
