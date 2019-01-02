/*********************************************************************************
*     File Name           :     Dolorean.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2019-01-01 19:03]
*     Description         :     The Dolorean is a Town and Town itself is a Building.
*     					Dolorean is the first step to create a TimeTown and is worth 1 victory point for the owner.
**********************************************************************************/

package Katane;


/* the Dolorean class */
public class Dolorean extends Town{

	/* Constructor - owner is a Player and have coordinates */
	public Dolorean(Player player, Coordinates coor) {
		super(player, coor);
	}
}
