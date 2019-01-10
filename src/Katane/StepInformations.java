/*********************************************************************************
*     File Name           :     StepInformations.java
*     Created By          :     Jacques PEREIRA | jacques.pereira@utbm.fr
*     Creation Date       :     [2019-01-07 19:16]
*     Last Modified       :     [2019-01-08 01:17]
*     Description         :     Temporary
**********************************************************************************/

package Katane;


public class StepInformations {
	public Coordinates coordinate;
	public int length;
	public boolean everythingExplored;
	public boolean wasThere;

	public StepInformations (Coordinates c, int len) {
		coordinate = new Coordinates(c);
		length = len;
		everythingExplored = false;
		wasThere = false;
	}

	public void print () {
		System.out.println(toString());
	}

	public String toString () {
		return coordinate.toString() + ", " + length + ", " +  ", " + everythingExplored;
	}
}
