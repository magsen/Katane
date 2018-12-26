/*********************************************************************************
*     File Name           :     Dolorean.java
*     Created By          :     The LO43 Katane team
*     Creation Date       :     [2018-09-14 13:32]
*     Last Modified       :     [2018-12-26 18:02]
*     Description         :     The Dolorean is a Town and Town itself is a Building.
*     					Dolorean is the first step to create a TimeTown and is worth 1 victory point for the owner.
**********************************************************************************/

package Katane;


/* the Dolorean class */
public class Dolorean extends Town{

	/* Constructor - owner is a Player and have coordinates */
	public Dolorean(Player player, Tile tile) {
		super(player, tile);
	}
}
